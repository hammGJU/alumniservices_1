/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hesham
 */
public class SendEmail implements Serializable {

    private final String HOST_KEY = "mail.smtp.host";
    private final String HOST = "smtp.gmail.com";
    private final String AUTH_KEY = "mail.smtp.auth";
    private final String PORT_KEY = "mail.smtp.port";
    private final String PORT = "465";
    private final String SOCKET_FACTORY_CLASS_KEY = "mail.smtp.socketFactory.class";
    private final String SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";
    private final String SOCKET_FACTORY_PORT_KEY = "mail.smtp.socketFactory.port";
    private final String SOCKET_FACTORY_FALLBACK_KEY = "mail.smtp.socketFactory.fallback";

    public void sendEmail(String userName, String password, String fromAdrs,
            String toAdrs, String subject, String message) throws AddressException, MessagingException {

        Properties properties = System.getProperties();
        properties.put(HOST_KEY, HOST);
        properties.put(AUTH_KEY, "true");
        properties.put(PORT_KEY, PORT);
        properties.put(SOCKET_FACTORY_CLASS_KEY, SOCKET_FACTORY_CLASS);
        properties.put(SOCKET_FACTORY_PORT_KEY, PORT);
        properties.put(SOCKET_FACTORY_FALLBACK_KEY, "false");
        Session emailSession = Session.getDefaultInstance(properties, null);
        emailSession.setDebug(true);
        Message emailMessage = new MimeMessage(emailSession);
        InternetAddress fromAddress = new InternetAddress(fromAdrs);
        emailMessage.setFrom(fromAddress);
        InternetAddress toAddress = new InternetAddress(toAdrs);
        emailMessage.setRecipient(Message.RecipientType.TO, toAddress);
        emailMessage.setSubject(subject);
        emailMessage.setContent(message, "text/html");
        Transport trans = emailSession.getTransport("smtp");
        trans.connect(HOST, userName, password);
        trans.sendMessage(emailMessage, emailMessage.getAllRecipients());

    }

}
