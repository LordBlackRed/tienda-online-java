package tiendaonline;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Factura;
import tiendaonline.clases.LineaFactura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class Paypal extends HttpServlet {

	private static final long serialVersionUID = 8349157835773778171L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pago = request.getParameter("payment_status");
		//String itemNumber = request.getParameter("item_number");

		if (pago.equals("Completed") || pago.equals("Completado")) {

			List<Producto> carrito = ServletIndex.carritoSesion;
			List<Long> idProductos = MisMetodos
					.obtenerIdProductosCarrito(request);

			Long numero = MisMetodos.obtenerIdFactura(request);

			Usuario usuario = ServletIndex.usuarioSesion;
			Factura factura = new Factura();

			factura.setUsuario(usuario);
			factura.setFecha(new Date());
			factura.setIdProductos(idProductos);
			factura.setNumero(numero);
			factura.setIdEnvio(ServletIndex.idEnvioCompra);
			factura.setPrecio(Double.parseDouble(request
					.getParameter("mc_gross")));

			for (Producto producto : carrito) {

				LineaFactura lineaFactura = new LineaFactura();

				lineaFactura.setCantidad(producto.getCantidad());
				lineaFactura.setIdFactura(factura.getNumero());
				lineaFactura.setPrecio(producto.getPrecio());
				lineaFactura.setIdProducto(producto.getId().getId());

				EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
						.getSession().getServletContext().getAttribute("emf");
				EntityManager entityManager = entityManagerFactory
						.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();

				transaction.begin();
				entityManager.persist(lineaFactura);
				transaction.commit();

				transaction.begin();
				// Actualizamos la cantidad del producto
				/*
				 * Metemos en una variable auxiliar la cantidad que ten’a en la
				 * sesi—n para que una vez realizada o canlecada la compra le
				 * aparezca en el carrito la cantidad que Žl quer’a/ha comprado,
				 * sino, se sustituir’a por la cantidad que existe realmente ene
				 * l stock
				 */
				int cantidadAux = producto.getCantidad();
				int cantidadProducto = MisMetodos.obtenerCantidadProducto(
						request, producto.getId().getId());
				producto.setCantidad(cantidadProducto - producto.getCantidad());
				entityManager.merge(producto);
				transaction.commit();

				producto.setCantidad(cantidadAux);

				entityManager.close();

			}

			EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
					.getSession().getServletContext().getAttribute("emf");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			transaction.begin();
			entityManager.persist(factura);
			transaction.commit();
			entityManager.close();
		}
	}
}
