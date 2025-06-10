package com.example.coaapp.controller;

import com.example.coaapp.model.Account;
import com.example.coaapp.model.AccountBuilder;
import com.example.coaapp.service.AccountService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AccountController {

    @FXML
    private TextField accountCodeField;
    @FXML
    private TextField accountNameField;
    @FXML
    private TextField accountTypeField;
    @FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, Long> idColumn;
    @FXML
    private TableColumn<Account, String> codeColumn;
    @FXML
    private TableColumn<Account, String> nameColumn;
    @FXML
    private TableColumn<Account, String> typeColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button printPdfButton;

    private AccountService accountService;
    private ObservableList<Account> accountList;

    public AccountController() {
        this.accountService = new AccountService();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("accountCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        accountList = FXCollections.observableArrayList();
        accountTable.setItems(accountList);

        loadAccounts();

        // Listener untuk memilih item di tabel
        accountTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAccountDetails(newValue));
    }

    private void loadAccounts() {
        accountList.clear();
        List<Account> accounts = accountService.getAllAccounts();
        accountList.addAll(accounts);
    }

    private void showAccountDetails(Account account) {
        if (account != null) {
            accountCodeField.setText(account.getAccountCode());
            accountNameField.setText(account.getAccountName());
            accountTypeField.setText(account.getAccountType());
        } else {
            clearFields();
        }
    }

    @FXML
    void handleAddAccount(ActionEvent event) {
        Account newAccount = new AccountBuilder()
                .withAccountCode(accountCodeField.getText())
                .withAccountName(accountNameField.getText())
                .withAccountType(accountTypeField.getText())
                .build();
        accountService.addAccount(newAccount);
        loadAccounts();
        clearFields();
    }

    @FXML
    void handleUpdateAccount(ActionEvent event) {
        Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            selectedAccount.setAccountCode(accountCodeField.getText());
            selectedAccount.setAccountName(accountNameField.getText());
            selectedAccount.setAccountType(accountTypeField.getText());
            accountService.updateAccount(selectedAccount);
            loadAccounts();
            clearFields();
        }
    }

    @FXML
    void handleDeleteAccount(ActionEvent event) {
        Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            accountService.deleteAccount(selectedAccount);
            loadAccounts();
            clearFields();
        }
    }

    @FXML
    void handlePrintPdf(ActionEvent event) {
        Command printCommand = new PrintCommand(accountService, "chart_of_accounts.pdf");
        printCommand.execute();
    }

    private void clearFields() {
        accountCodeField.clear();
        accountNameField.clear();
        accountTypeField.clear();
    }
}