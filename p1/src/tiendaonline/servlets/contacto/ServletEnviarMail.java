package tiendaonline.servlets.contacto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletEnviarMail extends HttpServlet{

	private static final long serialVersionUID = 6384481626177977808L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = request.getParameter("mensaje");
        String asunto = request.getParameter("asunto");
        String mail = request.getParameter("mail");
        String nombre = request.getParameter("nombre");

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mail, nombre));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("LordBlackRed@gmail.com"));
            msg.setSubject(asunto);
            msg.setText(msgBody);
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
		
		response.sendRedirect("Index");
	
	}
}
