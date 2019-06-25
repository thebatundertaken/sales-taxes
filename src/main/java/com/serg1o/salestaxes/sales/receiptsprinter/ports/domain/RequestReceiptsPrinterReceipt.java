package com.serg1o.salestaxes.sales.receiptsprinter.ports.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestReceiptsPrinterReceipt {

  private List<RequestReceiptsPrinterReceiptGood> entries;
  private BigDecimal total;
  private BigDecimal taxesTotal;
}
