package service.AAADEVCRUD.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author umansilla
 */
@Entity
@Table(name = "facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturacion.findAll", query = "SELECT f FROM Facturacion f")
    , @NamedQuery(name = "Facturacion.findByNumerodecuenta", query = "SELECT f FROM Facturacion f WHERE f.numerodecuenta = :numerodecuenta")
    , @NamedQuery(name = "Facturacion.findByNombre", query = "SELECT f FROM Facturacion f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "Facturacion.findByEmail", query = "SELECT f FROM Facturacion f WHERE f.email = :email")
    , @NamedQuery(name = "Facturacion.findByApellido", query = "SELECT f FROM Facturacion f WHERE f.apellido = :apellido")
    , @NamedQuery(name = "Facturacion.findByTelefonocasa", query = "SELECT f FROM Facturacion f WHERE f.telefonocasa = :telefonocasa")
    , @NamedQuery(name = "Facturacion.findByTelefonomovil", query = "SELECT f FROM Facturacion f WHERE f.telefonomovil = :telefonomovil")
    , @NamedQuery(name = "Facturacion.findByMonto", query = "SELECT f FROM Facturacion f WHERE f.monto = :monto")
    , @NamedQuery(name = "Facturacion.findByNumerodefactura", query = "SELECT f FROM Facturacion f WHERE f.numerodefactura = :numerodefactura")
    , @NamedQuery(name = "Facturacion.findByFechadevencimiento", query = "SELECT f FROM Facturacion f WHERE f.fechadevencimiento = :fechadevencimiento")
    , @NamedQuery(name = "Facturacion.findByStatus", query = "SELECT f FROM Facturacion f WHERE f.status = :status")})
public class Facturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numerodecuenta")
    private Integer numerodecuenta;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 2147483647)
    @Column(name = "telefonocasa")
    private String telefonocasa;
    @Size(max = 2147483647)
    @Column(name = "telefonomovil")
    private String telefonomovil;
    @Size(max = 2147483647)
    @Column(name = "monto")
    private String monto;
    @Size(max = 2147483647)
    @Column(name = "numerodefactura")
    private String numerodefactura;
    @Size(max = 2147483647)
    @Column(name = "fechadevencimiento")
    private String fechadevencimiento;
    @Column(name = "status")
    private Integer status;

    public Facturacion() {
    }

    public Facturacion(Integer numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public Integer getNumerodecuenta() {
        return numerodecuenta;
    }

    public void setNumerodecuenta(Integer numerodecuenta) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerodecuenta != null ? numerodecuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturacion)) {
            return false;
        }
        Facturacion other = (Facturacion) object;
        if ((this.numerodecuenta == null && other.numerodecuenta != null) || (this.numerodecuenta != null && !this.numerodecuenta.equals(other.numerodecuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.aaadevrud.entity.Facturacion[ numerodecuenta=" + numerodecuenta + " ]";
    }
    
}
