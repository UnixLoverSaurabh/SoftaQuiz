package com.company.Controllers;

import com.company.Main;
import com.company.Messages.Message;
import com.company.Messages.MessageDecryption;
import com.company.Messages.MessageEncryption;
import com.company.Messages.MessagePopUpTopic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.company.alertBox.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_PopUpTopic extends Controller_Index implements Initializable{
    @FXML
    private JFXComboBox<String> subjectPopUp;

    @FXML
    private JFXTextField topicPopUp;

    @FXML
    private JFXDatePicker datePopUp;

    @FXML
    private JFXTextField noOfSinglePopUp;

    @FXML
    private JFXTextField timeSinglePopUp;

    @FXML
    private JFXTextField noOfMultiplePopUp;

    @FXML
    private JFXTextField timeMultiplePopUp;

    @FXML
    private JFXTextField noOfTrueFalsePopUp;

    @FXML
    private JFXTextField timeTrueFalsePopUp;

    @FXML
    private JFXButton saveButton;

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

    private static String subject;
    private static String topic;
    private static String noOfSingle;
    private static String noOfMultiple;
    private static String noOfTrueFalse;

    public static String getSubject() {
        return subject;
    }
    public static String getTopic() {
        return topic;
    }
    public static String getNoOfSingle() { return noOfSingle; }
    public static String getNoOfMultiple() { return noOfMultiple; }
    public static String getNoOfTrueFalse() { return noOfTrueFalse; }


    @FXML
    private void cancel() {
        Stage stage = (Stage) topicPopUp.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addNewTopicForQuiz(ActionEvent event) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        subject = subjectPopUp.getSelectionModel().getSelectedItem();
        System.out.println("Selected subject is " + subject);
        topic = topicPopUp.getText();
        LocalDate date = datePopUp.getValue();
        if (date == null) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Improper Date format.", "Please enter data in all fields.");
            return;
        }
        System.out.println("Date is : " + date);
        String eveDate = date.toString();
        noOfSingle = noOfSinglePopUp.getText();
        String timeSingle = timeSinglePopUp.getText();
        noOfMultiple = noOfMultiplePopUp.getText();
        String timeMultiple = timeMultiplePopUp.getText();
        noOfTrueFalse = noOfTrueFalsePopUp.getText();
        String timeTrueFalse = timeTrueFalsePopUp.getText();

        boolean flag = subject.isEmpty() || topic.isEmpty() || noOfSingle.isEmpty() || timeSingle.isEmpty() || noOfMultiple.isEmpty() || timeMultiple.isEmpty() || noOfTrueFalse.isEmpty() || timeTrueFalse.isEmpty();
        if (flag) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter data in all fields.");
            return;
        }
        boolean flagNumber = checkNumber(noOfSingle) && checkNumber(timeSingle) && checkNumber(noOfMultiple) && checkNumber(timeMultiple) && checkNumber(noOfTrueFalse) && checkNumber(timeTrueFalse);
        if (!flagNumber) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Wrong Data format", "Please enter correct data format (Int)");
            return;
        }

        MessageEncryption popUsername = new MessageEncryption(Main.sessionUsername, Main.key);
        MessageEncryption popUpSubject = new MessageEncryption(subject, Main.key);
        MessageEncryption popUpTopic = new MessageEncryption(topic, Main.key);
        MessageEncryption popUpEveDate = new MessageEncryption(eveDate, Main.key);
        MessageEncryption popUpNoOfSingle = new MessageEncryption(noOfSingle, Main.key);
        MessageEncryption popUpTimeSingle = new MessageEncryption(timeSingle, Main.key);
        MessageEncryption popUpNoOfMultiple = new MessageEncryption(noOfMultiple, Main.key);
        MessageEncryption popUpTimeMultiple = new MessageEncryption(timeMultiple, Main.key);
        MessageEncryption popUpNoOfTrueFalse = new MessageEncryption(noOfTrueFalse, Main.key);
        MessageEncryption popUpTimeTrueFalse = new MessageEncryption(timeTrueFalse, Main.key);

        String encUsername = popUsername.getMessage();
        String encSubject = popUpSubject.getMessage();
        String encTopic = popUpTopic.getMessage();
        String encEveDate = popUpEveDate.getMessage();
        String encNoOfSingle = popUpNoOfSingle.getMessage();
        String encTimeSingle = popUpTimeSingle.getMessage();
        String encNoOfMultiple = popUpNoOfMultiple.getMessage();
        String encTimeMultiple = popUpTimeMultiple.getMessage();
        String encNoOfTrueFalse = popUpNoOfTrueFalse.getMessage();
        String encTimeTrueFalse = popUpTimeTrueFalse.getMessage();

        Main.stringToEcho.writeObject("MessagePopUpTopic");
        Main.stringToEcho.flush();
        MessagePopUpTopic messagePopUpTopic = new MessagePopUpTopic(encUsername, encSubject, encTopic, encEveDate, encNoOfSingle, encTimeSingle, encNoOfMultiple, encTimeMultiple, encNoOfTrueFalse, encTimeTrueFalse);
        Main.stringToEcho.writeObject(messagePopUpTopic);
        Main.stringToEcho.flush();
        System.out.println("Topic popUpmessage sent");

        Message message;
        MessageDecryption mess;
        String plainMessage = "";
        try {
            message = (Message) Main.echoes.readObject();
            mess = new MessageDecryption(message.getMessage(), Main.key);
            plainMessage = mess.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (plainMessage.equals("New topic has been added successfully")) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "New Topic added ", topic + " has been added successfully");
            //clearEntries();
            if (event.getSource() == saveButton) {
                Stage stagePopUp = (Stage) saveButton.getScene().getWindow();
                stagePopUp.close();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/company/fxml/addQuestionsFromTeacher.fxml"));
                    Stage teacherIndexStage = new Stage();
                    teacherIndexStage.setScene(new Scene(root));
                    teacherIndexStage.getIcons().add(new Image("/com/company/icons/icon.png"));
                    teacherIndexStage.initModality(Modality.APPLICATION_MODAL);
                    teacherIndexStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Failed to add new topic", "Check you entries and try again.");
        }
    }

    private boolean checkNumber(String str) {
        int flag = 1;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                flag = 0;
        }
        return flag == 1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
