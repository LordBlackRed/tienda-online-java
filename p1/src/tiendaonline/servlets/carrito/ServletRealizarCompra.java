package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletRealizarCompra extends HttpServlet{

		private static final long serialVersionUID = 464588348788960164L;

		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String totalFactura = request.getParameter("precio");
			
			List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
			List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
			
			request.setAttribute(MisAtributos.categorias.toString(), categorias);
			request.setAttribute(MisAtributos.fabricantes.toString(), fabricantes);
			request.setAttribute(MisAtributos.totalFactura.toString(), totalFactura);
			
			request.getRequestDispatcher("realizar-compra.jsp").forward(request,
					response);
			
		}
		
}
