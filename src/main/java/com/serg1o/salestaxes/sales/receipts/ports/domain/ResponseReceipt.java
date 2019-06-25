package com.serg1o.salestaxes.sales.receipts.ports.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseReceipt {

  private List<ResponseReceiptGood> entries;
  private BigDecimal total;
  private BigDecimal taxesTotal;

}
