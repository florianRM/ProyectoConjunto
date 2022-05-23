package elementos;

import java.util.Objects;
import java.util.Random;

import logicaJuego.Constantes;

public class Coordenada {
	private int x;
	private int y;
	private Random r;
	
	public Coordenada() {
		this.r = new Random();
		this.x = r.nextInt(Constantes.TAMANNO);
		this.y = r.nextInt(Constantes.TAMANNO);
	}
	
	public Coordenada(int x, int y) {
		if((x >= 0 && x < Constantes.TAMANNO) && (y >= 0 && y < Constantes.TAMANNO)) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x = 0;
			this.y = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Coordenada [x=" + x + ", y=" + y + "]";
	}
	
	public boolean goRight() {
		boolean mover = true;
		if (this.x == Constantes.TAMANNO - 1) {
			mover = false;
		} else {
			this.x ++;
		}
		return mover;
	}
	
	public boolean goLeft() {
		boolean mover = true;
		if (this.x == 0) {
			mover = false;
		} else {
			this.x --;
		}
		return mover;
	}
	
	public boolean goUp() {
		boolean mover = true;
		if (this.y == 0) {
			mover = false;
		} else {
			this.y--;
		}
		return mover;
	}
	
	public boolean goDown() {
		boolean mover = true;
		if (this.y == Constantes.TAMANNO - 1) {
			mover = false;
		} else {
			this.y++;
		}
		return mover;
	}
	
	@Override
	public Coordenada clone() throws CloneNotSupportedException {
		Coordenada resultado;
		resultado = new Coordenada(this.x, this.y);
		return resultado;
	}
	
}
