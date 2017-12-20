package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jogo.Atirador;
import jogo.BatalhaNaval;
import jogo.Computador;
import jogo.Jogador;
import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;
import jogo.TipoDeDisparo;

public class JogoFrame extends JFrame implements BotaoListener {
	private JLabel contentPane;
	
	private GridPanel defesa;
	private GridPanel ataque;
	private JComboBox<TipoDeDisparo> tipoDeDisparo;
	private JLabel tipoDeDisparoLabel;
	private JButton dicaButton;
	private JButton sairButton;
	
	private BatalhaNaval batalhaNaval;
	private int dicas;
	
	public JogoFrame() {
		this(Tabuleiro.tabuleiroAleatorio(), Tabuleiro.tabuleiroAleatorio());
	}
	
	public JogoFrame(Tabuleiro tabuleiroDoJogador) {
		this(tabuleiroDoJogador, Tabuleiro.tabuleiroAleatorio());
	}
	
	/**
	 * Create the frame.
	 */
	public JogoFrame(Tabuleiro tabuleiroDoJogador, Tabuleiro tabuleiroDoComputador) {
		this(new BatalhaNaval(new Jogador(tabuleiroDoJogador), new Computador(tabuleiroDoComputador)));
	}
	
	public JogoFrame(BatalhaNaval batalhaNaval) {
		inicializaFrame();
				
		this.batalhaNaval = batalhaNaval;
		dicas = 3;
		
		inicializaIcones();
	}
	
	private void inicializaFrame() {
		setResizable(false);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 465);
		
		setIconImage(new ImageIcon("img/icone.png").getImage());
		
