package tiendaonline.clases;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class Producto {

	private Key id;
	private double precio;
	private String fecha;
	private String nombre;
	private int cantidad;
	private String urlImagen;
	private Categoria categoria;
	private String categoriaString;
	private Fabricante fabricante;

	public Producto(double precio, String fecha, String nombre, int cantidad,
			String urlImagen, Categoria categoria, String categoriaString,
			Fabricante fabricante) {
		super();
		this.precio = precio;
		this.fecha = fecha;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.urlImagen = urlImagen;
		this.categoria = categoria;
		this.categoriaString = categoriaString;
		this.fabricante = fabricante;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getCategoriaString() {
		return categoriaString;
	}

	public void setCategoriaString(String categoriaString) {
		this.categoriaString = categoriaString;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Producto() {

	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", precio=" + precio + ", fecha=" + fecha
				+ ", nombre=" + nombre + ", cantidad=" + cantidad
				+ ", urlImagen=" + urlImagen + ", categoria=" + categoria
				+ ", categoriaString=" + categoriaString + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
