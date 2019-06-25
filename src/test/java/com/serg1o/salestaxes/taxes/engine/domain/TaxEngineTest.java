package com.serg1o.salestaxes.taxes.engine.domain;

import com.serg1o.salestaxes.taxes.engine.ports.domain.TaxGoodType;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TaxEngineTest {

  @ParameterizedTest
  @MethodSource("provideTaxesDataset")
  void apply_correct_tax_to_good_by_type(TaxGoodType goodType, boolean imported, double price,
      String expected) {
    var taxEngine = new TaxEngine();

    var actual = taxEngine.applyTaxes(goodType, imported, price);

    Assertions.assertEquals(expected, actual.toString());
  }

  private static Stream<Arguments> provideTaxesDataset() {
    return Stream.of(
        Arguments.of(TaxGoodType.BOOK, false, 12.49, "12.49"),
        Arguments.of(TaxGoodType.OTHER, false, 14.99, "16.49"),
        Arguments.of(TaxGoodType.FOOD, false, 0.85, "0.85"),

        Arguments.of(TaxGoodType.FOOD, true, 10, "10.50"),
        Arguments.of(TaxGoodType.OTHER, true, 47.5, "54.65"),

        Arguments.of(TaxGoodType.OTHER, true, 27.99, "32.19"),
        Arguments.of(TaxGoodType.OTHER, false, 18.99, "20.89"),
        Arguments.of(TaxGoodType.MEDICAL, false, 9.75, "9.75"),
        Arguments.of(TaxGoodType.FOOD, true, 11.25, "11.85")
    );
  }
}