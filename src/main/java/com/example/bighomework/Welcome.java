package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Welcome extends Application {
    @Override
    public void start(Stage welcomeStage) {
        File video = new File("src/main/java/com/example/bighomework/video.mp4");
        Media media = new Media(video.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setMute(true);
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        Label welcomeLabel = new Label("亚运知识竞赛答题系统");
        welcomeLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 50;-fx-opacity: 0.5");
        welcomeLabel.setTextFill(Color.WHITE);
        Button enterButton = new Button("进入");
        Button exitButton = new Button("退出");
        enterButton.setStyle("-fx-background-color: #6dcf6d;-fx-opacity: 0.7");
        exitButton.setStyle("-fx-background-color: #ff4e4e;-fx-opacity: 0.7");
        StackPane welcomePane = new StackPane(mediaView);
        mediaView.fitHeightProperty().bind(welcomePane.heightProperty());
        mediaView.fitWidthProperty().bind(welcomePane.widthProperty());
        HBox welcomeHBox2 = new HBox(welcomeLabel);
        HBox welcomeHBox1 = new HBox(enterButton, exitButton);
        welcomeHBox1.setSpacing(60);
        VBox welcomeVBox = new VBox(welcomeHBox2, welcomeHBox1);
        welcomePane.getChildren().addAll(welcomeVBox);
        welcomeVBox.setAlignment(Pos.CENTER);
        welcomeHBox1.setAlignment(Pos.BOTTOM_CENTER);
        welcomeHBox2.setAlignment(Pos.BOTTOM_CENTER);
        welcomeHBox1.setPadding(new Insets(150));
        enterButton.setPrefHeight(45);
        enterButton.setPrefWidth(80);
        exitButton.setPrefHeight(45);
        exitButton.setPrefWidth(80);
        welcomeStage.setResizable(false);
        welcomeStage.setTitle("争做亚运达人，科普亚运知识");
        welcomeStage.setScene(new Scene(welcomePane, 800, 450));
        welcomeStage.getIcons().addAll(icon);
        welcomeStage.setMinWidth(800);
        welcomeStage.setMinHeight(450);
        welcomeStage.show();
        exitButton.setOnAction(actionEvent -> System.exit(0));
        enterButton.setOnAction(actionEvent -> {
            welcomeStage.close();
            Login login = new Login();
            Stage loginStage = new Stage();
            try {
                login.start(loginStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}