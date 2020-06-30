package service.AAADEVCRUD.Service;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import service.AAADEVCRUD.Dao.FacturacionJpaController;
import service.AAADEVCRUD.Entity.Facturacion;

/**
 *
 * @author umansilla
 */
public class FacturacionImpl implements IFacturacion {

    private final EntityManagerFactory emf;

    public FacturacionImpl() {
        this.emf = Persistence.createEntityManagerFactory("Persistencia");
    }

    @Override
    public List<Facturacion> ontenerTodosLosRegistros() {
        return new FacturacionJpaController(emf).findFacturacionEntities();
    }
    @Override
    public Facturacion obtenerFacturacion(Facturacion facturacion) {
        return new FacturacionJpaController(emf).findFacturacion(facturacion.getNumerodecuenta());
    }

    @Override
    public void editarFacturacion(Facturacion facturacion) throws Exception{
       new FacturacionJpaController(emf).edit(facturacion);
    }

}
