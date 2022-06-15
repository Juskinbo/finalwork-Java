package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Login extends Application {

    @Override
    public void start(Stage loginStage) {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        AtomicInteger nowUser = new AtomicInteger();
        File backGroundFile = new File("src/main/java/com/example/bighomework/loginbackground.jpg");
        ImageView backGround = new ImageView(backGroundFile.toURI().toString());
        Label loginLabel = new Label("用户登录");
        Label loginAccountLabel = new Label("账号：");
        Label loginPasswordLabel = new Label("密码：");
        loginLabel.setStyle("-fx-font-size: 35;-fx-font-family: 'Microsoft YaHei UI';-fx-font-weight: bold");
        TextField loginAccountTextField = new TextField();
        loginAccountLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        loginAccountTextField.setPrefSize(100, 30);
        loginPasswordLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPrefSize(100, 30);
        Button loginButton = new Button("登录");
        loginButton.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Button registerButton = new Button("没有帐户？");
        Button forgetButton = new Button("忘记密码？");
        registerButton.setPrefHeight(30);
        forgetButton.setPrefHeight(30);
        loginButton.setPrefWidth(100);
        loginButton.setPrefHeight(50);
        StackPane stackPane = new StackPane(backGround);
        Scene loginScene = new Scene(stackPane, 300, 400);
        HBox hBox1 = new HBox(), hBox2 = new HBox(), hBox3 = new HBox(), hBox4 = new HBox();
        hBox1.getChildren().addAll(loginLabel);
        hBox2.getChildren().addAll(loginAccountLabel, loginAccountTextField, registerButton);
        hBox3.getChildren().addAll(loginPasswordLabel, loginPasswordField, forgetButton);
        hBox4.getChildren().addAll(loginButton);
        hBox2.setSpacing(10);
        hBox3.setSpacing(10);
        hBox4.setSpacing(10);
        VBox vBox = new VBox(hBox1, hBox2, hBox3, hBox4);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);
        backGround.fitWidthProperty().bind(stackPane.widthProperty());
        backGround.fitHeightProperty().bind(stackPane.heightProperty());
        stackPane.getChildren().addAll(vBox);
        loginStage.setScene(loginScene);
        loginStage.setResizable(false);
        loginStage.setTitle("登录");
        loginStage.show();
        Stage loginFailedStage = new Stage();
        Label loginFailedLabel = new Label("账号或密码错误！");
        loginFailedLabel.setTextFill(Color.RED);
        loginFailedLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 20");
        Button OKButton = new Button("确定");
        OKButton.setPrefSize(100, 30);
        VBox loginFailedVBox = new VBox(loginFailedLabel, OKButton);
        StackPane loginFailedPane = new StackPane(loginFailedVBox);
        loginFailedPane.setAlignment(Pos.CENTER);
        loginFailedVBox.setAlignment(Pos.CENTER);
        loginFailedVBox.setSpacing(30);
        loginFailedStage.setScene(new Scene(loginFailedPane, 200, 100));
        forgetButton.setOnAction(actionEvent -> {
            Reset reset = new Reset();
            Stage warningStage = new Stage();
            try {
                reset.start(warningStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        registerButton.setOnAction(actionEvent -> {
            Register register = new Register();
            Stage registerStage = new Stage();
            try {
                register.start(registerStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        loginButton.setOnAction(actionEvent -> {
            AtomicInteger userNumber = new AtomicInteger();
            ArrayList<User> users = new ArrayList<>();
            File informationUsersFile = new File("src/main/java/com/example/bighomework/usersinformation.txt");
            BufferedReader reader1;
            try {
                reader1 = new BufferedReader(new FileReader(informationUsersFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String str;
            while (true) {
                try {
                    if ((str = reader1.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] s = str.split(" ");
                User temp = new User(s[0], s[1], s[2], s[3], s[4], s[5], s[6], parseInt(s[7]));
                users.add(temp);
                userNumber.getAndIncrement();
            }
            boolean flag = false;
            for (int i = 0; i < userNumber.get(); i++) {
                if (users.get(i).getAccount().equals(loginAccountTextField.getText()) && users.get(i).getPassword().equals(loginPasswordField.getText())) {
                    flag = true;
                    nowUser.set(i);
                    break;
                }
            }
            if (flag) {
                loginStage.close();
                Main main = new Main();
                Stage mainStage = new Stage();
                main.setNowUser(nowUser);
                try {
                    main.start(mainStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                loginFailedStage.show();
            }
        });
        OKButton.setOnAction(actionEvent -> {
            loginAccountTextField.clear();
            loginPasswordField.clear();
            loginFailedStage.close();
        });
        loginStage.getIcons().addAll(icon);
        loginFailedStage.getIcons().addAll(icon);
        loginStage.setResizable(false);
        loginFailedStage.setResizable(false);
        loginStage.setTitle("用户登录");
        loginFailedStage.setTitle("登录失败");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
