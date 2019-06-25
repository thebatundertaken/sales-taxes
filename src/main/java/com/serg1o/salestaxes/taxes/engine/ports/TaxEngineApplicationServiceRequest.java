package com.serg1o.salestaxes.taxes.engine.ports;

import com.serg1o.salestaxes.taxes.engine.ports.domain.TaxGoodType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaxEngineApplicationServiceRequest {

  private TaxGoodType goodType;
  private boolean imported;
  private double price;
}
