package jogo;
import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {

	/**
	 * Todas as peças em jogo, não importando se estão destruídas
	 */
	private ArrayList<Peca> pecas;

	/**
	 * Todos os tiros que foram disparados neste tabuleiro, usado para impedir tiros repetidos
	 */
	private ArrayList<Posicao> tiros;
	
	public Tabuleiro() {
		tiros = new ArrayList<Posicao>();
		pecas = new ArrayList<Peca>();
	}

	public static Tabuleiro tabuleiroAleatorio() {
		Random gerador = new Random();
		int l, c;
		Tabuleiro tabuleiro = new Tabuleiro();
		Peca[] pecasParaAdicionar = new Peca[]{new Caca(), new NavioDeEscolta(), new Submarino(), new PortaAviao()};
		for(int i=0; i<4; i++)
		{
			Peca peca = pecasParaAdicionar[i];
			l = gerador.nextInt(10);
			c = gerador.nextInt(10 - (peca.getTamanho()-1));
			boolean ocupada = false;
			for(int j=c; j<c+peca.getTamanho();j++)
			{
				if(tabuleiro.posicaoOcupada(new Posicao(l, j)))
				{
					ocupada = true;
				}
			}
			if(ocupada)
			{
				i--;
			} else {
				peca.getPosicao().setLinha(l);
				peca.getPosicao().setColuna(c);
				tabuleiro.addPeca(peca);
			}
		}
		return tabuleiro;
	}

	/**
	 * Retorna true se existe uma peca na posicao informada, usada para dar uma dica
	 */
	public boolean posicaoOcupada(Posicao posicao) {
		for(int i=0; i<pecas.size(); i++)
		{
			if(pecas.get(i).posicaoOcupada(posicao)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna true se a posicao eh valida para receber tiro
	 */
	public boolean posicaoValida(Posicao posicao) {
		if(tiros.indexOf(posicao) == -1){
			if(posicao.getLinha()<10 && posicao.getColuna()<10 && posicao.getLinha()>=0 && posicao.getColuna()>=0)
			{
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean atingir(Posicao posicao) {
		if(posicaoValida(posicao) && posicaoOcupada(posicao)) {
			for(int i=0; i<pecas.size(); i++) 
			{
				if(pecas.get(i).posicaoOcupada(posicao)) {
					pecas.get(i).atingir();
				}
			}
			tiros.add(posicao);
			return true;
		} else if(posicaoValida(posicao)) {
			tiros.add(posicao);
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Retorna true se todas as pecas deste tabuleiro foram destruidas
	 */
	public boolean destruido() {
		for(int i=0; i<pecas.size(); i++) 
		{
			if(!pecas.get(i).destruido()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retorna um vetor com todos os Atiradores que nao foram destruidos
	 */
	public Atirador[] getAtiradores() {
		ArrayList<Atirador> atiradores = new ArrayList<Atirador>();
		for(int i=0; i<pecas.size(); i++)
		{
			if(pecas.get(i) instanceof Atirador && !pecas.get(i).destruido())
			{
				atiradores.add((Atirador)pecas.get(i));
			}
		}
		
		Atirador[] array = new Atirador[atiradores.size()];
		return atiradores.toArray(array);
	}

	public Peca getPeca(int i) {
		return pecas.get(i);
	}
	
	/**
	 * Adiciona uma peca no tabuleiro
	 */
	public void addPeca(Peca peca) {
		pecas.add(peca);
	}

	/**
	 * Limpa todas as pecaas do tabuleiro
	 */
	public void removerPecas() {
		pecas.clear();
	}

	/**
	 * Limpa todos os tiros do tabuleiro
	 */
	public void removerTiros() {
		tiros.clear();
	}
	
	public void reiniciar() {
		removerTiros();
		for (Peca peca : pecas) {
			peca.reiniciarVida();
		}
	}

}
