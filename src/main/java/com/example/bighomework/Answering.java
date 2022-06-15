package com.example.bighomework;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Answering extends Application {
    @Override
    public void start(Stage answerStage) throws Exception {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
        File backGroundFile = new File("src/main/java/com/example/bighomework/answeringbackground.jpg");
        ImageView backGround = new ImageView(backGroundFile.toURI().toString());
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
        AtomicInteger questionNumber = new AtomicInteger();
        ArrayList<Question> questions = new ArrayList<>();
        File questionFile = new File("src/main/java/com/example/bighomework/questionbank.txt");
        BufferedReader reader2 = new BufferedReader(new FileReader(questionFile));
        String str1;
        String str2;
        while ((str1 = reader2.readLine()) != null) {
            str2 = reader2.readLine();
            questionNumber.getAndIncrement();
            Question temp = new Question(str1, str2);
            questions.add(temp);
        }
        reader1.close();
        reader2.close();
        Label questionLabel = new Label();
        questionLabel.setStyle("-fx-font-size: 20");
        TextField answerTextField = new TextField();
        answerTextField.setMaxHeight(30);
        answerTextField.setMaxWidth(250);
        answerTextField.setStyle("-fx-font-size: 20");
        Button nextQuestionButton = new Button("下一题");
        nextQuestionButton.setPrefSize(150, 50);
        StackPane answerStackPane = new StackPane(backGround);
        VBox answerVBox = new VBox(questionLabel, answerTextField, nextQuestionButton);
        answerVBox.setAlignment(Pos.CENTER);
        answerVBox.setSpacing(30);
        answerStackPane.getChildren().addAll(answerVBox);
        backGround.fitHeightProperty().bind(answerStackPane.heightProperty());
        backGround.fitWidthProperty().bind(answerStackPane.widthProperty());
        answerStage.setScene(new Scene(answerStackPane, 800, 450));
        int[] a = new int[10];
        AtomicInteger num = new AtomicInteger();
        answerTextField.clear();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(questionNumber.get() - 1) % (questionNumber.get() + 1);
            for (int j = 0; j < i; j++) {
                if (a[j] == a[i]) {
                    i--;
                    break;
                }
            }
        }
        nextQuestionButton.setText("下一题");
        questionLabel.setText((num.get() + 1) + ": " + questions.get(a[num.get()]).getQuestion());
        num.getAndIncrement();
        Stage answerFinishedStage = new Stage();
        Button OKButton = new Button("确定");
        Label congratulationsLabel = new Label();
        congratulationsLabel.setStyle("-fx-font-size: 15");
        VBox congratulationVBox = new VBox(congratulationsLabel, OKButton);
        congratulationVBox.setAlignment(Pos.CENTER);
        congratulationVBox.setSpacing(30);
        OKButton.setPrefSize(100, 30);
        StackPane congratulationsPane = new StackPane(congratulationVBox);
        answerFinishedStage.setScene(new Scene(congratulationsPane, 200, 100));
        AtomicInteger scores = new AtomicInteger();
        nextQuestionButton.setOnAction(actionEvent -> {
            if (num.get() < 9 && num.get() > 0) {
                if (answerTextField.getText().equals(questions.get(a[num.get() - 1]).getAnswer())) {
                    scores.getAndIncrement();
                }
                answerTextField.clear();
                questionLabel.setText((num.get() + 1) + ": " + questions.get(a[num.get()]).getQuestion());
                num.getAndIncrement();
            } else if (num.get() == 9) {
                nextQuestionButton.setText("结束答题");
                if (answerTextField.getText().equals(questions.get(a[num.get() - 1]).getAnswer())) {
                    scores.getAndIncrement();
                }
                answerTextField.clear();
                questionLabel.setText((num.get() + 1) + ": " + questions.get(a[num.get()]).getQuestion());
                num.getAndIncrement();
            } else {
                if (answerTextField.getText().equals(questions.get(a[num.get() - 1]).getAnswer())) {
                    scores.getAndIncrement();
                }
                congratulationsLabel.setText("恭喜你！您本次的得分为：" + scores.get());
                answerFinishedStage.show();
                answerStage.close();
                num.set(0);
                users.get(nowUser.get()).setScore(users.get(nowUser.get()).getScore() + scores.get());
                try {
                    PrintWriter outputFile = new PrintWriter(informationUsersFile);
                    for (int i = 0; i < userNumber.get(); i++) {
                        outputFile.println(users.get(i).getId() + " " + users.get(i).getAccount() + " " + users.get(i).getPassword() + " " + users.get(i).getSecurityQuestion1() + " " + users.get(i).getSecurityAnswer1() + " " + users.get(i).getSecurityQuestion2() + " " + users.get(i).getSecurityAnswer2() + " " + users.get(i).getScore());
                    }
                    outputFile.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        OKButton.setOnAction(actionEvent -> answerFinishedStage.close());
        answerStage.getIcons().addAll(icon);
        answerFinishedStage.getIcons().addAll(icon);
        answerStage.setTitle("答题界面");
        answerFinishedStage.setTitle("答题结束");
        answerStage.setResizable(false);
        answerFinishedStage.setResizable(false);
        answerStage.show();
    }

    private AtomicInteger nowUser = new AtomicInteger();

    public void setNowUser(AtomicInteger nowUser) {
        this.nowUser = nowUser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
