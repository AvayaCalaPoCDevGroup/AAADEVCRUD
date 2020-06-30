package service.AAADEVCRUD.NVP.Bean;


import java.io.Serializable;

/**
 *
 * @author umansilla
 */
public class Notificacion implements Serializable{
    private int numerodecuenta;
    private String nombre;
    private String apellido;
    private String telefonocasa;
    private String telefonomovil;
    private String email;
    private String monto;
    private String numerodefactura;
    private String fechadevencimiento;
    private int status;
    private int countNotificaciones;

    public Notificacion() {
    }
    
    public Notificacion(int countNotificaciones) {
        this.countNotificaciones = countNotificaciones;
    }

    public Notificacion(int numerodecuenta, String nombre) {
        this.numerodecuenta = numerodecuenta;
        this.nombre = nombre;
    }
    
    public Notificacion(int numerodecuenta, String nombre, String apellido, String telefonocasa, String telefonomovil, String email, String monto, String numerodefactura, String fechadevencimiento, int status) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getCountNotificaciones() {
        return countNotificaciones;
    }

    public void setCountNotificaciones(int countNotificaciones) {
        this.countNotificaciones = countNotificaciones;
    }
    
    
}
