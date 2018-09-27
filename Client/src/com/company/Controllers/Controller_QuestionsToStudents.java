package com.company.Controllers;

import com.company.Main;
import com.company.Messages.QuestionsAndOptions;
import com.company.Model.QuestionNumberButtons;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class Controller_QuestionsToStudents implements Initializable {

    @FXML
    private TextArea questionBox;

    @FXML
    private TextField option1Box;

    @FXML
    private TextField option2Box;

    @FXML
    private TextField option3Box;

    @FXML
    private TextField option4Box;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    @FXML
    private Button submitAnswer;

    @FXML
    private Label timerHead;

    @FXML
    TableView<QuestionNumberButtons> tableview;

    private List<String[]> rowList;
    private HashMap<Integer, String> A = new HashMap<>();

    private Button [] button = new Button[Integer.parseInt(Controller_Index_Student.getNoOfQuestions())];

    private int buttonNumber;

    private void handleButtonAction(ActionEvent event) {
        for (int i = 0; i < button.length; i++) {
            if(event.getSource() == button[i]) {
                System.out.println(" Button " + (i + 1) + " Pressed");
                questionBox.setText(rowList.get(i)[0]);
                option1Box.setText(rowList.get(i)[1]);
                option2Box.setText(rowList.get(i)[2]);
                option3Box.setText(rowList.get(i)[3]);
                option4Box.setText(rowList.get(i)[4]);
                buttonNumber = i;
            }
        }
    }

    @FXML
    private void addAnswerToHashMap(ActionEvent event) {
        String answer = "";
        if (option1.isSelected()) {
            answer += option1.getText();
        }
        if (option2.isSelected()) {
            answer += option2.getText();
        }
        if (option3.isSelected()) {
            answer += option3.getText();
        }
        if (option4.isSelected()) {
            answer += option4.getText();
        }
        System.out.println("Answer " + answer + " has been submited");
        button[buttonNumber].setBackground(new Background(new BackgroundFill(Paint.valueOf("#42f45c"), CornerRadii.EMPTY, Insets.EMPTY)));
        A.put((buttonNumber+1),answer);
        System.out.println(A);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        QuestionsAndOptions questionsAndOptions;
        try {
            questionsAndOptions = (QuestionsAndOptions) Main.echoes.readObject();
            rowList = questionsAndOptions.getRowList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<QuestionNumberButtons> data;
        for(int i = 0; i<button.length ; i++) {
            button[i] = new Button();
            button[i].setOnAction(this::handleButtonAction);
        }
        data = FXCollections.observableArrayList();
        for (Button aButton : button) {
            data.add(new QuestionNumberButtons(aButton));
        }

        TableColumn<QuestionNumberButtons, String> buttonColumn = new TableColumn<>("button");

        tableview.getColumns().addAll(buttonColumn);

        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        tableview.setItems(data);

        TimerTime timerTime = new TimerTime();
        timerTime.start();

    }

    private class TimerTime extends Thread{
        private Timer timer;
        private int minute;
        private int hour;
        private int second;

        @Override
        public void run() {
            System.out.println("Task scheduled.");
            timer = new Timer();
            timer.schedule(new RemindTask(), Integer.parseInt(Controller_Index_Student.getDuration()) * 1000* 60 );
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                second = LocalDateTime.now().getSecond();
                minute = LocalDateTime.now().getMinute();
                hour = LocalDateTime.now().getHour();
                timerHead.setText(hour + ":" + (minute) + ":" + second);
            }),
                    new KeyFrame(Duration.seconds(1))
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
        }

        private class RemindTask extends TimerTask {
            public void run() {
                System.out.println("Time's up!");
                submitAnswer.setDisable(true);
                try {
                    Main.stringToEcho.writeObject("StudentHashMap");
                    Main.stringToEcho.flush();
                    Main.stringToEcho.writeObject(A);
                    Main.stringToEcho.flush();

                    int rightAns = (int) Main.echoes.readObject();
                    System.out.println("You got " + rightAns + " correct answer");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                timer.cancel(); //Terminate the timer thread
            }
        }
    }
}



