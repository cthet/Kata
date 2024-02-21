package main.java.com.taxIncome.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Household {

    private List<Adult> adults = new ArrayList<>();
    private int childNumber;

    public Household() {
    }

    public void setAdults(List<Adult> adults) {
        this.adults = adults;
    }

    public void setChildNumber(int childNumber) {
        if(childNumber<0){
            throw new IllegalArgumentException("child number must be postive.");
        }
        this.childNumber = childNumber;
    }

    public List<Adult> getAdults() {
        return Collections.unmodifiableList(adults);
    }

    public int getChildNumber() {
        return childNumber;
    }
}
