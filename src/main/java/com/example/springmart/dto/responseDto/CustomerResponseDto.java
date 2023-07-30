package com.example.springmart.dto.responseDto;

import com.example.springmart.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerResponseDto {
    String name;

    String emailId;

    String mobNo;

    Gender gender;
}
