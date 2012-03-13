package tiendaonline.servlets.sesiones;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nombre");
		String pass = request.getParameter("pass");

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		List<Usuario> usuarios = (List<Usuario>) entityManager.createQuery(
				"select usuario from Usuario usuario").getResultList();

		boolean login = false;

		for (Usuario usuario : usuarios) {
			if (usuario.getNick().equals(nick)
					&& usuario.getPass().equals(pass)) {
				login = true;
			}
		}
		System.out.println("va a loguearse");
		if (login) {
			System.out.println("¿Logueado¿");
			boolean admin = false;
			if (nick.equals("admin") && pass.equals("admin")) {
				admin = true;
			}

			MisMetodos.introducirUsuarioSesion(request, nick, pass, admin);

			entityManager.close();

			response.sendRedirect("Index");
		} else {
			// No ha podido loguearse, usuario o contraseña incorrectos
			request.setAttribute(MisAtributos.error.toString(), true);
			List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
			List<Fabricante> fabricantes = MisMetodos
					.obtenerFabricantes(request);

			List<Producto> productos = MisMetodos.obtenerProductos(request);

			Collections.sort(productos);

			MisMetodos.asignarRequest(request, categorias, fabricantes);
			request.setAttribute(MisAtributos.productos.toString(), productos);

			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}

	}

}
