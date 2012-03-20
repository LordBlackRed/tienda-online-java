package tiendaonline.servlets.administrar.fabricantes;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Fabricante;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletFabricanteNuevo extends HttpServlet{

	private static final long serialVersionUID = -4158352439888813425L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombreFabricante");
		
		Fabricante fabricante = new Fabricante();
		fabricante.setNombre(nombre);
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
	
		transaction.begin();
		entityManager.persist(fabricante);
		transaction.commit();
		
		response.sendRedirect("Index");
	}

}
