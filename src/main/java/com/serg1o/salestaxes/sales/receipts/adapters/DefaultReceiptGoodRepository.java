package com.serg1o.salestaxes.sales.receipts.adapters;

import com.serg1o.salestaxes.sales.receipts.domain.ReceiptGood;
import com.serg1o.salestaxes.sales.receipts.domain.ReceiptGoodRepository;
import com.serg1o.salestaxes.taxes.engine.ports.TaxEngineApplicationService;
import com.serg1o.salestaxes.taxes.engine.ports.TaxEngineApplicationServiceRequest;
import com.serg1o.salestaxes.taxes.engine.ports.domain.TaxGoodType;
import com.serg1o.salestaxes.warehouse.goods.ports.GoodsApplicationService;
import com.serg1o.salestaxes.warehouse.goods.ports.GoodsApplicationServiceRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultReceiptGoodRepository implements ReceiptGoodRepository {

  private GoodsApplicationService goodsApplicationService;
  private TaxEngineApplicationService taxEngineApplicationService;

  public DefaultReceiptGoodRepository(
      GoodsApplicationService goodsApplicationService,
      TaxEngineApplicationService taxEngineApplicationService
  ) {
    this.goodsApplicationService = goodsApplicationService;
    this.taxEngineApplicationService = taxEngineApplicationService;
  }

  @Override
  public List<ReceiptGood> findByIds(List<UUID> ids) {

    var response = goodsApplicationService.retrieveGoods(
        new GoodsApplicationServiceRequest(ids)
    );

    return response.getGoods()
        .stream()
        .map(responseGood -> {
          //We can easily improve performance by bulk call TaxEngine and cache results in memory
          BigDecimal netPrice = taxEngineApplicationService.applyTaxes(
              new TaxEngineApplicationServiceRequest(
                  TaxGoodType.valueOf(responseGood.getGoodType().toString()),
                  responseGood.isImported(),
                  responseGood.getPrice()
              )
          );

          return ReceiptGood.builder()
              .description(responseGood.getDescription())
              .quantity(1)
              .price(
                  new BigDecimal(responseGood.getPrice()).setScale(2, RoundingMode.HALF_UP)
              )
              .netPrice(netPrice)
              .build();
        })
        .collect(Collectors.toList());
  }
}
