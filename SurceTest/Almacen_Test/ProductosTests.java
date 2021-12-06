package Almacen_Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;

class ProductosTests {

private Almacen almacenTest;
	
	public void SetUp() {
		try {
			almacenTest= new Almacen(new File( "./data/datos.txt" ));
		} catch (AlmacenException e) {
			fail("No se pudo cargar el archivo");
		}
	}
	
	@Test//Verifica que un producto nuevo se agrege y se deja de agregar uno cuyo codigo este ocupado.
	public void testAgregarProducto() {
		SetUp();
		
		try {//Producto nuevo
			almacenTest.agregarProducto("1132A", "24981723", "ProductoTest", "Un producto para testea", 1);
			assertTrue(true);
		} catch (AlmacenException e) {
			fail("Ya existe un producto con el codigo dado");
		}
		try {//Deberia fallar, producto con id usada
			almacenTest.agregarProducto("1131A1", "24981721", "ProductoTest2", "Un producto para testea 2", 1);
			fail("No se debería crear repetido");
		} catch (AlmacenException e) {
			assertTrue(true);
		}
		
		
	}
	
	@Test
	public void EliminarProducto() {
		SetUp();
		almacenTest.eliminarProducto("24981721");
		Categoria cat = almacenTest.darCategoriaRaiz();//Asumiendo que darCategorias() funciona.
		assertTrue(cat.buscarProducto("24981721")==null);
	}
	
	@Test
	public void VenderProducto() {
		SetUp();
		int i =3;
		Categoria cat = almacenTest.darCategoriaRaiz();//Asumiendo que darCategorias() funciona.
		//Vender una cantidad entera positiva
		int v_1=cat.buscarProducto("24981721").darCantidadUnidadesVendidas();
		almacenTest.venderProducto("24981721", i);
		int v_2=cat.buscarProducto("24981721").darCantidadUnidadesVendidas();
		assertEquals(i, v_2-v_1);
		//Vender una cantidad negativa
		i=-3;
		v_1=cat.buscarProducto("24981721").darCantidadUnidadesVendidas();
		almacenTest.venderProducto("24981721", i);
		v_2=cat.buscarProducto("24981721").darCantidadUnidadesVendidas();
		assertNotEquals(i, v_2-v_1);//Se espera que nos e vendan -3 unidades
	}
	
}











