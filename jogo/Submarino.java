package jogo;

/**
 * O submarino tem tipo de disparo Simples
 * Eh uma peca de tamanho 2
 */
public class Submarino extends Peca implements Atirador {

	public Submarino() {
		super(2);
	}

	/**
	 * Retorna 1 posicao: no alvo.
	 */
	public Posicao[] atirar(Posicao posicao) {
		return new Posicao[] {posicao};
	}

	public TipoDeDisparo getTipoDeDisparo() {
		return TipoDeDisparo.SIMPLES;
	}
	
	@Override
	public String toString() {
		return "Submarino";
	}

}
