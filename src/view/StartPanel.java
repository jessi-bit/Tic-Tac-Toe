package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class StartPanel extends JPanel {

	private JButton playButton;
	private BufferedImage trisImage;
	
	
	public StartPanel() {
		this.playButton = new JButton("PLAY NOW!");
		
		try{
			trisImage = ImageIO.read(new File("images/trisBlack.jpeg"));
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		this.add(playButton);
		
		playButton.setFont(new Font("Synchro LET", Font.PLAIN, 30));
		playButton.setBackground(Color.BLACK);
		playButton.setForeground(Color.WHITE);
		
		
		
		
	}

	public JButton getPlayButton() {
		return playButton;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(trisImage, 0, 0, this);            
    }
	
}
