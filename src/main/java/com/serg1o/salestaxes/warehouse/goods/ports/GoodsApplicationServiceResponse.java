package com.serg1o.salestaxes.warehouse.goods.ports;

import com.serg1o.salestaxes.warehouse.goods.ports.domain.ResponseGood;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoodsApplicationServiceResponse {

  private List<ResponseGood> goods;
}
