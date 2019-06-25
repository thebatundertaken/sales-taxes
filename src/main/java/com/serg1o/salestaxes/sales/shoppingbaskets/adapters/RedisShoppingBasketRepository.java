package com.serg1o.salestaxes.sales.shoppingbaskets.adapters;

import com.serg1o.salestaxes.sales.shoppingbaskets.domain.ShoppingBasket;
import com.serg1o.salestaxes.sales.shoppingbaskets.domain.ShoppingBasketRepository;
import com.serg1o.salestaxes.sales.shoppingbaskets.domain.exception.RepositoryException;

public class RedisShoppingBasketRepository implements ShoppingBasketRepository {

  @Override
  public void upsert(ShoppingBasket basket) throws RepositoryException {
    throw new UnsupportedOperationException("Out of scope of this solution. Not implemented");
  }
}
