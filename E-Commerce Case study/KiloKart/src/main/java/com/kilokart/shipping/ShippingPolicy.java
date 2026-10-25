package com.kilokart.shipping;

import java.math.BigDecimal;

public interface ShippingPolicy {
    BigDecimal calculate(BigDecimal subtotal, boolean subscriber);
}
