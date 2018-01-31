package com.sample.model;

/**
 * Created by gsati on 1/31/2018.
 */
import java.util.Date;

public class Customer  {
    int custId;
    String custName;
    String email;
    Date dob;

    public Customer() {
    }

    public Customer(int custId, String custName, String email, Date dob) {
        this.custId = custId;
        this.custName = custName;
        this.email = email;
        this.dob = dob;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}
