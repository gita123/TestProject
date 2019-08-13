package uk.gov.ho.domain.component.ui.pages;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.*;

public class ReadEmailData {
    public static String userPassword;

    public static String check(String host, String storeType, String user, String password) {
        try {
            String emailSubject;

            //create properties field
            Properties properties = new Properties();
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("imaps");
            store.connect("imap.googlemail.com", 993, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.search(
                    new FlagTerm(new Flags(Flags.Flag.RECENT), false));
            System.out.println("messages.length---" + messages.length);

            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getSentDate().compareTo(m1.getSentDate());
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                emailSubject = message.getSubject();
//                System.out.println("---------------------------------");
//                System.out.println("Email Number " + (i));
//                System.out.println("Subject: " + message.getSubject());
//                System.out.println("From: " + message.getFrom()[0]);
//                System.out.println("Text: " + message.getContent().toString());
//                System.out.println("email subject is :: " + emailSubject);
                if (emailSubject.equalsIgnoreCase("Sponsor application registration")) {
                    System.out.println("message number is : " + i);
                    String emailText = message.getContent().toString();
                    String[] emailContentArr = emailText.split("Your password is: ");
                    String[] passwordContent = emailContentArr[1].split(". If");
                    userPassword = passwordContent[0];
                    System.out.println("Password is :" + passwordContent[0]+"hello");
                    return userPassword;
                }
                System.out.println("hello ***");
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userPassword;
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "pbs.inttesting@gmail.com";// change accordingly
        String password = "P1ssword!";// change accordingly

        check(host, mailStoreType, username, password);

    }

}

