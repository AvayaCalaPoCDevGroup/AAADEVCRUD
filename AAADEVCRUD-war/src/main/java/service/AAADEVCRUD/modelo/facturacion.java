package service.AAADEVCRUD.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facturacion")
public class facturacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "numerodecuenta")
	private int numerodecuenta;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "email")
	private String email;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "telefonocasa")
	private String telefonocasa;
	@Column(name = "telefonomovil")
	private String telefonomovil;
	@Column(name = "monto")
	private String monto;
	@Column(name = "numerodefactura")
	private String numerodefactura;
	@Column(name = "fechadevencimiento")
	private String fechadevencimiento;
	@Column(name = "status")
	private int status;
	
	public facturacion(){
		
	}
	
	public facturacion(int numerodecuenta, String nombre,
			String apellido, String telefonocasa, String telefonomovil, String email,
			String monto, String numerodefactura, String fechadevencimiento,
			int status) {
		super();
		this.numerodecuenta = numerodecuenta;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefonocasa = telefonocasa;
		this.telefonomovil = telefonomovil;
		this.email = email;
		this.monto = monto;
		this.numerodefactura = numerodefactura;
		this.fechadevencimiento = fechadevencimiento;
		this.status = status;
	}

	public int getNumerodecuenta() {
		return numerodecuenta;
	}

	public void setNumerodecuenta(int numerodecuenta) {
		this.numerodecuenta = numerodecuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefonocasa() {
		return telefonocasa;
	}

	public void setTelefonocasa(String telefonocasa) {
		this.telefonocasa = telefonocasa;
	}

	public String getTelefonomovil() {
		return telefonomovil;
	}

	public void setTelefonomovil(String telefonomovil) {
		this.telefonomovil = telefonomovil;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getNumerodefactura() {
		return numerodefactura;
	}

	public void setNumerodefactura(String numerodefactura) {
		this.numerodefactura = numerodefactura;
	}

	public String getFechadevencimiento() {
		return fechadevencimiento;
	}

	public void setFechadevencimiento(String fechadevencimiento) {
		this.fechadevencimiento = fechadevencimiento;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "notificaciondepago [numerodecuenta=" + numerodecuenta
				+ ", nombre=" + nombre + ", email=" + email + ", apellido="
				+ apellido + ", telefonocasa=" + telefonocasa
				+ ", telefonomovil=" + telefonomovil + ", monto=" + monto
				+ ", numerodefactura=" + numerodefactura
				+ ", fechadevencimiento=" + fechadevencimiento + ", status="
				+ status + "]";
	}	
	
}
