package com.serg1o.salestaxes.sales.receipts.ports;

import static org.mockito.Mockito.when;

import com.serg1o.salestaxes.sales.receipts.adapters.DefaultReceiptGoodRepository;
import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceipt;
import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceiptGood;
import com.serg1o.salestaxes.sales.shoppingbaskets.domain.ShoppingBasket;
import com.serg1o.salestaxes.taxes.engine.domain.TaxEngine;
import com.serg1o.salestaxes.taxes.engine.ports.TaxEngineApplicationService;
import com.serg1o.salestaxes.warehouse.goods.domain.Good;
import com.serg1o.salestaxes.warehouse.goods.domain.GoodType;
import com.serg1o.salestaxes.warehouse.goods.domain.GoodsRepository;
import com.serg1o.salestaxes.warehouse.goods.ports.GoodsApplicationService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReceiptApplicationServiceTest {

  private static final UUID TEST_BOOK_ID = UUID.randomUUID();
  private static final UUID TEST_CD_ID = UUID.randomUUID();
  private static final UUID TEST_CHOCOLATE_ID = UUID.randomUUID();

  private static final UUID TEST_IMPORTED_CHOCOLATE_ID = UUID.randomUUID();
  private static final UUID TEST_IMPORTED_PERFUME_ID = UUID.randomUUID();

  private static final UUID TEST_IMPORTED_PERFUME2_ID = UUID.randomUUID();
  private static final UUID TEST_PERFUME_ID = UUID.randomUUID();
  private static final UUID TEST_PILLS_ID = UUID.randomUUID();
  private static final UUID TEST_IMPORTED_CHOCOLATE2_ID = UUID.randomUUID();

  private static final String TEST_BOOK_DESCRIPTION = "book";
  private static final String TEST_CD_DESCRIPTION = "music CD";
  private static final String TEST_CHOCOLATE_DESCRIPTION = "chocolate bar";

  private static final String TEST_IMPORTED_CHOCOLATE_DESCRIPTION = "imported box of chocolates";
  private static final String TEST_IMPORTED_PERFUME_DESCRIPTION = "imported bottle of perfume";

  private static final String TEST_IMPORTED_PERFUME2_DESCRIPTION = "imported bottle of perfume";
  private static final String TEST_PERFUME_DESCRIPTION = "bottle of perfume";
  private static final String TEST_PILLS_DESCRIPTION = "packet of headache pills";
  private static final String TEST_IMPORTED_CHOCOLATE2_DESCRIPTION = "box of imported chocolates";

  @Mock
  private GoodsRepository goodsRepository;
  private ReceiptApplicationService applicationService;

  @BeforeEach
  void setup() {
    var taxEngineApplicationService = new TaxEngineApplicationService(
        new TaxEngine()
    );
    var goodsApplicationService = new GoodsApplicationService(
        goodsRepository
    );

    var receiptGoodRepository = new DefaultReceiptGoodRepository(
        goodsApplicationService,
        taxEngineApplicationService
    );

    applicationService = new ReceiptApplicationService(receiptGoodRepository);
  }

  void setupMockRepositoryResponses() {
    when(goodsRepository.findById(TEST_BOOK_ID)).thenReturn(
        Good.builder().description(TEST_BOOK_DESCRIPTION).goodType(GoodType.BOOK).imported(false)
            .price(12.49)
            .build()
    );
    when(goodsRepository.findById(TEST_CD_ID)).thenReturn(
        Good.builder().description(TEST_CD_DESCRIPTION).goodType(GoodType.OTHER).imported(false)
            .price(14.99)
            .build()
    );
    when(goodsRepository.findById(TEST_CHOCOLATE_ID)).thenReturn(
        Good.builder().description(TEST_CHOCOLATE_DESCRIPTION).goodType(GoodType.FOOD)
            .imported(false)
            .price(0.85).build()
    );

    when(goodsRepository.findById(TEST_IMPORTED_CHOCOLATE_ID)).thenReturn(
        Good.builder().description(TEST_IMPORTED_CHOCOLATE_DESCRIPTION).goodType(GoodType.FOOD)
            .imported(true).price(10).build()
    );
    when(goodsRepository.findById(TEST_IMPORTED_PERFUME_ID)).thenReturn(
        Good.builder().description(TEST_IMPORTED_PERFUME_DESCRIPTION).goodType(GoodType.OTHER)
            .imported(true).price(47.5).build()
    );

    when(goodsRepository.findById(TEST_IMPORTED_PERFUME2_ID)).thenReturn(
        Good.builder().description(TEST_IMPORTED_PERFUME2_DESCRIPTION).goodType(GoodType.OTHER)
            .imported(true).price(27.99).build()
    );
    when(goodsRepository.findById(TEST_PERFUME_ID)).thenReturn(
        Good.builder().description(TEST_PERFUME_DESCRIPTION).goodType(GoodType.OTHER)
            .imported(false)
            .price(18.99).build()
    );
    when(goodsRepository.findById(TEST_PILLS_ID)).thenReturn(
        Good.builder().description(TEST_PILLS_DESCRIPTION).goodType(GoodType.MEDICAL)
            .imported(false).price(9.75).build()
    );
    when(goodsRepository.findById(TEST_IMPORTED_CHOCOLATE2_ID)).thenReturn(
        Good.builder().description(TEST_IMPORTED_CHOCOLATE2_DESCRIPTION).goodType(GoodType.FOOD)
            .imported(true).price(11.25).build()
    );
  }

  @Test
  void throws_exception_on_invalid_request() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> applicationService.generateReceipt(null)
    );
  }

  @Test
  void create_empty_receipt_for_empty_basket() {
    var request = new ReceiptApplicationServiceRequest(
        new ShoppingBasket(UUID.randomUUID())
    );

    var actual = applicationService.generateReceipt(request);

    Assertions.assertEquals(0, actual.getResponseReceipt().getEntries().size());
    Assertions.assertEquals(new BigDecimal(0), actual.getResponseReceipt().getTotal());
    Assertions.assertEquals(new BigDecimal(0), actual.getResponseReceipt().getTaxesTotal());
  }

  @ParameterizedTest
  @MethodSource("provideReceiptsDataset")
  void generate_receipt_with_entries_taxes_and_total(
      ReceiptApplicationServiceRequest request,
      ResponseReceipt expected
  ) {
    setupMockRepositoryResponses();

    var actual = applicationService.generateReceipt(request);

    Assertions.assertEquals(expected, actual.getResponseReceipt());
  }

  private static Stream<Arguments> provideReceiptsDataset() {
    var input1 = new ShoppingBasket(UUID.randomUUID());
    input1.addToBasket(TEST_BOOK_ID);
    input1.addToBasket(TEST_CD_ID);
    input1.addToBasket(TEST_CHOCOLATE_ID);

    var output1 = new ResponseReceipt(
        new ArrayList<>() {{
          add(ResponseReceiptGood.builder()
              .description(TEST_BOOK_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(12.49).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(12.49).setScale(2, RoundingMode.HALF_UP))
              .build());
          add(ResponseReceiptGood.builder()
              .description(TEST_CD_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(16.49).setScale(2, RoundingMode.HALF_UP))
              .build());
          add(ResponseReceiptGood.builder()
              .description(TEST_CHOCOLATE_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(0.85).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(0.85).setScale(2, RoundingMode.HALF_UP))
              .build());
        }},
        new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP),
        new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP)
    );

    var input2 = new ShoppingBasket(UUID.randomUUID());
    input2.addToBasket(TEST_IMPORTED_CHOCOLATE_ID);
    input2.addToBasket(TEST_IMPORTED_PERFUME_ID);

    var output2 = new ResponseReceipt(
        new ArrayList<>() {{
          add(ResponseReceiptGood.builder()
              .description(TEST_IMPORTED_CHOCOLATE_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(10.00).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(10.50).setScale(2, RoundingMode.HALF_UP))
              .build());
          add(ResponseReceiptGood.builder()
              .description(TEST_IMPORTED_PERFUME_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(47.50).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(54.65).setScale(2, RoundingMode.HALF_UP))
              .build());
        }},
        new BigDecimal(65.15).setScale(2, RoundingMode.HALF_UP),
        new BigDecimal(7.65).setScale(2, RoundingMode.HALF_UP)
    );

    var input3 = new ShoppingBasket(UUID.randomUUID());
    input3.addToBasket(TEST_IMPORTED_PERFUME2_ID);
    input3.addToBasket(TEST_PERFUME_ID);
    input3.addToBasket(TEST_PILLS_ID);
    input3.addToBasket(TEST_IMPORTED_CHOCOLATE2_ID);

    var output3 = new ResponseReceipt(
        new ArrayList<>() {{
          add(ResponseReceiptGood.builder()
              .description(TEST_IMPORTED_PERFUME2_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(27.99).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(32.19).setScale(2, RoundingMode.HALF_UP))
              .build());
          add(ResponseReceiptGood.builder()
              .description(TEST_PERFUME_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(18.99).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(20.89).setScale(2, RoundingMode.HALF_UP))
              .build());
          add(ResponseReceiptGood.builder()
              .description(TEST_PILLS_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(9.75).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(9.75).setScale(2, RoundingMode.HALF_UP))
              .build());
          add(ResponseReceiptGood.builder()
              .description(TEST_IMPORTED_CHOCOLATE2_DESCRIPTION)
              .quantity(1)
              .price(new BigDecimal(11.25).setScale(2, RoundingMode.HALF_UP))
              .netPrice(new BigDecimal(11.85).setScale(2, RoundingMode.HALF_UP))
              .build());
        }},
        new BigDecimal(74.68).setScale(2, RoundingMode.HALF_UP),
        new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP)
    );

    return Stream.of(
        Arguments.of(new ReceiptApplicationServiceRequest(input1), output1),
        Arguments.of(new ReceiptApplicationServiceRequest(input2), output2),
        Arguments.of(new ReceiptApplicationServiceRequest(input3), output3)
    );
  }

}