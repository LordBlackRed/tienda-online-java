package tiendaonline.servlets.administrar.productos;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import tiendaonline.clases.Producto;

public class ServletEliminarProducto extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String idProducto = request.getParameter("id");
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		String jpql = "select producto from Producto producto";
		Query query =  entityManager.createQuery(jpql);
		List<Producto> productos = (List<Producto>)query.getResultList();
		
		for (Producto producto: productos){
			if (producto.getId().getId() == Long.parseLong(idProducto)){
				transaction.begin();
				entityManager.remove(producto);
				transaction.commit();
				break;
			}
		}
	
		response.sendRedirect("Index");
	}
	

}
