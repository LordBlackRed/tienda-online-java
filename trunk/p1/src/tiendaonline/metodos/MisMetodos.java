package tiendaonline.metodos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;

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
	
}
