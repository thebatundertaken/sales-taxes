package com.serg1o.salestaxes.sales.receipts.ports;

import com.serg1o.salestaxes.sales.receipts.domain.Receipt;
import com.serg1o.salestaxes.sales.receipts.domain.ReceiptGoodRepository;
import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceipt;
import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceiptGood;
import java.util.stream.Collectors;

public class ReceiptApplicationService {

  private ReceiptGoodRepository receiptGoodRepository;

  public ReceiptApplicationService(ReceiptGoodRepository receiptGoodRepository) {
    this.receiptGoodRepository = receiptGoodRepository;
  }

  public ReceiptApplicationServiceResponse generateReceipt(
      ReceiptApplicationServiceRequest request
  ) {
    if (request == null || request.getBasket() == null) {
      throw new IllegalArgumentException("Invalid input");
    }

    var receipt = new Receipt();
    receiptGoodRepository.findByIds(request.getBasket()
        .getBasketContents())
        .forEach(receipt::addEntry);

    var responseReceiptGoods = receipt.getEntries()
        .stream()
        .map(ResponseReceiptGood::of)
        .collect(Collectors.toList());

    return new ReceiptApplicationServiceResponse(
        new ResponseReceipt(
            responseReceiptGoods,
            receipt.getTotal(),
            receipt.getTaxesTotal()
        )
    );
  }
}
