package com.example.springmart.dto.requestDto;

import com.example.springmart.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequestDto {

    String name;

    String emailId;

    String mobNo;

    Gender gender;

}
