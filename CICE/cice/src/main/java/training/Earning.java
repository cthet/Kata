package training;

import java.math.BigDecimal;

public class Earning {
    private BigDecimal amount;
    private EarningType type;

    public Earning(final String amount, EarningType type) {
        this.amount = new BigDecimal(amount);
        this.type = type;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public EarningType getType() {
        return this.type;
    }

    public void setType(EarningType type) {
        this.type = type;
    }
}
