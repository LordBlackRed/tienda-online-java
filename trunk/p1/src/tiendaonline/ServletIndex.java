package tiendaonline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.html.HTMLLabelElement;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Factura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.listeners.ContextoListener;
import tiendaonline.metodos.MisMetodos;

public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 3797767980886873521L;
	public static Usuario usuarioSesion;
	public static List<Producto> carritoSesion;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = null;
		if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") == null)) {
			productos = MisMetodos.obtenerProductos(request);
		} else if ((request.getParameter(MisAtributos.categoria.toString()) != null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") == null)) {
			productos = MisMetodos.obtenerProductosCategoria(request,
					request.getParameter(MisAtributos.categoria.toString()));
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) != null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") == null)) {
			productos = MisMetodos.obtenerProductosFabricante(request,
					request.getParameter(MisAtributos.fabricante.toString()));
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) != null)
				&& (request.getParameter("search") == null)) {
			try {
				if ((Usuario) request.getSession().getAttribute(
						MisAtributos.usuario.toString()) == null) {
					productos = new ArrayList<Producto>();
				} else {
					productos = MisMetodos.obtenerProductosFavoritos(
							request,
							(Usuario) request.getSession().getAttribute(
									MisAtributos.usuario.toString()));
				}
			} catch (NoResultException e) {
				request.setAttribute(MisAtributos.productos.toString(),
						productos);

				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") != null)) {
			productos = MisMetodos.obtenerProductosSearch(request,
					request.getParameter("search"));
		}

		Collections.sort(productos);
		request.setAttribute(MisAtributos.producto.toString(),
				ContextoListener.productosCabecera.get(0));
		request.setAttribute(MisAtributos.productos.toString(), productos);
		MisMetodos.asignarRequest(request, categorias, fabricantes);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productos = null;
		if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") == null)) {
			productos = MisMetodos.obtenerProductos(request);
		} else if ((request.getParameter(MisAtributos.categoria.toString()) != null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") == null)) {
			productos = MisMetodos.obtenerProductosCategoria(request,
					request.getParameter(MisAtributos.categoria.toString()));
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) != null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") == null)) {
			productos = MisMetodos.obtenerProductosFabricante(request,
					request.getParameter(MisAtributos.fabricante.toString()));
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) != null)
				&& (request.getParameter("search") == null)) {
			if ((Usuario) request.getSession().getAttribute(
					MisAtributos.usuario.toString()) == null) {
				productos = new ArrayList<Producto>();
			} else {
				productos = MisMetodos.obtenerProductosFavoritos(
						request,
						(Usuario) request.getSession().getAttribute(
								MisAtributos.usuario.toString()));
			}
		} else if ((request.getParameter(MisAtributos.categoria.toString()) == null)
				&& (request.getParameter(MisAtributos.fabricante.toString()) == null)
				&& (request.getParameter(MisAtributos.fav.toString()) == null)
				&& (request.getParameter("search") != null)) {
			productos = MisMetodos.obtenerProductosSearch(request,
					request.getParameter("search"));
		}

		Collections.sort(productos);

		request.setAttribute(MisAtributos.producto.toString(),
				ContextoListener.productosCabecera.get(0));
		request.setAttribute(MisAtributos.productos.toString(), productos);
		MisMetodos.asignarRequest(request, categorias, fabricantes);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}
}
