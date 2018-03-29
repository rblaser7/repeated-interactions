package player;

public class Player {
	public Player(int playerNum, int gameNum, int[][] board) {
		this.playerNum = playerNum;
		this.gameNum = gameNum;
		this.board = board;
	}
	
	private int playerNum;
	private int gameNum;
	private int[][] board;
	private int opponentsPreviousMove = 0;
	private int score = 0;
	private int maximin = -1;
	private int round = 0;
	private int numTimesPlayed0 = 0;
	
	public int titForTat() {
		return opponentsPreviousMove;
	}
	
	public int bully() {
		if (gameNum == 3) {
			return 0;
		}
		return 1;
	}
	
	public int fictitiousPlay() {
		if (round == 0) {
			round++;
			return random();
		}
		double percent0 = numTimesPlayed0 / (double) round;
		double percent1 = 1 - percent0;
		double expectedPayoffIf0 = board[0][playerNum] * percent0 + board[1][playerNum] * percent1;
		double expectedPayoffIf1 = board[2][playerNum] * percent0 + board[3][playerNum] * percent1;
		round++;
		return expectedPayoffIf0 > expectedPayoffIf1 ? 0 : 1;
	}
	
	public int maximin() {
		if (maximin == -1) {
			int smallestSoFar = Integer.MAX_VALUE;
			int smallestIndexSoFar = 0;
			for (int i = 0; i < board.length; i++) {
				if (board[i][playerNum] < smallestSoFar) {
					smallestSoFar = board[i][playerNum];
					smallestIndexSoFar = i;
				}
			}
			int opponent;
			if (smallestIndexSoFar == 0 || smallestIndexSoFar == 2) {
				opponent = 0;
			} else {
				opponent = 1;
			}
			int other = opponent == 0 ? 1: 0;
			maximin = board[opponent][playerNum] > board[opponent + 2][playerNum] ? opponent : other;
		}
		return maximin;
	}
	
	public int random() {
		return Math.random() > .5 ? 1 : 0;
	}
	
	public int myAlgorithm() {
		int pick = round % 6;
		if (pick == 0 || pick == 1 || pick == 4) {
			return 1;
		}
		return 0;
	}
	
	public int getOpponentsPreviousMove() {
		return opponentsPreviousMove;
	}
	
	public void setOpponentsPreviousMove(int move) {
		opponentsPreviousMove = move;
		if (move == 0) {
			numTimesPlayed0++;
		}
	}
	
	public void incrementScore(int round) {
		score += round;
	}
	
	public int getScore() {
		return score;
	}
}
