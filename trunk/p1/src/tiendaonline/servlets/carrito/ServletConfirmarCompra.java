package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletConfirmarCompra extends HttpServlet {

	private static final long serialVersionUID = 934747537190517515L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Comprobamos si los productos elegidos est‡n todos en stock, si es as’
		// se continua para confirmar la compra, sino, vuelve al carrito
		boolean correcto = true;
		@SuppressWarnings("unchecked")
		List<Producto> carrito = (List<Producto>) request.getSession()
				.getAttribute(MisAtributos.carrito.toString());

		Iterator<Producto> itCarrito = carrito.iterator();

		while (itCarrito.hasNext() && correcto) {
			Producto productoCarrito = itCarrito.next();

			correcto = MisMetodos.comprobarStockProducto(request,
					productoCarrito.getId().getId(),
					productoCarrito.getCantidad());
		}

		if (correcto) {
			String totalFactura = request.getParameter("precio");

			List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
			List<Fabricante> fabricantes = MisMetodos
					.obtenerFabricantes(request);

			MisMetodos.asignarRequest(request, categorias, fabricantes);
			request.setAttribute(MisAtributos.totalFactura.toString(),
					totalFactura);

			request.getRequestDispatcher("confirmarCompra.jsp").forward(
					request, response);
		} else {
			List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
			List<Fabricante> fabricantes = MisMetodos
					.obtenerFabricantes(request);
			List<Envio> empresasEnvio = MisMetodos
					.obtenerEmpresasEnvio(request);

			MisMetodos.asignarRequest(request, categorias, fabricantes);
			request.setAttribute(MisAtributos.empresasEnvio.toString(),
					empresasEnvio);
			request.setAttribute(MisAtributos.error.toString(), true);

			request.getRequestDispatcher("carrito.jsp").forward(request,
					response);
		}

	}

}
