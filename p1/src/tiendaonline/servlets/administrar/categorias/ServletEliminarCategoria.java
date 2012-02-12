package tiendaonline.servlets.administrar.categorias;

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

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Producto;

public class ServletEliminarCategoria extends HttpServlet {

	private static final long serialVersionUID = 2176165060521312964L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombreCategoria = request.getParameter("eliminarCategoria");
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		String jpql = "select categoria from Categoria categoria";
		Query query =  entityManager.createQuery(jpql);
		List<Categoria> categorias = (List<Categoria>)query.getResultList();
		
		for (Categoria categoria: categorias){
			if (categoria.getTitulo().equals(nombreCategoria)){
				transaction.begin();
				entityManager.remove(categoria);
				transaction.commit();
				break;
			}
		}
	
		response.sendRedirect("Index");
	}

}
