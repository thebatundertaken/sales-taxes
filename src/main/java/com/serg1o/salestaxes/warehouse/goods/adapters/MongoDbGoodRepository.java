package com.serg1o.salestaxes.warehouse.goods.adapters;

import com.serg1o.salestaxes.warehouse.goods.domain.Good;
import com.serg1o.salestaxes.warehouse.goods.domain.GoodsRepository;
import com.serg1o.salestaxes.warehouse.goods.domain.exception.GoodNotFoundException;
import com.serg1o.salestaxes.warehouse.goods.domain.exception.RepositoryException;
import java.util.UUID;

public class MongoDbGoodRepository implements GoodsRepository {

  @Override
  public Good findById(UUID id) throws GoodNotFoundException, RepositoryException {
    throw new UnsupportedOperationException("Out of scope of this solution. Not implemented");
  }

  @Override
  public UUID insert(Good good) throws RepositoryException {
    throw new UnsupportedOperationException("Out of scope of this solution. Not implemented");
  }
}
