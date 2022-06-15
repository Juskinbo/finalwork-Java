package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Information extends Application {
    @Override
    public void start(Stage informationStage) throws Exception {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        File informationBackGround = new File("src/main/java/com/example/bighomework/informationbackground.png");
        ImageView backGround = new ImageView(informationBackGround.toURI().toString());
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
        }
        Label userIdLabel = new Label();
        userIdLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Label userAccountLabel = new Label();
        userAccountLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Label userScoreLabel = new Label();
        userScoreLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        VBox informationVBox1 = new VBox(userIdLabel, userAccountLabel, userScoreLabel);
        HBox informationHBox = new HBox(informationVBox1);
        informationVBox1.setPadding(new Insets(15));
        informationHBox.setSpacing(50);
        informationVBox1.setSpacing(20);
        StackPane informationPane = new StackPane(backGround);
        backGround.fitWidthProperty().bind(informationPane.widthProperty());
        backGround.fitHeightProperty().bind(informationPane.heightProperty());
        informationPane.getChildren().addAll(informationHBox);
        informationStage.setScene(new Scene(informationPane, 400, 225));
        userIdLabel.setText("用户名：" + users.get(nowUser.get()).getId());
        userAccountLabel.setText("账号：" + users.get(nowUser.get()).getAccount());
        userScoreLabel.setText("积分：" + users.get(nowUser.get()).getScore());
        informationStage.show();
        informationStage.getIcons().addAll(icon);
        informationStage.setResizable(false);
        informationStage.setTitle("个人信息");
    }

    private AtomicInteger nowUser = new AtomicInteger();

    public void setNowUser(AtomicInteger nowUser) {
        this.nowUser = nowUser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
