package jogo;

/**
 * O caca tem tipo de disparo Estrela
 * Eh uma pecaa de tamanho 2
 */
public class Caca extends Peca implements Atirador {

	public Caca() {
		super(2);
	}

	/**
	 * Retorna 5 posicoes: acima, abaixo, a esquerda, a direita e no alvo.
	 */
	public Posicao[] atirar(Posicao posicao) {
		return new Posicao[] {posicao, new Posicao(posicao.getLinha(), posicao.getColuna()-1), 
				new Posicao(posicao.getLinha(), posicao.getColuna()+1), 
				new Posicao(posicao.getLinha()-1, posicao.getColuna()), 
				new Posicao(posicao.getLinha()+1, posicao.getColuna())};
	}

	public TipoDeDisparo getTipoDeDisparo() {
		return TipoDeDisparo.ESTRELA;
	}

	@Override
	public String toString() {
		return "Caca";
	}
}
