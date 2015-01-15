/**
 * Filename: EmailSender.java
 * Description: Controller that is in charge of sending emails to the user.
 */

package controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


import javax.mail.Message;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class Name: EmailSender
 * Description: Controls the email sending operation.
 */
public class EmailSender {
    /**
     * In charge of sending email to the user.
     * @param session This is the email session to be sent.
     * @param toEmail This is email address to send to.
     * @param subject This is the subject of the email.
     * @param body This is the body of the email.
     * @return void
     */
    private static  void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
          MimeMessage msg = new MimeMessage(session); // initialize message
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");

          msg.setFrom(new InternetAddress("no_reply@journaldev.com", "NoReply-JD")); // set the email components

          msg.setReplyTo(InternetAddress.parse("no_reply@journaldev.com", false));

          msg.setSubject(subject, "UTF-8");

          msg.setText(body, "UTF-8");

          msg.setSentDate(new Date());

          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          Transport.send(msg);  // send email

          System.out.println("Email Sent Successfully!!");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

    /**
     * In charge of preliminary email sending operations
     * @param toAddress This is the email address to send to.
     * @param content This is the body of the email.
     * @param title This is the subject of the email.
     * @return void
     */
    public  void send(String toAddress,String content,String title) {
        final String fromEmail = "williamlin59@outlook.com"; //requires valid gmail id
        final String password = "56666493"; // correct password for gmail id


        Properties property = new Properties(); // set email properties
        property.put("mail.smtp.host", "smtp.live.com"); //SMTP Host
        property.put("mail.smtp.port", "587"); //TLS Port
        property.put("mail.smtp.auth", "true"); //enable authentication
        property.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

                //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(property, auth); // get the session

        sendEmail(session, toAddress,title,content); // delegate to the sendEmail method
         
    }
 
     
}


