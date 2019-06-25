package com.serg1o.salestaxes.warehouse.goods.domain;

import com.serg1o.salestaxes.warehouse.goods.domain.exception.GoodNotFoundException;
import com.serg1o.salestaxes.warehouse.goods.domain.exception.RepositoryException;
import java.util.UUID;

public interface GoodsRepository {

  /**
   * Retrieves a good entity by its id.
   *
   * @param id entity id.
   * @return good
   * @throws GoodNotFoundException if no matches
   * @throws RepositoryException due to I/O
   */
  public Good findById(UUID id) throws GoodNotFoundException, RepositoryException;

  /**
   * Inserts a new entity into persistance.
   *
   * @param good entity to insert.
   * @return entity generated id.
   * @throws RepositoryException due to I/O
   */
  public UUID insert(Good good) throws RepositoryException;
}
