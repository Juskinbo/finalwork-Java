package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class addQuestion extends Application {
    @Override
    public void start(Stage helpStage) throws Exception {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        Label helpLabel = new Label("帮助我们完善题库！");
        helpLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 35");
        Label questionLabel = new Label("题目：");
        questionLabel.setStyle("-fx-font-size: 20");
        Label answerLabel = new Label("答案：");
        answerLabel.setStyle("-fx-font-size: 20");
        TextField questionTextField = new TextField();
        TextField answerTextField = new TextField();
        Button button = new Button("确定");
        button.setPrefSize(100, 30);
        VBox vBox = new VBox();
        HBox hBox1 = new HBox(helpLabel);
        HBox hBox2 = new HBox(questionLabel, questionTextField);
        HBox hBox3 = new HBox(answerLabel, answerTextField);
        HBox hBox4 = new HBox(button);
        vBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4);
        helpStage.setResizable(false);
        helpStage.getIcons().add(icon);
        helpStage.setScene(new Scene(vBox, 400, 225));
        helpStage.setTitle("帮助我们");
        helpStage.show();
        Stage warningStage = new Stage();
        Label addSucceedLabel = new Label("添加成功!");
        Button addSucceedButton = new Button("确定");
        VBox addSucceedVBox = new VBox(addSucceedLabel, addSucceedButton);
        addSucceedVBox.setAlignment(Pos.CENTER);
        addSucceedLabel.setTextFill(Color.GREEN);
        addSucceedLabel.setStyle("-fx-font-size: 20");
        addSucceedButton.setPrefSize(100, 30);
        addSucceedVBox.setSpacing(30);
        warningStage.setScene(new Scene(addSucceedVBox, 200, 100));
        warningStage.setResizable(false);
        warningStage.setTitle("添加成功");
        warningStage.getIcons().add(icon);
        button.setOnAction(actionEvent -> {
            File questionFile = new File("src/main/java/com/example/bighomework/questionbank.txt");
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(questionFile, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileWriter.write("\n" + questionTextField.getText() + "\n" + answerTextField.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            warningStage.show();
        });
        addSucceedButton.setOnAction(actionEvent -> {
            warningStage.close();
            helpStage.close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
