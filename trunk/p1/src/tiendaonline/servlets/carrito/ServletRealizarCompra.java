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

public class ServletRealizarCompra extends HttpServlet {

	private static final long serialVersionUID = 464588348788960164L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
			String totalFactura = request.getParameter("precio");
			
			
			request.getSession().setAttribute(
					MisAtributos.totalFactura.toString(), totalFactura);

			List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
			List<Fabricante> fabricantes = MisMetodos
					.obtenerFabricantes(request);
			long numeroFactura = MisMetodos.obtenerNumeroFactura(request);

			MisMetodos.asignarRequest(request, categorias, fabricantes);
			request.setAttribute(MisAtributos.numeroFactura.toString(),
					numeroFactura);

			// lo inicializamos de nuevo
			ServletIndex.usuarioSesion = new Usuario();
			ServletIndex.carritoSesion = new ArrayList<Producto>();
			ServletIndex.usuarioSesion = (Usuario) request.getSession()
					.getAttribute(MisAtributos.usuario.toString());
			ServletIndex.carritoSesion = (List<Producto>) request.getSession()
					.getAttribute(MisAtributos.carrito.toString());
			//
			//
			//
			// List<Producto> carrito = ServletIndex.carritoSesion;
			// List<Long> idProductos =
			// MisMetodos.obtenerIdProductosCarrito(request);
			//
			// Long numero = MisMetodos.obtenerIdFactura(request);
			//
			// Usuario usuario = ServletIndex.usuarioSesion;
			// Factura factura = new Factura();
			//
			// factura.setUsuario(usuario);
			// factura.setFecha(new Date());
			// factura.setIdProductos(idProductos);
			// factura.setNumero(numero);
			// //factura.setPrecio(Double.parseDouble(request.getParameter("mc_gross")));
			//
			// for (Producto producto : carrito) {
			//
			// LineaFactura lineaFactura = new LineaFactura();
			//
			// lineaFactura.setCantidad(producto.getCantidad());
			// lineaFactura.setIdFactura(factura.getNumero());
			// lineaFactura.setPrecio(producto.getPrecio());
			// lineaFactura.setIdProducto(producto.getId().getId());
			//
			// EntityManagerFactory entityManagerFactory =
			// (EntityManagerFactory) request
			// .getSession().getServletContext().getAttribute("emf");
			// EntityManager entityManager = entityManagerFactory
			// .createEntityManager();
			// EntityTransaction transaction = entityManager.getTransaction();
			//
			// transaction.begin();
			// entityManager.persist(lineaFactura);
			// transaction.commit();
			// entityManager.close();
			//
			// }
			//
			// EntityManagerFactory entityManagerFactory =
			// (EntityManagerFactory) request
			// .getSession().getServletContext().getAttribute("emf");
			// EntityManager entityManager = entityManagerFactory
			// .createEntityManager();
			// EntityTransaction transaction = entityManager.getTransaction();
			//
			// transaction.begin();
			// entityManager.persist(factura);
			// transaction.commit();
			// entityManager.close();

			request.getRequestDispatcher("realizar-compra.jsp").forward(
					request, response);
		
	}

}
