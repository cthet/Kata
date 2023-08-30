package training.taxIncome;

import java.math.BigDecimal;

public class TaxBracket {
    private final BigDecimal limit;
    private final BigDecimal rate;

    public TaxBracket(BigDecimal limit, BigDecimal rate) {
        this.limit = limit;
        this.rate = rate;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
