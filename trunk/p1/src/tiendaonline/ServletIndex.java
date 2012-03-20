package tiendaonline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.listeners.ContextoListener;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 * 
 */
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 3797767980886873521L;
	public static Usuario usuarioSesion;
	public static List<Producto> carritoSesion;
	public static Long idEnvioCompra;
	public static String categoria;
	public static String fabricante;
	public static String busquedaPer;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = null;

		int productosPorPagina = 6;
		int numPaginas = 1;
		// Lo metemos dentro de un try catch ya que, (aún no sé porqué) la
		// página se recarga varias veces, y a veces el párametro que se le pasa
		// es null
		// Además que puede darse el caso de que el usuario escriba Index en el
		// navegador sin pasarle ningún parámetro
		int paginaActual = 1;
		try {
			paginaActual = Integer.parseInt(request.getParameter("pag"));
		} catch (NumberFormatException e) {

		}
		int start = productosPorPagina * (paginaActual - 1);

		// TODOS LOS PRODUCTOS
		if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)
				&& request.getParameter("reg") == null) {
			categoria = null;
			fabricante = null;
			busquedaPer = null;
			numPaginas = MisMetodos.numPaginas(request, productosPorPagina);
			productos = MisMetodos.paginacion(request, start,
					productosPorPagina);
			request.setAttribute(MisAtributos.registrado.toString(), false);
			request.setAttribute(MisAtributos.fav.toString(), null);
			// productos = MisMetodos.obtenerProductos(request);
			// PRODUCTOS DE UNA CATEGORÍA
		} else if ((request.getParameter(MisAtributos.categoria.toString()) != null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)
				&& request.getParameter("reg") == null) {
			fabricante = null;
			categoria = request.getParameter(MisAtributos.categoria.toString());
			busquedaPer = null;
			numPaginas = MisMetodos.numPaginasCategoria(request,
					productosPorPagina, categoria);
			request.setAttribute(MisAtributos.fav.toString(), false);
			productos = MisMetodos.obtenerProductosCategoriaPaginados(request,
					request.getParameter(MisAtributos.categoria.toString()),
					start, productosPorPagina);
			request.setAttribute(MisAtributos.registrado.toString(), false);
			// PRODUCTOS DE UN FABRICANTE
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) != null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)
				&& request.getParameter("reg") == null) {
			categoria = null;
			fabricante = request.getParameter(MisAtributos.fabricante
					.toString());
			busquedaPer = null;
			request.setAttribute(MisAtributos.fav.toString(), false);
			String idFabricante = request.getParameter(MisAtributos.fabricante
					.toString());
			numPaginas = MisMetodos.numPaginasFabricante(request,
					productosPorPagina, fabricante);
			productos = MisMetodos.obtenerProductosFabricante(request,
					idFabricante, start, productosPorPagina);
			request.setAttribute(MisAtributos.registrado.toString(), false);
			// PRODUCTOS FAVORITOS DE UN USUARIO
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) != null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)
				&& request.getParameter("reg") == null) {
			try {
				categoria = null;
				fabricante = null;
				busquedaPer = null;
				request.setAttribute(MisAtributos.fav.toString(), true);
				if ((Usuario) request.getSession().getAttribute(
						MisAtributos.usuario.toString()) == null) {
					productos = new ArrayList<Producto>();
				} else {
					Usuario usuario = (Usuario) request.getSession()
							.getAttribute(MisAtributos.usuario.toString());
					numPaginas = MisMetodos.numPaginasFavorito(request,
							usuario, productosPorPagina);
					productos = MisMetodos.obtenerProductosFavoritos(request,
							usuario, start, productosPorPagina);
				}
			} catch (NoResultException e) {
				request.setAttribute(MisAtributos.productos.toString(),
						productos);
				request.setAttribute(MisAtributos.registrado.toString(), false);

				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
			// PRODUCTOS DE LA BÚSQUEDA PERSONALIZADA
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) != null)
				&& request.getParameter("reg") == null) {
			categoria = null;
			fabricante = null;
			busquedaPer = request.getParameter(MisAtributos.search.toString());
			String parameter = request.getParameter(MisAtributos.search
					.toString());
			request.setAttribute(MisAtributos.fav.toString(), false);
			productos = MisMetodos.obtenerProductosSearchPaginado(request,
					parameter, start, productosPorPagina);
			numPaginas = MisMetodos.numPaginasSearch(request,
					productosPorPagina, parameter);
			request.setAttribute(MisAtributos.registrado.toString(), false);
			// USUARIO REGISTRADO
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)
				&& request.getParameter("reg") != null) {
			categoria = null;
			fabricante = null;
			busquedaPer = null;
			numPaginas = MisMetodos.numPaginas(request, productosPorPagina);
			productos = MisMetodos.paginacion(request, start,
					productosPorPagina);
			request.setAttribute(MisAtributos.fav.toString(), null);
			request.setAttribute(MisAtributos.registrado.toString(), true);
		}

		Collections.sort(productos);
		request.setAttribute(MisAtributos.producto.toString(),
				ContextoListener.productosCabecera.get(0));
		request.setAttribute(MisAtributos.productos.toString(), productos);
		MisMetodos.asignarRequest(request, categorias, fabricantes);

		// Metemos en la sesión la página actual para que pueda volver a la
		// página que el usuario a la hora de pulsar en add carrito
		request.setAttribute(MisAtributos.paginaSiguiente.toString(),
				paginaActual + 1);
		request.setAttribute(MisAtributos.paginaAnterior.toString(),
				paginaActual - 1);
		request.setAttribute(MisAtributos.paginaActual.toString(), paginaActual);
		request.setAttribute(MisAtributos.numPaginas.toString(), numPaginas);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Vaciamos el carrito porque si ha llegado aquí es que se ha realizado
		 * una compra y ha vuelto a través de paypal
		 */

		request.getSession()
				.setAttribute(MisAtributos.carrito.toString(), null);

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = null;

		int productosPorPagina = 6;
		int numPaginas = 0;
		// Lo metemos dentro de un try catch ya que, (aún no sé porqué) la
		// página se recarga varias veces, y a veces el párametro que se le pasa
		// es null
		// Además que puede darse el caso de que el usuario escriba Index en el
		// navegador sin pasarle ningún parámetro
		int paginaActual = 1;
		try {
			paginaActual = Integer.parseInt(request.getParameter("pag"));
		} catch (NumberFormatException e) {

		}
		int start = productosPorPagina * (paginaActual - 1);

		// TODOS LOS PRODUCTOS
		if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)) {
			categoria = null;
			fabricante = null;
			busquedaPer = null;
			numPaginas = MisMetodos.numPaginas(request, productosPorPagina);
			productos = MisMetodos.paginacion(request, start,
					productosPorPagina);
			request.setAttribute(MisAtributos.fav.toString(), null);
			// productos = MisMetodos.obtenerProductos(request);
			// PRODUCTOS DE UNA CATEGORÍA
		} else if ((request.getParameter(MisAtributos.categoria.toString()) != null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)) {
			fabricante = null;
			categoria = request.getParameter(MisAtributos.categoria.toString());
			busquedaPer = null;
			numPaginas = MisMetodos.numPaginasCategoria(request,
					productosPorPagina, categoria);
			request.setAttribute(MisAtributos.fav.toString(), false);
			productos = MisMetodos.obtenerProductosCategoriaPaginados(request,
					request.getParameter(MisAtributos.categoria.toString()),
					start, productosPorPagina);
			// PRODUCTOS DE UN FABRICANTE
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) != null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)) {
			categoria = null;
			fabricante = request.getParameter(MisAtributos.fabricante
					.toString());
			busquedaPer = null;
			request.setAttribute(MisAtributos.fav.toString(), false);
			String idFabricante = request.getParameter(MisAtributos.fabricante
					.toString());
			numPaginas = MisMetodos.numPaginasFabricante(request,
					productosPorPagina, fabricante);
			productos = MisMetodos.obtenerProductosFabricante(request,
					idFabricante, start, productosPorPagina);
			// PRODUCTOS FAVORITOS DE UN USUARIO
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) != null)
				&& (request.getParameter(MisAtributos.search.toString()) == null)) {
			try {
				categoria = null;
				fabricante = null;
				busquedaPer = null;
				request.setAttribute(MisAtributos.fav.toString(), true);
				if ((Usuario) request.getSession().getAttribute(
						MisAtributos.usuario.toString()) == null) {
					productos = new ArrayList<Producto>();
				} else {
					Usuario usuario = (Usuario) request.getSession()
							.getAttribute(MisAtributos.usuario.toString());
					numPaginas = MisMetodos.numPaginasFavorito(request,
							usuario, productosPorPagina);
					productos = MisMetodos.obtenerProductosFavoritos(request,
							usuario, start, productosPorPagina);
				}
			} catch (NoResultException e) {
				request.setAttribute(MisAtributos.productos.toString(),
						productos);

				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
			// PRODUCTOS DE LA BÚSQUEDA PERSONALIZADA
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter(MisAtributos.search.toString()) != null)) {
			categoria = null;
			fabricante = null;
			busquedaPer = request.getParameter(MisAtributos.search.toString());
			String parameter = request.getParameter(MisAtributos.search
					.toString());
			request.setAttribute(MisAtributos.fav.toString(), false);
			productos = MisMetodos.obtenerProductosSearchPaginado(request,
					parameter, start, productosPorPagina);
			numPaginas = MisMetodos.numPaginasSearch(request,
					productosPorPagina, parameter);
		}

		Collections.sort(productos);
		request.setAttribute(MisAtributos.producto.toString(),
				ContextoListener.productosCabecera.get(0));
		request.setAttribute(MisAtributos.productos.toString(), productos);
		MisMetodos.asignarRequest(request, categorias, fabricantes);

		// Metemos en la sesión la página actual para que pueda volver a la
		// página que el usuario a la hora de pulsar en add carrito
		request.setAttribute(MisAtributos.paginaSiguiente.toString(),
				paginaActual + 1);
		request.setAttribute(MisAtributos.paginaAnterior.toString(),
				paginaActual - 1);
		request.setAttribute(MisAtributos.paginaActual.toString(), paginaActual);
		request.setAttribute(MisAtributos.numPaginas.toString(), numPaginas);
		request.setAttribute(MisAtributos.registrado.toString(), false);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}
}
