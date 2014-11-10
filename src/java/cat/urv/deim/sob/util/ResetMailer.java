/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.util;

import cat.urv.deim.sob.command.ForgotPassCommand;
import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class ResetMailer {

    public HttpServletResponse sendEmail(String dest, String username, String url, HttpServletResponse response) throws SOBException {
        // Get system properties
        Properties properties = new Properties();
        // Setup smtp mail server properties
        properties.put("mail.smtp.user", Config.DEFAULT_MAIL_ADDR);
        properties.put("mail.smtp.host", Config.HOST);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", Config.DEFAULT_MAIL_PORT);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        // Get the custom authentificator object
        SmtpAuthenticator authenticator = new SmtpAuthenticator();
        // Get the default mail Session object
        Session session = Session.getInstance(properties, authenticator);
        // Set response content type
        response.setContentType("text/html");
        try {
            // Create default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            // FROM: header field
            message.setFrom(new InternetAddress(Config.DEFAULT_MAIL_ADDR));
            // TO: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
            // SUBJECT: header field
            message.setSubject(Config.RESET_EMAIL_SUBJECT);
            // Set the content of the message
            message.setContent("<h1>goShort! | Reset your password, " + username + "</h1><br>"
                    + "<h3> Please, reset your password by clicking on the following link: </h3>"
                    + "<a href=\"" + url + "\">Reset my password</a>", "text/html");
            // Send message
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(ForgotPassCommand.class.getName()).log(Level.SEVERE, null, ex);
            throw new SOBException(SOBError.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    private class SmtpAuthenticator extends Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Config.DEFAULT_MAIL_ADDR, Config.DEFAULT_MAIL_PASSWD);
        }
    }

}
