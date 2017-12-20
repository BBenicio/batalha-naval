package jogo;

/**
 * O PortaAviao nao atira, e nao possui nenhum comportamento especial.
 * Eh uma peca de tamanho 4
 */

public class PortaAviao extends Peca {

	public PortaAviao() {
		super(4);
	}

	@Override
	public String toString() {
		return "Porta Aviao";
	}
}
