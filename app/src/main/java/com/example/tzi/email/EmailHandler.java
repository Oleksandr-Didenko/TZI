package com.example.tzi.email;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.ContentView;

import com.example.tzi.DecriptMessageActivity;
import com.example.tzi.EncriptMessageActivity;
import com.example.tzi.R;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;


public class EmailHandler {
    private static String username;
    private static String password;
    private static String receiver;

    public EmailHandler() { }

    public EmailHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public EmailHandler(String username, String password, String receiver) {
        this.username = username;
        this.password = password;
        this.receiver = receiver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public static void sendMessage(String messageSubject, String messageText){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject(messageSubject);
            message.setText(messageText);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String receiveMessage(String messageSubject, String receiver){
        Properties prop = new Properties();
        prop.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.pop3.socketFactory.fallback", "false");
        prop.put("mail.pop3.socketFactory.port", "995");
        prop.put("mail.pop3.port", "995");
        prop.put("mail.pop3.host", "pop.gmail.com");
        prop.put("mail.store.protocol", "pop3");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Store store = session.getStore("pop3");
            store.connect("pop.gmail.com",username, password);

            Folder emailFolder = store.getFolder("inbox");
            emailFolder.open(Folder.READ_ONLY);


            Message[] messages = emailFolder.getMessages();

            String returnMessage = null;

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];

                System.out.println(message.getSubject().equals(messageSubject) && InternetAddress.
                        toString(message.getRecipients(Message.RecipientType.TO)).equals(receiver));

                if (message.getSubject().equals(messageSubject) && InternetAddress.
                        toString(message.getRecipients(Message.RecipientType.TO)).equals(receiver)) {
                    returnMessage = message.getContent().toString();
                }
            }


            emailFolder.close(false);
            store.close();

            if (returnMessage == null){
                return "Error";
            }
            else return returnMessage;
        }
        catch (NoSuchProviderException e) {e.printStackTrace();}
        catch (MessagingException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        return null;
    }
}
