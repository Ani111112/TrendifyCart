package com.example.springmart.service;

import com.example.springmart.dto.requestDto.ItemRequestDto;
import com.example.springmart.model.Item;

public interface ItemService {

    Item createItem(ItemRequestDto itemRequestDto);

}
