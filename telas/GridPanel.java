package telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GridPanel extends JPanel implements ActionListener {
	private JButton[][] botoes;
	private BotaoListener listener;
	
	public GridPanel() {
		botoes = new JButton[10][10];
		
		setLayout(new GridLayout(11, 11, 0, 0));
		
		Dimension dim = new Dimension(440, 440);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		
		setBackground(new Color(0, 0, 0, 0));
		
		JLabel label = new JLabel();
		label.setSize(40, 40);
		add(label);
		
		for (int i = 0; i < 10; i++) {
			label = new JLabel(Integer.toString(i + 1));
			label.setFont(new Font("Arial", Font.PLAIN, 12));
			label.setForeground(Color.WHITE);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			label.setPreferredSize(new Dimension(40, 40));
			add(label);
		}
		
		final Color corAgua = new Color(0, 136, 170);
		
		for (int i = 0; i < 10; i++) {
			label = new JLabel(Character.toString((char) ('A' + i)));
			label.setFont(new Font("Arial", Font.PLAIN, 12));
			label.setForeground(Color.WHITE);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			label.setPreferredSize(new Dimension(40, 40));
			add(label);
			
			for (int j = 0; j < 10; j++) {
				botoes[i][j] = new JButton(" ");
				botoes[i][j].setFocusPainted(false);
				botoes[i][j].addActionListener(this);
				botoes[i][j].setPreferredSize(new Dimension(40, 40));
				botoes[i][j].setBackground(corAgua);
				add(botoes[i][j]);
			}
		}
	}

	public void setListener(BotaoListener listener) {
		this.listener = listener;
	}
	
	public JButton getBotao(int linha, int coluna) {
		return botoes[linha][coluna];
	}
	
	public void tiroNaAgua(int linha, int coluna) {
		botoes[linha][coluna].setBackground(new Color(0, 82, 103));
	}
	
	public void setIcon(int linha, int coluna, String path) {
		ImageIcon icon = new ImageIcon(path);
		botoes[linha][coluna].setIcon(icon);
		botoes[linha][coluna].setDisabledIcon(icon);
		botoes[linha][coluna].setBorder(new EmptyBorder(0, 12, 0, 0));
	}
	
	public void setEnabledAll(boolean enable) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				botoes[i][j].setEnabled(enable);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botao = (JButton) e.getSource();
		int linha = 0;
		int coluna = 0;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (botoes[i][j].equals(botao)) {
					linha = i;
					coluna = j;
				}
			}
		}
		
		if (listener != null) {
			listener.botaoClicado(linha, coluna);
		}
	}
	
}
