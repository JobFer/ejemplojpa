package com.enlawebdekaaf.jpa.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

	public static void main(String[] args)
	{
//		insertar();
//		editar();
//		eliminar();
		leerTodo();
//		leerPorDocumentoIdentidad("5555");
//		leerTodoSinNamedQuery();
	}
	
	private static void leerTodoSinNamedQuery() {
		
		EntityManagerFactory emf=null;
		EntityManager em=null;
		
		Logger log = Logger.getLogger("Main");
		
		try
		{
			emf=Persistence.createEntityManagerFactory("ejemplojpa");
			em=emf.createEntityManager();
//			EntityTransaction et=em.getTransaction();

//			//En una sola linea
//			List<Tpersona> lista = em.createQuery("SELECT t FROM Tpersona t",Tpersona.class).getResultList();
			
			TypedQuery<Tpersona> query=em.createQuery("SELECT t FROM Tpersona t",Tpersona.class);
			List<Tpersona> lista = query.getResultList();
//			
//			System.out.println(lista);
			for(Tpersona item : lista)
			{
//				System.out.println(item.getNombre()+"......."+item.getCorreoElectronico());
				System.out.printf("%-30s %-10s %-30s\n", item.getNombre(), item.getDocumentoIdentidad(), item.getCorreoElectronico());
			}
			
			log.info("FIN");			
		}
		catch(Exception ex)
		{
			System.out.println("Error en listar: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}		
	}

	public static void insertar()
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ejemplojpa");;
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		try
		{
			Tpersona tPersona=new Tpersona();
			
//			tPersona.setIdPersona(4); //Da EXCEPCION. Habria que quitar el "GenerationType" para que funcione esto.
			tPersona.setNombre("Pepe");
			tPersona.setDocumentoIdentidad("11111111");
			tPersona.setCorreoElectronico("pepe@gmail.com");
			tPersona.setFechaNacimiento("2014/01/01");
			tPersona.setEstado(true);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String fechaRegistro = sdf.format(new Date());
			tPersona.setFechaRegistro(fechaRegistro);
			
			et.begin(); //ES LO MISMO QUE: em.getTransaction().begin();
			em.persist(tPersona);
//			//Para saber que ID le ha asignado
//			System.out.println("tPersona.getIdPersona(): " + tPersona.getIdPersona());
			
			et.commit();
//			et.rollback(); //Aqui se puede poner esto
			
			System.out.println("Registro realizado correctamente");
		}
		catch(Exception ex)
		{
//			et.rollback(); //Aqui no se puede poner esto
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
	}
	
	public static void editar()
	{

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try
		{		
			emf=Persistence.createEntityManagerFactory("ejemplojpa");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();

			//FORMA 1: FIND + MERGE
			int idPersona=3;
			Tpersona tPersona=em.find(Tpersona.class, idPersona);
			
			tPersona.setNombre("Paco");
			tPersona.setDocumentoIdentidad("77778888");
			tPersona.setCorreoElectronico("paco@hotmail.com");

			et.begin();
			em.merge(tPersona);
			et.commit();

			
//			//FORMA 2: Solo FIND
//			Tpersona tPersona=em.find(Tpersona.class, 3);		
//			
//			et.begin();
//			tPersona.setDocumentoIdentidad("2222");
//			et.commit();
			
			
//			//FORMA 3: Solo MERGE
//			Tpersona tPersona = new Tpersona(3);
//			tPersona.setDocumentoIdentidad("7777");
//			
//			et.begin();
//			em.merge(tPersona);
//			et.commit();
			
			
//			//Ventajas de MERGE
//			Tpersona tPersona2 = em.find(Tpersona.class, 2);
//			em.detach(tPersona2);
//			tPersona2.setIdPersona(3);
//			et.begin();
//			em.merge(tPersona2);
//			et.commit();
			
			
			System.out.println("Edición realizada correctamente");
		}
		catch(Exception ex)
		{
			System.out.println("Error: "+ex.getMessage());
//			et.rollback();
		}
		finally
		{
//			em.close();
			emf.close();
//			System.out.println("em.isOpen(): " + em.isOpen());
//			System.out.println("emf.isOpen(): " + emf.isOpen());
		}
	}	

	public static void eliminar()
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ejemplojpa");;
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		try
		{
			int idPersona=12;
			Tpersona tPersona=em.find(Tpersona.class, idPersona);
//			System.out.println("tPersona: " + tPersona); //Si esto es null, no hacer lo siguiente
			
			et.begin();
			em.remove(tPersona);
			et.commit();
			
			System.out.println("Registro eliminado correctamente");
			
//			//Para comprobar si ha sido correctamente borrado
//			Tpersona tPersona2=em.find(Tpersona.class, 12);
//			if(tPersona2 == null) 
//				System.out.println("Definitivamente borrado");
//			else System.out.println("NO ha sido borrado");
		}
		catch(Exception ex)
		{
//			et.rollback();
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
	}	
	
	public static void leerTodo()
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ejemplojpa");;
		EntityManager em=emf.createEntityManager();
//		EntityTransaction et=em.getTransaction();
		
		Logger log = Logger.getLogger("Main");
		
		try
		{
//			et.begin();
			
			//De cq de las 2 formas
			TypedQuery<Tpersona> query=em.createNamedQuery("Tpersona.findAll", Tpersona.class);
//			TypedQuery query=(TypedQuery)em.createNamedQuery("Tpersona.findAll");
			
			List<Tpersona> listaTpersona = query.getResultList();
			
//			et.commit();
			
//			System.out.println(listaTpersona);			
			for(Tpersona item : listaTpersona)
			{
//				System.out.println(item.getNombre()+"......."+item.getCorreoElectronico());
				System.out.printf("%-30s %-10s %-30s\n", item.getNombre(), item.getDocumentoIdentidad(), item.getCorreoElectronico());
			}
			
			log.info("FIN");	
		}
		catch(Exception ex)
		{
//			et.rollback();
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
	}
	
	public static void leerPorDocumentoIdentidad(String valorDocumentoIdentidad)
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ejemplojpa");;
		EntityManager em=emf.createEntityManager();
//		EntityTransaction et=em.getTransaction();
		
		try
		{
//			et.begin();
			TypedQuery<Tpersona> query=em.createNamedQuery("Tpersona.findByDocumentoIdentidad", Tpersona.class);
			query.setParameter("parametroDocumentoIdentidad", valorDocumentoIdentidad);
			
			List<Tpersona> listaTpersona=query.getResultList();
//			et.commit();
			
			for(Tpersona item : listaTpersona)
			{
//				System.out.println(item.getDocumentoIdentidad()+"......."+item.getNombre()+"......."+item.getCorreoElectronico());
				System.out.printf("%-30s %-10s %-30s\n", item.getNombre(), item.getDocumentoIdentidad(), item.getCorreoElectronico());
			}
		}
		catch(Exception ex)
		{
//			et.rollback();
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
	}	
}
