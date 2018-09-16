package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
    private Statement st;

    // Constructor
    public DBConnect(){
        try{
            Connection con;
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root", "");
            st = con.createStatement();

        }catch (Exception e){
            System.out.println("Error in connecting database " + e);
        }
    }

    public void getData(){
        try {
            ResultSet rs;
            String query = "select * from users";
            rs = st.executeQuery(query);
            System.out.println("Records from Database:");
            while(rs.next()){
                String name = rs.getString("username");
                String email = rs.getString("email");

                System.out.println("Hello "+ name + " your email is " + email);
            }
        }catch (Exception e){
            System.out.println("No result found");
        }
    }

    // Data for Controller_registration
    public void newUser(String Username, String Email, String Password){
        try {
            String query = "insert into users(username, email, password) values ('"+Username+"', '"+Email+"', '"+Password+"')";
            st.executeUpdate(query);
            System.out.println("Successfully registered");
        }catch (Exception e){
            System.out.println("Unable to register");
        }
    }
    // Validate existing user for Sign Up
    public boolean checkUser(String Username, String Email){
        try {
            int count_row = 0;
            ResultSet rs;
            String query = "select * from users where username = '"+Username+"' and email = '"+Email+"'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                count_row = rs.getInt(1);
            }
            if (count_row != 0) {
                System.out.println(count_row);
                return false;
            }
            else
                return true;
        }catch (Exception e){
            System.out.println("Unable to check register");
            return false;
        }
    }

    // Validate existing User for Log In
    public boolean checkUserLogIn(String Username, String Password) {
        try {
            int count_row = 0;
            ResultSet rs;
            String query = "select * from users where username = '"+Username+"' and email = '"+Password+"'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                count_row = rs.getInt(1);
            }
            if (count_row != 0) {
                System.out.println(count_row);
                return false;
            }
            else
                return true;
        }catch (Exception e){
            System.out.println("Unable to check register");
            return false;
        }
    }
}
