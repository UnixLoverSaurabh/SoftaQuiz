package com.company.Controllers;

import com.company.Main;
import com.company.Messages.GiveQuestions;
import com.company.Messages.TablesOfStudent;
import com.company.Model.Students;
import com.company.Model.StudentsFirstTable;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Controller_Index_Student {
    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private JFXHamburger hamburger1;

    @FXML
    private JFXDrawer drawer1;

    @FXML
    private TableView<StudentsFirstTable> tbData1;

    @FXML
    public TableColumn<StudentsFirstTable, String> teacherColumn1;

    @FXML
    public TableColumn<StudentsFirstTable, String> subjectColumn1;

    @FXML
    public TableColumn<StudentsFirstTable, String> topicColumn1;

    @FXML
    public TableColumn<StudentsFirstTable, String> dateColumn1;

    @FXML
    public TableColumn<StudentsFirstTable, String> durationColumn1;

    @FXML
    public TableColumn<StudentsFirstTable, String> noOfQuesColumn1;

    @FXML
    private TableView<Students> tbData2;

    @FXML
    public TableColumn<Students, String> subjectColumn2;

    @FXML
    public TableColumn<Students, String> topicColumn2;

    @FXML
    public TableColumn<Students, String> dateColumn2;

    private List<String[]> data1;

    private ObservableList<StudentsFirstTable> Data1;

    private ObservableList<Students> Data2;

    private Button [] button;

    private static String noOfQuestions;
    private static String Duration;

    public static String getNoOfQuestions() {
        return noOfQuestions;
    }

    public static String getDuration() {
        return Duration;
    }

    private void setNumberOfButtons(int num) {
        button = new Button[num];
    }

    private void handleButtonAction(ActionEvent event) {
        for (int i = 0; i < button.length; i++) {
            if(event.getSource() == button[i]) {
                System.out.println(" Button " + (i + 1) + " Pressed");

                // send the packet to server to receive questions on this topic to next student fxml page
                try {
                    sendPacketOfIThRowTopic(i);
                    Stage studentIndexStage = (Stage) anchorpane1.getScene().getWindow();
                    studentIndexStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/company/fxml/QuestionsToStudents.fxml"));
                    Stage studentQuestionsStage = new Stage();
                    studentQuestionsStage.setScene(new Scene(root, 1000, 600));
                    studentQuestionsStage.getIcons().add(new Image("/com/company/icons/icon.png"));
                    studentQuestionsStage.initModality(Modality.APPLICATION_MODAL);
                    studentQuestionsStage.show();
                } catch (IOException e) {
                    System.out.println("Cound not send request to receive Questions packet of this topic");
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendPacketOfIThRowTopic(int i) throws IOException {
        System.out.println(Arrays.toString(data1.get(i)));
        Duration = data1.get(i)[5];
        noOfQuestions = data1.get(i)[5];
        Main.stringToEcho.writeObject("GiveQuestions");
        Main.stringToEcho.flush();
        GiveQuestions giveQuestions = new GiveQuestions(Main.sessionUsername,data1.get(i)[0], data1.get(i)[1], data1.get(i)[2]);
        Main.stringToEcho.writeObject(giveQuestions);
        Main.stringToEcho.flush();
    }

    private void insertTab(List<String[]> data1) {
        Data1 = FXCollections.observableArrayList();
        for (int i = 0; i < data1.size(); i++) {
            Data1.add(new StudentsFirstTable(data1.get(i)[0], data1.get(i)[1], button[i], data1.get(i)[3], data1.get(i)[4], data1.get(i)[5], data1.get(i)[2]));
        }
    }

    private void insertTab2(List<String[]> data2) {
        Data2 = FXCollections.observableArrayList();
        for (int i = 0; i < data2.size(); i++) {
            Data2.add(new Students(data2.get(i)[0], data2.get(i)[1], data2.get(i)[2]));
        }
    }

    private ObservableList<Students> student = FXCollections.observableArrayList(
            new Students("Structure", "Amos", "Chepchieng"),
            new Students("Data Structure", "Amos", "Mors"),
            new Students("Data Structure", "Amos",  "Data "),
            new Students("Data Structure", "Amos",  "Data ")
    );

    private void loadTable1(ObservableList<StudentsFirstTable> studentsFirstTable) {
        teacherColumn1.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectColumn1.setCellValueFactory(new PropertyValueFactory<>("subject"));
        topicColumn1.setCellValueFactory(new PropertyValueFactory<>("button"));
        dateColumn1.setCellValueFactory(new PropertyValueFactory<>("eventdate"));
        durationColumn1.setCellValueFactory(new PropertyValueFactory<>("duration"));
        noOfQuesColumn1.setCellValueFactory(new PropertyValueFactory<>("noOfQuestion"));
        tbData1.setItems(studentsFirstTable);
    }

    private void loadTable2(ObservableList<Students> student) {
        subjectColumn2.setCellValueFactory(new PropertyValueFactory<>("subject"));
        topicColumn2.setCellValueFactory(new PropertyValueFactory<>("topic"));
        dateColumn2.setCellValueFactory(new PropertyValueFactory<>("eventdate"));
        tbData2.setItems(student);
    }

    @FXML
    public void initialize() throws IOException {
        Main.stringToEcho.writeObject("StudentIndexPage");
        Main.stringToEcho.flush();

        VBox box1 = FXMLLoader.load(getClass().getResource("/com/company/fxml/DrawerContent.fxml"));

        //StudentTable string message is sent after DrawerContent so that status is dumped after calling initialization of DrawerContent
        Main.stringToEcho.writeObject("StudentTable");
        Main.stringToEcho.flush();
        TablesOfStudent tablesOfStudent;
        try {
            tablesOfStudent = (TablesOfStudent) Main.echoes.readObject();
            System.out.println("Received server input for Table Update: ");
            data1 = tablesOfStudent.getData1();
            setNumberOfButtons(data1.size());
            for(int i = 0; i<button.length ; i++) {
                button[i] = new Button();
                button[i].setOnAction(this::handleButtonAction);
            }
            insertTab(data1);
            loadTable1(Data1);
            List<String[]> data2 = tablesOfStudent.getData2();
            insertTab2(data2);
            loadTable2(Data2);
        } catch (ClassNotFoundException e) {
            System.out.println("Error receiving the tables of student");
            e.printStackTrace();
        }

        drawer1.setSidePane(box1);
        for (Node node : box1.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "Material_Six": {
                            anchorpane1.setBackground(new Background(new BackgroundFill(Paint.valueOf("orange"), CornerRadii.EMPTY, Insets.EMPTY)));
                            break;
                        }
                    }
                });
            }
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger1);
        transition.setRate(-1);
        hamburger1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer1.isOpened()) {
                drawer1.close();
            } else {
                drawer1.open();
            }
        });
    }
}
