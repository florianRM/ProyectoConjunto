package com.jacaranda.elementostest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elementos.Jugador;
import elementos.JugadorException;
import elementos.PlayerType;
import logicaJuego.Constantes;

class JugadorTest {
	Jugador j1 = new Jugador(PlayerType.ELFO);
	Jugador j2 = new Jugador(PlayerType.GUERRERO);
	Jugador j3 = new Jugador(PlayerType.MAGO);
	Jugador j4 = new Jugador(PlayerType.OGRO);
	
	@Test
	public void testGetFuerzaParaLuchar() {
		for(int i=0; i < 20; i++) {
			int fuerzaJ1 = j1.getFuerzaParaLuchar();
			int fuerzaJ2 = j2.getFuerzaParaLuchar();
			int fuerzaJ3 = j3.getFuerzaParaLuchar();
			int fuerzaJ4 = j4.getFuerzaParaLuchar();
			
			assertTrue(fuerzaJ1 >= 0 && fuerzaJ1 <= PlayerType.ELFO.getFuerza(), "La fuerza del J1 excede el límite en el rango");
			assertTrue(fuerzaJ2 >= 0 && fuerzaJ2 <= PlayerType.GUERRERO.getFuerza(), "La fuerza del J2 excede el límite en el rango");
			assertTrue(fuerzaJ3 >= 0 && fuerzaJ3 <= PlayerType.MAGO.getFuerza(), "La fuerza del J3 excede el límite en el rango");
			assertTrue(fuerzaJ4 >= 0 && fuerzaJ4 <= PlayerType.OGRO.getFuerza(), "La fuerza del J4 excede el límite en el rango");
		}
	}
	
	public void testGetMagiaParaLuchar() {
		for(int i=0; i < 20; i++) {
			int magiaJ1 = j1.getMagiaParaLuchar();
			int magiaJ2 = j2.getMagiaParaLuchar();
			int magiaJ3 = j3.getMagiaParaLuchar();
			int magiaJ4 = j4.getMagiaParaLuchar();
			
			assertTrue(magiaJ1 >= 0 && magiaJ1 <= PlayerType.ELFO.getMagia(), "La magia del J1 excede el límite en el rango");
			assertTrue(magiaJ2 >= 0 && magiaJ2 <= PlayerType.GUERRERO.getMagia(), "La magia del J2 excede el límite en el rango");
			assertTrue(magiaJ3 >= 0 && magiaJ3 <= PlayerType.MAGO.getMagia(), "La magia del J3 excede el límite en el rango");
			assertTrue(magiaJ4 >= 0 && magiaJ4 <= PlayerType.OGRO.getMagia(), "La magia del J4 excede el límite en el rango");
		}
	}
	
	@Test
	public void testGetVelocidadParaLuchar() {
		for(int i=0; i < 20; i++) {
			int velocidadJ1 = j1.getVelocidadParaLuchar();
			int velocidadJ2 = j2.getVelocidadParaLuchar();
			int velocidadJ3 = j3.getVelocidadParaLuchar();
			int velocidadJ4 = j4.getVelocidadParaLuchar();
			
			assertTrue(velocidadJ1 >= 0 && velocidadJ1 <= PlayerType.ELFO.getVelocidad(), "La velocidad del J1 excede el límite en el rango");
			assertTrue(velocidadJ2 >= 0 && velocidadJ2 <= PlayerType.GUERRERO.getVelocidad(), "La velocidad del J2 excede el límite en el rango");
			assertTrue(velocidadJ3 >= 0 && velocidadJ3 <= PlayerType.MAGO.getVelocidad(), "La velocidad del J3 excede el límite en el rango");
			assertTrue(velocidadJ4 >= 0 && velocidadJ4 <= PlayerType.OGRO.getVelocidad(), "La velocidad del J4 excede el límite en el rango");
		}
	}
	
	@Test
	public void testLucha() throws JugadorException {
		for(int i=0; i < 30; i++) {
			int ganador = j1.lucha(j2);
			
			assertTrue(ganador == 0 || ganador == 3 || ganador == 6, "El resultado no corresponde");
		}
	}
	
	@Test
	public void testLuchaDinero() throws JugadorException {
		for(int i=0; i < 30; i++) {
			j1.setDinero(1);
			j2.setDinero(1);
			int ganador = j1.lucha(j2);
			
			if(ganador == 2) {
				assertTrue(j2.getDinero() == 0, "J2 debería perder el dinero");
			} else if(ganador == 5) {
				assertTrue(j1.getDinero() == 0, "J1 debería perder el dinero");
			} else if(ganador == 0) {
				assertTrue(j1.getDinero() == 1 && j2.getDinero() == 1, "Ninguno debería perder el dinero");
			}
		}
	}
	
	@Test
	public void testLuchaPociones() throws JugadorException {
		for(int i=0; i < 30; i++) {
			j1.setPociones(1);
			j2.setPociones(1);
			int ganador = j1.lucha(j2);
			
			if(ganador == 1) {
				assertTrue(j2.getPociones() == 0, "J2 debería perder las pociones");
			} else if(ganador == 4) {
				assertTrue(j1.getPociones() == 0, "J1 debería perder las pociones");
			} else if(ganador == 0) {
				assertTrue(j1.getPociones() == 1 && j2.getPociones() == 1, "Ninguno debería perder las pociones");
			}
		}
	}
	
	@Test
	public void testEncuentraRoca() {
		for(int i=0; i < 20; i++) {
			int ganador = j1.encuentraRoca();
			assertTrue(ganador == 1 || ganador == 2);
		}
	}
	
	@Test
	public void testEncuentraRocaConGema() {
		try {
			j1.setGemas(1);
		} catch (JugadorException e) {
		}
		int expected = 0;
		int expected2 = 0;
		int actual = j1.encuentraRoca();
		int actual2 = j1.getGemas();
		
		assertEquals(expected, actual);
		assertEquals("El jugador debia perder la gema", expected2, actual2);
	}
	
	@Test
	public void testExceptionSetDinero() {
		try {
			j1.setDinero(-1);
			assertEquals("El dinero se ha asginado", j1.getDinero(), -1);
			fail("Error, debía lanzarse una excepción");
		} catch (JugadorException e) {
			System.out.println("Excepcíon de número negativo: " + e.getMessage());
		}
	}
	
	@Test
	public void testExceptionSetPociones() {
		try {
			j1.setPociones(-1);
			fail("Error, debía lanzarse una excepción");
		} catch (JugadorException e) {
			System.out.println("Excepcíon de número negativo: " + e.getMessage());
		}
	}
	
	@Test
	public void testExceptionSetGemas() {
		try {
			j1.setGemas(-1);
			fail("Error, debía lanzarse una excepción");
		} catch (JugadorException e) {
			System.out.println("Excepcíon de número negativo: " + e.getMessage());
		}
	}

}
