package com.example.coaapp.model;

public class AccountBuilder {
    String accountCode;
    String accountName;
    String accountType;

    public AccountBuilder withAccountCode(String accountCode) {
        this.accountCode = accountCode;
        return this;
    }

    public AccountBuilder withAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public AccountBuilder withAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public Account build() {
        return new Account(this);
    }
}