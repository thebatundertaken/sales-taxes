package com.serg1o.salestaxes.sales.receipts.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceiptGood {

  private String description;
  private int quantity;
  private BigDecimal price;
  private BigDecimal netPrice;
}
