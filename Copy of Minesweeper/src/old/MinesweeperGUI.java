package old;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MinesweeperGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Minesweeper m;
	private JPanel board;
	private JToggleButton[][] squaresArray;
	private String[][] noMines;

	public MinesweeperGUI() {
		super("Minesweeper");
		// this.m = m;
		// // Sets theme
		// //setTheme();
		initUi();
	}

	public void setMine(Minesweeper m) {
		this.m = m;
		// Constructs actual board
		constructBoard();
		setVisible(true);
	}

	public void setTheme() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
	}

	public void initUi() {

		// Basic GUI Layout
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenu settings = new JMenu("Settings");
		JMenu help = new JMenu("Help");

		// Settings menu
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");
		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(9, 9, 10);
				MinesweeperGUI gui = new MinesweeperGUI();
				gui.setMine(min);
				gui.setSize(500, 500);

			}

		});
		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(16, 16, 40);
				MinesweeperGUI gui = new MinesweeperGUI();
				gui.setMine(min);
				gui.setSize(1000, 1000);

			}

		});
		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(30, 16, 99);
				MinesweeperGUI gui = new MinesweeperGUI();
				gui.setMine(min);
				gui.setSize(1600, 900);

			}

		});
		settings.add(easy);
		settings.add(medium);
		settings.add(hard);

		// Create Top Tool bar
		JToolBar toolbar = new JToolBar();
		JButton createNew = new JButton("New");
		JButton pause = new JButton("Pause");
		toolbar.add(createNew);
		toolbar.add(pause);

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Minesweeper min = new Minesweeper(9, 9, 10);
				setMine(min);
				setVisible(true);
			}

		});

		// Creates Bottom Tool bar
		JToolBar botbar = new JToolBar();
		JLabel statusBar = new JLabel("Status bar");
		botbar.add(statusBar);
		add(botbar, BorderLayout.SOUTH);

		menuBar.add(menu);
		menuBar.add(settings);
		menuBar.add(help);

		setJMenuBar(menuBar);
		add(toolbar, BorderLayout.NORTH);
		add(botbar, BorderLayout.SOUTH);

		// Constructs actual board
		// constructBoard();

		// Sets up main JFrame
		setVisible(true);
		// setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void constructBoard() {
		// Game board
		board = new JPanel();
		board.setLayout(new GridLayout(m.getHeight(), m.getWidth()));

		// Adds squares to board
		squaresArray = new JToggleButton[m.getHeight()][m.getWidth()];
		noMines = new String[m.getHeight()][m.getWidth()];
		for (int i = 0; i < m.getHeight(); i++) {
			for (int j = 0; j < m.getWidth(); j++) {
				final JToggleButton temp = new JToggleButton();
				// temp.setText(m.noMine[i][j]);
				noMines[i][j] = m.noMine[i][j];
				final String text = m.noMine[i][j];
				// temp.setText(m.noMine[i][j]);
				final int row = i;
				final int column = j;
				temp.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							if (!temp.getText().equals("F")) {
								temp.setText("F");
							} else {
								temp.setText("");
							}

						}

						if (SwingUtilities.isLeftMouseButton(e)) {
							System.out.println("Pressed on row " + row
									+ " column" + column);

							if (temp.isEnabled()) {
								if (text.equals("M")) {
									temp.setSelected(false);
									gameOver();
								} else {
									if(text.equals("0")){
										temp.setText(text);
										temp.setEnabled(false);
										temp.setOpaque(false);
										temp.setContentAreaFilled(false);
										temp.setBorderPainted(false);
										revealSquares(row, column);
									}else{
										temp.setText(text);
										temp.setOpaque(false);
										temp.setEnabled(false);
										temp.setContentAreaFilled(false);
										temp.setBorderPainted(false);
									}
//									recursiveCheck(temp, text, row, column);
								}
							}
							// else if (!temp.getText().equals("F")) {
							// temp.setText(text);
							// temp.setOpaque(false);
							// temp.setEnabled(false);
							// temp.setContentAreaFilled(false);
							// temp.setBorderPainted(false);
							// if(text.equalsIgnoreCase("0")){
							// revealSquares(row, column);
							//
							// }
//							revealSquares(row, column);
							// }
							// }

						}

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

				});
				squaresArray[i][j] = temp;
				board.add(temp);
			}
		}
		add(board, BorderLayout.CENTER);

	}

