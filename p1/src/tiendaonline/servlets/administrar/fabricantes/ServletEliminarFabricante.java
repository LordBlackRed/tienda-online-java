package tiendaonline.servlets.administrar.fabricantes;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Fabricante;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletEliminarFabricante extends HttpServlet{

	private static final long serialVersionUID = 1286532133741704819L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String nombreFabricante = request.getParameter("eliminarFabricante");
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		String jpql = "select fabricante from Fabricante fabricante";
		Query query =  entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Fabricante> fabricantes = (List<Fabricante>)query.getResultList();
		
		for (Fabricante fabricante: fabricantes){
			if (fabricante.getNombre().equals(nombreFabricante)){
				transaction.begin();
				entityManager.remove(fabricante);
				transaction.commit();
				break;
			}
		}
	
		response.sendRedirect("Index");
	}
	
}
