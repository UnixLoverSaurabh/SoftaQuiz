package com.company;

import javax.crypto.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
            System.out.println("Key has been sent to client");


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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
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