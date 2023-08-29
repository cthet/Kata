package training;

import java.util.List;

public class Employee {
    private List<Earning> earnings;
    private Boolean intern;

    public List<Earning> getEarnings() {
        return this.earnings;
    }

    public void setEarnings(final List<Earning> earnings) {
        this.earnings = earnings;
    }

    public Boolean getIntern() {
        return this.intern;
    }

    public void setIntern(final Boolean intern) {
        this.intern = intern;
    }
}
