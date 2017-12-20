package jogo;

public interface Atirador {

	/**
	 * Retorna todas as posicoes que serao atingidas pelo tiro desse Atirador
	 */
	public abstract Posicao[] atirar(Posicao posicao);

	public abstract TipoDeDisparo getTipoDeDisparo();

}
