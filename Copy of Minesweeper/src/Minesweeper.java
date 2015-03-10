import java.util.Random;

public class Minesweeper {
	protected int widthOfBoard;
	protected int heightOfBoard;
	protected int numberOfMines;
	protected boolean[][] minePos;
	// protected AroundSquare[][] aroundMines;
	protected String[][] aroundMines;

	private enum AroundSquare {
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE, ZERO
	}

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

	// Assigns mine to board
	public void assignMine() {
		minePos = new boolean[heightOfBoard][widthOfBoard];
		for (int i = 0; i < numberOfMines; i++) {
			Random x = new Random();
			Random y = new Random();
			int w = x.nextInt(heightOfBoard);
			int h = y.nextInt(widthOfBoard);
			if (minePos[w][h] == true) {
				System.out.println("Already mine there");
				i = i - 1;
			} else {
				System.out.println("Adding mine to pos " + w + " " + h);
				minePos[w][h] = true;
			}
		}
		System.out.println("Finished");
		// assignValue();
//		assignValues();
	}

	// Checks surrounding of each square
	public void assignValue() {

		aroundMines = new AroundSquare[widthOfBoard][heightOfBoard];
		int count = 0;
		for (int i = 0; i < heightOfBoard; i++) {
			for (int j = 0; j < widthOfBoard; j++) {
				count = 0;
				int topx = i - 1;
				int topy = j;

				int bottomx = i + 1;
				int bottomy = j;

				int rightx = i;
				int righty = j + 1;

				int leftx = i;
				int lefty = j - 1;

				int topRightx = i - 1;
				int topRighty = j + 1;

				int topLeftx = i - 1;
				int topLefty = j - 1;

				int bottomRightx = i + 1;
				int bottomRighty = j + 1;

				int bottomLeftx = i + 1;
				int bottomLefty = j - 1;
				// Checks if first square

				if (i == 0 && j == 0) {
					if (minePos[i][j] == true) {
						aroundMines[i][j] = AroundSquare.MINE;
					} else {
						if (minePos[rightx][righty] == true) {
							count++;
						}
						if (minePos[bottomx][bottomy] == true) {
							count++;
						}
						if (minePos[bottomRightx][bottomRighty] == true) {
							count++;
						}
						aroundMines[i][j] = giveNumber(count);
						count = 0;
					}
				} else if (i > 0 && j < 0 && j < widthOfBoard - 1) {
					if (minePos[i][j] == true) {
						aroundMines[i][j] = AroundSquare.MINE;
					} else {
						if (minePos[leftx][lefty] == true) {
							count++;
						}
						if (minePos[bottomLeftx][bottomLefty] == true) {
							count++;
						}
						if (minePos[rightx][righty] == true) {
							count++;
						}
						if (minePos[bottomx][bottomy] == true) {
							count++;
						}
						if (minePos[bottomRightx][bottomRighty] == true) {
							count++;
						}
						aroundMines[i][j] = giveNumber(count);
						count = 0;
					}
				} else if (i == 0 && j == widthOfBoard - 1) {
					if (minePos[leftx][lefty] == true) {
						count++;
					}
					if (minePos[bottomLeftx][bottomLefty] == true) {
						count++;
					}
					if (minePos[bottomx][bottomy] == true) {
						count++;
					}
					aroundMines[i][j] = giveNumber(count);
					count = 0;
				} else if (i > 0 && j == 0 && i < heightOfBoard - 1) {
					if (minePos[topx][topy] == true) {
						count++;
					}
					if (minePos[topRightx][topRighty] == true) {
						count++;
					}
					if (minePos[rightx][righty] == true) {
						count++;
					}
					if (minePos[bottomx][bottomy] == true) {
						count++;
					}
					if (minePos[bottomRightx][bottomRighty] == true) {
						count++;
					}
					aroundMines[i][j] = giveNumber(count);
					count = 0;
				} else if (i > 0 && j > 0 && j < widthOfBoard - 1) {
					if (minePos[topx][topy] == true) {
						count++;
					}
					if (minePos[rightx][righty] == true) {
						count++;
					}
					if (minePos[bottomx][bottomy] == true) {
						count++;
					}
					if (minePos[leftx][lefty] == true) {
						count++;
					}
					if (minePos[topRightx][topRighty] == true) {
						count++;
					}
					if (minePos[topLeftx][topLefty] == true) {
						count++;
					}
					if (minePos[bottomLeftx][bottomRighty] == true) {
						count++;
					}
					if (minePos[bottomRightx][bottomRighty] == true) {
						count++;
					}
					aroundMines[i][j] = giveNumber(count);
					count = 0;
				} else if (i > 0 && j == widthOfBoard - 1) {
					if (minePos[topx][topy] == true) {
						count++;
					}
					if (minePos[bottomx][bottomy] == true) {
						count++;
					}
					if (minePos[leftx][lefty] == true) {
						count++;
					}
					if (minePos[topLeftx][topLefty] == true) {
						count++;
					}
					if (minePos[bottomLeftx][bottomRighty] == true) {
						count++;
					}

					aroundMines[i][j] = giveNumber(count);
					count = 0;
				} else if (i == heightOfBoard - 1 && j == 0) {
					if (minePos[topx][topy] == true) {
						count++;
					}
					if (minePos[rightx][righty] == true) {
						count++;
					}
					if (minePos[topRightx][topRighty] == true) {
						count++;
					}
					aroundMines[i][j] = giveNumber(count);
					count = 0;
				} else if (i == heightOfBoard - 1 && j > 0
						&& j < widthOfBoard - 1) {
					if (minePos[topx][topy] == true) {
						count++;
					}
					if (minePos[rightx][righty] == true) {
						count++;
					}
					if (minePos[leftx][lefty] == true) {
						count++;
					}
					if (minePos[topRightx][topRighty] == true) {
						count++;
					}
					if (minePos[topLeftx][topLefty] == true) {
						count++;
					}
					aroundMines[i][j] = giveNumber(count);
					count = 0;
				} else if (i == heightOfBoard - 1 && j == widthOfBoard - 1) {
					if (minePos[topx][topy] == true) {
						count++;
					}
					if (minePos[leftx][lefty] == true) {
						count++;
					}

					if (minePos[topLeftx][topLefty] == true) {
						count++;
					}
					aroundMines[i][j] = giveNumber(count);
					count = 0;
				}

			}
		}

	}

