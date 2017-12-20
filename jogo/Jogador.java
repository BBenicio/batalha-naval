package jogo;

import java.util.ArrayList;

/**
 * Representa um jogador, com tabuleiro de defesa
 */
public class Jogador {

	/**
	 * Tabuleiro de defesa deste jogador
	 */
	protected Tabuleiro tabuleiro;

	/**
	 * Atirador selecionado para realizar o proximo tiro
	 */
	protected Atirador atirador;
	
	protected ArrayList<Posicao> acertos;
	protected ArrayList<Posicao> tirosNaAgua;

	public Jogador(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		atirador = null;
		
		acertos = new ArrayList<Posicao>();
		tirosNaAgua = new ArrayList<Posicao>();
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public Atirador getAtirador() {
		return atirador;
	}
	
	public void setAtirador(Atirador atirador) {
		this.atirador = atirador;
	}

	public ArrayList<Posicao> getAcertos() {
		return acertos;
	}

	public ArrayList<Posicao> getTirosNaAgua() {
		return tirosNaAgua;
	}

	/**
	 * Retorna true se este jogador perdeu (nenhum atirador disponivel)
	 */
	public boolean perdeu() {
		if(tabuleiro.getAtiradores().length==0)
		{
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Atira com o atirador selecionado na posicao informada
	 * Recebe o tabuleiro para ataque
	 * [Eh uma boa filtrar as posicoes invalidas antes de chamar tabuleiro.atingir()]
	 */
	public void atacar(Tabuleiro tabuleiro, Posicao posicao) {
			for(int i=0; i<atirador.atirar(posicao).length; i++)
			{
				Posicao pokemon = atirador.atirar(posicao)[i];
				if(tabuleiro.posicaoValida(pokemon))
				{
					if(tabuleiro.atingir(pokemon))
					{
						acertos.add(pokemon);
					} else {
						tirosNaAgua.add(pokemon);
					}
				}
			}
		// adiciona os tiros certos na lista acertos e os errados na tirosNaAgua
		// essas listas sao usadas para desenhar na tela
	}

}
