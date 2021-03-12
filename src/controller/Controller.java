package controller;

import java.awt.event.*;

import javax.swing.JButton;

import model.*;
import view.*;

public class Controller extends Observable implements IObserver {

	private static Controller instance;
	
	private Status model;
	private ViewFrame frame;
	
	private Controller(Status model, ViewFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		this.setListeners();
	}
	
	public static Controller getInstance(Status model, ViewFrame frame) {
		if (instance == null) {
			instance = new Controller(model, frame);
		}
		
		return instance;
	}
	
	public void setListeners() {
		StartPanel startPanel = this.frame.getStartPanel();
		TrisPanel trisPanel = this.frame.getTrisPanel();
		WinPanel winPanel = this.frame.getWinPanel();
		
		startPanel.getPlayButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.reset();
				trisPanel.reset();
				frame.setSize(800, 800);
				frame.put(trisPanel);
			}
			
		});
		
		trisPanel.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.put(startPanel);
				frame.setSize(759, 661);
			}
			
		});
		
		JButton[][] gameButton = trisPanel.getGameButton();
		
		for (int i=0; i<gameButton.length; i++) {
			for (int j=0; j<gameButton[i].length; j++) {
				this.setGameButtonListeners(gameButton[i][j], i, j);
			}
		}
		
		winPanel.getRestartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.put(startPanel);
				frame.setSize(759, 661);
			}
			
		});
	}
	
	private void setGameButtonListeners(JButton button, int i, int j) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getPlayerTurn()) {
					button.setText("X");
					model.setCharGame(i, j);
				} else {
					button.setText("O");
					model.setCharGame(i, j);
				}
				notifyAllObservers();
			}
			
		});
	}

	@Override
	public void update() {
		char winner = this.model.getWinner();
		WinPanel winPanel = frame.getWinPanel();
		winPanel.getWinnerLabel().setText(""+winner+" IS THE");
		this.frame.put(winPanel);
	}
}
