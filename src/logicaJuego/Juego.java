package logicaJuego;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import elementos.*;

public class Juego {

	private HashMap<Coordenada, Element> tablero;
	private ArrayList<Coordenada> coordenadaJugadores;
	private int jugadorJuega;
	private int dado; // Dado para ver los movimientos del jugador que juega
	private ArrayList<Jugador> jugadores;

	public Juego(PlayerType[] jugadores) {
		this.tablero = new HashMap<>();
		this.coordenadaJugadores = new ArrayList<>();
		this.jugadorJuega = 0;
		this.dado = 0;
		this.jugadores = new ArrayList<>();
		crearTablero();
		for(int i=0; i < jugadores.length; i++) {
			crearJugador(jugadores[i]);
		}
	}
	
	//Finalised
	private void crearTablero() {
		crearRocas();
		crearGemas();
		crearPociones();
		crearDinero();
	}
	
	//Finalised
	private boolean crearJugador(PlayerType tipo) {
		Coordenada nueva = new Coordenada();
		Jugador nuevoJugador = new Jugador(tipo);
		this.jugadores.add(nuevoJugador);
		this.coordenadaJugadores.add(nueva);
		this.tablero.put(nueva, this.jugadores.get(jugadores.indexOf(nuevoJugador)));
		boolean resultado = false;
		
		return true;
	}
	
	//Finalised
	private void crearRocas() {
		for(int i=0; i < Constantes.NUM_ROCAS; i++) {
			this.tablero.put(new Coordenada(), new Element(ElementType.ROCA));
		}
	}
	
	//Finalised
	private void crearGemas() {
		for(int i=0; i < Constantes.NUM_GEMAS; i++) {
			this.tablero.put(new Coordenada(), new Element(ElementType.GEMA));
		}
	}
	
	//Finalised
	private void crearPociones() {
		for(int i=0; i < Constantes.NUM_POCIONES; i++) {
			this.tablero.put(new Coordenada(), new Element(ElementType.POCION));
		}
	}
	
	//Finalised
	private void crearDinero() {
		for(int i=0; i < Constantes.NUM_DINERO; i++) {
			this.tablero.put(new Coordenada(), new Element(ElementType.DINERO));
		}
	}
	
	/**
	 * Escribe el tablero en formato no gráfico. Devuelve el string con la
	 * información
	 */
	@Override
	public String toString() {
		StringBuilder resul = new StringBuilder();
		resul.append(barra());
		resul.append("     |");

		for (int fila = 0; fila < Constantes.TAMANNO; fila++) {
			for (int columna = 0; columna < Constantes.TAMANNO; columna++) {
				Coordenada coor = new Coordenada(columna, fila);

				if (this.tablero.get(coor) != null) {
					resul.append(" " + this.tablero.get(coor).getType().getSymbol() + " ");
				} else {
					resul.append("   ");
				}

				resul.append("|");
			}
			resul.append("\n");
			resul.append(barra());
			resul.append("     |");
		}
		resul.delete(resul.length() - 5, resul.length());
		return resul.toString();
	}
	
	//Finalised
	public boolean isTerminado() {
		boolean resultado = false;
		int longitud = this.coordenadaJugadores.size();
		boolean contiene = this.tablero.containsValue(new Element(ElementType.DINERO));
		
		if(longitud == 1 || !contiene) {
			resultado = true;
		}
		return resultado;
	}
	
	/**
	 * Simplemente escribe una barra en pantalla
	 * 
	 * @return
	 */
	private String barra() {
		StringBuilder resul = new StringBuilder();
		resul.append("     ");
		for (int i = 0; i < Constantes.TAMANNO * 4; i++) {
			resul.append("-");
		}
		resul.append("\n");
		return resul.toString();
	}
	
