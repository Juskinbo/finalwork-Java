package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Settings extends Application {
    @Override
    public void start(Stage settingsStage) throws Exception {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        Button informationButton = new Button("个人信息");
        informationButton.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Button changePasswordButton = new Button("修改密码");
        Button helpButton = new Button("帮助我们");
        Button exitAllButton = new Button("退出");
        HBox settingsHBox = new HBox(changePasswordButton, helpButton);
        settingsHBox.setAlignment(Pos.CENTER);
        settingsHBox.setSpacing(50);
        VBox settingsVBox = new VBox(settingsHBox, exitAllButton);
        settingsVBox.setAlignment(Pos.CENTER);
        settingsVBox.setSpacing(50);
        settingsStage.setScene(new Scene(settingsVBox, 400, 225));
        changePasswordButton.setPrefSize(100, 50);
        helpButton.setPrefSize(100, 50);
        exitAllButton.setPrefSize(250, 50);
        changePasswordButton.setOnAction(actionEvent -> {
            ChangePassword changePassword = new ChangePassword();
            Stage changePasswordStage = new Stage();
            changePassword.setNowUser(nowUser);
            try {
                changePassword.start(changePasswordStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        helpButton.setOnAction(actionEvent -> {
            addQuestion addQuestion = new addQuestion();
            Stage helpStage = new Stage();
            try {
                addQuestion.start(helpStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        exitAllButton.setOnAction(actionEvent -> System.exit(0));
        settingsStage.setTitle("设置");
        settingsStage.getIcons().addAll(icon);
        settingsStage.setResizable(false);
        settingsStage.show();
    }

    private AtomicInteger nowUser = new AtomicInteger();

    public void setNowUser(AtomicInteger nowUser) {
        this.nowUser = nowUser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
