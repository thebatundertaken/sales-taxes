package com.serg1o.salestaxes.sales.receiptsprinter.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceiptsPrinterReceiptGood {

  private String description;
  private int quantity;
  private BigDecimal price;
  private BigDecimal netPrice;

}
