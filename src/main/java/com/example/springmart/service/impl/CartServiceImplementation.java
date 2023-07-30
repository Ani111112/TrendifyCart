package com.example.springmart.service.impl;

import com.example.springmart.dto.requestDto.CheckOutCartRequestDto;
import com.example.springmart.dto.requestDto.ItemRequestDto;
import com.example.springmart.dto.responseDto.CartResponseDto;
import com.example.springmart.dto.responseDto.OrderResponseDto;
import com.example.springmart.exception.CustomerNotFoundException;
import com.example.springmart.exception.EmptyCardException;
import com.example.springmart.exception.InvalidCardException;
import com.example.springmart.model.*;
import com.example.springmart.repository.*;
import com.example.springmart.service.CardService;
import com.example.springmart.service.CartService;
import com.example.springmart.service.OrderService;
import com.example.springmart.transformer.CartTransformer;
import com.example.springmart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @Override
    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item) {

        // get the customer,and his cart in which we have to add the item
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Cart cart = customer.getCart();

        // get the product
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        // set the cart totals
        int numberOfItems = itemRequestDto.getRequiredQuantity();
        int priceOfOneItem = item.getProduct().getPrice();
        int newCost = numberOfItems * priceOfOneItem;
        cart.setCartTotal(cart.getCartTotal() + newCost);   // prev cart total in terms of cost + new cost

        // no need to set the customer as we have got this cart from the customer itself, but if you want you can set it
        item.setCart(cart);
        item.setProduct(product);
        Item savedItem = itemRepository.save(item);

        // add the item to the list of items in the cart
        cart.getItems().add(savedItem);

        // before save the cart make sure any entity which is used in this needs change or not
        product.getItems().add(savedItem);

        //now we have to save the item, cart, product, and item is the child of both cart and product
        // so we needed to save the item first, other wise if we save any of the other parent first
        // then duplicate records will get saved
        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        // prepare cart response
        return CartTransformer.CartToCartResponseDto(savedCart);
    }


    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) {
        // check customer is valid or not
        Customer customer = customerRepository.findByEmailId(checkOutCartRequestDto.getEmailId());

        if(customer == null){
            throw new CustomerNotFoundException("customer not found");
        }

        // now check that the card is valid or not
        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date todayDate = new Date();
        if(card == null || card.getCvv() != checkOutCartRequestDto.getCvv() || todayDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid card");
        }

        // check kahi cart empty to nahi hai
        Cart cart = customer.getCart();
        if(cart.getItems().isEmpty()){
            throw new EmptyCardException("Cart is empty");
        }

        // Now Place the order
        OrderEntity order = orderService.placeOrder(cart,card);
        resetCart(cart);

        OrderEntity savedOrder = orderRepository.save(order);

        // prepare the response dto
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);

    }

    public void  resetCart(Cart cart){

        cart.setCartTotal(0);

        // once we place the order then card it will become null , becoz now we have ordered all the items,
        for (Item item : cart.getItems()){
            item.setCart(null);
        }

        cart.setItems(new ArrayList<>());
    }
}
