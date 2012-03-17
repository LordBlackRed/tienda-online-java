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

public class ServletFacturacion extends HttpServlet {

	private static final long serialVersionUID = -9037497643024107580L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Factura> facturas;

		if (request.getParameter("dni") != null) {
			Usuario usuario = MisMetodos.obtenerUsuarioPorDni(request.getParameter("dni"), request);
			System.out.println("nick usuario: " + usuario.getNick());
			facturas = MisMetodos.obtenerFacturasUsuario(request, usuario);
			request.setAttribute(MisAtributos.isFacturaUsuarioDni.toString(), true);
			request.setAttribute(MisAtributos.facturaUsuarioDni.toString(), usuario.getDni());
		} else {
			request.setAttribute(MisAtributos.isFacturaUsuarioDni.toString(), false);
			facturas = MisMetodos.obtenerFacturas(request);
		}

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = MisMetodos.obtenerProductos(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		request.setAttribute(MisAtributos.facturas.toString(), facturas);
		request.setAttribute(MisAtributos.productos.toString(), productos);
		
		request.getRequestDispatcher("facturacion.jsp").forward(request,
				response);
	}

}
