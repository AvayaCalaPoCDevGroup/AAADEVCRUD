package service.AAADEVCRUD.NVP.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;	

import service.AAADEVCRUD.NVP.Bean.Notificacion;
import service.AAADEVCRUD.NVP.JDBC.BaseDatosPG;
/**
 *
 * @author umansilla
 */
public class NotificacionDeVencimientoDao {

    public Notificacion obtenerCuentaDeNotificaciones() {
        Notificacion notifiaciones = null;
        try {
            BaseDatosPG base = new BaseDatosPG();
            String query = "SELECT COUNT(numerodecuenta) FROM public.notificaciondepago;";
            PreparedStatement ps = base.getconnection().prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                notifiaciones = new Notificacion(rs.getInt("count"));
            }
        } catch (SQLException ex) {
            System.out.println("Error; " + ex.toString());
        }
        return notifiaciones;
    }

    public List<Notificacion> obtenerTodas() {
        Notificacion notifiaciones;
        List<Notificacion> listNotificaciones = new ArrayList<>();
        try {
            BaseDatosPG base = new BaseDatosPG();
            String query = "SELECT numerodecuenta, nombre, email, apellido, monto, fechadevencimiento, status, telefonocasa, telefonomovil, numerodefactura FROM public.notificaciondepago;";
            PreparedStatement ps = base.getconnection().prepareCall(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                notifiaciones = new Notificacion(rs.getInt("numerodecuenta"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefonocasa"),
                        rs.getString("telefonomovil"),
                        rs.getString("email"),
                        rs.getString("monto"),
                        rs.getString("numerodefactura"),
                        rs.getString("fechadevencimiento"),
                        rs.getInt("status"));
                listNotificaciones.add(notifiaciones);
            }
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        }
        return listNotificaciones;
    }

    public JSONObject crearNotificacion(Notificacion notificacion) {
        try {
            BaseDatosPG base = new BaseDatosPG();
            String sql = "INSERT INTO public.notificaciondepago(numerodecuenta, nombre, email, apellido, monto, fechadevencimiento, status, telefonocasa, telefonomovil, numerodefactura) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = base.getconnection().prepareStatement(sql);
            ps.setInt(1, notificacion.getNumerodecuenta());
            ps.setString(2, notificacion.getNombre());
            ps.setString(3, notificacion.getEmail());
            ps.setString(4, notificacion.getApellido());
            ps.setString(5, notificacion.getMonto());
            ps.setString(6, notificacion.getFechadevencimiento());
            ps.setInt(7, notificacion.getStatus());
            ps.setString(8, notificacion.getTelefonocasa());
            ps.setString(9, notificacion.getTelefonomovil());
            ps.setString(10, notificacion.getNumerodefactura());
            ResultSet rs = ps.executeQuery();
            return new JSONObject()
                    .put("status", "ok")
                    .put("numerodecuenta", notificacion.getNumerodecuenta())
                    .put("nombre", notificacion.getNombre())
                    .put("email", notificacion.getEmail())
                    .put("apellido", notificacion.getApellido())
                    .put("monto", notificacion.getMonto())
                    .put("fechadevencimiento", notificacion.getFechadevencimiento())
                    .put("statusBD", notificacion.getStatus())
                    .put("telefonocasa", notificacion.getTelefonocasa())
                    .put("telefonomovil", notificacion.getTelefonomovil())
                    .put("numerodefactura", notificacion.getNumerodefactura());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            return new JSONObject()
                    .put("status", "ok")
                    .put("numerodecuenta", notificacion.getNumerodecuenta())
                    .put("nombre", notificacion.getNombre())
                    .put("email", notificacion.getEmail())
                    .put("apellido", notificacion.getApellido())
                    .put("monto", notificacion.getMonto())
                    .put("fechadevencimiento", notificacion.getFechadevencimiento())
                    .put("statusBD", notificacion.getStatus())
                    .put("telefonocasa", notificacion.getTelefonocasa())
                    .put("telefonomovil", notificacion.getTelefonomovil())
                    .put("numerodefactura", notificacion.getNumerodefactura());
        }

    }

    public JSONObject modificarNotificacion(Notificacion notificacion) {
        System.out.println("modificarNotificacion DAO");
        try {
            BaseDatosPG base = new BaseDatosPG();
            String sql = "UPDATE public.notificaciondepago SET numerodecuenta=?, nombre=?, email=?, apellido=?, monto=?, fechadevencimiento=?, status=?, telefonocasa=?, telefonomovil=?, numerodefactura=? WHERE numerodecuenta = ?;";
            PreparedStatement ps = base.getconnection().prepareStatement(sql);
            ps.setInt(1, notificacion.getNumerodecuenta());
            ps.setString(2, notificacion.getNombre());
            ps.setString(3, notificacion.getEmail());
            ps.setString(4, notificacion.getApellido());
            ps.setString(5, notificacion.getMonto());
            ps.setString(6, notificacion.getFechadevencimiento());
            ps.setInt(7, notificacion.getStatus());
            ps.setString(8, notificacion.getTelefonocasa());
            ps.setString(9, notificacion.getTelefonomovil());
            ps.setString(10, notificacion.getNumerodefactura());
            ps.setInt(11, notificacion.getNumerodecuenta());
            ResultSet rs = ps.executeQuery();
            return new JSONObject()
                    .put("status", "ok")
                    .put("numerodecuenta", notificacion.getNumerodecuenta())
                    .put("nombre", notificacion.getNombre())
                    .put("email", notificacion.getEmail())
                    .put("apellido", notificacion.getApellido())
                    .put("monto", notificacion.getMonto())
                    .put("fechadevencimiento", notificacion.getFechadevencimiento())
                    .put("statusBD", notificacion.getStatus())
                    .put("telefonocasa", notificacion.getTelefonocasa())
                    .put("telefonomovil", notificacion.getTelefonomovil())
                    .put("numerodefactura", notificacion.getNumerodefactura());
        } catch (Exception e) {
            System.out.println("Error DAO" + e.toString());
            return new JSONObject()
                    .put("status", "ok")
                    .put("numerodecuenta", notificacion.getNumerodecuenta())
                    .put("nombre", notificacion.getNombre())
                    .put("email", notificacion.getEmail())
                    .put("apellido", notificacion.getApellido())
                    .put("monto", notificacion.getMonto())
                    .put("fechadevencimiento", notificacion.getFechadevencimiento())
                    .put("statusBD", notificacion.getStatus())
                    .put("telefonocasa", notificacion.getTelefonocasa())
                    .put("telefonomovil", notificacion.getTelefonomovil())
                    .put("numerodefactura", notificacion.getNumerodefactura());
        }
    }

    public JSONObject borrarNotificacion(Notificacion notificacion) {
        try {
            BaseDatosPG base = new BaseDatosPG();
            String sql = "DELETE FROM public.notificaciondepago WHERE numerodecuenta = ? AND nombre = ?;";
            PreparedStatement ps = base.getconnection().prepareStatement(sql);
            ps.setInt(1, notificacion.getNumerodecuenta());
            ps.setString(2, notificacion.getNombre());
            ResultSet rs = ps.executeQuery();
            return new JSONObject().put("status", "ok").put("numeroDeCuenta", notificacion.getNumerodecuenta()).put("nombre", notificacion.getNombre());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            return new JSONObject().put("status", "ok").put("numeroDeCuenta", notificacion.getNumerodecuenta()).put("nombre", notificacion.getNombre());
        }
    }

}