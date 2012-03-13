package tiendaonline.metodos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Transaction;

import tiendaonline.ServletIndex;
import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Factura;
import tiendaonline.clases.LineaFactura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Puntuacion;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.listeners.ContextoListener;

public class MisMetodos {

	public static List<Categoria> obtenerCategorias(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpqlCategoria = "select categoria from Categoria categoria";
		List<Categoria> categorias = entityManager.createQuery(jpqlCategoria)
				.getResultList();
		transaction.commit();
		entityManager.close();

		return categorias;
	}

	public static List<Producto> obtenerProductos(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select producto from Producto producto";
		List<Producto> productos = entityManager.createQuery(jpql)
				.getResultList();

		transaction.commit();
		entityManager.close();

		return productos;
	}

	public static List<Puntuacion> obtenerPuntuaciones(
			HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select puntuacion from Puntuacion puntuacion";
		List<Puntuacion> puntuaciones = entityManager.createQuery(jpql)
				.getResultList();

		transaction.commit();
		entityManager.close();

		return puntuaciones;
	}

	public static List<Fabricante> obtenerFabricantes(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select fabricante from Fabricante fabricante";
		List<Fabricante> fabricantes = entityManager.createQuery(jpql)
				.getResultList();

		transaction.commit();
		entityManager.close();

		return fabricantes;
	}

	public static Producto obtenerProducto(List<Producto> productos,
			String idProducto) {
		for (Producto producto : productos) {
			if (producto.getId().getId() == Long.parseLong(idProducto)) {
				return producto;

			}
		}
		return null;
	}

	public static Usuario obtenerUsuario(String nick, HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		System.out.println("nick: " + nick);
		String jpql = "select usuario from Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", nick);

		Usuario usuario = (Usuario) query.getSingleResult();

		System.out.println("llega bien");

