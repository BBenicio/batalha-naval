package jogo;
import java.util.Random;

public class Computador extends Jogador {
	public Computador(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}

	/**
	 * Faz o ataque do computador
	 * Escolhe um atirador aleatoriamente entre os disponiveis
	 * Escolhe uma posicao aleatoriaria que seja valida
	 */
	public void atacar(Tabuleiro tabuleiro) {
		Random gerador = new Random();
		int l, c, a;
		boolean isItTrue;
		a = gerador.nextInt(tabuleiro.getAtiradores().length);
		l = gerador.nextInt(10);
		c = gerador.nextInt(10);
		isItTrue = false;
		Posicao posicionamento = new Posicao(l, c);
		atirador = tabuleiro.getAtiradores()[a];
		while(!isItTrue)
		{
			if(tabuleiro.posicaoValida(posicionamento))
			{
				isItTrue = true;
			} else {
				l = gerador.nextInt(10);
				c = gerador.nextInt(10);
				posicionamento.setLinha(l);
				posicionamento.setColuna(c);
			}
		}
		super.atacar(tabuleiro, posicionamento);
			
		// escolher um atirador aleatorio e uma posicao aletoria
		// a posicao tem que ser valida
		// pode chamar super.atacar() pra terminar o ataque
	}

}
