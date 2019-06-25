package com.serg1o.salestaxes.sales.receipts.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Receipt {

  private List<ReceiptGood> entries;
  private BigDecimal total;
  private BigDecimal taxesTotal;

  public Receipt() {
    entries = new LinkedList<>();
    total = new BigDecimal(0);
    taxesTotal = new BigDecimal(0);
  }

  public void addEntry(ReceiptGood receiptGood) {
    entries.add(receiptGood);
    total = total.add(receiptGood.getNetPrice());
    taxesTotal = taxesTotal.add(receiptGood.getNetPrice().subtract(receiptGood.getPrice()));
  }

  public List<ReceiptGood> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  public BigDecimal getTotal() {
    return total;
  }

  public BigDecimal getTaxesTotal() {
    return taxesTotal;
  }
}
