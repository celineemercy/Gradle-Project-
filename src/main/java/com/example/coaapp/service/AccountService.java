package com.example.coaapp.service;

import com.example.coaapp.dao.AccountDao;
import com.example.coaapp.dao.AccountDaoHibernateImpl;
import com.example.coaapp.model.Account;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AccountService {

    private final AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDaoHibernateImpl();
    }

    // Konstruktor untuk Dependency Injection (khususnya untuk unit test)
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    public void addAccount(Account account) {
        accountDao.save(account);
    }

    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    public void deleteAccount(Account account) {
        accountDao.delete(account);
    }

    // Fungsi utama untuk mencetak Chart of Accounts ke PDF
    public void printChartOfAccountsToPdf(String filePath) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Chart of Accounts", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(3); // 3 kolom: Code, Name, Type
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Header tabel
        table.addCell("Account Code");
        table.addCell("Account Name");
        table.addCell("Account Type");

        // Data dari database
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            table.addCell(account.getAccountCode());
            table.addCell(account.getAccountName());
            table.addCell(account.getAccountType());
        }

        document.add(table);
        document.close();
        System.out.println("Chart of Accounts PDF generated successfully at: " + filePath);
    }
}