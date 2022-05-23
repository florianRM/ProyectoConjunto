package elementosTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elementos.Coordenada;

class CoordenadaTest {

	@Test
	public void moverJugadorDerecha() {
		Coordenada target = new Coordenada(0, 0);
		assertTrue(target.goRight());
	}
	
	@Test
	public void excederDerecha() {
		Coordenada target = new Coordenada(9, 0);
		assertFalse(target.goRight());
	}
	
	@Test
	public void moverJugadorIzquierda() {
		Coordenada target = new Coordenada(9, 0);
		assertTrue(target.goLeft());
	}
	
	@Test
	public void excederIzquierda() {
		Coordenada target = new Coordenada(0, 0);
		assertFalse(target.goLeft());
	}
	
	@Test
	public void moverJugadorArriba() {
		Coordenada target = new Coordenada(0, 1);
		assertTrue(target.goUp());
	}
	
	@Test
	public void excederArriba() {
		Coordenada target = new Coordenada(0, 0);
		assertFalse(target.goUp());
	}
	
	@Test
	public void moverJugadorAbajo() {
		Coordenada target = new Coordenada(0, 8);
		assertTrue(target.goDown());
	}
	
	@Test
	public void excederAbajo() {
		Coordenada target = new Coordenada(0, 9);
		assertFalse(target.goDown());
	}

}
