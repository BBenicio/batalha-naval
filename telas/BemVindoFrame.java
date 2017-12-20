package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BemVindoFrame extends JFrame {

	private JLabel contentPane;
	
	/**
	 * Create the frame.
	 */
	public BemVindoFrame() {
		setTitle("Batalha Naval");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		
		setIconImage(new ImageIcon("img/icone.png").getImage());
		
		contentPane = new JLabel(new ImageIcon("img/BemVindo_bg.png"));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {10, 150, 80, 150, 10, 0};
		gbl_contentPane.rowHeights = new int[] {50, 100, 190, 40, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel bemVindoLabel = new JLabel("Batalha Naval");
		bemVindoLabel.setForeground(Color.WHITE);
		bemVindoLabel.setFont(new Font("Arial", Font.BOLD, 32));
		GridBagConstraints gbc_bemVindoLabel = new GridBagConstraints();
		gbc_bemVindoLabel.gridwidth = 3;
		gbc_bemVindoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bemVindoLabel.gridx = 1;
		gbc_bemVindoLabel.gridy = 1;
		contentPane.add(bemVindoLabel, gbc_bemVindoLabel);
		
		JButton aleatorioButton = new JButton("Jogo Aleatorio");
		aleatorioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JogoFrame jogoFrame = new JogoFrame();
				jogoFrame.setVisible(true);
				dispose();
			}
		});
		aleatorioButton.setFont(new Font("Arial", Font.PLAIN, 14));
		aleatorioButton.setToolTipText("Jogar com um tabuleiro aleatorio");
		GridBagConstraints gbc_aleatorioButton = new GridBagConstraints();
		gbc_aleatorioButton.fill = GridBagConstraints.BOTH;
		gbc_aleatorioButton.insets = new Insets(0, 0, 5, 5);
		gbc_aleatorioButton.gridx = 1;
		gbc_aleatorioButton.gridy = 3;
		contentPane.add(aleatorioButton, gbc_aleatorioButton);
		
		JButton definirButton = new JButton("Definir Jogo");
		definirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefinirJogoFrame definirJogoFrame = new DefinirJogoFrame();
				definirJogoFrame.setVisible(true);
				dispose();
			}
		});
		
		definirButton.setToolTipText("Definir o seu tabuleiro");
		definirButton.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_definirButton = new GridBagConstraints();
		gbc_definirButton.insets = new Insets(0, 0, 5, 5);
		gbc_definirButton.fill = GridBagConstraints.BOTH;
		gbc_definirButton.gridx = 3;
		gbc_definirButton.gridy = 3;
		contentPane.add(definirButton, gbc_definirButton);
		
//		pack();
	}
}