		return usuario;
	}

	// public static int obtenerPuntuacion(HttpServletRequest request,
	// Long idProducto) {
	//
	// EntityManagerFactory entityManagerFactory = (EntityManagerFactory)
	// request
	// .getSession().getServletContext().getAttribute("emf");
	// EntityManager entityManager = entityManagerFactory
	// .createEntityManager();
	// EntityTransaction transaction = entityManager.getTransaction();
	// transaction.begin();
	//
	// String jpql =
	// "select puntuacion from Puntuacion puntuacion where puntuacion.idProducto=:idProd";
	// Query query = entityManager.createQuery(jpql);
	// query.setParameter("idProd", idProducto);
	//
	// int puntuacion = 0;
	//
	// List<Puntuacion> puntuaciones= (List<Puntuacion>) query.getResultList();
	//
	// Collections.sort(puntuaciones);
	// try{
	// puntuacion = puntuaciones.get(0).getPuntuacionTotal();
	// }catch (IndexOutOfBoundsException ex){
	// puntuacion = 0;
	// }
	// System.out.println("PUNTUACION****" + puntuacion);
	// transaction.commit();
	// entityManager.close();
	//
	// return puntuacion;
	// }

	public static Long obtenerIdProducto(HttpServletRequest request,
			String nombre) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select producto from Producto producto";
		Query query = entityManager.createQuery(jpql);

		List<Producto> productos = (List<Producto>) query.getResultList();

		Long idProducto = 0L;
		for (Producto p : productos) {
			System.out.println("nombre producto: " + p.getNombre());
			if (p.getNombre().equals(nombre)) {
				idProducto = p.getId().getId();
				break;
			}
		}

		transaction.commit();
		entityManager.close();

		return idProducto;
	}

	public static List<Producto> obtenerProductosCategoria(
			HttpServletRequest request, String tituloCategoria) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select producto from Producto producto";
		Query query = entityManager.createQuery(jpql);

		List<Producto> productos = query.getResultList();

		List<Producto> productosCategoria = new ArrayList<Producto>();

		for (Producto producto : productos) {
			if (producto.getCategoriaString().equals(tituloCategoria)) {
				productosCategoria.add(producto);
			}
		}
		entityManager.close();

		return productosCategoria;
	}

	public static List<Puntuacion> obtenerPuntuacionesProducto(
			List<Puntuacion> puntuaciones, long idProducto) {

		List<Puntuacion> puntuacionProducto = new ArrayList<Puntuacion>();

		for (Puntuacion puntuacion : puntuaciones) {
			if (puntuacion.getIdProducto().longValue() == idProducto) {
				puntuacionProducto.add(puntuacion);
			}
		}
		return puntuacionProducto;
	}

	public static List<Envio> obtenerEmpresasEnvio(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select envio from Envio envio";
		Query query = entityManager.createQuery(jpql);

		List<Envio> empresasEnvio = query.getResultList();

		return empresasEnvio;
	}

	public static Envio obtenerEmpresaEnvio(List<Envio> empresasEnvio,
			long idEnvio) {

		Envio envio = null;
		for (Envio empresaEnvio : empresasEnvio) {
			if (empresaEnvio.getId().longValue() == idEnvio) {
				envio = empresaEnvio;
				break;
			}
		}

		return envio;
	}

	public static long obtenerNumeroFactura(HttpServletRequest request) {

		List<Factura> facturas = obtenerFacturas(request);

		long numeroFactura = 1L;
		System.out.println("tama–o lista: " + facturas.size());
		if (facturas.size() > 0) {
			numeroFactura = facturas.get(facturas.size() - 1).getNumero()
					.longValue();
		}
		return numeroFactura;
	}

	public static List<Factura> obtenerFacturas(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select factura from Factura factura";
		Query query = entityManager.createQuery(jpql);
		List<Factura> facturas = query.getResultList();

		return facturas;
	}

	public static void introducirUsuarioSesion(HttpServletRequest request,
			String nick, String pass, boolean admin) {

		// Buscamos el usuario en concreto en la base de datos por si tiene
		// otros datos en la BD
		Usuario usuario = obtenerUsuario(nick, request);

		if (usuario == null) {
			System.out.println("EL USUARIO ES NULO!!!");
			usuario = new Usuario();
			usuario.setAdmin(admin);
			usuario.setPass(pass);
			usuario.setNick(nick);
			usuario.setFacturas(new ArrayList<Factura>());

		}
		request.getSession().setAttribute(MisAtributos.usuario.toString(),
				usuario);

	}

	public static List<Factura> obtenerFacturasUsuario(
			HttpServletRequest request, Usuario usuario) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select usuario from Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", usuario.getNick());

		Usuario user = (Usuario) query.getSingleResult();

		List<Factura> facturas = user.getFacturas();
		for (Factura f : facturas) {
			System.out.println("facturaUsuario: " + f);
		}
		entityManager.close();

		return facturas;
	}

	public static List<Long> obtenerIdProductosCarrito(
			HttpServletRequest request) {

		List<Producto> carrito = ServletIndex.carritoSesion;
		List<Long> idProductosCarrito = new ArrayList<Long>();

		for (Producto producto : carrito) {
			idProductosCarrito.add(producto.getId().getId());
		}

		return idProductosCarrito;
	}

	public static Long obtenerIdFactura(HttpServletRequest request) {
		List<Factura> facturas = obtenerFacturas(request);

		if (facturas.size() == 0) {
			return 1L;
		} else {
			return facturas.get(facturas.size() - 1).getNumero() + 1;
		}
	}

	public static List<LineaFactura> obtenerLineasFacturas(
			HttpServletRequest request, Long idFactura) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select lineaFactura from LineaFactura lineaFactura where lineaFactura.idFactura=:idF";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idF", idFactura);

		List<LineaFactura> lineasFacturas = query.getResultList();

		return lineasFacturas;
	}

	public static List<Producto> obtenerProductosFabricante(
			HttpServletRequest request, String idFabricante) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto from Producto producto where producto.idFabricante=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", Long.parseLong(idFabricante));

		List<Producto> productosFabricante = query.getResultList();

		return productosFabricante;
	}

	public static List<Producto> obtenerProductosFavoritos(
			HttpServletRequest request, Usuario usuario)
			throws NoResultException {

		Set<Long> prodFavoritos = usuario.getProdFavoritos();
		List<Producto> productos = new ArrayList<Producto>();
		Iterator<Long> it = prodFavoritos.iterator();

		while (it.hasNext()) {
			Long id = it.next();
			System.out.println("idProductoFavorito" + id);
			EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
					.getSession().getServletContext().getAttribute("emf");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			String jpql = "select producto from Producto producto where producto.id=:id";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("id", id);
			try {
				productos.add((Producto) query.getSingleResult());
			} catch (NoResultException e) {
				prodFavoritos.remove(id);
				EntityTransaction tx = entityManager.getTransaction();
				usuario.setProdFavoritos(prodFavoritos);
				tx.begin();
				entityManager.merge(usuario);
				tx.commit();
				System.out.println("excepcion!!!!" + id);
				throw new NoResultException(
						"El producto favorito ha sido eliminado de la Base de Datos");
			}
			entityManager.close();
		}
		return productos;
	}

	public static boolean validarEmail(String mail) {

		char c1 = mail.charAt(0);
		int pos1 = 0;
		int pos2 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int x = 0;
		String cadena1 = "";
		String cadena2 = "";
		String cadena3 = "";

		try {
			if (c1 != '@' && c1 != '.') {

				for (int i = 1; i < mail.length(); i++) {
					if (mail.charAt(i) == '@') {
						pos1 = i;
					}
				}
				for (int i = 1; i < mail.length(); i++) {
					if (mail.charAt(i) == '.') {
						pos2 = i;
					}
				}

				cadena1 = mail.substring(0, pos1);
				cadena2 = mail.substring(pos1 + 1, pos2);
				cadena3 = mail.substring(pos2 + 1, mail.length());

				for (int i = 0; i < cadena1.length(); i++) {
					a1 = cadena1.codePointAt(i);//
					if ((a1 > 47 && a1 < 58) || (a1 > 64 && a1 < 91)
							|| (a1 > 96 && a1 < 123) || a1 == 46) {
						x++;
					}
				}
				System.out.println("1 " + cadena2);
				for (int i = 0; i < cadena2.length(); i++) {
					a2 = cadena2.codePointAt(i);
					if ((a2 > 47 && a2 < 58) || (a2 > 64 && a2 < 91)
							|| (a2 > 96 && a2 < 123)) {
						x++;
					}
				}

				for (int i = 0; i < cadena3.length(); i++) {
					a3 = cadena3.codePointAt(i);
					if ((a3 > 47 && a3 < 58) || (a3 > 64 && a3 < 91)
							|| (a3 > 96 && a3 < 123)) {
						x++;
					}
				}
				if (x == mail.length() - 2) {
					if (pos1 != 0 && pos2 != 0 && (pos1 + 2) < pos2) {

						if (mail.length() - 1 >= (pos2 + 2)) {

							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}

	}

	public static void eliminarFavUsuarioPro(HttpServletRequest request,
			String idProducto) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		String jpql = "select usuario from Usuario usuario";
		Query query = entityManager.createQuery(jpql);
		List<Usuario> usuarios = query.getResultList();

		for (Usuario usuario : usuarios) {
			Set<Long> favsUsuario = usuario.getProdFavoritos();
			Iterator<Long> it = favsUsuario.iterator();
			while (it.hasNext()) {
				Long idProductoFav = it.next();
				if (Long.parseLong(idProducto) == idProductoFav.longValue()) {
					favsUsuario.remove(idProductoFav);
					transaction.begin();
					entityManager.merge(usuario);
					transaction.commit();
				}
			}
		}
	}

	public static List<Producto> obtenerProductosSearch(
			HttpServletRequest request, String parameter) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto FROM Producto producto WHERE LOWER(alumno.nombre) LIKE :busq";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("busq", parameter + "%".toLowerCase());
		List<Producto> productos;

		try {
			productos = query.getResultList();
		} catch (NullPointerException ex) {
			productos = obtenerProductos(request);

		}

		if (productos.isEmpty()) {
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	public static Producto obtenerProductoNuevo(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto FROM Producto producto";
		Query query = entityManager.createQuery(jpql);
		List<Producto> productos = query.getResultList();

		Collections.sort(productos);

		return productos.get(0);
	}

	public static void asignarRequest(HttpServletRequest request,
			List<Categoria> categorias, List<Fabricante> fabricantes) {

		Producto productoNuevo = MisMetodos.obtenerProductoNuevo(request);

		request.setAttribute(MisAtributos.productoNuevo.toString(),
				productoNuevo);
		request.setAttribute(MisAtributos.productosCabecera.toString(),
				ContextoListener.productosCabecera);
		request.setAttribute(MisAtributos.productoEspecial.toString(),
				ContextoListener.productoEspecial);
		request.setAttribute(MisAtributos.sponsor.toString(),
				ContextoListener.sponsor);
		request.setAttribute(MisAtributos.categorias.toString(), categorias);
		request.setAttribute(MisAtributos.fabricantes.toString(), fabricantes);
	}

	public static boolean isProductoFavorito(HttpServletRequest request,
			Long idProducto, Usuario usuario)throws NullPointerException {

		boolean favorito = false;
		try{
		Set<Long> favoritos = usuario.getProdFavoritos();
		Iterator<Long> it = favoritos.iterator();

		while (it.hasNext()) {
			Long idP = it.next();

			if (idProducto.longValue() == idP.longValue()) {
				favorito = true;
			}
		}
		}catch (NullPointerException ex){
			throw new NullPointerException();
		}
		return favorito;
	}

	public static boolean comprobarExisteUsuario(HttpServletRequest request,
			String nick) {

		boolean existe = false;

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select usuario FROM Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", nick);

		try {
			Usuario usuario = (Usuario) query.getSingleResult();
			if (usuario != null) {
				existe = true;
			}
		} catch (NoResultException ex) {
			existe = false;
		} catch (NonUniqueResultException e) {
			existe = true;
		}

		return existe;
	}
}
