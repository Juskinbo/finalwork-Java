package com.example.bighomework;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Integer.parseInt;

public class Rank extends Application {
    private final TableView tableView = new TableView();

    @Override
    public void start(Stage rankStage) throws Exception {
        File iconFile = new File("src/main/java/com/example/bighomework/icon.jpg");
        Image icon = new Image(iconFile.toURI().toString());
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
        TableColumn rankingCol = new TableColumn("排名");
        rankingCol.setEditable(false);
        rankingCol.setPrefWidth(100);
        TableColumn nameCol = new TableColumn("用户名");
        nameCol.setEditable(false);
        nameCol.setPrefWidth(100);
        TableColumn scoresCol = new TableColumn("积分");
        scoresCol.setEditable(false);
        scoresCol.setPrefWidth(100);
        tableView.getColumns().addAll(rankingCol, nameCol, scoresCol);
        users.sort(new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getScore() >= o2.getScore()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setRank(i + 1);
        }
        Label rankLabel = new Label("排行榜");
        rankLabel.setStyle("-fx-font-size: 30;-fx-font-weight: bold");
        ObservableList<User> obsList = FXCollections.observableArrayList(users);
        tableView.setItems(obsList);
        rankingCol.setCellValueFactory(new PropertyValueFactory<User, String>("rank"));
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        scoresCol.setCellValueFactory(new PropertyValueFactory<User, String>("score"));
        VBox vBox = new VBox(rankLabel, tableView);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        rankStage.getIcons().addAll(icon);
        rankStage.setTitle("排行榜");
        rankStage.setScene(new Scene(vBox, 300, 400));
        rankStage.show();
        rankStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
