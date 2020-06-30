package service.AAADEVCRUD.Service;

import java.util.List;

import service.AAADEVCRUD.Entity.Facturacion;

/**
 *
 * @author umansilla
 */
public interface IFacturacion {
    public List<Facturacion> ontenerTodosLosRegistros(); 
    public Facturacion obtenerFacturacion(Facturacion facturacion);
    public void editarFacturacion(Facturacion facturacion)  throws Exception; 
}