//	public void recursiveCheck(JToggleButton temp, String text, int row,
//			int column) {
//		if (temp.isEnabled()) {
//			if (text.equals("M")) {
//				temp.setSelected(false);
//				gameOver();
//			} else if (!temp.getText().equals("F")) {
//				temp.setText(text);
//				temp.setOpaque(false);
//				temp.setEnabled(false);
//				temp.setContentAreaFilled(false);
//				temp.setBorderPainted(false);
//				if (text.equalsIgnoreCase("0")) {
//					revealSquares(row, column);
//
//				}
//				// revealSquares(row, column);
//			}
//		}
//	}

	public void squarePress(JToggleButton temp, String text,int row,int column) {
		System.out.println("Pressing");
		if (!temp.getText().equals("F")) {
			if(text.equals("0")){
			System.out.println("row "+row+" column "+column+" has 0");
			temp.setText(text);
			temp.setOpaque(false);
			temp.setContentAreaFilled(false);
			temp.setBorderPainted(false);
			temp.setEnabled(false);
			revealSquares(row, column);
			}else if(!text.equals("0")){
				temp.setText(text);
				temp.setOpaque(false);
				temp.setContentAreaFilled(false);
				temp.setBorderPainted(false);
				temp.setEnabled(false);
			}
			
//			if (text.equals("0")) {
//				temp.setText("0");
//				temp.setOpaque(false);
//				temp.setContentAreaFilled(false);
//				temp.setBorderPainted(false);
//				temp.setEnabled(false);
//			} else {
//				System.out.println("Revealed number");
//				temp.setText(text);
//				temp.setOpaque(false);
//				temp.setContentAreaFilled(false);
//				temp.setBorderPainted(false);
//				temp.setEnabled(false);
//				;
//
//			}
		}


	}

	public void gameOver() {
		System.out.println("Game over -Pressed on mine");

		for (int row = 0; row < m.getHeight(); row++) {
			for (int column = 0; column < m.getWidth(); column++) {
				squaresArray[row][column].setSelected(false);
				squaresArray[row][column].setEnabled(false);
				if (m.noMine[row][column].equals("M")) {
					squaresArray[row][column].setText("M");
					squaresArray[row][column].setOpaque(false);
					squaresArray[row][column].setContentAreaFilled(false);
					squaresArray[row][column].setBorderPainted(false);
				}
			}
		}
		JOptionPane.showMessageDialog(getParent(), "Game over");
	}

	public void revealSquares(int row, int column) {
//		if (squaresArray[row][column].getText().equals("0")) {
//			recursiveCheck(squaresArray[row][column], noMines[row][column],
//					row, column);
//		}
		// for (int i = 0; i <= 8; i++) {
		// Possible positions
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
		// Activates surrounding buttons
		if(row >= 0 && row < m.getHeight() && column >= 0 && column < getWidth()){
		if (topx >= 0 && topx < m.getHeight() && topy >= 0
				&& topy < m.getWidth()) {
			System.out.println("Checking top");
			squarePress(squaresArray[topx][topy], noMines[topx][topy],topx,topy);
			

		}
		if (bottomx >= 0 && bottomx < m.getHeight() && bottomy >= 0
				&& bottomy < m.getWidth()) {
			System.out.println("Checking bottom");
			System.out.println("Row "+bottomx+" column "+bottomy);
			squarePress(squaresArray[bottomx][bottomy],
					noMines[bottomx][bottomy],bottomx,bottomy);
			
		}
		if (rightx >= 0 && rightx < m.getHeight() && righty >= 0
				&& righty < m.getWidth()) {
			System.out.println("Checking right");
			squarePress(squaresArray[rightx][righty], noMines[rightx][righty],rightx,righty);
			
		}
		if (leftx >= 0 && leftx < m.getHeight() && lefty >= 0
				&& lefty < m.getWidth()) {
			System.out.println("Checking left");
			squarePress(squaresArray[leftx][lefty], noMines[leftx][lefty],leftx,lefty);
			
		}
		if (topRightx >= 0 && topRightx < m.getHeight() && topRighty >= 0
				&& topRighty < m.getWidth()) {
			System.out.println("Checking top Right");
			squarePress(squaresArray[topRightx][topRighty],
					noMines[topRightx][topRighty],topRightx,topRighty);

		}
		if (topLeftx >= 0 && topLeftx < m.getHeight() && topLefty >= 0
				&& topLefty < m.getWidth()) {
			System.out.println("Checking top left");
			squarePress(squaresArray[topLeftx][topLefty],
					noMines[topLeftx][topLefty],topLeftx,topLefty);

		}
		if (bottomRightx >= 0 && bottomRightx < m.getHeight()
				&& bottomRighty >= 0 && bottomRighty < m.getWidth()) {
			System.out.println("Checking bottom right");
			squarePress(squaresArray[bottomRightx][bottomRighty],
					noMines[bottomRightx][bottomRighty],bottomRightx,bottomRighty);

		}
		if (bottomLeftx >= 0 && bottomLeftx < m.getHeight() && bottomLefty >= 0
				&& bottomLefty < m.getWidth()) {
			System.out.println("Checking bottom left");
			squarePress(squaresArray[bottomLeftx][bottomLefty],
					noMines[bottomLeftx][bottomLefty],bottomLeftx,bottomLefty);
			
		}
//		if(noMines[topx][topy].equals("0")){
//			revealSquares(topx,topy);
//		}
//		if(noMines[bottomx][bottomy].equals("0")){
//			revealSquares(bottomx,bottomy);
//		}
//		if(noMines[rightx][righty].equals("0")){
//			revealSquares(rightx,righty);
//		}
//		if(noMines[leftx][lefty].equals("0")){
//			revealSquares(leftx,lefty);
//		}
//		if(noMines[topRightx][topRighty].equals("0")){
//			revealSquares(topRightx,topRighty);
//		}
//		if(noMines[topLeftx][topLefty].equals("0")){
//			revealSquares(topLeftx,topLefty);
//		}
//		if(noMines[bottomRightx][bottomRighty].equals("0")){
//			revealSquares(bottomRightx,bottomRighty);
//		}
//		if(noMines[bottomLeftx][bottomLefty].equals("0")){
//			revealSquares(bottomLeftx,bottomLefty);
//		}
		}
		// }
	}

	public static void main(String[] args) {
		MinesweeperGUI gui = new MinesweeperGUI();
		gui.setSize(500, 500);

	}
}
