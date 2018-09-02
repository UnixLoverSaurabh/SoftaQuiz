package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        try(Socket socket = new Socket("localhost", 5700)){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name of client.");
            String username = scanner.nextLine();


            new Main().sendMessage(socket, scanner, username);
        }
        catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }
    }

    private void sendMessage(Socket socket, Scanner scanner, String username) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        ObjectOutputStream stringToEcho = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream echoes = new ObjectInputStream(socket.getInputStream());

        Frame frame = null;
        try {
            frame = (Frame) echoes.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Key key = frame.key;

        while (true) {
            System.out.println("Enter message");
            String text = scanner.nextLine();
            MessageEncryption mess = new MessageEncryption(text, key);
            String cipherText = mess.getMessage();
            String from = "Client - " + username;
            Message message = new Message(cipherText, from, "Server", key);
            stringToEcho.writeObject(message);
            stringToEcho.flush();

            Message messageServer;
            try {
                messageServer = (Message) echoes.readObject();
                System.out.println("Received server input : ");
                MessageDecryption messDec = new MessageDecryption(messageServer.getMessage(), key);
                System.out.println(messDec.getMessage() + "FROM " + messageServer.getFrom());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}