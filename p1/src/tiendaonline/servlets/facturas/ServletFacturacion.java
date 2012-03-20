package tiendaonline.servlets.facturas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Factura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 * 
 */
public class ServletFacturacion extends HttpServlet {

	private static final long serialVersionUID = -9037497643024107580L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Factura> facturas;

		int facturasPorPagina = 10;
		int numPaginas = 1;
		int paginaActual = 1;
		try {
			paginaActual = Integer.parseInt(request.getParameter("pag"));
		} catch (NumberFormatException e) {

		}
		int start = facturasPorPagina * (paginaActual - 1);

		if (request.getParameter("dni") != null) {
			Usuario usuario = MisMetodos.obtenerUsuarioPorDni(
					request.getParameter("dni"), request);
			facturas = MisMetodos.obtenerFacturasUsuario(request, usuario);
		
			request.setAttribute(MisAtributos.isFacturaUsuarioDni.toString(),
					true);
			request.setAttribute(MisAtributos.facturaUsuarioDni.toString(),
					usuario.getDni());
		} else {
			request.setAttribute(MisAtributos.isFacturaUsuarioDni.toString(),
					false);
			numPaginas = MisMetodos.numPaginasFactura(request,
					facturasPorPagina);
			facturas = MisMetodos.obtenerFacturasPaginadas(request, start,
					facturasPorPagina);
		}

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = MisMetodos.obtenerProductos(request);

		MisMetodos.asignarRequest(request, categorias, fabricantes);

		request.setAttribute(MisAtributos.paginaSiguiente.toString(),
				paginaActual + 1);
		request.setAttribute(MisAtributos.paginaAnterior.toString(),
				paginaActual - 1);
		request.setAttribute(MisAtributos.paginaActual.toString(), paginaActual);
		request.setAttribute(MisAtributos.numFacturas.toString(),
				facturas.size());
		request.setAttribute(MisAtributos.facturas.toString(), facturas);
		request.setAttribute(MisAtributos.productos.toString(), productos);
		request.setAttribute(MisAtributos.numPaginas.toString(), numPaginas);

		request.getRequestDispatcher("facturacion.jsp").forward(request,
				response);
	}

}