package tiendaonline.servlets.sesiones;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiendaonline.clases.Usuario;

public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioString =request.getParameter("nombre");
		String pass = request.getParameter("pass");
		
		Usuario usuario = new Usuario();
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		boolean admin = false;
		
		if (pass.equals("admin") && (usuarioString.equals("admin"))){
			admin = true;
		}
		usuario.setPass(pass);
		usuario.setNombre(usuarioString);
		usuario.setAdmin(admin);
		
		transaction.begin();
		entityManager.persist(usuario);
		transaction.commit();
		
		//Se introduce el usuario en la sesi—n
		HttpSession sesion = request.getSession();
		sesion.setAttribute("usuario", usuario);
		
		response.sendRedirect("index.jsp");
		}

}
