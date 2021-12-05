package Almacen_Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;

class AgregarNodoTest {

	
	private Almacen almacenTest;
	
	public void SetUp() {
		try {
			almacenTest= new Almacen(new File( "./data/datos.txt" ));
		} catch (AlmacenException e) {
			fail("No se pudo cargar el archivo");
		}
	}
	
	@Test//Buscar un nodo
	public void testBuscarNodo1() {
		SetUp();
		NodoAlmacen nodo =almacenTest.buscarNodo("1111");
		assertTrue(nodo.darNombre().equals("SAMSUNG"),"El nodo es el esperado");
		assertTrue(nodo.darTipo().equals("Marca"),"El nodo es el esperado");
	}
	
	@Test//Buscar el ultimo nodo cargado
	public void testBuscarNodo2() {
		SetUp();
		NodoAlmacen nodo =almacenTest.buscarNodo("1132B2");
		assertTrue(nodo.darNombre().equals("Muebles&Accesorios"),"El nodo es el esperado");
		assertTrue(nodo.darTipo().equals("Marca"),"El nodo es el esperado");
	}
	
	@Test//Agregar un nodo
	public void testAgregarNodo() {
		SetUp();
		try {
			almacenTest.agregarNodo("11", "Marca", "9999", "MarcaTest");
		} catch (AlmacenException e) {
			fail("Error al agregar nodo");
		}
		NodoAlmacen nodo =almacenTest.buscarNodo("9999");
		assertTrue(nodo.darNombre().equals("MarcaTest"),"El nodo es el esperado");
	}
	
	@Test//Agregar un nodo a un padre inexistente
	public void testAgregarNodoFail() {
		SetUp();
		try {
			almacenTest.agregarNodo("99", "Marca", "9998", "MarcaTest");
		} catch (AlmacenException e) {
			fail("Error al agregar nodo");
		}
		NodoAlmacen nodo =almacenTest.buscarNodo("9998");
		
		assertTrue(nodo==null,"El nodo no fue creado");
	}
	
}
