	public void assignValues() {
		// aroundMines = new AroundSquare[widthOfBoard][heightOfBoard];
		aroundMines = new String[widthOfBoard][heightOfBoard];
		boolean[][] minePositions = makeBigger(minePos);
		System.out.println(minePositions.length);
		int mineCount = 0;
		int count = 0;
		for (int i = 0; i < heightOfBoard; i++) {
			for (int j = 0; j < widthOfBoard; j++) {
				count = 0;
				int topx = i - 1 + 5;
				int topy = j + 5;

				int bottomx = i + 1 + 5;
				int bottomy = j + 5;

				int rightx = i;
				int righty = j + 1 + 5;

				int leftx = i + 5;
				int lefty = j - 1 + 5;

				int topRightx = i - 1 + 5;
				int topRighty = j + 1 + 5;

				int topLeftx = i - 1 + 5;
				int topLefty = j - 1 + 5;

				int bottomRightx = i + 1 + 5;
				int bottomRighty = j + 1 + 2;

				int bottomLeftx = i + 1 + 5;
				int bottomLefty = j - 1 + 5;

				System.out.println("Row " + i + " Column " + j);
				if (i == 0 && j == 0) {
					System.out.println("First square");
					if (minePositions[5][5] == true) {
						// aroundMines[i][j] = AroundSquare.MINE;
						System.out.println("mine");
						aroundMines[i][j] = "M";
						mineCount++;

					} else {
						// Checks if first square
						if (minePositions[rightx][righty] == true) {
							System.out.println("Found right");
							count++;
						}
						if (minePositions[bottomx][bottomy] == true) {
							System.out.println("Found bottom");
							count++;
						}
						if (minePositions[bottomRightx][bottomRighty] == true) {
							System.out.println("Found bottomRight");
							count++;
						}

						System.out
								.println("Assigning value to aroundMines at row "
										+ i
										+ "+column+"
										+ j
										+ " with count "
										+ count);
						aroundMines[i][j] = giveNumber(count);
					}
				} else {
					if (minePositions[i + 2][j + 2] == true) {
						// aroundMines[i][j] = AroundSquare.MINE;
						System.out.println("mine");
						aroundMines[i][j] = "M";
						mineCount++;

					} else {
						// Checks if first square
						if (minePositions[topx][topy] == true) {
							System.out.println("Found top");
							count++;
						}
						if (minePositions[rightx][righty] == true) {
							System.out.println("Found right");
							count++;
						}
						if (minePositions[bottomx][bottomy] == true) {
							System.out.println("Found bottom");
							count++;
						}
						if (minePositions[leftx][lefty] == true) {
							System.out.println("Found left");
							count++;
						}
						if (minePositions[topRightx][topRighty] == true) {
							System.out.println("Found topRight");
							count++;
						}
						if (minePositions[topLeftx][topLefty] == true) {
							System.out.println("Found topleft");
							count++;
						}
						if (minePositions[bottomLeftx][bottomLefty] == true) {
							System.out.println("Found bottom left");
							count++;
						}
						if (minePositions[bottomRightx][bottomRighty] == true) {
							System.out.println("Found bottomRight");
							count++;
						}

						System.out
								.println("Assigning value to aroundMines at row "
										+ i
										+ "+column+"
										+ j
										+ " with count "
										+ count);
						aroundMines[i][j] = giveNumber(count);
					}

					System.out.println("Succesfully added");

				}
			}
		}
		System.out.println("Total mines " + mineCount);

	}

