package com.serg1o.salestaxes.sales.receiptsprinter.ports.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestReceiptsPrinterReceiptGood {

  private String description;
  private int quantity;
  private BigDecimal price;
  private BigDecimal netPrice;

}
