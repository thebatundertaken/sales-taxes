package com.serg1o.salestaxes.warehouse.goods.ports.domain;

import com.serg1o.salestaxes.warehouse.goods.domain.Good;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseGood {

  private UUID id;
  private ResponseGoodType goodType;
  private String description;
  private double price;
  private boolean imported;

  public static ResponseGood of(Good good) {
    return ResponseGood.builder()
        .id(good.getId())
        .goodType(ResponseGoodType.valueOf(good.getGoodType().toString()))
        .description(good.getDescription())
        .price(good.getPrice())
        .imported(good.isImported())
        .build();
  }
}
