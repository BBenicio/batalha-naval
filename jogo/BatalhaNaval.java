package jogo;

public class BatalhaNaval {

	private Jogador usuario;
	private Computador computador;
	
	public BatalhaNaval(Jogador usuario, Computador computador) {
		this.usuario = usuario;
		this.computador = computador;
	}
	

	public Jogador getUsuario() {
		return usuario;
	}

	public Computador getComputador() {
		return computador;
	}

	/**
	 * Retorna true se o jogo chegou ao fim
	 * O jogo termina se algum tabuleiro esta completamente destruido
	 * Uma opcao eh terminar tambem quando alguem nao tem mais nenhum Atirador
	 */
	public boolean jogoTerminado() {
		return usuario.perdeu() || computador.perdeu();
	}

	/**
	 * Faz uma rodada, atira com o usuario na posicao informada
	 * Depois atira com o computador
	 * Lembre de verificar se o jogo terminou depois que esse metodo terminar
	 */
	public void atirar(Posicao posicao) {
		usuario.atacar(computador.getTabuleiro(), posicao);
		
		if (!computador.perdeu()) {
			computador.atacar(usuario.getTabuleiro());
		}
	}
	
	public void reiniciar() {
		usuario.getTabuleiro().reiniciar();
		usuario.getAcertos().clear();
		usuario.getTirosNaAgua().clear();
		
		computador.getTabuleiro().reiniciar();
		computador.getAcertos().clear();
		computador.getTirosNaAgua().clear();
	}

}
