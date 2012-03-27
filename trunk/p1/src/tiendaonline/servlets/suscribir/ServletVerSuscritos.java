package tiendaonline.servlets.suscribir;

import java.io.IOException;
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
import tiendaonline.clases.Suscrito;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletVerSuscritos extends HttpServlet {

	private static final long serialVersionUID = 5078102087168571435L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Suscrito> suscritos = MisMetodos.obetenerSuscritos(request);
		int numeroSuscritos = MisMetodos.obtenerNumeroSuscritos(request);
		
		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productosCabecera = MisMetodos.obtenerProductos(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		
		request.setAttribute(MisAtributos.productosCabecera.toString(), productosCabecera);
		request.setAttribute(MisAtributos.suscritos.toString(), suscritos);
		request.setAttribute(MisAtributos.numeroSuscritos.toString(), numeroSuscritos);
		
		request.getRequestDispatcher("suscritos.jsp").forward(request, response);
		
	}

}
