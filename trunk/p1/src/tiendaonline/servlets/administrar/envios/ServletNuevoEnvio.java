package tiendaonline.servlets.administrar.envios;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Envio;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletNuevoEnvio extends HttpServlet{

	private static final long serialVersionUID = 2160736457431637303L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombreEmpresa = request.getParameter("empresa");
		double precio = Double.parseDouble(request.getParameter("precioEnvio"));
		
		Envio envio = new Envio();
		envio.setEmpresa(nombreEmpresa);
		envio.setPrecio(precio);
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
	
		transaction.begin();
		entityManager.persist(envio);
		transaction.commit();
		entityManager.close();
		
		response.sendRedirect("Index");
	}
		
}
