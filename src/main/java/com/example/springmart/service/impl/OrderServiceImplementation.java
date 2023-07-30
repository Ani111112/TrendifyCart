package com.example.springmart.service.impl;

import com.example.springmart.Enum.ProductStatus;
import com.example.springmart.dto.requestDto.CheckOutCartRequestDto;
import com.example.springmart.dto.requestDto.OrderRequestDto;
import com.example.springmart.dto.responseDto.OrderResponseDto;
import com.example.springmart.exception.*;
import com.example.springmart.model.*;
import com.example.springmart.repository.CardRepository;
import com.example.springmart.repository.CustomerRepository;
import com.example.springmart.repository.OrderRepository;
import com.example.springmart.repository.ProductRepository;
import com.example.springmart.service.CardService;
import com.example.springmart.service.OrderService;
import com.example.springmart.transformer.ItemTransformer;
import com.example.springmart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Autowired
    JavaMailSender javaMailSender ;


    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        // first check that customer exists or not
        Customer customer = customerRepository.findByMobNo(orderRequestDto.getCustomerMobileNo());

        // check customer kahi null toh nahi hai
        if((customer == null) ){
            throw new CustomerNotFoundException("customer does not exist !!");
        }


        // if the customer exists, now check for the product, that the product which you require exists or not
        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());

        // check kahi product exists karta hai ya nahi
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("product does not exists !!");
        }

        // check that if card not exists ya cvv is wrong ya card is invalid then throw the exception
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date todayDate = new Date();
        if((card == null) || (card.getCvv() != orderRequestDto.getCvv()) || (todayDate.after(card.getValidTill()))){
            throw new InvalidCardException("Invalid card");
        }


        // if product exists, then get the product
        Product product = productOptional.get();
        // make sure that the product have the quantity which you require
        if(product.getAvailableQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new ItemQuantityIsInsufficientException("Item quantity insufficient");
        }

        //as we placing the order so it will decreasing the product available quantity in database
        int newQuantity = product.getAvailableQuantity() - orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity == 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        // Now make the order from the dto
        OrderEntity order = new OrderEntity();

        String orderId = String.valueOf(UUID.randomUUID());
        order.setOrderNo(orderId);
        order.setCardUsed(cardService.generateMaskedCard(orderRequestDto.getCardUsed()));
        order.setTotalValue(orderRequestDto.getRequiredQuantity() * product.getPrice());
        // we have to set item in order, so make item
        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setOrderEntity(order);
        item.setProduct(product);

        order.getItems().add(item);
        order.setCustomer(customer);

        // saving entities in database
        OrderEntity savedOrder = orderRepository.save(order); // will save both order and items

        // now check which entities are involved and this , and does any attributes in that changing
        product.getItems().add(savedOrder.getItems().get(0));
        customer.getOrders().add(savedOrder);

        // send Email
        sendEmail(savedOrder);

        // prepare the response dto from order
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);

    }


    @Override
    public void sendEmail(OrderEntity order) {


        String text = "Congrats! your order has been placed. Following are the details: \n" +
                "Order id = " + order.getOrderNo() + "\n" +
                "Order Total = " + order.getTotalValue() + "\n" +
                "Order Date = " + order.getOrderDate();


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(order.getCustomer().getEmailId());
        mail.setFrom("bluesolutions7@gmail.com");
        mail.setSubject("Order Placed");
        mail.setText(text);

        javaMailSender.send(mail);
    }


    @Override
    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity order = new OrderEntity();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.generateMaskedCard(card.getCardNo()));

        int orderTotal = 0;

        for (Item item :cart.getItems()){
            Product product = item.getProduct();

            // check that item has sufficient qty or not
            if(product.getAvailableQuantity() < item.getRequiredQuantity()){
                throw new ItemQuantityIsInsufficientException("Insufficient item quantity :-" + item.getProduct().getName());
            }

            // if item qty is sufficient then
            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            // if the new quantity becomes 0 then set the product status to out of stock
            if(newQuantity ==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            // set the order total
            orderTotal += product.getPrice() * item.getRequiredQuantity();

            item.setOrderEntity(order);
        }

        order.setTotalValue(orderTotal);
        order.setItems(cart.getItems());
        order.setCustomer(cart.getCustomer());

        return order;
    }


}
