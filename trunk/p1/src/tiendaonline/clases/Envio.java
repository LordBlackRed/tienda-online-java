package tiendaonline.clases;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Rafael de los Santos Guirado
 * 
 */
@Entity
public class Envio implements Serializable {

	private static final long serialVersionUID = -9207941553571369065L;
	private Long id;
	private String empresa;
	private double precio;

	public Envio(Long id, String empresa, double precio) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.precio = precio;
	}

	public Envio() {

	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
