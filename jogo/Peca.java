package jogo;

public abstract class Peca {

	private final int tamanho;

	/**
	 * A posicao onde a peca foi colocada
	 * Para facilitar algumas coisas essa posicao se refere ao ponto mais a esquerda
	 */
	private Posicao posicao;

	/**
	 * Vida eh basicamente o tamanho - acertos
	 */
	private int vida;

	public Peca(int tamanho) {
		this.tamanho = tamanho;
		this.vida = tamanho;
		this.posicao = new Posicao();
	}
	/**
	 * Retorna true se a peca foi destruida, i.e. totalmente atingida
	 */
	public boolean destruido() {
		if(this.vida<=0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Diminui a vida da peca
	 */
	public void atingir() {
		this.vida = vida - 1;
	}
	
	public void reiniciarVida() {
		vida = tamanho;
	}

	/**
	 * Retorna true se a peca ocupa a posicao informada
	 */
	public boolean posicaoOcupada(Posicao posicao) {
		
		for(int i=this.posicao.getColuna(); i<(this.posicao.getColuna() + this.tamanho); i++)
		{
			if(i == posicao.getColuna() && posicao.getLinha() == this.posicao.getLinha()) {
				return true;
			}
		}
		
		return false;
	}
	public int getTamanho() {
		return tamanho;
	}
	public Posicao getPosicao() {
		return posicao;
	}
	

}
