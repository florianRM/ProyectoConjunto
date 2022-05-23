package elementos;

import java.util.Objects;
import java.util.Random;

import logicaJuego.Constantes;

public class Jugador extends Element {
	private int dinero;
	private int pociones;
	private PlayerType player;
	private int gemas;
	private Random r;

	public Jugador(PlayerType tipo) {
		super(ElementType.valueOf(tipo.name()));
		this.player = tipo;
		r= new Random();
	}

	public String getNombre() {
		return this.player.toString();
	}

	public int getFuerzaParaLuchar() {
		return r.nextInt(getFuerza());
	}

	private int getFuerza() {
		return this.player.getFuerza();
	}

	private int getMagia() {
		return this.player.getMagia();
	}

	public int getMagiaParaLuchar() {
		return r.nextInt(getMagia());
	}

	private int getVelocidad() {
		return this.player.getVelocidad()+1;
	}

	public int getVelocidadParaLuchar() {
		return r.nextInt(getVelocidad());
	}

	public int getDinero() {
		return this.dinero;
	}

	public void setDinero(int dinero) throws JugadorException {
		if (dinero < 0) {
			throw new JugadorException("El dinero no puede ser negativo.");
		}
		this.dinero = dinero;
	}

	public int getPociones() {
		return pociones;
	}

	public void setPociones(int pociones) throws JugadorException {
		if (pociones < 0) {
			throw new JugadorException("Las pociones no pueden ser negativas.");
		}
		this.pociones = pociones;
	}

	public int getGemas() {
		return gemas;
	}

	public void setGemas(int gemas) throws JugadorException {
		if (gemas < 0) {
			throw new JugadorException("Las gemas no pueden ser negativas.");
		}
		this.gemas = gemas;
	}

	public String resumen() {
		return "El jugador " + this.player + " tiene " + this.dinero + " dinero " + this.gemas + " gemas "
				+ this.pociones + " pociones.";
	}

	public PlayerType getPlayer() {
		return player;
	}

	public int lucha(Jugador enemigo) throws JugadorException {
		int resultado = Constantes.EMPATE;
		int fuerzaJugador = this.getFuerzaParaLuchar();
		int fuerzaEnemigo = enemigo.getFuerzaParaLuchar();
		
		if(fuerzaJugador > fuerzaEnemigo && enemigo.pociones > 0) {
			enemigo.pociones--;
			resultado = Constantes.GANA_USA_POCIMA;
		}
		else if(fuerzaJugador > fuerzaEnemigo && enemigo.dinero > 1) {
			this.dinero += enemigo.dinero;
			enemigo.setDinero(0);
			resultado = Constantes.GANA_DINERO;
		}
		else if(fuerzaJugador > fuerzaEnemigo) {
			resultado = Constantes.GANA_MUERE;
		}
		else if(fuerzaJugador < fuerzaEnemigo && this.pociones > 0) {
			this.pociones--;
			resultado = Constantes.PIERDE_USA_POCIMA;
		}
		else if(fuerzaJugador < fuerzaEnemigo && this.dinero > 0) {
			enemigo.dinero += this.dinero;
			this.setDinero(0);
			resultado = Constantes.PIERDE_DINERO;
		}
		else if(fuerzaJugador < fuerzaEnemigo) {
			resultado = Constantes.PIERDE_MUERE;
		}
		return resultado;
	}

	public int encuentraRoca() {
		int resultado = Constantes.PIERDE_A_LA_ROCA;
		int magia = this.getMagiaParaLuchar();

		if (this.gemas > 0) {
			this.gemas--;
			resultado = Constantes.ROMPE_ROCA_CON_GEMA;
		} else if (magia > 4) {
			resultado = Constantes.GANA_A_LA_ROCA;
		}
		return resultado;
	}

	public void encuentraDinero() {
		this.dinero++;
	}

	public void encuentraPocion() {
		this.pociones++;
	}

	public void encuentraGema() {
		this.gemas++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(player);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return player == other.player;
	}
	
}
