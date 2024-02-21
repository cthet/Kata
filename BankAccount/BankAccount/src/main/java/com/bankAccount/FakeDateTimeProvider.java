package main.java.com.bankAccount;

import java.util.Date;

public class FakeDateTimeProvider implements DateTimeProvider{
    private final Date fixedDate;

    public FakeDateTimeProvider(Date date) {
        this.fixedDate = date;
    }

    @Override
    public Date now() {
        return fixedDate;
    }
}
