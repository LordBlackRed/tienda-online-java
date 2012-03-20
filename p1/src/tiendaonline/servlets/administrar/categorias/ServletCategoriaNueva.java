package tiendaonline.servlets.administrar.categorias;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletCategoriaNueva extends HttpServlet{
	
	private static final long serialVersionUID = 8509397496751021039L;

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
		entityManager.close();
		
		response.sendRedirect("Index");
		
	}
	

}
