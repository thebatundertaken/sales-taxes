package com.serg1o.salestaxes.sales.receiptsprinter.ports;

import com.serg1o.salestaxes.sales.receiptsprinter.ports.domain.RequestReceiptsPrinterReceipt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiptsPrinterApplicationServiceRequest {

  private RequestReceiptsPrinterReceipt receipt;
}
