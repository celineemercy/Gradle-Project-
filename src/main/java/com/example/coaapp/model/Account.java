package com.example.coaapp.model;

import jakarta.persistence.*; // atau javax.persistence jika Hibernate 5

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_code", unique = true, nullable = false)
    private String accountCode;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type")
    private String accountType; // e.g., Asset, Liability, Equity, Revenue, Expense

    // Konstruktor default untuk Hibernate
    public Account() {}

    // Konstruktor dengan Builder Pattern
    Account(AccountBuilder builder) {
        this.accountCode = builder.accountCode;
        this.accountName = builder.accountName;
        this.accountType = builder.accountType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountCode='" + accountCode + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}