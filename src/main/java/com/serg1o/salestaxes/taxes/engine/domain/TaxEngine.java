package com.serg1o.salestaxes.taxes.engine.domain;

import com.serg1o.salestaxes.taxes.engine.ports.domain.TaxGoodType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class TaxEngine {

  private static final int GENERAL_TAX = 10;
  private static final int REDUCED_TAX = 0;
  private static final int IMPORT_TAX = 5;
  private static final int PRECISION = 2;

  private static Map<TaxGoodType, Integer> taxMap;

  static {
    taxMap = new HashMap<>();
    taxMap.put(TaxGoodType.BOOK, REDUCED_TAX);
    taxMap.put(TaxGoodType.FOOD, REDUCED_TAX);
    taxMap.put(TaxGoodType.MEDICAL, REDUCED_TAX);
    taxMap.put(TaxGoodType.OTHER, GENERAL_TAX);
  }

  public BigDecimal applyTaxes(TaxGoodType goodType, boolean imported, double price) {
    var tax = taxMap.get(goodType);
    if (imported) {
      tax += IMPORT_TAX;
    }

    double vat = (price * tax) / 100;
    return new BigDecimal(price + roundToClosest005(vat))
        .setScale(PRECISION, RoundingMode.HALF_UP);
  }

  private double roundToClosest005(double number) {
    return Math.ceil(number * 20d) / 20d;
  }

}
