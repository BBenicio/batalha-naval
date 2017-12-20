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

import jogo.BatalhaNaval;

public class FimDeJogoFrame extends JFrame {
	private JLabel contentPane;

	/**
	 * Create the frame.
	 */
	public FimDeJogoFrame(BatalhaNaval batalhaNaval) {
		setResizable(false);
		setTitle("Batalha Naval");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 280);
		
		setIconImage(new ImageIcon("img/icone.png").getImage());
		
		
		contentPane = new JLabel(new ImageIcon("img/FimDeJogo_bg.png"));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {20, 150, 150, 150, 20, 0};
		gbl_contentPane.rowHeights = new int[] {10, 60, 40, 100, 40, 10};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		boolean ganhou = batalhaNaval.getComputador().perdeu();
		JLabel resultadoLabel = new JLabel(ganhou ? "Voce Ganhou!" : "Voce Perdeu!");
		resultadoLabel.setForeground(Color.WHITE);
		resultadoLabel.setFont(new Font("Arial", Font.BOLD, 32));
		GridBagConstraints gbc_resultadoLabel = new GridBagConstraints();
		gbc_resultadoLabel.gridwidth = 3;
		gbc_resultadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_resultadoLabel.gridx = 1;
		gbc_resultadoLabel.gridy = 1;
		contentPane.add(resultadoLabel, gbc_resultadoLabel);
		
		JButton reiniciarButton = new JButton("Reiniciar Jogo");
		reiniciarButton.setFocusPainted(false);
		reiniciarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				batalhaNaval.reiniciar();
				JogoFrame jogoFrame = new JogoFrame(batalhaNaval);
				jogoFrame.setVisible(true);
				dispose();
			}
		});
		
		String motivo;
		if (!batalhaNaval.jogoTerminado()) {
			motivo = "Voce desistiu do jogo";
		} else if (ganhou) {
			if (batalhaNaval.getComputador().getTabuleiro().destruido()) {
				motivo = "Voce destruiu todas as embarcacoes do computador";
			} else {
				motivo = "O computador nao consegue mais atirar";
			}
		} else {
			if (batalhaNaval.getUsuario().getTabuleiro().destruido()) {
				motivo = "Todas as suas embarcacoes foram destruidos";
			} else {
				motivo = "Voce nao consegue mais atirar";
			}
		}
		
		JLabel motivoLabel = new JLabel(motivo);
		motivoLabel.setForeground(Color.WHITE);
		motivoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_motivoLabel = new GridBagConstraints();
		gbc_motivoLabel.anchor = GridBagConstraints.SOUTH;
		gbc_motivoLabel.gridwidth = 3;
		gbc_motivoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_motivoLabel.gridx = 1;
		gbc_motivoLabel.gridy = 2;
		contentPane.add(motivoLabel, gbc_motivoLabel);
		reiniciarButton.setToolTipText("Jogar com os mesmos tabuleiros");
		reiniciarButton.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_reiniciarButton = new GridBagConstraints();
		gbc_reiniciarButton.insets = new Insets(0, 0, 5, 5);
		gbc_reiniciarButton.fill = GridBagConstraints.BOTH;
		gbc_reiniciarButton.gridx = 1;
		gbc_reiniciarButton.gridy = 4;
		contentPane.add(reiniciarButton, gbc_reiniciarButton);
		
		JButton jogoAleatorioButton = new JButton("Jogo Aleatorio");
		jogoAleatorioButton.setFocusPainted(false);
		jogoAleatorioButton.setToolTipText("Jogar com um tabuleiro aleatorio");
		jogoAleatorioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JogoFrame jogoFrame = new JogoFrame();
				jogoFrame.setVisible(true);
				dispose();
			}
		});
		jogoAleatorioButton.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_jogoAleatrioButton = new GridBagConstraints();
		gbc_jogoAleatrioButton.fill = GridBagConstraints.BOTH;
		gbc_jogoAleatrioButton.insets = new Insets(0, 0, 5, 5);
		gbc_jogoAleatrioButton.gridx = 2;
		gbc_jogoAleatrioButton.gridy = 4;
		contentPane.add(jogoAleatorioButton, gbc_jogoAleatrioButton);
		
		JButton novoJogoButton = new JButton("Novo Jogo");
		novoJogoButton.setFocusPainted(false);
		novoJogoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefinirJogoFrame definirJogoFrame = new DefinirJogoFrame();
				definirJogoFrame.setVisible(true);
				dispose();
			}
		});
		novoJogoButton.setToolTipText("Definir o seu tabuleiro");
		novoJogoButton.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_novoJogoButton = new GridBagConstraints();
		gbc_novoJogoButton.insets = new Insets(0, 0, 5, 5);
		gbc_novoJogoButton.fill = GridBagConstraints.BOTH;
		gbc_novoJogoButton.gridx = 3;
		gbc_novoJogoButton.gridy = 4;
		contentPane.add(novoJogoButton, gbc_novoJogoButton);
		
		//pack();
	}

}
