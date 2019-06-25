package com.serg1o.salestaxes.sales.receipts.ports.domain;

import com.serg1o.salestaxes.sales.receipts.domain.ReceiptGood;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class ResponseReceiptGood {

  private String description;
  private int quantity;
  private BigDecimal price;
  private BigDecimal netPrice;

  public static ResponseReceiptGood of(ReceiptGood receiptGood) {
    return ResponseReceiptGood.builder()
        .description(receiptGood.getDescription())
        .quantity(receiptGood.getQuantity())
        .price(receiptGood.getPrice())
        .netPrice(receiptGood.getNetPrice())
        .build();
  }
}
