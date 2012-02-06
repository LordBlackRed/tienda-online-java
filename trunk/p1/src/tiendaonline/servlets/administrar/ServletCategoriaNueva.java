package tiendaonline.servlets.administrar;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;

public class ServletCategoriaNueva extends HttpServlet{
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String tituloCategoria = request.getParameter("titulo");
		
		Categoria categoria = new Categoria();
		categoria.setTitulo(tituloCategoria);

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
	
		transaction.begin();
		entityManager.persist(categoria);
		transaction.commit();
		response.sendRedirect("Index");
		
	}
	

}
