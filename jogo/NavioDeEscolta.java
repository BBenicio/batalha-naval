package jogo;

/**
 * O navio de escolta tem tipo de disparo Cascata
 * Eh uma peca de tamanho 3
 */
public class NavioDeEscolta extends Peca implements Atirador {

	public NavioDeEscolta() {
		super(3);
	}

	/**
	 * Retorna ate 2 posicoes: no alvo e a sua direita.
	 */
	public Posicao[] atirar(Posicao posicao) {
		return new Posicao[] {posicao, new Posicao(posicao.getLinha(), posicao.getColuna()+1)};
	}

	public TipoDeDisparo getTipoDeDisparo() {
		return TipoDeDisparo.CASCATA;
	}

	@Override
	public String toString() {
		return "Navio de Escolta";
	}
}