	//Finalised
	public String imprimeNombreJugadores() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i < this.jugadores.size(); i++) {
			resultado.append(this.jugadores.get(i).getNombre() + "\n");
		}
		return resultado.toString();
	}
	
	//Finalised
	public String imprimeValoreJugadores() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i < this.jugadores.size(); i++) {
			Jugador aux = this.jugadores.get(i);
			resultado.append(aux.getNombre() + " tiene, dinero:" + aux.getDinero() + ", pociones:" + aux.getPociones() + ", gemas:" + aux.getGemas() + "\n");
		}
		return resultado.toString();
	}
	
	//Finalised
	private void eliminarJugador(Coordenada coord) {
		int posicion = this.coordenadaJugadores.indexOf(coord);
		this.coordenadaJugadores.remove(posicion);
		this.tablero.remove(coord);
		this.jugadores.remove(posicion);
	}
	
	//Finalised
	private Coordenada getNextPosition(char direction) throws JuegoException {
		
		Coordenada aux = null;
		if(direction != 'N' && direction != 'S' && direction != 'E' && direction != 'O') {
			throw new JuegoException("La direccion debe ser N, S, E, O");
		}
		if(this.dado != 0) {
			aux = new Coordenada(obtenerCoordenadaJugadorJuega().getX(), obtenerCoordenadaJugadorJuega().getY());
			if(direction == 'N') {
				aux.goUp();
			}
			else if(direction == 'S') {
				aux.goDown();
			}
			else if(direction == 'E') {
				aux.goRight();
			}
			else if(direction == 'O') {
				aux.goLeft();
			}
		}
		return aux;
	}
	
	//Finalised
	private void cambiaJugadorAPosicion(Coordenada coord) {
		if(this.dado != 0) {
			this.tablero.remove(obtenerCoordenadaJugadorJuega());
			this.tablero.put(coord, this.jugadores.get(jugadorJuega));
			int posicion = this.coordenadaJugadores.indexOf(obtenerCoordenadaJugadorJuega());
			this.coordenadaJugadores.remove(posicion);
			this.coordenadaJugadores.add(posicion, coord);
		}
	}
	
	/**
	 * Mover el jugador
	 * 
	 * @param direction
	 * @return
	 * @throws JuegoException
	 * @throws JugadorException
	 */
	public String movePlayer(char direction) throws JuegoException, JugadorException {
		// Si no es una dirección válida, mando un exception
		String resul = "";
		Jugador jugador = (Jugador) this.tablero.get(this.coordenadaJugadores.get(jugadorJuega));

		Coordenada coordDestino = getNextPosition(direction);

		// Tengo que ver que hay en la nueva casilla
		Element elemento = this.tablero.get(coordDestino);

		if (elemento != null) { // Hay algo en la casilla
			if (elemento instanceof Jugador) {

				Jugador enemigo = (Jugador) elemento;
				int resultadoLucha = jugador.lucha(enemigo);
				switch (resultadoLucha) {
				case Constantes.EMPATE:
					resul = "Empate entre los jugadore";
					break;
				case Constantes.GANA_USA_POCIMA:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita una pócima al enemigo";
					break;
				case Constantes.GANA_DINERO:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					break;
				case Constantes.GANA_MUERE:
					resul = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					this.eliminarJugador(coordDestino);
					// Si se elimina el jugador que juega el dado se pone a 0 para que no siga
					// tirando
					break;
				case Constantes.PIERDE_USA_POCIMA:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita una pócima al jugador";
					break;
				case Constantes.PIERDE_DINERO:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					break;
				case Constantes.PIERDE_MUERE:
					resul = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					this.eliminarJugador(this.coordenadaJugadores.get(jugadorJuega));
					dado = 0;
					// Decrementamos en uno el jugador, para que no se salte al siguiente
					// ya que al borrarlo el siguiente apunta al siguiente, y al incrementarlo
					// se le salta
					this.jugadorJuega--;
					break;
				}
				// Después de la lucha los jugadores no se mueven
			} else if (elemento.getType() == ElementType.ROCA) {
				int resultadoRoca = jugador.encuentraRoca();
				switch (resultadoRoca) {
				case Constantes.ROMPE_ROCA_CON_GEMA:
					resul = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					this.cambiaJugadorAPosicion(coordDestino);
					break;

				case Constantes.GANA_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " gana a la roca";
					this.cambiaJugadorAPosicion(coordDestino);
					break;

				case Constantes.PIERDE_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					break;
				}
			} else if (elemento.getType() == ElementType.GEMA) {
				jugador.encuentraGema();
				this.cambiaJugadorAPosicion(coordDestino);

			} else if (elemento.getType() == ElementType.DINERO) {
				jugador.encuentraDinero();
				this.cambiaJugadorAPosicion(coordDestino);

			} else if (elemento.getType() == ElementType.POCION) {
				jugador.encuentraPocion();
				this.cambiaJugadorAPosicion(coordDestino);

			}

		} else {
			this.cambiaJugadorAPosicion(coordDestino);
		}

		return resul;
	}
	
	//Finalised
	public void proximoJugador() {
		if(this.jugadorJuega == this.jugadores.size() - 1) {
			this.jugadorJuega = 0;
		} else {
			this.jugadorJuega++;
		}
	}
	
	//Finalised
	public String getGanador() {
		String resultado = "";
		if(isTerminado()) {
			if(this.coordenadaJugadores.size() == 1) {
				Coordenada ultimoJugador = this.coordenadaJugadores.get(0);
				resultado = this.tablero.get(ultimoJugador).getType().toString();
			} else {
				resultado = this.tablero.get(obtenerCoordenadaJugadorJuega()).getType().toString();
			}
		}
		return resultado;
	}
	
	//Finalised
	public String getNombreJuegadorQueJuega() {
		return this.jugadores.get(jugadorJuega).getNombre();
	}
	
	//Finalised
	public int getMovimientoJugador() {
		return this.dado;
	}
	
	//Finalised
	public int getValorDado() {
		return this.dado;
	}
	
	//Finalised
	public void decrementaDado() {
		this.dado--;
	}
	
	//Finalised
	public void setDado() {
		Jugador aux = this.jugadores.get(jugadorJuega);
		this.dado = aux.getVelocidadParaLuchar();
	}
	
	//Finalised
	public Element obtenerElementoTablero(Coordenada coord) {
		return this.tablero.get(coord);
	}
	
	//Finalised
	public Coordenada obtenerCoordenadaJugadorJuega() {
		return this.coordenadaJugadores.get(this.jugadorJuega);
	}
}
