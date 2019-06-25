package com.serg1o.salestaxes.warehouse.goods.ports;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoodsApplicationServiceRequest {

  private List<UUID> goodIds;
}
