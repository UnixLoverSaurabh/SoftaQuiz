package com.company.Controllers;

import com.company.Main;
import com.company.Messages.Message;
import com.company.Messages.MessageDecryption;
import com.company.Messages.SendQueAns;
import com.company.alertBox.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Controller_addQuestionsFromTeacher {

    @FXML
    private TextArea singleCorrectQuestion;
    @FXML
    private RadioButton singleCorrectA;
    @FXML
    private RadioButton singleCorrectB;
    @FXML
    private RadioButton singleCorrectC;
    @FXML
    private RadioButton singleCorrectD;
    @FXML
    private TextField singleCorrectOption1;
    @FXML
    private TextField singleCorrectOption2;
    @FXML
    private TextField singleCorrectOption3;
    @FXML
    private TextField singleCorrectOption4;

    @FXML
    private Button singleCorrectSubmit;
    @FXML
    private Label singleCorrectLabel;
    @FXML
    private Label singleCorrectHeading;

    @FXML
    private TextArea multipleCorrectQuestion;
    @FXML
    private RadioButton multipleCorrectA;
    @FXML
    private RadioButton multipleCorrectB;
    @FXML
    private RadioButton multipleCorrectC;
    @FXML
    private RadioButton multipleCorrectD;
    @FXML
    private TextField multipleCorrectOption1;
    @FXML
    private TextField multipleCorrectOption2;
    @FXML
    private TextField multipleCorrectOption3;
    @FXML
    private TextField multipleCorrectOption4;
    @FXML
    private Button multipleCorrectSubmit;
    @FXML
    private Label multipleCorrectLabel;
    @FXML
    private Label multipleCorrectHeading;

    @FXML
    private TextArea trueFalseQuestion;
    @FXML
    private RadioButton trueFalseA;
    @FXML
    private RadioButton trueFalseB;
    @FXML
    private TextField trueFalseOption1;
    @FXML
    private TextField trueFalseOption2;
    @FXML
    private Button trueFalseSubmit;
    @FXML
    private Label trueFalseLabel;
    @FXML
    private Label trueFalseHeading;

    private int noOfQuestionSingleCorrect = 1;
    private int noOfQuestionMultipleCorrect = 1;
    private int noOfQuestionTrueFalse = 1;

    @FXML
    protected void handleSingleCorrectSubmit(ActionEvent event) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, IOException {
        singleCorrectHeading.setText("Question " + (noOfQuestionSingleCorrect+1));
        String answer = "";
        String question = singleCorrectQuestion.getText();
        singleCorrectQuestion.clear();
        if (singleCorrectA.isSelected()) {
            answer += singleCorrectA.getText();
        }
        if (singleCorrectB.isSelected()) {
            answer += singleCorrectB.getText();
        }
        if (singleCorrectC.isSelected()) {
            answer += singleCorrectC.getText();
        }
        if (singleCorrectD.isSelected()) {
            answer += singleCorrectD.getText();
        }
        singleCorrectLabel.setText("Answer " + answer + " is submitted with Question");
        String opt1 = singleCorrectOption1.getText();
        singleCorrectOption1.clear();
        String opt2 = singleCorrectOption2.getText();
        singleCorrectOption2.clear();
        String opt3 = singleCorrectOption3.getText();
        singleCorrectOption3.clear();
        String opt4 = singleCorrectOption4.getText();
        singleCorrectOption4.clear();

        new SendQueAns(Main.sessionUsername, Controller_PopUpTopic.getSubject(), Controller_PopUpTopic.getTopic(), question, opt1, opt2, opt3, opt4, answer, "singleCorrect");
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
        Window owner = singleCorrectSubmit.getScene().getWindow();
        if (plainMessage.equals("Question added successfully")) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Question added successfully !", "Answer " + answer + " is submitted with Question");
        } else {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Question not added !", "Answer " + answer + " is has not been submitted");
        }

        noOfQuestionSingleCorrect ++;
        if (noOfQuestionSingleCorrect > Integer.parseInt(Controller_PopUpTopic.getNoOfSingle())){
            singleCorrectSubmit.setDisable(true);
        }
    }
    @FXML
    protected void handleMultipleCorrectSubmit(ActionEvent event) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, IOException {
        multipleCorrectHeading.setText("Question " + (noOfQuestionMultipleCorrect+1));
        String answer = "";
        String question = multipleCorrectQuestion.getText();
        System.out.println(question);
        if (multipleCorrectA.isSelected()) {
            answer += singleCorrectA.getText();
        }
        if (multipleCorrectB.isSelected()) {
            answer += singleCorrectB.getText();
        }
        if (multipleCorrectC.isSelected()) {
            answer += singleCorrectC.getText();
        }
        if (multipleCorrectD.isSelected()) {
            answer += singleCorrectD.getText();
        }
        multipleCorrectLabel.setText("Answer " + answer + " is submitted with Question");
        String opt1 = multipleCorrectOption1.getText();
        multipleCorrectOption1.clear();
        String opt2 = multipleCorrectOption2.getText();
        multipleCorrectOption2.clear();
        String opt3 = multipleCorrectOption3.getText();
        multipleCorrectOption3.clear();
        String opt4 = multipleCorrectOption4.getText();
        multipleCorrectOption4.clear();

        new SendQueAns(Main.sessionUsername, Controller_PopUpTopic.getSubject(), Controller_PopUpTopic.getTopic(), question, opt1, opt2, opt3, opt4, answer, "multipleCorrect");
        Message messageMultiple;
        MessageDecryption mess;
        String plainMessage = "";
        try {
            messageMultiple = (Message) Main.echoes.readObject();
            mess = new MessageDecryption(messageMultiple.getMessage(), Main.key);
            plainMessage = mess.getMessage();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        Window owner = multipleCorrectSubmit.getScene().getWindow();
        if (plainMessage.equals("Question added successfully")) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Question added successfully !", "Answer " + answer + " is submitted with Question");
        } else {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Question not added !", "Answer " + answer + " is has not been submitted");
        }

        noOfQuestionMultipleCorrect ++;
        if (noOfQuestionMultipleCorrect > Integer.parseInt(Controller_PopUpTopic.getNoOfMultiple())){
            multipleCorrectSubmit.setDisable(true);
        }
    }
    @FXML
    protected void handleTrueFalseSubmit(ActionEvent event) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, IOException {
        trueFalseHeading.setText("Question " + (noOfQuestionTrueFalse+1));
        String answer = "";
        String question = trueFalseQuestion.getText();
        System.out.println(question);
        if (trueFalseA.isSelected()) {
            answer += singleCorrectA.getText();
        }
        if (trueFalseB.isSelected()) {
            answer += singleCorrectB.getText();
        }
        trueFalseLabel.setText("Answer " + answer + " is submitted with Question");
        String opt1 = trueFalseOption1.getText();
        trueFalseOption1.clear();
        String opt2 = trueFalseOption2.getText();
        trueFalseOption2.clear();
        String opt3 = "ZZ";
        String opt4 = "ZZ";

        new SendQueAns(Main.sessionUsername, Controller_PopUpTopic.getSubject(), Controller_PopUpTopic.getTopic(), question, opt1, opt2, opt3, opt4, answer, "trueFalse");

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
        Window owner = trueFalseSubmit.getScene().getWindow();
        if (plainMessage.equals("Question added successfully")) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Question added successfully !", "Answer " + answer + " is submitted with Question");
        } else {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Question not added !", "Answer " + answer + " is has not been submitted");
        }

        noOfQuestionTrueFalse ++;
        if (noOfQuestionTrueFalse > Integer.parseInt(Controller_PopUpTopic.getNoOfTrueFalse())){
            trueFalseSubmit.setDisable(true);
        }
    }
}
