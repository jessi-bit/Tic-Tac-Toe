package model;

import java.util.*;


public class Status extends Observable implements IObserver {
	
	private char[][] game;
	private boolean isA;
	private char winner;
	
	public Status() {
		super();
		this.game = new char[3][3];
		this.isA = true;
		this.winner = 0;
	}
	
	private void setPlayerTurn() {
		this.isA = !(this.isA);
	}
	
	public boolean getPlayerTurn() {
		return this.isA;
	}
	
	public void setCharGame(int i, int j) {
		if (this.isA) {
			this.game[i][j] = 'X';
		} else {
			this.game[i][j] = 'O';
		}
		this.setPlayerTurn();
	}
	
	public boolean verifyWin(char symbol) {
		return (verifyHVWin(symbol, 'H') || verifyHVWin(symbol, 'V') || verifyWinFirstDiagonal(symbol) || verifyWinSecondDiagonal(symbol));
	}
	
	private boolean verifyHVWin(char symbol, char hv) {
		int inserted = 0;
		for (int i=0; i<game.length; i++) {
			inserted = 0;
			for (int j=0; j<game[i].length; j++) {
				switch (hv) {
				case 'H' : {
					if (game[i][j] == symbol) {
						inserted++;
					}
					break;
				}
				case 'V': {
					if (game[j][i] == symbol) {
						inserted++;
					}
					break;
				}
				default : { break; }
				}			
				if (inserted == 3) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean verifyWinFirstDiagonal(char symbol) {
		int i = 0;
		int inserted = 0;
		
		for (int j=0; j < game[i].length - 2; j++) {
			inserted = 0;
			for (int k=0; k < 3; k++) {
				if (game[i+k][j+k] == symbol) {
					inserted++;
				}
				if (inserted == 3) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean verifyWinSecondDiagonal(char symbol) {
		int i = 2;
		int inserted = 0;
		
		for (int j=0; j < game[i].length - 2; j++) {
			inserted = 0;
			for (int k=0; k < 3; k++) {
				if (game[i-k][j+k] == symbol) {
					inserted++;
				}
				if (inserted == 3) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void update() {
		if (verifyWin('X')) {
			this.winner = 'X';
			this.notifyAllObservers();
		} else {
			if (verifyWin('O')) {
				this.winner = 'O';
				this.notifyAllObservers();
			}
		}	
	}

	public char getWinner() {
		return winner;
	}
	
	public void reset() {
		for (int i=0; i<this.game.length; i++) {
			for (int j=0; j<this.game[i].length; j++) {
				this.game[i][j] = 0;
			}
		}
	}
}
