package com.serg1o.salestaxes.sales.receiptsprinter.ports;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.serg1o.salestaxes.sales.receipts.ports.ReceiptApplicationService;
import com.serg1o.salestaxes.sales.receipts.ports.ReceiptApplicationServiceResponse;
import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceipt;
import com.serg1o.salestaxes.sales.receipts.ports.domain.ResponseReceiptGood;
import com.serg1o.salestaxes.sales.receiptsprinter.adapters.PlaintTextReceiptFormatter;
import com.serg1o.salestaxes.sales.receiptsprinter.ports.domain.RequestReceiptsPrinterReceipt;
import com.serg1o.salestaxes.sales.receiptsprinter.ports.domain.RequestReceiptsPrinterReceiptGood;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
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
class ReceiptsPrinterApplicationServiceTest {

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
  private ReceiptApplicationService applicationService;

  @ParameterizedTest
  @MethodSource("provideReceiptsDataset")
  void print_receipt_in_correct_format(
      ResponseReceipt receipt,
      String expected
  ) {
    when(applicationService.generateReceipt(any())).thenReturn(
        new ReceiptApplicationServiceResponse(receipt)
    );

    var formatter = new PlaintTextReceiptFormatter();
    var applicationService = new ReceiptsPrinterApplicationService(
        formatter
    );

    var actual = applicationService.printReceipt(
        new ReceiptsPrinterApplicationServiceRequest(
            new RequestReceiptsPrinterReceipt(
                receipt.getEntries()
                    .stream()
                    .map(
                        entry -> new RequestReceiptsPrinterReceiptGood(
                            entry.getDescription(),
                            entry.getQuantity(),
                            entry.getPrice(),
                            entry.getNetPrice()
                        )
                    )
                    .collect(Collectors.toList()),
                receipt.getTotal(),
                receipt.getTaxesTotal()
            )
        )
    );

    Assertions.assertEquals(expected, actual);
  }

  private static Stream<Arguments> provideReceiptsDataset() throws IOException, URISyntaxException {
    var receipt1 = new ResponseReceipt(
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

    var receipt2 = new ResponseReceipt(
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

    var receipt3 = new ResponseReceipt(
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

    var classLoader = ClassLoader.getSystemClassLoader();
    var url1 = classLoader.getResource("print-output-test1.txt");
    var url2 = classLoader.getResource("print-output-test2.txt");
    var url3 = classLoader.getResource("print-output-test3.txt");

    var printOutput1 = Files.readString(Path.of(url1.toURI()));
    var printOutput2 = Files.readString(Path.of(url2.toURI()));
    var printOutput3 = Files.readString(Path.of(url3.toURI()));

    return Stream.of(
        Arguments.of(receipt1, printOutput1),
        Arguments.of(receipt2, printOutput2),
        Arguments.of(receipt3, printOutput3)
    );
  }

}