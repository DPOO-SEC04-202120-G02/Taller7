package uniandes.DPOO.categoria.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.*;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.*;

public class CategoriaTest {
	
	private Almacen almacen;
	private Categoria categoriaPrueba1;
	
	@BeforeEach
	public void setup() {
		try {
			this.almacen= new Almacen(new File( "./data/datos.txt" ));
		} 
		catch (AlmacenException e) {
			fail("No se pudo cargar el archivo");
		}
		categoriaPrueba1 = (Categoria) almacen.buscarNodo("1");
	}
	
	@Test
	public void darNodos() {
		assertFalse(categoriaPrueba1.darNodos().isEmpty());
	}
	@Test
	public void tieneHijo() {
		assertTrue(categoriaPrueba1.tieneHijo("11"));
	}
	
	@Test
	public void buscarProducto() {
		assertEquals(categoriaPrueba1.buscarProducto("24881271").darNombre(), "LED 55\" Full HD Smart TV");
	}
	
	@Test
	public void darMarcas () {
		assertFalse(categoriaPrueba1.darMarcas().isEmpty());
	}
	
	@Test
	public void darPreorden() {
		assertEquals(categoriaPrueba1.darPreorden().get(0), categoriaPrueba1);
	}
	
	@Test
	public void darPosorden() {
		assertTrue(categoriaPrueba1.darPosorden().lastIndexOf(categoriaPrueba1) == categoriaPrueba1.darPosorden().size() - 1);
	}
	
	@Test 
	public void darValorVentas() {
		assertEquals(categoriaPrueba1.darValorVentas(), 0);
	}

}
