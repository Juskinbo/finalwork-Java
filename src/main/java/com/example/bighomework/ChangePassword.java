package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class ChangePassword extends Application {
    @Override
    public void start(Stage changePasswordStage) throws Exception {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        ArrayList<User> users = new ArrayList<>();
        AtomicInteger userNumber = new AtomicInteger();
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
        Label newPasswordLabel1 = new Label("  新密码：");
        Label confirmPasswordLabel1 = new Label("确认密码：");
        PasswordField newPasswordField1 = new PasswordField();
        PasswordField confirmPasswordField1 = new PasswordField();
        Button changePasswordFinishedButton = new Button("确定");
        changePasswordFinishedButton.setPrefSize(100, 30);
        HBox changePasswordHBox1 = new HBox(newPasswordLabel1, newPasswordField1);
        HBox changePasswordHBox2 = new HBox(confirmPasswordLabel1, confirmPasswordField1);
        HBox changePasswordHBox3 = new HBox(changePasswordFinishedButton);
        changePasswordHBox3.setAlignment(Pos.CENTER);
        changePasswordHBox2.setAlignment(Pos.CENTER);
        changePasswordHBox1.setAlignment(Pos.CENTER);
        VBox changePasswordVBox = new VBox(changePasswordHBox1, changePasswordHBox2, changePasswordHBox3);
        changePasswordVBox.setAlignment(Pos.CENTER);
        changePasswordVBox.setSpacing(30);
        changePasswordStage.setScene(new Scene(changePasswordVBox, 300, 200));
        Button changePasswordSucceedButton = new Button("确定");
        Label changePasswordSucceedLabel = new Label("密码修改成功！");
        changePasswordSucceedButton.setPrefSize(100, 30);
        changePasswordSucceedLabel.setTextFill(Color.GREEN);
        changePasswordSucceedLabel.setStyle("-fx-font-size: 20");
        Stage changePasswordSucceedStage = new Stage();
        VBox changePasswordSucceedVBox = new VBox(changePasswordSucceedLabel, changePasswordSucceedButton);
        changePasswordSucceedVBox.setSpacing(30);
        changePasswordSucceedVBox.setAlignment(Pos.CENTER);
        changePasswordSucceedStage.setScene(new Scene(changePasswordSucceedVBox, 200, 100));
        changePasswordFinishedButton.setOnAction(actionEvent -> {
            if (newPasswordField1.getText().equals(confirmPasswordField1.getText())) {
                users.get(nowUser.get()).setPassword(newPasswordField1.getText());
                try {
                    PrintWriter outputFile = new PrintWriter(informationUsersFile);
                    for (int i = 0; i < userNumber.get(); i++) {
                        outputFile.println(users.get(i).getId() + " " + users.get(i).getAccount() + " " + users.get(i).getPassword() + " " + users.get(i).getSecurityQuestion1() + " " + users.get(i).getSecurityAnswer1() + " " + users.get(i).getSecurityQuestion2() + " " + users.get(i).getSecurityAnswer2() + " " + users.get(i).getScore());
                    }
                    changePasswordSucceedStage.show();
                    outputFile.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                confirmPasswordLabel1.setTextFill(Color.RED);
                confirmPasswordLabel1.setText("两次密码不同！");
            }
        });
        changePasswordSucceedButton.setOnAction(actionEvent -> {
            changePasswordStage.close();
            changePasswordSucceedStage.close();
        });
        changePasswordStage.show();
        changePasswordStage.getIcons().addAll(icon);
        changePasswordSucceedStage.getIcons().addAll(icon);
        changePasswordStage.setResizable(false);
        changePasswordSucceedStage.setResizable(false);
        changePasswordStage.setTitle("密码修改");
        changePasswordSucceedStage.setTitle("密码修改成功");
    }

    private AtomicInteger nowUser = new AtomicInteger();

    public void setNowUser(AtomicInteger nowUser) {
        this.nowUser = nowUser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
