package tiendaonline.servlets.suscribir;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Suscrito;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletSuscribir extends HttpServlet {

	private static final long serialVersionUID = 3842326830074738722L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mail = request.getParameter(MisAtributos.mail.toString());

		boolean mailValido = MisMetodos.validarEmail(mail);

		if (mailValido) {
			EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
					.getSession().getServletContext().getAttribute("emf");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			Suscrito suscrito = new Suscrito();
			suscrito.setMail(mail);

			transaction.begin();
			entityManager.persist(suscrito);
			transaction.commit();

			entityManager.close();
		}

		response.sendRedirect("Index");
	}

}
