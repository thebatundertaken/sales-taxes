package com.serg1o.salestaxes.taxes.engine.ports;

import com.serg1o.salestaxes.taxes.engine.domain.TaxEngine;
import java.math.BigDecimal;

public class TaxEngineApplicationService {

  private TaxEngine taxEngine;

  public TaxEngineApplicationService(TaxEngine taxEngine) {
    this.taxEngine = taxEngine;
  }

  public BigDecimal applyTaxes(TaxEngineApplicationServiceRequest request) {
    return taxEngine.applyTaxes(request.getGoodType(), request.isImported(), request.getPrice());
  }
}
