package service.AAADEVCRUD.controlador;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import service.AAADEVCRUD.modelo.facturacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Controlador {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public static void main(String[] args) {
    	System.out.println(showNotifications());
    }
    
    public static void deleteNotification(facturacion mybean){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    	EntityManager manager = emf.createEntityManager();
    	manager.getTransaction().begin();
    	facturacion n = manager.find(facturacion.class, mybean.getNumerodecuenta());
    	manager.remove(n);
        manager.getTransaction().commit();
        manager.close();
    }
	
    public static void createNotification(facturacion mybean){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    	EntityManager manager = emf.createEntityManager();
    	manager.getTransaction().begin();
    	facturacion n = new facturacion(mybean.getNumerodecuenta(), mybean.getNombre(), mybean.getApellido(), mybean.getTelefonocasa(), mybean.getTelefonomovil(), mybean.getEmail(), mybean.getMonto(), mybean.getNumerodefactura(), mybean.getFechadevencimiento(), mybean.getStatus());
    	manager.persist(n);
    	manager.getTransaction().commit();
    	manager.close();
    }
    
    public static void updateNotification(facturacion mybean){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    	EntityManager manager = emf.createEntityManager();
    	manager.getTransaction().begin();
    	facturacion n = manager.find(facturacion.class, mybean.getNumerodecuenta());
    	n.setApellido(mybean.getApellido());
    	n.setEmail(mybean.getEmail());
    	n.setFechadevencimiento(mybean.getFechadevencimiento());
    	n.setMonto(mybean.getMonto());
    	n.setNombre(mybean.getNombre());
    	n.setNumerodefactura(mybean.getNumerodefactura());
    	n.setStatus(mybean.getStatus());
    	n.setTelefonocasa(mybean.getTelefonocasa());
    	n.setTelefonomovil(mybean.getTelefonomovil());
        manager.getTransaction().commit();
        manager.close();	
    }
    
    @SuppressWarnings("unchecked")
	public static String showNotifications(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    	EntityManager manager = emf.createEntityManager();
    	manager.getTransaction().begin();
    	List<facturacion> notificaciones = (List<facturacion>) manager.createQuery("SELECT n FROM facturacion n").getResultList();
        List<facturacion> list = new LinkedList<>();
        for (facturacion nvp : notificaciones){
        	facturacion notificacion = new facturacion();
        	notificacion.setApellido(nvp.getApellido());
        	notificacion.setEmail(nvp.getEmail());
        	notificacion.setFechadevencimiento(nvp.getFechadevencimiento());
        	notificacion.setMonto(nvp.getMonto());
        	notificacion.setNombre(nvp.getNombre());
        	notificacion.setNumerodecuenta(nvp.getNumerodecuenta());
        	notificacion.setNumerodefactura(nvp.getNumerodefactura());
        	notificacion.setStatus(nvp.getStatus());
        	notificacion.setTelefonocasa(nvp.getTelefonocasa());
        	notificacion.setTelefonomovil(nvp.getTelefonomovil());
        	list.add(notificacion);
        }
        Gson jsonGson = new GsonBuilder().setPrettyPrinting().create();
        manager.getTransaction().commit();
        manager.close();
        return jsonGson.toJson(list);
    }
    
    @SuppressWarnings("unchecked")
	public static String showNotificationByAccount(int numerodecuenta){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    	EntityManager manager = emf.createEntityManager();
    	manager.getTransaction().begin();
        List<facturacion> notificaciones = (List<facturacion>) manager.createQuery("SELECT n FROM facturacion n WHERE n.numerodecuenta = /'"+numerodecuenta+"/'").getResultList();
        List<facturacion> list = new LinkedList<>();
        for (facturacion nvp : notificaciones){
        	facturacion notificacion = new facturacion();
        	notificacion.setApellido(nvp.getApellido());
        	notificacion.setEmail(nvp.getEmail());
        	notificacion.setFechadevencimiento(nvp.getFechadevencimiento());
        	notificacion.setMonto(nvp.getMonto());
        	notificacion.setNombre(nvp.getNombre());
        	notificacion.setNumerodecuenta(nvp.getNumerodecuenta());
        	notificacion.setNumerodefactura(nvp.getNumerodefactura());
        	notificacion.setStatus(nvp.getStatus());
        	notificacion.setTelefonocasa(nvp.getTelefonocasa());
        	notificacion.setTelefonomovil(nvp.getTelefonomovil());
        	list.add(notificacion);
        }
        Gson jsonGson = new GsonBuilder().setPrettyPrinting().create();
        manager.getTransaction().commit();
        manager.close();
        return jsonGson.toJson(list);
    }
	
	
}
