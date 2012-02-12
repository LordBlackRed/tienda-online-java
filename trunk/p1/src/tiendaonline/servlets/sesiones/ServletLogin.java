package tiendaonline.servlets.sesiones;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;


public class ServletLogin extends HttpServlet{

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//EntityTransaction transaction = entityManager.getTransaction();
		
		List<Usuario> usuarios = (List<Usuario>) entityManager.createQuery("select usuario from Usuario usuario").getResultList();
		
		boolean login = false;
		
		for (Usuario usuario: usuarios){
			if (usuario.getNick().equals(nombre) && usuario.getPass().equals(pass)){
				login = true;
			}
		}
		
		if (login){
			boolean admin = false;
			if (nombre.equals("admin") && pass.equals("admin")){
				admin = true;
			}
			
			Long idUsuario = MisMetodos
					.obtenerUsuario(nombre, request).getId();
			
			Usuario usuario = new Usuario();
			usuario.setId(idUsuario);
			usuario.setNick(nombre);
			usuario.setPass(pass);
			usuario.setAdmin(admin);
			request.getSession().setAttribute(MisAtributos.usuario.toString(), usuario);
			
			entityManager.close();
			
			response.sendRedirect("Index");
		}
		
		
		
	}
		
}
