package com.example.coaapp.controller;

import com.example.coaapp.service.AccountService;
import com.itextpdf.text.DocumentException;
import javafx.scene.control.Alert;
import java.io.IOException;

public class PrintCommand implements Command {

    private final AccountService accountService;
    private final String filePath;

    public PrintCommand(AccountService accountService, String filePath) {
        this.accountService = accountService;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            accountService.printChartOfAccountsToPdf(filePath);
            showAlert(Alert.AlertType.INFORMATION, "Success", "PDF Generated", "Chart of Accounts PDF has been successfully generated.");
        } catch (DocumentException | IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "PDF Generation Failed", "Failed to generate Chart of Accounts PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}