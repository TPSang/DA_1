/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.tienich;

import java.io.File;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class GuiMail {

    public static void SendMail(String mail) {

        final String username = "haigb01@gmail.com";
        final String password = "egbmmuksiedxyhps";

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

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("haigb01@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail)
            );
            message.setSubject("Hóa đơn");
            //
            MimeBodyPart filePart = new MimeBodyPart();
            File file = new File("./pdf.pdf");
            FileDataSource fds = new FileDataSource(file);
            filePart.setDataHandler(new DataHandler(fds));
            filePart.setFileName(file.getName());
            //
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(filePart);
            //
            message.setContent(multiPart);
//            Transport.send(message);
            MailSender.queue(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

