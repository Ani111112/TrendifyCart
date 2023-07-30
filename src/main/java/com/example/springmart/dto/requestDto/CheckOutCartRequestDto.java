package com.example.springmart.dto.requestDto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckOutCartRequestDto {

    String emailId; //customer

    String cardNo;

    int cvv;
}
