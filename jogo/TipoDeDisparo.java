package jogo;

public enum TipoDeDisparo {

	SIMPLES,

	CASCATA,

	ESTRELA;
	
	@Override
	public String toString() {
		switch (this) {
		case SIMPLES:
			return "Simples";
		case CASCATA:
			return "Cascata";
		case ESTRELA:
			return "Estrela";
		}
		
		return ""; // nunca eh alcancado mas o Eclipse ta me xingando...
	}
}
