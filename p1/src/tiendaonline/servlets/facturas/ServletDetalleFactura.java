package tiendaonline.servlets.facturas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.ServletIndex;
import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.LineaFactura;
import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 * 
 */
public class ServletDetalleFactura extends HttpServlet {

	private static final long serialVersionUID = 5918651634103304573L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idFactura = Long.parseLong(request.getParameter("id"));

		List<LineaFactura> lineasFacturas = MisMetodos.obtenerLineasFacturas(
				request, idFactura);
		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = MisMetodos.obtenerProductos(request);

		// Obtenemos el id del env’o para meter luego en el request el precio
		// del env’o y mostrarlo en la factura
		Long idEnvio = MisMetodos.obtenerIdEnvio(request, idFactura);
		List<Envio> empresasEnvio = MisMetodos.obtenerEmpresasEnvio(request);
		Envio empresaEnvio = MisMetodos.obtenerEmpresaEnvio(empresasEnvio,
				idEnvio);

		double precioEnvio = empresaEnvio.getPrecio();
		String nombreEmpresaEnvio = empresaEnvio.getEmpresa();

		MisMetodos.asignarRequest(request, categorias, fabricantes);

		request.setAttribute(MisAtributos.nombreEmpresaEnvio.toString(),
				nombreEmpresaEnvio);
		request.setAttribute(MisAtributos.precioEnvio.toString(), precioEnvio);
		request.setAttribute(MisAtributos.lineasFactura.toString(),
				lineasFacturas);
		request.setAttribute(MisAtributos.idFactura.toString(), idFactura);
		request.setAttribute(MisAtributos.productos.toString(), productos);

		request.getRequestDispatcher("detallesFactura.jsp").forward(request,
				response);
	}

}
