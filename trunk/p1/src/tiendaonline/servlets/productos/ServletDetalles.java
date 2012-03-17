package tiendaonline.servlets.productos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletDetalles extends HttpServlet {

	private static final long serialVersionUID = -1252991606342186892L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idProducto = request.getParameter("id");
		List<Producto> productos = MisMetodos.obtenerProductos(request);
		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		Producto producto = MisMetodos.obtenerProducto(productos, idProducto);

		// Obtenemos los productos similares, es decir, los de su misma
		// categor’a
		String tituloCategoria = producto.getCategoriaString();
		List<Producto> productosCategoria = MisMetodos
				.obtenerProductosCategoria(request, tituloCategoria);
		// Aleatoriamente obtenemos 3 productos similares de la misma categor’a
		List<Producto> productosSimilares = new ArrayList<Producto>();
		Random rnd = new Random();
		int numeros[] = new int[productosCategoria.size()];
		for (int i = 0; i < productosCategoria.size(); i++) {
			int numero = (int) (rnd.nextDouble() * productosCategoria.size());
			numeros[i] = numero;
			boolean repetido = false;
			int contador = 0;
			while (!repetido && contador < numeros.length) {
				if (numeros[contador] == numero && contador != i) {
					repetido = true;
				}
				contador++;
			}
			if (repetido == false) {
				productosSimilares.add(productosCategoria.get(numero));
			}
		}
		Usuario usuario = (Usuario) request.getSession().getAttribute(
				MisAtributos.usuario.toString());
		boolean error = false;
		boolean favorito = false;

		try {
			usuario.getNick();

		} catch (NullPointerException e) {

			error = true;

		}
		if (!error) {
			// Averiguamos si el producto es favorito del usuario en concreto
			favorito = MisMetodos.isProductoFavorito(request,
					Long.parseLong(idProducto), usuario);
		}
		MisMetodos.asignarRequest(request, categorias, fabricantes);

		request.setAttribute(MisAtributos.error.toString(), error);
		request.setAttribute(MisAtributos.fav.toString(), favorito);
		request.setAttribute(MisAtributos.productosSimilares.toString(),
				productosSimilares);
		request.setAttribute(MisAtributos.producto.toString(), producto);
		request.setAttribute(MisAtributos.productos.toString(), productos);
		request.getRequestDispatcher("detalles.jsp").forward(request, response);
	}

}
