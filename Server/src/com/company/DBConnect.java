package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBConnect {
    private Statement st;
    private Statement st2;
    private HashMap<Integer, String> A = new HashMap<>();

    public HashMap<Integer, String> getA() {
        return A;
    }

    // Constructor
    public DBConnect() {
        try {
            Connection con;
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softaQuiz", "root", "");
            st = con.createStatement();
            st2 = con.createStatement();

        } catch (Exception e) {
            System.out.println("Error in connecting database " + e);
        }
    }

    public void getData() {
        try {
            ResultSet rs;
            String query = "select * from users";
            rs = st.executeQuery(query);
            System.out.println("Records from Database:");
            while (rs.next()) {
                String name = rs.getString("username");
                String email = rs.getString("email");

                System.out.println("Hello " + name + " your email is " + email);
            }
        } catch (Exception e) {
            System.out.println("No result found");
        }
    }

    // Data for Controller_registration
    public void newUser(String Username, String Email, String Password) {
        try {
            String query = "insert into users(username, email, password) values ('" + Username + "', '" + Email + "', '" + Password + "')";
            st.executeUpdate(query);
            System.out.println("Successfully registered ya bro");
        } catch (Exception e) {
            System.out.println("Unable to register");
        }
    }

    // Validate existing user for Sign Up
    public boolean checkUser(String Username, String Email) {
        try {
            int count = 0;
            ResultSet rs;
            String query = "select * from users where username = '" + Username + "' or email = '" + Email + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ++count;
            }
            System.out.println("Number of users for signUp: " + count);
            return count == 0;
        } catch (Exception e) {
            System.out.println("Unable to check register");
            return false;
        }
    }

    // Validate existing User for Log In
    public boolean checkUserLogIn(String Username, String Password) {
        try {
            int count = 0;
            ResultSet rs;
            String query = "select * from users where username = '" + Username + "' and password = '" + Password + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ++count;
            }
            System.out.println("Number of users for login: " + count);

            return count == 1;
        } catch (Exception e) {
            System.out.println("Unable to check register");
            return false;
        }
    }

    // Data for Inserting and Updating Status of Teacher
    public boolean updateTeacherStatus(String Username, String Name, String Gender, String Dob, String Contact, String Qualification, String College, String Optional) {
        try {
            int count_row = 0;
            ResultSet rs;
            String query = "select * from users where username = '" + Username + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ++count_row;
            }
            if (count_row != 0) {
                System.out.println(count_row);
                String query2 = "update users SET name = '" + Name + "', gender = '" + Gender + "', dob = '" + Dob + "', contact = '" + Contact + "' , qualification = '" + Qualification + "', college = '" + College + "', optional = '" + Optional + "' where username = '" + Username + "'";
                st.executeUpdate(query2);
                System.out.println("Status Successfully updated");
                return true;
            } else {
                String query1 = "insert into users(username, name, gender, dob, contact, qualification, college, optional) values ('" + Username + "', '" + Name + "', '" + Gender + "', '" + Contact + "', '" + Qualification + "', '" + College + "', '" + Optional + "' )";
                st.executeUpdate(query1);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Unable to update teacher status");
            return false;
        }
    }

    public ArrayList returnStatusTeacher(String Username) {
        try {
            ResultSet rs;
            String query = "select * from users where username = '" + Username + "'";
            rs = st.executeQuery(query);
            System.out.println("Records from Database for Teacher status update:");
            ArrayList<String> list = new ArrayList<String>();
            while (rs.next()) {
                String name = rs.getString("username");
                System.out.println(name);
//                String email = rs.getString("name");
//                String name = rs.getString("gender");
//                String email = rs.getString("dob");
//                String name = rs.getString("contact");
//                String email = rs.getString("qualification");
//                String name = rs.getString("college");
//                String email = rs.getString("optional");
                list.add(rs.getString("username"));
                list.add(rs.getString("name"));
                list.add(rs.getString("gender"));
                list.add(rs.getString("dob"));
                list.add(rs.getString("contact"));
                list.add(rs.getString("qualification"));
                list.add(rs.getString("college"));
                list.add(rs.getString("optional"));
            }
            return list;
        } catch (Exception e) {
            ArrayList<String> list = new ArrayList<String>();
            System.out.println("No result found");
            list.add(" ");
            return list;
        }
    }

    public void insertNewSubject(String Username, String Subject) {
        try {
            int count_row = 0;
            ResultSet rs;
            String query = "select * from teachers where username = '" + Username + "' and subject = '" + Subject + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ++count_row;
            }
            if (count_row == 0) {
                String query2 = "insert into teachers(username, subject) values ('" + Username + "', '" + Subject + "')";
                st.executeUpdate(query2);
                System.out.println("Successfully added new subject");
            }
        } catch (Exception e) {
            System.out.println("Unable to add new subject");
        }
    }

    public int getIdFromTeacher(String Username, String Subject) {
        try {
            int count_row = 0;
            int returnId = 0;
            ResultSet rs;
            String query = "select * from teachers where username = '" + Username + "' and subject = '" + Subject + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ++count_row;
                returnId = rs.getInt("id");
            }
            if (count_row == 1) {
                System.out.println("Successfully returned the id " + returnId);
                return returnId;
            } else
                return 0;
        } catch (Exception e) {
            System.out.println("Unable to return id");
            e.printStackTrace();
            return 0;
        }
    }

    public int getNewIdFromTopics(int Id, String Topic) {
        try {
            int count_row = 0;
            int returnNewId = 0;
            ResultSet rs;
            String query = "select * from topics where id = " + Id + " and topic = '" + Topic + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ++count_row;
                returnNewId = rs.getInt("newId");
            }
            if (count_row == 1) {
                System.out.println("Successfully returned the id " + returnNewId);
                return returnNewId;
            } else
                return 0;
        } catch (Exception e) {
            System.out.println("Unable to return id");
            e.printStackTrace();
            return 0;
        }
    }

    public void insertNewTopic(int Id, String Topic, String Date, String NoOfSingleCorrect, String SingleTimeLimit, String NoOfMultipleCorrect, String MultipleTimeLimit, String NoOfTrueFalse, String TrueFalseTimeLimit) {
        try {
            String query = "insert into topics(id, topic, topicDate, single, singleTime, multiple, multipleTime, trueFalse, trueFalseTime) values (" + Id + ", '" + Topic + "', '" + Date + "', '" + NoOfSingleCorrect + "', '" + SingleTimeLimit + "', '" + NoOfMultipleCorrect + "', '" + MultipleTimeLimit + "', '" + NoOfTrueFalse + "', '" + TrueFalseTimeLimit + "' )";
            st.executeUpdate(query);
            System.out.println("Successfully added new subject");
        } catch (SQLException e1) {
            System.out.println("Unable to add new subject");
        }
    }

    public boolean addQueAnsFromTeacher(String queAnsUsername, String queAnsSubject, String queAnsTopic, String queAnsQuestion, String queAnsOption1, String queAnsOption2, String queAnsOption3, String queAnsOption4, String queAnsAnswer, String queAnsType) {
        int id = getIdFromTeacher(queAnsUsername, queAnsSubject);
        if (id != 0) {
            int newId = getNewIdFromTopics(id, queAnsTopic);
            if (newId != 0) {
                if ("singleCorrect".equals(queAnsType)) {
                    try {
                        String query = "insert into singleCorr(newId, question, option1, option2, option3, option4, answer) values (" + newId + ", '" + queAnsQuestion + "', '" + queAnsOption1 + "', '" + queAnsOption2 + "', '" + queAnsOption3 + "', '" + queAnsOption4 + "', '" + queAnsAnswer + "' )";
                        st.executeUpdate(query);
                        System.out.println("Successfully added this question");
                        return true;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        System.out.println("Unable to add this question");
                        return false;
                    }
                } else if ("multipleCorrect".equals(queAnsType)) {
                    try {
                        String query = "insert into multipleCorr(newId, question, option1, option2, option3, option4, answer) values (" + newId + ", '" + queAnsQuestion + "', '" + queAnsOption1 + "', '" + queAnsOption2 + "', '" + queAnsOption3 + "', '" + queAnsOption4 + "', '" + queAnsAnswer + "' )";
                        st.executeUpdate(query);
                        System.out.println("Successfully added this question");
                        return true;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        System.out.println("Unable to add this question");
                        return false;
                    }
                } else if ("trueFalse".equals(queAnsType)) {
                    try {
                        String query = "insert into trueFalseCorr(newId, question, option1, option2, option3, option4, answer) values (" + newId + ", '" + queAnsQuestion + "', '" + queAnsOption1 + "', '" + queAnsOption2 + "', '" + queAnsOption3 + "', '" + queAnsOption4 + "', '" + queAnsAnswer + "' )";
                        st.executeUpdate(query);
                        System.out.println("Successfully added this question");
                        return true;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        System.out.println("Unable to add this question");
                        return false;
                    }
                } else {
                    System.out.println("Aisa nahi hoga only three Single Multiple TrueFalse");
                    return false;
                }
            } else {
                System.out.println("Ooops this should not be there fault in getting Newid from table Topics");
                return false;
            }
        } else {
            System.out.println("Ooops this should not be there fault in getting id from table Teachers");
            return false;
        }
        //SELECT id FROM teachers WHERE username = "a" and subject = "a"
        //SELECT newId FROM topics WHERE id = 3 and topic = "as"
    }

    public List<String[]> returnStudentTables() {
        //ObservableList<ObservableList> data = FXCollections.observableArrayList();
        List<String[]> rowList = new ArrayList<>();
        try {
            ResultSet rs;
            String query = "select * from topics";
            rs = st.executeQuery(query);
            System.out.println("Records from table topics:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String topic = rs.getString("topic");
                String topicDate = rs.getString("topicDate");
                int noOfSingleCorr = rs.getInt("single");
                int timeOfSingleCorr = rs.getInt("singleTime");
                int noOfMultipleCorr = rs.getInt("multiple");
                int timeOfMultipleCorr = rs.getInt("multipleTime");
                int noOfTrueFalseCorr = rs.getInt("trueFalse");
                int timeOfTrueFalseCorr = rs.getInt("trueFalseTime");
                int totalQuestion = noOfSingleCorr + noOfMultipleCorr + noOfTrueFalseCorr;
                int totalDuration = timeOfSingleCorr + timeOfMultipleCorr + timeOfTrueFalseCorr;
                String username = null;
                String subject = null;
                try {
                    ResultSet resultSet;
                    String query2 = "select * from teachers where id = "+id+"";
                    resultSet = st2.executeQuery(query2);
                    System.out.println("Records from Database of table teachers:");
                    while(resultSet.next()){
                        username = resultSet.getString("username");
                        subject = resultSet.getString("subject");
                        //System.out.println(username + " " + subject + " " + topic + " " + topicDate );
                    }
                }catch (Exception e){
                    System.out.println("No result found in table teachers for returning student table (error)");
                    rowList.clear();
                    rowList.add(new String[] {"error", "error","error", "error","error", "error"});
                    return rowList;
                }
//                ObservableList<String> row = FXCollections.observableArrayList();
//                row.add(username);
//                row.add(subject);
//                row.add(topic);
//                row.add(topicDate);
//                row.add(String.valueOf(totalDuration));
//                row.add(String.valueOf(totalQuestion));
//                data.add(row);
                rowList.add(new String[] {username, subject, topic, topicDate, String.valueOf(totalDuration), String.valueOf(totalQuestion)});
            }
            return rowList;
        } catch (Exception e) {
            System.out.println("No result found");
            e.printStackTrace();
            rowList.clear();
            rowList.add(new String[]{"error", "error", "error", "error", "error", "error"});
            return rowList;
        }
    }

    public List<String[]> returnTeacherTables(String Username) {
        List<String[]> rowList = new ArrayList<>();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            String query = "select * from topics";
            rs = st.executeQuery(query);
            System.out.println("Records from table topics:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String topic = rs.getString("topic");
                String topicDate = rs.getString("topicDate");
                String subject = null;
                try {
                    ResultSet resultSet;
                    String query2 = "select * from teachers where id = "+id+" and username = '"+Username+"'";
                    resultSet = st2.executeQuery(query2);
                    //System.out.println("Records from Database of table teachers:");
                    while(resultSet.next()){
                        subject = resultSet.getString("subject");
                        System.out.println("Subject " + subject + " " + topic + " " + topicDate );
                    }
                }catch (Exception e){
                    System.out.println("No result found in table teachers for returning student table (error)");
                    rowList.clear();
                    rowList.add(new String[] {"error", "error","error", "error","error", "error"});
                    return rowList;
                }
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(subject);
                row.add(topic);
                row.add(topicDate);
                row.add("4");
                data.add(row);
                if (subject != null) {
                    rowList.add(new String[] {subject, topic, topicDate, "4"});
                }
            }
            return rowList;
        } catch (Exception e) {
            System.out.println("No result found");
            e.printStackTrace();
            rowList.clear();
            rowList.add(new String[]{"error", "error", "error", "error"});
            return rowList;
        }
    }

    public List<String[]> returnQuestionsOfThisTopic(String TeacherUsername, String Subject, String Topic) {
        int count_row = 0;
        int idOfTopic = 0;
        int questionNo = 0;
        List<String[]> rowList = new ArrayList<>();
        try {
            ResultSet resultSet;
            String query = "SELECT newId FROM topics INNER JOIN teachers ON topics.id = teachers.id WHERE teachers.username = '"+TeacherUsername+"' AND teachers.subject = '"+Subject+"' AND topics.topic = '"+Topic+"'";
            resultSet = st.executeQuery(query);
            System.out.println("Records to give questions on this topic:");
            while(resultSet.next()){
                ++count_row;
                idOfTopic = resultSet.getInt("newId");
            }
            if (count_row == 1) {
                System.out.println("Successfully returned the id " + idOfTopic);
                try {
                    ResultSet rs;
                    String query2 = "select * from singleCorr where newId = "+idOfTopic+"";
                    rs = st2.executeQuery(query2);
                    System.out.println("Records from Database of table singleCorr:");
                    while (rs.next()) {
                        String Question = rs.getString("question");
                        String Option1 = rs.getString("option1");
                        String Option2 = rs.getString("option2");
                        String Option3 = rs.getString("option3");
                        String Option4 = rs.getString("option4");
                        rowList.add(new String[] {Question, Option1, Option2, Option3, Option4});
                        A.put(++questionNo, rs.getString("answer"));
                    }
                } catch (Exception e) {
                    System.out.println("No result found");
                    e.printStackTrace();
                    rowList.clear();
                    rowList.add(new String[] {"error", "error","error", "error","error"});
                    return rowList;
                }
                try {
                    ResultSet rs;
                    String query2 = "select * from multipleCorr where newId = "+idOfTopic+"";
                    rs = st2.executeQuery(query2);
                    System.out.println("Records from Database of table singleCorr:");
                    while (rs.next()) {
                        String Question = rs.getString("question");
                        String Option1 = rs.getString("option1");
                        String Option2 = rs.getString("option2");
                        String Option3 = rs.getString("option3");
                        String Option4 = rs.getString("option4");
                        rowList.add(new String[] {Question, Option1, Option2, Option3, Option4});
                        A.put(++questionNo, rs.getString("answer"));
                    }
                } catch (Exception e) {
                    System.out.println("No result found");
                    e.printStackTrace();
                    rowList.clear();
                    rowList.add(new String[] {"error", "error","error", "error","error"});
                    return rowList;
                }
                try {
                    ResultSet rs;
                    String query2 = "select * from trueFalseCorr where newId = "+idOfTopic+"";
                    rs = st2.executeQuery(query2);
                    System.out.println("Records from Database of table singleCorr:");
                    while (rs.next()) {
                        String Question = rs.getString("question");
                        String Option1 = rs.getString("option1");
                        String Option2 = rs.getString("option2");
                        String Option3 = rs.getString("option3");
                        String Option4 = rs.getString("option4");
                        rowList.add(new String[] {Question, Option1, Option2, Option3, Option4});
                        A.put(++questionNo, rs.getString("answer"));
                    }
                } catch (Exception e) {
                    System.out.println("No result found");
                    e.printStackTrace();
                    rowList.clear();
                    rowList.add(new String[] {"error", "error","error", "error","error"});
                    return rowList;
                }
            } else{
                System.out.println("Oops not able to get a unique id for this topic to give Questions (My code error for database)");
                rowList.clear();
                rowList.add(new String[] {"error", "error","error", "error","error"});
                return rowList;
            }
        }catch (Exception e){
            System.out.println("No result found to give Questions (error)");
            rowList.clear();
            rowList.add(new String[] {"error", "error","error", "error","error"});
            return rowList;
        }
        //SELECT newId FROM topics INNER JOIN teachers ON topics.id = teachers.id WHERE teachers.username = 'a' AND teachers.subject = 'a' AND topics.topic = 'a'
        return rowList;
    }
}
