package com.serg1o.salestaxes.sales.receipts.ports;

import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceipt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiptApplicationServiceResponse {

  private ResponseReceipt responseReceipt;
}