		contentPane = new JLabel(new ImageIcon("img/Jogo_bg.png"));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {10, 150, 440, 440, 10, 0};
		gbl_contentPane.rowHeights = new int[] {10, 30, 30, 30, 320, 30, 10, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
		tipoDeDisparoLabel = new JLabel("Tipo de Disparo");
		tipoDeDisparoLabel.setForeground(Color.WHITE);
		tipoDeDisparoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_tipoDeDisparoLabel = new GridBagConstraints();
		gbc_tipoDeDisparoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoDeDisparoLabel.gridx = 1;
		gbc_tipoDeDisparoLabel.gridy = 1;
		contentPane.add(tipoDeDisparoLabel, gbc_tipoDeDisparoLabel);
		
		defesa = new GridPanel();
		defesa.setEnabledAll(false);
		GridBagConstraints gbc_defesa = new GridBagConstraints();
		gbc_defesa.gridheight = 5;
		gbc_defesa.anchor = GridBagConstraints.NORTHWEST;
		gbc_defesa.insets = new Insets(0, 0, 5, 5);
		gbc_defesa.gridx = 2;
		gbc_defesa.gridy = 1;
		contentPane.add(defesa, gbc_defesa);
		
		ataque = new GridPanel();
		ataque.setListener(this);
		GridBagConstraints gbc_ataque = new GridBagConstraints();
		gbc_ataque.insets = new Insets(0, 0, 5, 5);
		gbc_ataque.gridheight = 5;
		gbc_ataque.anchor = GridBagConstraints.NORTHWEST;
		gbc_ataque.gridx = 3;
		gbc_ataque.gridy = 1;
		
		Color corAtaque = new Color(204, 96, 96);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ataque.getBotao(i, j).setBackground(corAtaque);
			}
		}
		
		contentPane.add(ataque, gbc_ataque);
		
		tipoDeDisparo = new JComboBox<TipoDeDisparo>();
		tipoDeDisparo.setFont(new Font("Arial", Font.PLAIN, 12));
		tipoDeDisparo.setModel(new DefaultComboBoxModel<TipoDeDisparo>(new TipoDeDisparo[] { TipoDeDisparo.SIMPLES, TipoDeDisparo.CASCATA, TipoDeDisparo.ESTRELA }));
		
		GridBagConstraints gbc_tipoDeDisparo = new GridBagConstraints();
		gbc_tipoDeDisparo.insets = new Insets(0, 0, 5, 5);
		gbc_tipoDeDisparo.fill = GridBagConstraints.BOTH;
		gbc_tipoDeDisparo.gridx = 1;
		gbc_tipoDeDisparo.gridy = 2;
		contentPane.add(tipoDeDisparo, gbc_tipoDeDisparo);
		
		dicaButton = new JButton("Dica (3)");
		dicaButton.setFocusPainted(false);
		dicaButton.setFont(new Font("Arial", Font.PLAIN, 12));
		dicaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				darDica();
			}
		});
		
		GridBagConstraints gbc_dicaButton = new GridBagConstraints();
		gbc_dicaButton.fill = GridBagConstraints.BOTH;
		gbc_dicaButton.insets = new Insets(0, 0, 5, 5);
		gbc_dicaButton.gridx = 1;
		gbc_dicaButton.gridy = 3;
		contentPane.add(dicaButton, gbc_dicaButton);
		
		sairButton = new JButton("Sair");
		sairButton.setFocusPainted(false);
		sairButton.setFont(new Font("Arial", Font.PLAIN, 12));
		sairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				terminaJogo();
			}
		});
		GridBagConstraints gbc_sairButton = new GridBagConstraints();
		gbc_sairButton.fill = GridBagConstraints.BOTH;
		gbc_sairButton.insets = new Insets(0, 0, 5, 5);
		gbc_sairButton.gridx = 1;
		gbc_sairButton.gridy = 5;
		contentPane.add(sairButton, gbc_sairButton);
		
		pack();
	}

	private void inicializaIcones() {
		for (int i = 0; i < 4; i++) {
			Peca peca = batalhaNaval.getUsuario().getTabuleiro().getPeca(i);
			
			for (int j = 0 ; j < peca.getTamanho(); j++) {
				defesa.setIcon(peca.getPosicao().getLinha(), peca.getPosicao().getColuna() + j, "img/" + peca.toString() + "_" + j + ".png");
			}
		}
	}
	
	@Override
	public void botaoClicado(int linha, int coluna) {
		Atirador[] atiradores = batalhaNaval.getUsuario().getTabuleiro().getAtiradores();
		for (Atirador atirador : atiradores) {
			if (atirador.getTipoDeDisparo().equals(tipoDeDisparo.getSelectedItem())) {
				batalhaNaval.getUsuario().setAtirador(atirador);
			}
		}
		
		batalhaNaval.atirar(new Posicao(linha, coluna));
		
		for (Posicao pos : batalhaNaval.getUsuario().getAcertos()) {
			JButton botao = ataque.getBotao(pos.getLinha(), pos.getColuna());
			ataque.setIcon(pos.getLinha(), pos.getColuna(), "img/x.png");
			botao.setEnabled(false);
		}
		
		for (Posicao pos : batalhaNaval.getUsuario().getTirosNaAgua()) {
			JButton botao = ataque.getBotao(pos.getLinha(), pos.getColuna());
			botao.setEnabled(false);
			ataque.tiroNaAgua(pos.getLinha(), pos.getColuna());
		}
		
		for (Posicao pos : batalhaNaval.getComputador().getAcertos()) {
			JButton botao = defesa.getBotao(pos.getLinha(), pos.getColuna());
			botao.setDisabledIcon(null);
		}
		
		for (Posicao pos : batalhaNaval.getComputador().getTirosNaAgua()) {
			defesa.tiroNaAgua(pos.getLinha(), pos.getColuna());
		}
		
		ArrayList<TipoDeDisparo> tipos = new ArrayList<TipoDeDisparo>();
		for (Atirador atirador : batalhaNaval.getUsuario().getTabuleiro().getAtiradores()) {
			tipos.add(atirador.getTipoDeDisparo());
		}
		
		for (int i = 0; i < tipoDeDisparo.getItemCount(); i++) {
			if (tipos.indexOf(tipoDeDisparo.getItemAt(i)) == -1) {
				tipoDeDisparo.removeItemAt(i);
			}
		}
		
		if (batalhaNaval.jogoTerminado()) {
			terminaJogo();
		}
	}
	
	private void terminaJogo() {
		FimDeJogoFrame fimDeJogoFrame = new FimDeJogoFrame(batalhaNaval);
		fimDeJogoFrame.setVisible(true);
		dispose();
	}
	
	private void darDica() {		
		String s = JOptionPane.showInputDialog(this, "Insira uma linha (A-J) ou coluna (1-10):");
		if (s == null || s.isEmpty()) {
			System.out.println("Dica cancelada ou vazia, quita daqui plis");
			return;
		}
		
		s = s.toUpperCase();
		
		int valor = 0;
		boolean dicaDada = false;
		Tabuleiro tab = batalhaNaval.getComputador().getTabuleiro();
		
		if (Character.isAlphabetic(s.charAt(0)) && s.length() == 1 && s.charAt(0) >= 'A' && s.charAt(0) <= 'J') {
			System.out.println("entrou uma linha");
			valor = s.charAt(0) - 'A';
			
			boolean encontrou = false;
			Posicao pos = new Posicao(valor, 0);
			for (int i = 0; i < 10 && !encontrou; i++) {
				pos.setColuna(i);
				if (tab.posicaoOcupada(pos))
					encontrou = true;
			}
			
			JOptionPane.showMessageDialog(this, (encontrou ? "Existe" : "Nao existe") + " um objeto na linha " + s.charAt(0));
			dicaDada = true;
		} else {
			try {
				valor = Integer.parseInt(s);
				if (valor <= 0 || valor > 10) {
					// to reutilizando a exception pq eu acho que assim fica mais facil de entender, nao eh uma boa pratica mas ne
					throw new NumberFormatException("Valor para coluna deve ser entre 1 e 10");
				}
				valor--; // o usuario digita [1, 10] mas a gente so trabalha com [0, 9] 
				
				boolean encontrou = false;
				Posicao pos = new Posicao(0, valor);
				for (int i = 0; i < 10 && !encontrou; i++) {
					pos.setLinha(i);
					if (tab.posicaoOcupada(pos))
						encontrou = true;
				}
				
				JOptionPane.showMessageDialog(this, (encontrou ? "Existe" : "Nao existe") + " um objeto na coluna " + (valor + 1));
				dicaDada = true;
			} catch (NumberFormatException nfex) {
				System.out.println("po deu ruim na hora da dica\t" + nfex.getMessage());
				JOptionPane.showMessageDialog(this, "'" + s + "' nao eh uma linha nem coluna valida");
			}
		} 
		
		if (dicaDada) {
			dicas--;
			dicaButton.setText("Dica (" + dicas + ")");
			if (dicas == 0) {
				dicaButton.setEnabled(false);
			}
		}
	}
}
