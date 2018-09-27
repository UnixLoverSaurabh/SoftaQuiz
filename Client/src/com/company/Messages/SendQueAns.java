package com.company.Messages;

import com.company.Main;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SendQueAns {
    public SendQueAns(String username, String subject, String topic, String question, String option1, String option2, String option3, String option4, String answer, String type) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        MessageEncryption popUpUsername = new MessageEncryption(username, Main.key);

        System.out.println("**********************************************************");
        System.out.println("Subject " + subject + " and Topic " + topic);
        MessageEncryption popUpSubject = new MessageEncryption(subject, Main.key);
        MessageEncryption popUpTopic = new MessageEncryption(topic, Main.key);
        MessageEncryption popUpquestion = new MessageEncryption(question, Main.key);
        MessageEncryption popUpoption1 = new MessageEncryption(option1, Main.key);
        MessageEncryption popUpoption2 = new MessageEncryption(option2, Main.key);
        MessageEncryption popUpoption3 = new MessageEncryption(option3, Main.key);
        MessageEncryption popUpoption4 = new MessageEncryption(option4, Main.key);
        MessageEncryption popUpanswer = new MessageEncryption(answer, Main.key);
        MessageEncryption popUpType = new MessageEncryption(type, Main.key);

        String encUsername = popUpUsername.getMessage();
        String encSubject = popUpSubject.getMessage();
        String encTopic = popUpTopic.getMessage();
        String encQuestion = popUpquestion.getMessage();
        String encOption1 = popUpoption1.getMessage();
        String encOption2 = popUpoption2.getMessage();
        String encOption3 = popUpoption3.getMessage();
        String encOption4 = popUpoption4.getMessage();
        String encAnswer = popUpanswer.getMessage();
        String enctype = popUpType.getMessage();

        Main.stringToEcho.writeObject("TeacherQueAns");
        Main.stringToEcho.flush();
        TeacherQueAns messagePopUpTopic = new TeacherQueAns(encUsername, encSubject, encTopic, encQuestion, encOption1, encOption2, encOption3, encOption4, encAnswer, enctype);
        Main.stringToEcho.writeObject(messagePopUpTopic);
        Main.stringToEcho.flush();
        System.out.println("Question and Answers are sent");
    }
}
