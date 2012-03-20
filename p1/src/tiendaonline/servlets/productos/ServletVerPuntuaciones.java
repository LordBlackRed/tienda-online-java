package tiendaonline.servlets.productos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Puntuacion;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletVerPuntuaciones extends HttpServlet {

	private static final long serialVersionUID = 3641461419535790848L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String idProducto = request.getParameter("id");

		List<Producto> productos = MisMetodos.obtenerProductos(request);
		Producto producto = MisMetodos.obtenerProducto(productos, idProducto);

		List<Puntuacion> puntuaciones = MisMetodos.obtenerPuntuaciones(request);
		List<Puntuacion> puntuacionesProducto = MisMetodos.obtenerPuntuacionesProducto(puntuaciones, Long.parseLong(idProducto));
		
		int puntosPositivos = 0;
		int puntosNegativos = 0;
		for (Puntuacion p: puntuacionesProducto){
			if (p.getPuntos() == 1){
			puntosPositivos++;
			}else if (p.getPuntos() == -1){
				puntosNegativos++;
			}
		}
		int puntuacionTotal = puntosPositivos + puntosNegativos;
		
		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		request.setAttribute(MisAtributos.puntosTotales.toString(), puntuacionTotal);
		request.setAttribute(MisAtributos.puntosPositivos.toString(), puntosPositivos);
		request.setAttribute(MisAtributos.puntosNegativos.toString(), puntosNegativos);
		request.setAttribute(MisAtributos.producto.toString(), producto);
		
		request.getRequestDispatcher("puntuaciones.jsp").forward(request,
				response);
	}

}
