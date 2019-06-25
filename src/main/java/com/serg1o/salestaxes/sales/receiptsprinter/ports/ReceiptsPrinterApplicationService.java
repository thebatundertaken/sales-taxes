package com.serg1o.salestaxes.sales.receiptsprinter.ports;

import com.serg1o.salestaxes.sales.receiptsprinter.domain.ReceiptFormatter;
import com.serg1o.salestaxes.sales.receiptsprinter.domain.ReceiptsPrinterReceipt;
import com.serg1o.salestaxes.sales.receiptsprinter.domain.ReceiptsPrinterReceiptGood;
import java.util.stream.Collectors;

public class ReceiptsPrinterApplicationService {

  private ReceiptFormatter formatter;

  public ReceiptsPrinterApplicationService(ReceiptFormatter formatter) {
    this.formatter = formatter;
  }

  public String printReceipt(ReceiptsPrinterApplicationServiceRequest request) {
    var receipt = ReceiptsPrinterReceipt.builder()
        .entries(
            request.getReceipt().getEntries()
                .stream()
                .map(
                    requestReceiptsPrinterReceiptGood -> ReceiptsPrinterReceiptGood.builder()
                        .description(requestReceiptsPrinterReceiptGood.getDescription())
                        .quantity(requestReceiptsPrinterReceiptGood.getQuantity())
                        .price(requestReceiptsPrinterReceiptGood.getPrice())
                        .netPrice(requestReceiptsPrinterReceiptGood.getNetPrice())
                        .build()
                )
                .collect(Collectors.toList())
        )
        .taxesTotal(request.getReceipt().getTaxesTotal())
        .total(request.getReceipt().getTotal())
        .build();

    return formatter.format(receipt);
  }
}
