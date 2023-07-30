package com.example.springmart.dto.responseDto;

import com.example.springmart.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CardResponseDto {

    String customerName;

    String maskedCardNo;

    CardType cardType;

    Date validTill;
}
