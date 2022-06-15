package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Register extends Application {
    @Override
    public void start(Stage registerStage) {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        File backGroundFile = new File("src/main/java/com/example/bighomework/registerBackGround.jpg");
        ImageView backGround = new ImageView(backGroundFile.toURI().toString());
        StackPane registerStackPane = new StackPane(backGround);
        backGround.fitWidthProperty().bind(registerStackPane.widthProperty());
        backGround.fitHeightProperty().bind(registerStackPane.heightProperty());
        GridPane registerPane = new GridPane();
        registerStackPane.getChildren().addAll(registerPane);
        Label registerAccountLabel = new Label("账号：");
        Label registerPasswordLabel = new Label("密码：");
        TextField registerAccountTextField = new TextField();
        PasswordField registerPasswordField = new PasswordField();
        Label registerLabel = new Label("用户注册");
        Label registerIdLabel = new Label("用户名：");
        PasswordField confirmPasswordField = new PasswordField();
        Label confirmPasswordLabel = new Label("确认密码：");
        TextField registerIdTextField = new TextField();
        Label securityQuestionLabel1 = new Label("安全问题一：");
        Label securityQuestionLabel2 = new Label("安全问题二：");
        Label securityAnswerLabel1 = new Label("答案：");
        Label securityAnswerLabel2 = new Label("答案：");
        TextField securityQuestion1 = new TextField();
        TextField securityQuestion2 = new TextField();
        TextField securityAnswer1 = new TextField();
        TextField securityAnswer2 = new TextField();
        registerLabel.setStyle("-fx-font-size: 35;-fx-font-family: 'Microsoft YaHei UI';-fx-font-weight: bold");
        Button registerFinishedButton = new Button("注册");
        registerFinishedButton.setPrefHeight(40);
        registerFinishedButton.setPrefWidth(110);
        registerPane.add(registerLabel, 1, 0);
        registerPane.add(registerIdLabel, 0, 1);
        registerPane.add(registerIdTextField, 1, 1);
        registerPane.add(registerAccountLabel, 0, 2);
        registerPane.add(registerAccountTextField, 1, 2);
        registerPane.add(registerPasswordLabel, 0, 3);
        registerPane.add(registerPasswordField, 1, 3);
        registerPane.add(confirmPasswordLabel, 0, 4);
        registerPane.add(confirmPasswordField, 1, 4);
        registerPane.add(securityQuestionLabel1, 0, 5);
        registerPane.add(securityQuestion1, 1, 5);
        registerPane.add(securityAnswerLabel1, 0, 6);
        registerPane.add(securityAnswer1, 1, 6);
        registerPane.add(securityQuestionLabel2, 0, 7);
        registerPane.add(securityQuestion2, 1, 7);
        registerPane.add(securityAnswerLabel2, 0, 8);
        registerPane.add(securityAnswer2, 1, 8);
        registerPane.add(registerFinishedButton, 1, 9);
        registerPane.setPadding(new Insets(20, 0, 0, 0));
        GridPane.setHalignment(registerIdLabel, HPos.RIGHT);
        GridPane.setHalignment(registerAccountLabel, HPos.RIGHT);
        GridPane.setHalignment(registerPasswordLabel, HPos.RIGHT);
        GridPane.setHalignment(confirmPasswordLabel, HPos.RIGHT);
        GridPane.setHalignment(securityQuestionLabel1, HPos.RIGHT);
        GridPane.setHalignment(securityAnswerLabel1, HPos.RIGHT);
        GridPane.setHalignment(securityQuestionLabel2, HPos.RIGHT);
        GridPane.setHalignment(securityAnswerLabel2, HPos.RIGHT);
        registerPane.setVgap(10);
        registerPane.setHgap(10);
        registerStage.setTitle("注册");
        registerStage.setScene(new Scene(registerStackPane, 300, 400));
        registerStage.show();
        Stage registerFailedStage1 = new Stage();
        Stage registerFailedStage2 = new Stage();
        StackPane registerFailedPane1 = new StackPane();
        StackPane registerFailedPane2 = new StackPane();
        VBox registerFailedVBox1 = new VBox();
        VBox registerFailedVBox2 = new VBox();
        Label registerFailedAccountLabel = new Label("账户已存在！");
        Label registerFailedPasswordLabel = new Label("两次密码不同!");
        Button OK1Button = new Button("确定");
        Button OK2Button = new Button("确定");
        Stage registerSucceedStage = new Stage();
        StackPane registerSucceedPane = new StackPane();
        VBox registerSucceedVBox = new VBox();
        Label registerSucceedLabel = new Label("注册成功");
        registerSucceedLabel.setTextFill(Color.GREEN);
        registerSucceedLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 20");
        Button OK3Button = new Button("确定");
        OK3Button.setPrefSize(100, 30);
        registerSucceedVBox.getChildren().addAll(registerSucceedLabel, OK3Button);
        registerSucceedPane.getChildren().addAll(registerSucceedVBox);
        registerSucceedPane.setAlignment(Pos.CENTER);
        registerSucceedVBox.setAlignment(Pos.CENTER);
        registerSucceedVBox.setSpacing(30);
        registerSucceedStage.setScene(new Scene(registerSucceedPane, 200, 100));
        registerFailedAccountLabel.setTextFill(Color.RED);
        registerFailedPasswordLabel.setTextFill(Color.RED);
        registerFailedAccountLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 20");
        registerFailedPasswordLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 20");
        OK1Button.setPrefSize(100, 30);
        OK2Button.setPrefSize(100, 30);
        registerFailedVBox1.getChildren().addAll(registerFailedAccountLabel, OK1Button);
        registerFailedVBox2.getChildren().addAll(registerFailedPasswordLabel, OK2Button);
        registerFailedVBox1.setAlignment(Pos.CENTER);
        registerFailedVBox2.setAlignment(Pos.CENTER);
        registerFailedPane1.setAlignment(Pos.CENTER);
        registerFailedPane2.setAlignment(Pos.CENTER);
        registerFailedVBox1.setSpacing(30);
        registerFailedVBox2.setSpacing(30);
        registerFailedStage1.setScene(new Scene(registerFailedVBox1, 200, 100));
        registerFailedStage2.setScene(new Scene(registerFailedVBox2, 200, 100));
        registerFinishedButton.setOnAction(actionEvent -> {
            AtomicInteger userNumber = new AtomicInteger();
            ArrayList<User> users = new ArrayList<>();
            File informationUsersFile = new File("src/main/java/com/example/bighomework/usersinformation.txt");
            BufferedReader reader1;
            String str;
            try {
                reader1 = new BufferedReader(new FileReader(informationUsersFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
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
            try {
                reader1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean flag = true;
            for (int i = 0; i < userNumber.get(); i++) {
                if (users.get(i).getAccount().equals(registerAccountTextField.getText())) {
                    flag = false;
                    break;
                }
            }
            if (flag && registerPasswordField.getText().equals(confirmPasswordField.getText())) {
                File registerFile = new File("src/main/java/com/example/bighomework/usersinformation.txt");
                try {
                    FileWriter fileWriter = new FileWriter(registerFile, true);
                    userNumber.getAndIncrement();
                    fileWriter.write(registerIdTextField.getText() + " " + registerAccountTextField.getText() + " " + registerPasswordField.getText() + " " + securityQuestion1.getText() + " " + securityAnswer1.getText() + " " + securityQuestion2.getText() + " " + securityAnswer2.getText() + " 0\n");
                    fileWriter.close();
                    User temp = new User(registerIdTextField.getText(), registerAccountTextField.getText(), registerPasswordField.getText(), securityQuestion1.getText(), securityAnswer1.getText(), securityQuestion2.getText(), securityAnswer2.getText(), 0);
                    users.add(temp);
                    registerSucceedStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (!flag) {
                registerFailedStage1.show();
            } else {
                registerFailedStage2.show();
            }
        });
        OK1Button.setOnAction(actionEvent -> {
            confirmPasswordField.clear();
            registerPasswordField.clear();
            registerIdTextField.clear();
            securityQuestion1.clear();
            securityQuestion2.clear();
            securityAnswer1.clear();
            securityAnswer2.clear();
            registerAccountTextField.clear();
            registerFailedStage1.close();
        });
        OK2Button.setOnAction(actionEvent -> {
            confirmPasswordField.clear();
            registerPasswordField.clear();
            registerIdTextField.clear();
            securityQuestion1.clear();
            securityQuestion2.clear();
            securityAnswer1.clear();
            securityAnswer2.clear();
            registerAccountTextField.clear();
            registerFailedStage2.close();
        });
        OK3Button.setOnAction(actionEvent -> {
            registerSucceedStage.close();
            registerStage.close();
        });
        registerStage.setTitle("用户注册");
        registerFailedStage1.setTitle("注册失败");
        registerFailedStage2.setTitle("注册失败");
        registerSucceedStage.setTitle("注册成功");
        registerStage.getIcons().addAll(icon);
        registerFailedStage1.getIcons().addAll(icon);
        registerFailedStage2.getIcons().addAll(icon);
        registerSucceedStage.getIcons().addAll(icon);
        registerStage.setResizable(false);
        registerSucceedStage.setResizable(false);
        registerFailedStage1.setResizable(false);
        registerFailedStage2.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
