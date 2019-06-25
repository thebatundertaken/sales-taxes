package com.serg1o.salestaxes.sales.receipts.domain;

import java.util.List;
import java.util.UUID;

public interface ReceiptGoodRepository {

  public List<ReceiptGood> findByIds(List<UUID> ids);
}
