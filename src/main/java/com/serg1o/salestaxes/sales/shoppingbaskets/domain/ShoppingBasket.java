package com.serg1o.salestaxes.sales.shoppingbaskets.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ShoppingBasket {

  private UUID id;
  private List<UUID> goods;

  public ShoppingBasket(UUID id) {
    this.id = id;
    this.goods = new LinkedList<>();
  }

  public void addToBasket(UUID goodId) {
    goods.add(goodId);
  }

  public void removeFromBasket(UUID goodId) {
    goods.remove(goodId);
  }

  /**
   * Returns current basket contents.
   *
   * @return immutable copy of content.
   */
  public List<UUID> getBasketContents() {
    return Collections.unmodifiableList(goods);
  }

  public UUID getId() {
    return id;
  }
}
