package com.example.springmart.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerRequestDto {

    String name;

    String emailId;

    String mobNo;

    String panNo;
}
