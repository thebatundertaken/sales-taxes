package com.serg1o.salestaxes.sales.receiptsprinter.adapters;

import com.serg1o.salestaxes.sales.receiptsprinter.domain.ReceiptFormatter;
import com.serg1o.salestaxes.sales.receiptsprinter.domain.ReceiptsPrinterReceipt;

public class PlaintTextReceiptFormatter implements ReceiptFormatter {

  @Override
  public String format(ReceiptsPrinterReceipt receipt) {
    var builder = new StringBuilder();

    receipt.getEntries()
        .forEach(entry -> {
          builder.append(entry.getQuantity());
          builder.append(" ");
          builder.append(entry.getDescription());
          builder.append(": ");
          builder.append(entry.getNetPrice());
          builder.append(System.lineSeparator());
        });

    builder.append("Sales Taxes: ");
    builder.append(receipt.getTaxesTotal().toString());
    builder.append(System.lineSeparator());

    builder.append("Total: ");
    builder.append(receipt.getTotal().toString());
    builder.append(System.lineSeparator());

    return builder.toString();
  }
}
