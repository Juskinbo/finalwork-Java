package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    @Override
    public void start(Stage mainStage) {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        File backgroundFile = new File("src/main/java/com/example/bighomework/mainbackground.jpeg");
        ImageView backGround = new ImageView(backgroundFile.toURI().toString());
        Button startAnswerButton = new Button("开始答题");
        startAnswerButton.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Button settingsButton = new Button("设置");
        settingsButton.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Button rankButton = new Button("排行榜");
        rankButton.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Button informationButton = new Button("个人信息");
        informationButton.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
        Button changePasswordButton = new Button("修改密码");
        Button exitAllButton = new Button("退出");
        startAnswerButton.setPrefSize(400, 50);
        rankButton.setPrefSize(400, 50);
        informationButton.setPrefSize(400, 50);
        settingsButton.setPrefSize(400, 50);
        VBox allVBox = new VBox(startAnswerButton, rankButton, informationButton, settingsButton);
        allVBox.setPadding(new Insets(0, 0, 40, 0));
        StackPane mainStackPane = new StackPane(backGround);
        mainStackPane.getChildren().addAll(allVBox);
        backGround.fitWidthProperty().bind(mainStackPane.widthProperty());
        backGround.fitHeightProperty().bind(mainStackPane.heightProperty());
        mainStage.setScene(new Scene(mainStackPane, 800, 450));
        mainStage.setMinWidth(800);
        mainStage.setMinHeight(450);
        allVBox.setAlignment(Pos.BOTTOM_CENTER);
        allVBox.setSpacing(20);
        HBox settingsHBox = new HBox(changePasswordButton);
        settingsHBox.setAlignment(Pos.CENTER);
        settingsHBox.setSpacing(50);
        VBox settingsVBox = new VBox(settingsHBox, exitAllButton);
        settingsVBox.setAlignment(Pos.CENTER);
        settingsVBox.setSpacing(50);
        changePasswordButton.setPrefSize(100, 50);
        exitAllButton.setPrefSize(250, 50);
        exitAllButton.setOnAction(actionEvent -> System.exit(0));
        startAnswerButton.setOnAction(actionEvent -> {
            Answering answering = new Answering();
            Stage answerStage = new Stage();
            answering.setNowUser(nowUser);
            try {
                answering.start(answerStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        informationButton.setOnAction(actionEvent -> {
            Information information = new Information();
            Stage informationStage = new Stage();
            information.setNowUser(nowUser);
            try {
                information.start(informationStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        settingsButton.setOnAction(actionEvent -> {
            Settings settings = new Settings();
            Stage settingsStage = new Stage();
            settings.setNowUser(nowUser);
            try {
                settings.start(settingsStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        rankButton.setOnAction(actionEvent -> {
            Rank rank = new Rank();
            Stage rankStage = new Stage();
            try {
                rank.start(rankStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        mainStage.getIcons().addAll(icon);
        mainStage.setTitle("主界面");
        mainStage.setResizable(false);
        mainStage.show();
    }

    private AtomicInteger nowUser = new AtomicInteger();

    public void setNowUser(AtomicInteger nowUser) {
        this.nowUser = nowUser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
