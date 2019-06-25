package com.serg1o.salestaxes.warehouse.goods.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Good {

  private UUID id;
  private String description;
  private GoodType goodType;
  private boolean imported;
  private double price;
}
