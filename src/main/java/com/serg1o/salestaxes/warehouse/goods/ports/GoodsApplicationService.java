package com.serg1o.salestaxes.warehouse.goods.ports;

import com.serg1o.salestaxes.warehouse.goods.domain.GoodsRepository;
import com.serg1o.salestaxes.warehouse.goods.ports.domain.ResponseGood;
import java.util.stream.Collectors;

public class GoodsApplicationService {

  private GoodsRepository repository;

  public GoodsApplicationService(GoodsRepository repository) {
    this.repository = repository;
  }

  public GoodsApplicationServiceResponse retrieveGoods(GoodsApplicationServiceRequest request) {
    if (request == null || request.getGoodIds() == null) {
      throw new IllegalArgumentException("Invalid input");
    }

    var goods = request.getGoodIds().stream()
        .map(id -> ResponseGood.of(repository.findById(id)))
        .collect(Collectors.toList());

    return new GoodsApplicationServiceResponse(goods);
  }
}
