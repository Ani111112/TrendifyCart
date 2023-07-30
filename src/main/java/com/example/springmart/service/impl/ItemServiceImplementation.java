package com.example.springmart.service.impl;

import com.example.springmart.dto.requestDto.ItemRequestDto;
import com.example.springmart.exception.CustomerNotFoundException;
import com.example.springmart.exception.ItemQuantityIsInsufficientException;
import com.example.springmart.exception.ProductNotFoundException;
import com.example.springmart.model.Customer;
import com.example.springmart.model.Item;
import com.example.springmart.model.Product;
import com.example.springmart.repository.CustomerRepository;
import com.example.springmart.repository.ProductRepository;
import com.example.springmart.service.ItemService;
import com.example.springmart.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImplementation  implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public Item createItem(ItemRequestDto itemRequestDto) {

        // check customer exists or not
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());

        if(customer == null){
            throw  new CustomerNotFoundException("Customer not Found !!");
        }

        // now if the customer exits then check product id exists or not
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not exists !!");
        }


        // if product exists then get the product
        Product product = productOptional.get();

        // now check that the quantity which we required for that item , is available or not
        if(product.getAvailableQuantity() < itemRequestDto.getRequiredQuantity()){
            throw new ItemQuantityIsInsufficientException("Item quantity insufficient");
        }

        // if required quantity is sufficient then create the item
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        item.setProduct(product);

        return item;
    }
}
