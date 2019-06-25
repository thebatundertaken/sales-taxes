package com.serg1o.salestaxes.sales.shoppingbaskets.domain;

import com.serg1o.salestaxes.sales.shoppingbaskets.domain.exception.RepositoryException;

public interface ShoppingBasketRepository {

  public void upsert(ShoppingBasket basket) throws RepositoryException;
}
