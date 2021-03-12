package model;

import java.util.*;

/*
 *  1. scrivere una interfaccia utente con una griglia 3x3 
 *  2. ogni cella contiene un bottone che quando viene premuto dall'utente A viene mostrata una X, quando viene premuto dall'utente B viene mostrata una O
 *  3. quando viene premuto un pulsante il pulsante viene nascosto ed al suo posto viene mostrata la label
 *  4. è il sistema che sa a chi tocca (inizia sempre il giocatore A)
 *  5. ad ogni pulsante premuto il sistema verifica se c'è un tris
 *  6. se non c'è un tris tocca all'altro giocatore
 *  7. se c'è un tris i pulsanti sono bloccati e viene mostrato un nuovo pulsante "inizia"
 *  8. se si preme "inizia" si crea una nuova partita
 * 
 */

/*
 * Serve:
 * 
 * Ad ogni pressione del pulsante viene emesso un evento verso lo status
 * lo status controlla se c'è un tris
 * se c'è un tris emette un evento alla view che si sottoscrive e mostra chi ha vinto e blocca l'interfaccia
 * 
 * 1) una classe status che contiene lo stato della partita, ovvero un array 3x3 cioeè da GAME[0..2][0..2] il valore di ogni cella può essere vuoto, "X", "O"
 * 2) devi fare un metodo che ti dice se c'è un tris. Per vedere se c'è un tris devi vedere tutte le righe, tutte le colonne e tutte le diagonali se hanno lo stesso valore
 * if (game[0][0] == game[0][1] && game[0][1] == game[0][2] && game[0][0] == "X") { ha vinto A } sono 8 controlli per il giocatore A e 8 controlli per il giocatore B
 * dando il risultato di ogni mossa emetti un evento verso la view (esattamente come fatto con la calcolatrice)
 * 
 */

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
