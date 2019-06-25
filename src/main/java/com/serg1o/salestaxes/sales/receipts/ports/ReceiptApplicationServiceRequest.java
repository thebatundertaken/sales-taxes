package com.serg1o.salestaxes.sales.receipts.ports;

import com.serg1o.salestaxes.sales.shoppingbaskets.domain.ShoppingBasket;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiptApplicationServiceRequest {

  private ShoppingBasket basket;
}
