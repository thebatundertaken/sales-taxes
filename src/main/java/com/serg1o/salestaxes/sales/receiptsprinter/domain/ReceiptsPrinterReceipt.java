package com.serg1o.salestaxes.sales.receiptsprinter.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceiptsPrinterReceipt {

  private List<ReceiptsPrinterReceiptGood> entries;
  private BigDecimal total;
  private BigDecimal taxesTotal;
}
