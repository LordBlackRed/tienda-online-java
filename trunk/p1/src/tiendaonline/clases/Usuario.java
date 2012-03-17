package tiendaonline.clases;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 3895972343640291772L;

	private Long id;
	private String nick;
	private String nombre;
	private String pass;
	private boolean admin;
	private String apellidos;
	private String direccion;
	private int cp;
	private String localidad;
	private String provincia;
	private String dni;
	private String fechaNacimiento;
	private int telefonoFijo;
	private int telefonoMovil;
	private List<Factura> facturas;
	private Set<Long> prodFavoritos;

	public Usuario(Long id, String nick, String nombre, String pass,
			boolean admin, String apellidos, String direccion, int cp,
			String localidad, String provincia, String dni,
			String fechaNacimiento, int telefonoFijo, int telefonoMovil,
			List<Factura> facturas, Set<Long> prodFavoritos) {
		super();
		this.id = id;
		this.nick = nick;
		this.nombre = nombre;
		this.pass = pass;
		this.admin = admin;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.cp = cp;
		this.localidad = localidad;
		this.provincia = provincia;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.telefonoFijo = telefonoFijo;
		this.telefonoMovil = telefonoMovil;
		this.facturas = facturas;
		this.prodFavoritos = prodFavoritos;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(int telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public int getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(int telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Usuario() {
		// this.facturas = new ArrayList<Factura>();

	}

	@OneToMany(mappedBy = "usuario")
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Long getId() {
		return id;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Long> getProdFavoritos() {
		return prodFavoritos;
	}

	public void setProdFavoritos(Set<Long> prodFavoritos) {
		this.prodFavoritos = prodFavoritos;
	}

}
