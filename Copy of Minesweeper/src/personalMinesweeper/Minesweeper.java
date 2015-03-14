package personalMinesweeper;

import java.util.Random;

public class Minesweeper {
	protected int widthOfBoard;
	protected int heightOfBoard;
	protected int numberOfMines;
	protected boolean[][] minePos;
	protected SquareStatus[][] squareStatus;

	public Minesweeper(int width, int height, int mines) {
		widthOfBoard = width;
		heightOfBoard = height;
		numberOfMines = mines;
		assignMine();

	}

	public int getWidth() {
		return widthOfBoard;
	}

	public int getHeight() {
		return heightOfBoard;
	}

	public int getNoMines() {
		return numberOfMines;
	}

	// Assigns mine to board Randomly
	public void assignMine() {
		minePos = new boolean[heightOfBoard][widthOfBoard];
		for (int i = 0; i < numberOfMines; i++) {
			Random x = new Random();
			Random y = new Random();
			int w = x.nextInt(heightOfBoard);
			int h = y.nextInt(widthOfBoard);
			//First checks if position already has mine
			if (minePos[w][h] == true) {
				System.out.println("Already mine there");
				i = i - 1;
			} else {
				//No mine here- so mine placed here
				System.out.println("Adding mine to pos " + w + " " + h);
				minePos[w][h] = true;
			}
		}
		//Calculates number of mines around each square
		calcMine();
	}
	/**
	 * Method goes through all possible
	 * 8 adjacent positions of each square
	 * And counts number of mines
	 */
	public void calcMine(){
		squareStatus = new SquareStatus[getHeight()][getWidth()];
		int count = 0;
		for(int row = 0;row < getHeight();row++){
			for(int column = 0; column < getWidth();column++){
				count = 0;
				//Possible positions
				int topx = row - 1;
				int topy = column;

				int bottomx = row + 1;
				int bottomy = column;

				int rightx = row;
				int righty = column + 1;

				int leftx = row;
				int lefty = column - 1;

				int topRightx = row - 1;
				int topRighty = column + 1;

				int topLeftx = row - 1;
				int topLefty = column - 1;

				int bottomRightx = row + 1;
				int bottomRighty = column + 1;

				int bottomLeftx = row + 1;
				int bottomLefty = column - 1;

				//First checks if square is a mine
				//If it isn't then checks surrounding squares
				if(minePos[row][column] == true){
					squareStatus[row][column] = SquareStatus.MINE;
				}else{
				if(topx >= 0 && topx < getHeight() && topy >= 0 && topy < getWidth()){
					if(minePos[topx][topy] == true){
						count++;
					}
				}
				if(bottomx >= 0 && bottomx < getHeight() && bottomy >= 0 && bottomy < getWidth()){
					if(minePos[bottomx][bottomy] == true){
						count++;
					}
				}
				if(rightx >= 0 && rightx < getHeight() && righty >= 0 && righty < getWidth()){
					if(minePos[rightx][righty] == true){
						count++;
					}
				}
				if(leftx >= 0 && leftx < getHeight() && lefty >= 0 && lefty < getWidth()){
					if(minePos[leftx][lefty] == true){
						count++;
					}
				}
				if(topRightx >= 0 && topRightx < getHeight() && topRighty >= 0 && topRighty < getWidth()){
					if(minePos[topRightx][topRighty] == true){
						count++;
					}
				}
				if(topLeftx >= 0 && topLeftx < getHeight() &&topLefty >= 0 &&topLefty < getWidth()){
					if(minePos[topLeftx][topLefty] == true){
						count++;
					}
				}
				if(bottomRightx >= 0 && bottomRightx < getHeight() && bottomRighty >= 0 && bottomRighty < getWidth()){
					if(minePos[bottomRightx][bottomRighty] == true){
						count++;
					}
				}
				if(bottomLeftx >= 0 && bottomLeftx < getHeight() && bottomLefty >= 0 && bottomLefty < getWidth()){
					if(minePos[bottomLeftx][bottomLefty] == true){
						count++;
					}
				}
				squareStatus[row][column] = getEnum(count);
				}
			}
		}
	}
	
	//Enums to encode number of mines around
	public SquareStatus getEnum(int n){
		switch(n){
		case 0: return SquareStatus.ZERO;
		case 1: return SquareStatus.ONE;
		case 2: return SquareStatus.TWO;
		case 3: return SquareStatus.THREE;
		case 4: return SquareStatus.FOUR;
		case 5: return SquareStatus.FIVE;
		case 6: return SquareStatus.SIX;
		case 7: return SquareStatus.SEVEN;
		case 8: return SquareStatus.EIGHT;
		case 9: return SquareStatus.MINE;
		}
		return null;
		
	}

}
