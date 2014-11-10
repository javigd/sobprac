package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.util.Config;
import cat.urv.deim.sob.util.ResetMailer;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class ForgotPassCommand implements Command {

    private final IUserHandler dbUsrHandler;
    private final ResetMailer resetMailer;
    
    public ForgotPassCommand(IUserHandler dbHandler) {
        dbUsrHandler = dbHandler;
        resetMailer = new ResetMailer();
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String email = request.getParameter("email");

        try {
            // 2. Generate, save and get a ticket
            SOBUser u = dbUsrHandler.rememberPassword(email);
            // Generate the password reset URL using uid and ticket values
            String url = Config.SERVER_MAIL_PREFIX + "resetpass.jsp"
                    + "?uid=" + u.getId()
                    + "&ticket=" + u.getResetTicket();
            System.out.println("Generated URL:\t" + url);
            System.out.println("sending mail to..." + u.getEmail());
            response = resetMailer.sendEmail(u.getEmail(), u.getUsername(), url, response);
            response.sendRedirect("login.jsp");
        } catch (SOBException ex) {
            // 3. produce the view with the web result
            request.setAttribute("responseMessage", ex.getError().getMessage());
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/iforgot.jsp").forward(request, response);
        }
    }

    private HttpServletResponse sendEmail(String dest, String username, String url, HttpServletResponse response) throws SOBException {
        // Get system properties
        Properties properties = System.getProperties();
        // Setup smtp mail server
        properties.setProperty("smtp.gmail.com", Config.HOST);
        // Get the default mail Session object
        Session session = Session.getDefaultInstance(properties);
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
}

//
//public class SendMail {
//    final String meuCorreu = "francesc.martorell46@gmail.com";
//    final String mevaContrasenya = "dasouzacky";
//    final String servidorSMTP = "smtp.gmail.com";
//    final String portEnvio = "465";
//    String mailReceptor = null;
//    String assumpte = null;
//    String cos = null;
//
//    public SendMail(String mailReceptor, String assumpte,String cos) {
//        this.mailReceptor = mailReceptor;
//        this.assumpte = assumpte;
//        this.cos = cos;
//
//        Properties props = new Properties();
//        props.put("mail.smtp.user", meuCorreu);
//        props.put("mail.smtp.host", servidorSMTP);
//        props.put("mail.smtp.port", portEnvio);
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.socketFactory.port", portEnvio);
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//
//        SecurityManager security = System.getSecurityManager();
//
//        try {
//            Authenticator auth = new autentificadorSMTP();
//            Session session = Session.getInstance(props, auth);
//            // session.setDebug(true);
//
//            MimeMessage msg = new MimeMessage(session);
//            msg.setText(cos);
//            msg.setSubject(assumpte);
//            msg.setFrom(new InternetAddress(meuCorreu));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
//                    mailReceptor));
//            Transport.send(msg);
//        } catch (Exception mex) {
//            mex.printStackTrace();
//        }
//
//    }