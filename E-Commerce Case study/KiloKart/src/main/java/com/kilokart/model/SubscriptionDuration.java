package com.kilokart.model;

import java.time.Period;

public enum SubscriptionDuration {
    MONTHLY(Period.ofMonths(1)),
    QUARTERLY(Period.ofMonths(3)),
    HALF_YEARLY(Period.ofMonths(6)),
    YEARLY(Period.ofYears(1));

    private final Period period;

    SubscriptionDuration(Period period) {
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }
}
