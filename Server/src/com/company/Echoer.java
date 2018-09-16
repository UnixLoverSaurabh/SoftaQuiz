package com.company;

import com.company.Messages.*;

import javax.crypto.*;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            DBConnect connect = new DBConnect();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // get a AES private key  (Generates the key)
            System.out.println("\nStart generating AES key");
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);    //In AES cipher block size is 128 bits
            Key key = keyGen.generateKey();
            System.out.println("Finish generating AES key");

            Frame frame = new Frame();
            frame.key = key;
            output.writeObject(frame);
            output.flush();
            System.out.println("Key has been sent to client");

            MessageDecryption username;
            MessageDecryption email;
            MessageDecryption password;
            try {
                boolean val = true;
                while (val) {
                    MessageAuth messageFirstPacket = (MessageAuth) input.readObject();
                    username = new MessageDecryption(messageFirstPacket.getUsername(), key);
                    email = new MessageDecryption(messageFirstPacket.getEmail(), key);
                    password = new MessageDecryption(messageFirstPacket.getPassword(), key);
                    System.out.println("Username: " + username.getMessage());
                    System.out.println("Email: " + email.getMessage());
                    System.out.println("Password : " + password.getMessage());

                    MessageEncryption messEnc;
                    // Validating for Sign Up
                    if (!email.getMessage().equals("NoNeedLoginEmail")) {
                        if (!connect.checkUser(username.getMessage(), email.getMessage())) {
                            messEnc = new MessageEncryption("Username or email already exists.", key);
                            System.out.println("Username or email already exists. Form Error!");
                        } else {
                            messEnc = new MessageEncryption("Registration Successful!", key);
                            System.out.println("Registration Successful!");
                            connect.newUser(username.getMessage(), email.getMessage(), password.getMessage());
                            val = false;
                        }
                    } // Validating for Sign In
                    else {
                        if (connect.checkUserLogIn(username.getMessage(), password.getMessage())) {
                            messEnc = new MessageEncryption("Not a valid combination", key);
                            System.out.println("Not a valid combination. Form Error!");
                        } else {
                            messEnc = new MessageEncryption("Login Successful!", key);
                            System.out.println("Login Successful!");
                            val = false;
                        }
                    }
                    Message messageSend = new Message(messEnc.getMessage(), "server", username.getMessage(), key);
                    output.writeObject(messageSend);
                    output.flush();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            while (true) {
                Message message = null;
                MessageDecryption mess = null;
                String plainMessage = "";
                try {
                    message = (Message) input.readObject();
                    mess = new MessageDecryption(message.getMessage(), key);
                    plainMessage = mess.getMessage();
                    if (plainMessage.equals("exit")) {
                        socket.close();
                        break;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Received client input : ");
                System.out.println(plainMessage + " FROM " + message.getFrom());

                MessageEncryption messEnc = new MessageEncryption("Server thik chal raha hai", key);
                Message messageSend = new Message(messEnc.getMessage(), "server", message.getFrom(), key);
                output.writeObject(messageSend);
                output.flush();
            }
        } catch (IOException e){
            System.out.println("Ooops : " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e){
                System.out.println("Error in closing socket");
            }
        }
    }
}