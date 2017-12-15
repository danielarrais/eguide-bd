/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.util.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {

    private static final String SERVIDOR_SMTP = "smtp.gmail.com";
    private static final String PORTA_SERVIDOR_SMTP = "465";
    private static final String EMAIL = "danielarraiscarvalho@gmail.com";
    private static final String SENHA = "";

    public void enviarEmail(String assunto, String mensagem, String email) throws AddressException, MessagingException {
        Autenticacao autenticacao = new Autenticacao(EMAIL, SENHA);

        Session session = Session.getDefaultInstance(getProperties(), autenticacao);
        session.setDebug(true);

        MimeMessage mail = new MimeMessage(session);

        mail.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mail.setFrom(new InternetAddress(EMAIL));
        mail.setSubject(assunto);
        mail.setContent(mensagem, "text/plain");
        mail.setSentDate(new Date(System.currentTimeMillis()));

        Transport envio = session.getTransport("smtp");
        envio.connect(SERVIDOR_SMTP, EMAIL, SENHA);
        mail.saveChanges();

        envio.sendMessage(mail, mail.getAllRecipients());
        envio.close();
    }

    public static Properties getProperties() {
        Properties propriedades = new Properties();
        propriedades.put("mail.transport.protocol", "smtp");
        propriedades.put("mail.smtp.starttls.enable", "true");
        propriedades.put("mail.smtp.host", SERVIDOR_SMTP);
        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.user", EMAIL);
        propriedades.put("mail.debug", "true");
        propriedades.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        propriedades.put("mail.smtp.socketFactory.port", PORTA_SERVIDOR_SMTP);
        propriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propriedades.put("mail.smtp.socketFactory.fallback", "false");
        propriedades.put("mail.smtp.ssl.enable", "true");

        return propriedades;
    }
}
