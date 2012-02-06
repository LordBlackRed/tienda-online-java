package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;

public class ServletAgregarCarro extends HttpServlet{
	private static final long serialVersionUID = -8880754418495708991L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Long id = Long.parseLong(request.getParameter("id"));
		
		transaction.begin();
		Producto producto = entityManager.find(Producto.class, id);
		transaction.commit();
	
		HttpSession sesion = request.getSession();
		//Obtenemos primero la lista de productos que es el carrito para a–adirle un producto m‡s
		List<Producto> carrito = (List<Producto>) sesion.getAttribute(MisAtributos.carrito.toString());
		carrito.add(producto);
		
		response.sendRedirect("carrito.jsp");
	}
}
