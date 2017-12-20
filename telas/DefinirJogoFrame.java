package telas;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import jogo.Caca;
import jogo.NavioDeEscolta;
import jogo.Peca;
import jogo.PortaAviao;
import jogo.Posicao;
import jogo.Submarino;
import jogo.Tabuleiro;

public class DefinirJogoFrame extends JFrame implements BotaoListener {
	private JLabel contentPane;
	
	private JButton jogarButton;
	private JComboBox<Peca> pecaComboBox;
	private GridPanel gridPane;
	
	private Tabuleiro tabuleiro;
	
	private final Peca[] modelo = new Peca[] { new Submarino(), new Caca(), new NavioDeEscolta(), new PortaAviao() };
	private JLabel lblObjeto;

	/**
	 * Create the frame.
	 */
	public DefinirJogoFrame() {
		setResizable(false);
		setTitle("Definir Jogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 600);
		
		setIconImage(new ImageIcon("img/icone.png").getImage());
		
		contentPane = new JLabel(new ImageIcon("img/DefinirJogo_bg.png"));
		setContentPane(contentPane);
		
		pecaComboBox = new JComboBox<Peca>();
		pecaComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		pecaComboBox.setModel(new DefaultComboBoxModel<Peca>(modelo));
		
		JButton limparTabuleiroButton = new JButton("Limpar Tabuleiro");
		limparTabuleiroButton.setFocusPainted(false);
		limparTabuleiroButton.setFont(new Font("Arial", Font.PLAIN, 12));
		limparTabuleiroButton.setToolTipText("Remover todas as pecas posicionadas");
		limparTabuleiroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabuleiro();
			}
		});
		
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		lblObjeto = new JLabel("Objeto:");
		lblObjeto.setForeground(Color.WHITE);
		lblObjeto.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(lblObjeto);
		contentPane.add(pecaComboBox);
		
		JButton carregarJogoButton = new JButton("Carregar Jogo");
		carregarJogoButton.setFocusPainted(false);
		carregarJogoButton.setFont(new Font("Arial", Font.PLAIN, 12));
		carregarJogoButton.setToolTipText("Carregar um tabuleiro de um arquivo");
		carregarJogoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarJogo();
			}
		});
		contentPane.add(carregarJogoButton);
		contentPane.add(limparTabuleiroButton);
		
		gridPane = new GridPanel();
		gridPane.setListener(this);
		contentPane.add(gridPane);
		
		jogarButton = new JButton("Jogar");
		jogarButton.setFocusPainted(false);
		jogarButton.setToolTipText("Iniciar a batalha");
		jogarButton.setFont(new Font("Arial", Font.PLAIN, 12));
		jogarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JogoFrame jogoFrame = new JogoFrame(tabuleiro);
				jogoFrame.setVisible(true);
				dispose();
			}
		});
		jogarButton.setEnabled(false);
		contentPane.add(jogarButton);
		
		tabuleiro = new Tabuleiro();
	}
	
	public void botaoClicado(int linha, int coluna) {
		Peca selecionada = (Peca) pecaComboBox.getSelectedItem();
		boolean podePosicionar = coluna >= 0 && coluna + selecionada.getTamanho() <= 10 && linha >= 0 && linha < 10;
		
		for (int i = 0; i < selecionada.getTamanho() && podePosicionar; i++) {
			if (tabuleiro.posicaoOcupada(new Posicao(linha, coluna + i))) {
				podePosicionar = false;
			}
		}
		
		if (!podePosicionar) {
			JOptionPane.showMessageDialog(this, "Nao foi possivel posicionar o " + selecionada.toString() + "!\nSelecione outra posicao");
			
			System.out.println("Posicao invalida!");
			return;
		}
		
		selecionada.getPosicao().setLinha(linha);
		selecionada.getPosicao().setColuna(coluna);
		tabuleiro.addPeca(selecionada);
		
		for (int i = 0; i < selecionada.getTamanho() && podePosicionar; i++) {
			JButton botao = gridPane.getBotao(linha, coluna + i);
			gridPane.setIcon(linha, coluna + i, "img/" + selecionada.toString() + "_" + i + ".png");
			botao.setEnabled(false);
		}
		
		pecaComboBox.removeItem(selecionada);
		if (pecaComboBox.getItemCount() == 0) {
			gridPane.setEnabledAll(false);
			jogarButton.setEnabled(true);
		}

		System.out.println("Botao clicado (" + linha + ", " + coluna + ")");
	}
	
	public void limparTabuleiro() {
		pecaComboBox.setModel(new DefaultComboBoxModel<Peca>(modelo));
		jogarButton.setEnabled(false);
		gridPane.setEnabledAll(true);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JButton botao = gridPane.getBotao(i, j); 
				botao.setIcon(null);
				botao.setDisabledIcon(null);
				botao.setBorder(UIManager.getBorder("Button.border"));
			}
		}
		
		tabuleiro.removerPecas();
	}
	
	public void carregarJogo() {
		JFileChooser fileChooser = new JFileChooser();
		int value = fileChooser.showOpenDialog(contentPane);
		
		if (value == JFileChooser.APPROVE_OPTION) {
			System.out.println("Escolheu o arquivo '" + fileChooser.getSelectedFile().getPath() + "'");
			 
			
			Scanner scanner = null;
			try {
				scanner = new Scanner(fileChooser.getSelectedFile());
				
				// se conseguiu carregar o arquivo limpa a tela
				limparTabuleiro();
				
				while (scanner.hasNextInt() && pecaComboBox.getItemCount() > 0) {
					int tamanho = scanner.nextInt();
					String pos = scanner.next();
					int linha = pos.charAt(0) - 'A';
					int coluna = pos.charAt(1) - '1';
					
					switch (tamanho) {
						case 2:
							pecaComboBox.setSelectedIndex(0);
							break;
						case 3:
							pecaComboBox.setSelectedItem(modelo[2]);
							break;
						case 4:
							pecaComboBox.setSelectedItem(modelo[3]);
							break;
						default:
							System.out.println("Nao existe nenhuma peca de tamanho " + tamanho);
							return;
					}
					
					Peca selecionada = (Peca) pecaComboBox.getSelectedItem();
					if (selecionada != null && selecionada.getTamanho() != tamanho) {
						System.out.println("deu ruim ai ein");
						continue;
					}
					
					botaoClicado(linha, coluna);
				}
			} catch (FileNotFoundException fnfex) {
				JOptionPane.showMessageDialog(this, "O arquivo nao foi encontrado!"); // quase certeza que isso nao acontece mas nunca se sabe ne
			} finally {
				scanner.close();
			}
		}
	}
}
