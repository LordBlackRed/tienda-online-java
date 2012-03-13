package tiendaonline.servlets.productos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Producto;
import tiendaonline.clases.Puntuacion;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletAddPuntuacion extends HttpServlet {

	private static final long serialVersionUID = 4812630311801914205L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean correcto = true;
		Long idUsuario = 0L;
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute(
					MisAtributos.usuario.toString());
			idUsuario = usuario.getId();
		} catch (NullPointerException e) {
			correcto = false;
		}
		if (correcto) {
			Long idProducto = Long.parseLong(request.getParameter("id"));
			String gusta = request.getParameter("megusta");

			List<Producto> productos = MisMetodos.obtenerProductos(request);
			// Producto productoVoto = MisMetodos.obtenerProducto(productos,
			// idProducto.toString());
			Producto producto = null;

			for (Producto p : productos) {
				if (p.getId().getId() == idProducto) {
					producto = p;
					break;
				}
			}
			EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
					.getSession().getServletContext().getAttribute("emf");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			List<Puntuacion> puntuaciones = MisMetodos
					.obtenerPuntuaciones(request);

			boolean encontrado = false;
			// Buscamos en la lista de usuarios votados el usuario de la sesi—n
			// y si
			// est‡ NO se incluye el voto, y si no est‡ SI se incluye
			for (Puntuacion puntuacion : puntuaciones) {
				if ((puntuacion.getIdUsuario().longValue() == idUsuario
						.longValue())
						&& (idProducto.longValue() == puntuacion
								.getIdProducto().longValue())) {
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				Puntuacion puntuacionNueva = new Puntuacion();
				puntuacionNueva.setIdUsuario(idUsuario);
				puntuacionNueva.setIdProducto(idProducto);
				puntuacionNueva.setFecha(new Date());

				// Obtenemos la puntuaci—n dle producto en concreto apra sumarle
				// o
				// restarle uno
				int puntos = 0;

				if (gusta.equals("t")) {
					puntos++;
				} else {
					puntos--;
				}
				puntuacionNueva.setPuntos(puntos);

				transaction.begin();
				entityManager.persist(puntuacionNueva);
				transaction.commit();
				entityManager.close();

			}

			response.sendRedirect("Index");
		} else {
			response.sendRedirect("Index");
		}
	}

}
