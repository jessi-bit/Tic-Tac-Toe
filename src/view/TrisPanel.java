package view;

import java.awt.*;
import javax.swing.*;

public class TrisPanel extends JPanel {

	private JButton backButton;
	private JButton[][] gameButton = new JButton[3][3];
	
	public TrisPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel north = new JPanel();
		this.backButton = new JButton("RESTART");
		backButton.setFont(new Font("Syncro LET", Font.PLAIN, 40));
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		north.setBackground(Color.BLACK);
		north.add(backButton);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3,3));
		
		for (int i=0; i<gameButton.length; i++) {
			for (int j=0; j<gameButton[i].length; j++) {
				this.gameButton[i][j] = new JButton("");
				this.gameButton[i][j].setFont(new Font("Syncro LET", Font.PLAIN, 60));
				this.gameButton[i][j].setBackground(Color.BLACK);
				this.gameButton[i][j].setForeground(Color.WHITE);
				center.add(this.gameButton[i][j]);
			}
		}
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		
	}

	public JButton[][] getGameButton() {
		return gameButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
	
	public void reset() {
		for (int i=0; i < this.gameButton.length; i++) {
			for (int j=0; j < this.gameButton[i].length; j++) {
				this.gameButton[i][j].setText("");
			}
		}
	}
	
}
