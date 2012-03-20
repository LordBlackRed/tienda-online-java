package tiendaonline.servlets.perfil;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletActualizarUsuario extends HttpServlet {

	private static final long serialVersionUID = -7029758949000076883L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombreUsuario");
		String apellidos = request.getParameter("apellidos");
		String dni = request.getParameter("dni");
		String direccion = request.getParameter("direccion");
		String localidad = request.getParameter("localidad");
		String provincia = request.getParameter("provincia");
		int cp = Integer.parseInt(request.getParameter("cp"));
		String fechaNacimiento = request.getParameter("fechaN");
		int telefonoFijo = Integer.parseInt(request.getParameter("telFijo"));
		int telefonoMovil = Integer.parseInt(request.getParameter("telMovil"));
		

		Usuario usuario = (Usuario) request.getSession().getAttribute(
				MisAtributos.usuario.toString());
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setCp(cp);
		usuario.setDireccion(direccion);
		usuario.setDni(dni);
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setLocalidad(localidad);
		usuario.setProvincia(provincia);
		usuario.setTelefonoFijo(telefonoFijo);
		usuario.setTelefonoMovil(telefonoMovil);

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
				
		transaction.begin();
		entityManager.merge(usuario);
		transaction.commit();
		entityManager.close();

		response.sendRedirect("MiCuenta");
	}

}
