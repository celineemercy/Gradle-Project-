package com.example.coaapp;

import com.example.coaapp.config.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inisialisasi Hibernate saat aplikasi dimulai
        HibernateUtil.getSessionFactory();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Chart of Accounts App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Tutup SessionFactory saat aplikasi berhenti
        HibernateUtil.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}