package games;

import player.Player;

public class StagHunt {
	int[][] board = 	{ { 4,4 }, { -3,3 }, 
			{ 3,-3 }, { 2,2 } };
	Player player1 = new Player(0, 0, board);
	Player player2 = new Player(1, 0, board);
	
	StagHunt() {
		for (int i = 0; i < 100; i++) {
			int player1move = player1.titForTat();
			int player2move = player2.titForTat();
			player1.setOpponentsPreviousMove(player2move);
			player2.setOpponentsPreviousMove(player1move);
			int[] score;
			if (player1move == 0) {
				if (player2move == 0) {
					score = board[0];
				} else {
					score = board[1];
				}
			} else {
				if (player2move == 0) {
					score = board[2];
				} else {
					score = board[3];
				}
			}
			player1.incrementScore(score[0]);
			player2.incrementScore(score[1]);
		}
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public static void main(String [] args) {
		StagHunt game = new StagHunt();
		System.out.println("Player 1 (Row): " + game.getPlayer1().getScore()/100.0);
		System.out.println("Player 2 (Column): " + game.getPlayer2().getScore()/100.0);
	}
}
