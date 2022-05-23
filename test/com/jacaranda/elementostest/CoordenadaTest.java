package com.jacaranda.elementostest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elementos.Coordenada;

class CoordenadaTest {
	
	@Test
	public void testCoordenadaXPositiva() {
		Coordenada target = new Coordenada(1, 0);
		int expected = 1;
		int actual = target.getX();
		assertEquals("La coordenada x debe ser 1", expected, actual);
	}
	
	@Test
	public void testCoordenadaYPositiva() {
		Coordenada target = new Coordenada(0, 1);
		int expected = 1;
		int actual = target.getY();
		assertEquals("La coordenada y debe ser 1", expected, actual);
	}
	
	@Test
	public void testCoordenadaXSuperaLimite() {
		Coordenada target = new Coordenada(10, 0);
		int expected = 0;
		int actual = target.getX();
		assertEquals("La coordenada x debe ser 0", expected, actual);
	}
	
	@Test
	public void testCoordenadaYSuperaLimite() {
		Coordenada target = new Coordenada(0, 10);
		int expected = 0;
		int actual = target.getY();
		assertEquals("La coordenada y debe ser 0", expected, actual);
	}
	
	@Test
	public void testCoordenadaXNegativa() {
		Coordenada target = new Coordenada(-1, 0);
		int expected = 0;
		int actual = target.getX();
		assertEquals("La coordenada x debe ser 0", expected, actual);
	}
	
	@Test
	public void testCoordenadaYNegativa() {
		Coordenada target = new Coordenada(0, -1);
		int expected = 0;
		int actual = target.getY();
		assertEquals("La coordenada y debe ser 0", expected, actual);
	}

	@Test
	public void testGoRight() {
		Coordenada target = new Coordenada(0, 0);
		assertTrue(target.goRight());
	}
	
	@Test
	public void testGoRightExceder() {
		Coordenada target = new Coordenada(9, 0);
		assertFalse(target.goRight());
	}
	
	@Test
	public void testGoLeft() {
		Coordenada target = new Coordenada(9, 0);
		assertTrue(target.goLeft());
	}
	
	@Test
	public void testGoLeftExceder() {
		Coordenada target = new Coordenada(0, 0);
		assertFalse(target.goLeft());
	}
	
	@Test
	public void testGoUp() {
		Coordenada target = new Coordenada(0, 1);
		assertTrue(target.goUp());
	}
	
	@Test
	public void testGoUpExceder() {
		Coordenada target = new Coordenada(0, 0);
		assertFalse(target.goUp());
	}
	
	@Test
	public void testGoDown() {
		Coordenada target = new Coordenada(0, 8);
		assertTrue(target.goDown());
	}
	
	@Test
	public void testGoDownExceder() {
		Coordenada target = new Coordenada(0, 9);
		assertFalse(target.goDown());
	}
	
}
