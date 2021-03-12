package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Toolkit;

public class WinPanel extends JPanel {

	private JLabel winnerLabel;
	
	private Image winnerGif;
	
	private JButton restartButton;
	
	public WinPanel() {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.winnerLabel = new JLabel("");
		this.winnerLabel.setForeground(Color.WHITE);
		this.winnerLabel.setBackground(Color.BLACK);
		this.winnerLabel.setOpaque(true);
		this.winnerLabel.setFont(new Font("Syncro LET", Font.PLAIN, 60));
		
		JPanel north = new JPanel();
		north.setBackground(Color.BLACK);
		north.add(winnerLabel);
		
		this.add(north, BorderLayout.NORTH);
		
		this.winnerGif = Toolkit.getDefaultToolkit().getImage("images/giphy.gif");
		
		this.restartButton = new JButton("RESTART");
		JPanel south = new JPanel();
		south.setBackground(Color.BLACK);
		restartButton.setBackground(Color.BLACK);
		restartButton.setForeground(Color.WHITE);
		restartButton.setFont(new Font("Syncro LET", Font.PLAIN, 40));
		south.add(restartButton);
		
		this.add(south, BorderLayout.SOUTH);
	}

	public JButton getRestartButton() {
		return restartButton;
	}

	public JLabel getWinnerLabel() {
		return winnerLabel;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(winnerGif, 80, 150, this);            
    }
}
