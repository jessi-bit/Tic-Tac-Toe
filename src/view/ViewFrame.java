package view;

import java.awt.*;
import javax.swing.*;

public class ViewFrame extends JFrame {

	private Container container;
	private StartPanel startPanel;
	private TrisPanel trisPanel;
	private WinPanel winPanel;
	
	public ViewFrame() {
		this.setTitle("TRIS");
		this.setSize(759, 661);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.startPanel = new StartPanel();
		this.trisPanel = new TrisPanel();
		this.winPanel = new WinPanel();
		
		this.container = getContentPane();
		this.container.add(startPanel);
		
		this.setVisible(true);
	}

	public StartPanel getStartPanel() {
		return startPanel;
	}

	public TrisPanel getTrisPanel() {
		return trisPanel;
	}
	
	public WinPanel getWinPanel() {
		return winPanel;
	}

	public void put(JPanel panel) {
		this.container.removeAll();
		this.container.add(panel);
		this.setVisible(true);
	}
	
}
