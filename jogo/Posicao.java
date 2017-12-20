package jogo;

public class Posicao {

	private int linha;
	private int coluna;
	
	public Posicao() {
		this(0, 0);
	}
	
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public boolean equals(Object o) {
		Posicao p = (Posicao) o;
		return linha == p.linha && coluna == p.coluna;
	}

}
