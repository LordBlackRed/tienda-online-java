package tiendaonline.servlets.administrar.sponsors;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.listeners.ContextoListener;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletAddSponsor extends HttpServlet{

	private static final long serialVersionUID = -6291856045925134103L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		List<String> sponsor = ContextoListener.sponsor;
		if (request.getParameter("urlRight") == null){
			url = request.getParameter("urlLeft");
			sponsor.set(0, url);
		}else if (request.getParameter("urlLeft") == null){
			url = request.getParameter("urlRight");
			sponsor.set(1, url);
		}
		
		response.sendRedirect("Index");
		
	}

}
