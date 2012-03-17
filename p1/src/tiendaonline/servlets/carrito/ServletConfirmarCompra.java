package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.ServletIndex;
import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Factura;
import tiendaonline.clases.LineaFactura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletConfirmarCompra extends HttpServlet {

	private static final long serialVersionUID = 934747537190517515L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String totalFactura = request.getParameter("precio");

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos
				.obtenerFabricantes(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		request.setAttribute(MisAtributos.totalFactura.toString(), totalFactura);
		
			request.getRequestDispatcher("confirmarCompra.jsp").forward(
					request, response);
		
	}

}
