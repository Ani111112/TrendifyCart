package com.example.springmart.dto.requestDto;

import com.example.springmart.Enum.CardType;
import com.example.springmart.model.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardRequestDto {

    String customerMobNo;

    String cardNo;

    int cvv;

    CardType cardType;

    Date validTill;


}
