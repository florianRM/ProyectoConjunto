package com.jacaranda.logicajuegotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elementos.Coordenada;
import elementos.Jugador;
import elementos.JugadorException;
import elementos.PlayerType;
import logicaJuego.Juego;
import logicaJuego.JuegoException;

class JuegoTest {
	
	@Test
	public void testImprimirNombres() {
		Juego juego = new Juego(PlayerType.values());
		PlayerType[] jugadores = PlayerType.values();
		String expected = "";
		
		for(int i=0; i < jugadores.length; i++) {
			expected += jugadores[i].name() + "\n";
		}
		String actual = juego.imprimeNombreJugadores();
		
		assertEquals("Los nombres no se imprimen bien", expected, actual);
	}
	
	@Test
	public void testImprimirValores() {
		Juego juego = new Juego(PlayerType.values());
		PlayerType[] jugadores = PlayerType.values();
		String expected = "";
		for(int i=0; i < jugadores.length; i++) {
			Jugador aux = new Jugador(jugadores[i]);
			expected += aux.getNombre() + " tiene, dinero:" + aux.getDinero() + ", pociones:" + aux.getPociones() + ", gemas:" + aux.getGemas() + "\n";
		}
		
		String actual = juego.imprimeValoreJugadores();
		
		assertEquals("Los nombres no se imprimen bien", expected, actual);
	}
	
	@Test
	public void testIsTerminado() {
		PlayerType[] jugador = new PlayerType[]{PlayerType.ELFO};
		
		Juego target = new Juego(jugador);
		boolean expected = true;
		boolean actual = target.isTerminado();
		
		assertEquals("El juego debe acabarse por tener solo 1 jugador",expected, actual);
	}
	
	@Test
	public void testIsTerminadoFalso() {
		Juego juego = new Juego(PlayerType.values());
		boolean actual = juego.isTerminado();
		assertFalse("Se esperaba que el resultado sea true", actual);
	}
	
	@Test
	public void testMovePlayerNorte() throws JuegoException, JugadorException {
		Juego juego = new Juego(PlayerType.values());
		String actual = " ";
		Coordenada auxPrincipio = juego.obtenerCoordenadaJugadorJuega();
		
		while(!actual.equals("")) {
			juego.setDado();
			actual = juego.movePlayer('N');
		}
		
		Coordenada auxSegunda = juego.obtenerCoordenadaJugadorJuega();
		int coordenadaY = auxSegunda.getY();
		
		if(auxPrincipio.getY() == coordenadaY) {
			assertEquals("No debía moverse", coordenadaY, auxPrincipio.getY());
		} else {
			assertNotEquals("Debía moverse al norte", coordenadaY, auxPrincipio.getY());
		}
	}
	
	@Test
	public void testMovePlayerSur() throws JuegoException, JugadorException {
		Juego juego = new Juego(PlayerType.values());
		String actual = " ";
		Coordenada auxPrincipio = juego.obtenerCoordenadaJugadorJuega();
		
		while(!actual.equals("")) {
			juego.setDado();
			actual = juego.movePlayer('S');
		}
		
		Coordenada auxSegunda = juego.obtenerCoordenadaJugadorJuega();
		int coordenadaY = auxSegunda.getY();
		
		if(auxPrincipio.getY() == coordenadaY) {
			assertEquals("No debía moverse", coordenadaY, auxPrincipio.getY());
		} else {
			assertNotEquals("Debía moverse al sur", coordenadaY, auxPrincipio.getY());
		}
	}
	
	@Test
	public void testMovePlayerEste() throws JuegoException, JugadorException {
		Juego juego = new Juego(PlayerType.values());
		String actual = " ";
		Coordenada auxPrincipio = juego.obtenerCoordenadaJugadorJuega();
		
		while(!actual.equals("")) {
			juego.setDado();
			actual = juego.movePlayer('E');
		}
		
		Coordenada auxSegunda = juego.obtenerCoordenadaJugadorJuega();
		int coordenadaX = auxSegunda.getX();
		
		if(auxPrincipio.getX() == coordenadaX) {
			assertEquals("No debía moverse", coordenadaX, auxPrincipio.getX());
		} else {
			assertNotEquals("Debía moverse al este", coordenadaX, auxPrincipio.getX());
		}
	}
	
	@Test
	public void testMovePlayerOeste() throws JuegoException, JugadorException {
		Juego juego = new Juego(PlayerType.values());
		String actual = " ";
		
		Coordenada auxPrincipio = juego.obtenerCoordenadaJugadorJuega();
		
		while(!actual.equals("")) {
			juego.setDado();
			actual = juego.movePlayer('O');
		}
		
		Coordenada auxSegunda = juego.obtenerCoordenadaJugadorJuega();
		int coordenadaX = auxSegunda.getX();
		
		if(auxPrincipio.getX() == coordenadaX) {
			assertEquals("No debía moverse", coordenadaX, auxPrincipio.getX());
		} else {
			assertNotEquals("Debía seguir moviendose al oeste", coordenadaX, auxPrincipio.getX());
		}
	}

	@Test
	public void testExceptionMovePlayer() {
		Juego juego = new Juego(PlayerType.values());
		try {
			juego.movePlayer('A');
			fail("Error debería devolver una exepción");
		} catch (JuegoException e) {
			System.out.println("Exepción por carácter no válido: " + e.getMessage());
		} catch (JugadorException e) {
			System.out.println("Exepción tipo JugadorException: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testGetMovimientoJugador() {
		Juego juego = new Juego(PlayerType.values());
		PlayerType aux = PlayerType.valueOf(juego.getNombreJuegadorQueJuega());
		for(int i=0; i < 20; i++) {
			int actual = juego.getMovimientoJugador();
			assertTrue(actual >= 0 && actual <= aux.getVelocidad(), "El movimiento supera el rango");
		}
	}
	
	@Test
	public void testGetGanador() {
		PlayerType[] aux = new PlayerType[]{PlayerType.ELFO};
		Juego juego = new Juego(aux);
		String expected = "ELFO";
		String actual = juego.getGanador();
		assertEquals("Imprime un nombre incorrecto", expected, actual);
	}

}