	public String giveNumber(int n) {
		return n + "";
	}

	public boolean[][] makeBigger(boolean[][] mP) {
		boolean[][] bigger = new boolean[400][400];
		System.out.println("First assinging false to all bits");
		for (int k = 0; k < 400; k++) {
			for (int l = 0; l < 400; l++) {
				bigger[k][l] = false;
			}
		}
		System.out.println("Now matching from minePos");
		for (int row = 0; row < heightOfBoard; row++) {
			for (int column = 0; column < widthOfBoard; column++) {
				if (mP[row][column] == true) {
					bigger[row + 5][column + 5] = true;
				}
			}
		}
		System.out.println("Display innards");
		for(int i = 0;i < 400;i++){
			for(int j = 0;j < 400;j++){
				if(bigger[i][j] == true){
					System.out.println("Mine position at row "+ (i-5) +" column "+(j-5));
				}
			}
		}
		System.out.println("Finished matching");

		return bigger;
	}

	// public AroundSquare giveNumber(int n) {
	// if (n == 0) {
	// return AroundSquare.ZERO;
	// }
	// if (n == 1) {
	// return AroundSquare.ONE;
	// }
	// if (n == 2) {
	// return AroundSquare.TWO;
	// }
	// if (n == 3) {
	// return AroundSquare.THREE;
	// }
	// if (n == 4) {
	// return AroundSquare.FOUR;
	// }
	// if (n == 5) {
	// return AroundSquare.FIVE;
	// }
	// if (n == 6) {
	// return AroundSquare.SIX;
	// }
	// if (n == 7) {
	// return AroundSquare.SEVEN;
	// }
	// if (n == 8) {
	// return AroundSquare.EIGHT;
	// }
	// return null;
	//
	// }

}
