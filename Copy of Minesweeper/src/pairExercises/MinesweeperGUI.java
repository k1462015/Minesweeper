package pairExercises;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

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

public class MinesweeperGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Minesweeper m;
	private JPanel board;
	private JToggleButton[][] squaresArray;

	public MinesweeperGUI(Minesweeper m) {
		super("Minesweeper");
		this.m = m;
		initUi();
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

		// Adds actionListeners to all difficulty settings
		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(9, 9, 10);
				MinesweeperGUI gui = new MinesweeperGUI(min);
				gui.setSize(500, 500);
				gui.setLocationRelativeTo(null);
			}

		});
		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(16, 16, 40);
				MinesweeperGUI gui = new MinesweeperGUI(min);
				gui.setSize(1000, 1000);
				gui.setLocationRelativeTo(null);
			}

		});
		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(30, 16, 99);
				MinesweeperGUI gui = new MinesweeperGUI(min);
				gui.setSize(1600, 900);
				gui.setLocationRelativeTo(null);

			}

		});

		// Adds to settings menu
		settings.add(easy);
		settings.add(medium);
		settings.add(hard);

		// Create Top Tool bar
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		JButton createNew = new JButton("New");
		JButton pause = new JButton("Pause");
		toolbar.add(createNew);
		toolbar.add(pause);

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				if(getWidth() == 500){
					Minesweeper min = new Minesweeper(9, 9, 10);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(500, 500);
					gui.setLocationRelativeTo(null);
				}else if(getWidth() == 1000){
					Minesweeper min = new Minesweeper(16, 16, 40);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(1000, 1000);
					gui.setLocationRelativeTo(null);
				}else if(getWidth() == 1600){
					Minesweeper min = new Minesweeper(30, 16, 99);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(1600, 900);
					gui.setLocationRelativeTo(null);
				}else{
					Minesweeper min = new Minesweeper(9, 9, 10);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(500, 500);
					gui.setLocationRelativeTo(null);
				}

			}

		});

		// Creates Bottom Tool bar
		JToolBar botbar = new JToolBar();
		botbar.setFloatable(false);
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
		constructBoard();

		// Sets up main JFrame
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void constructBoard() {
		// Game board
		board = new JPanel();
		board.setLayout(new GridLayout(m.getHeight(), m.getWidth()));

		// Adds squares to board
		squaresArray = new JToggleButton[m.getHeight()][m.getWidth()];
		for (int i = 0; i < m.getHeight(); i++) {
			for (int j = 0; j < m.getWidth(); j++) {
				final JToggleButton temp = new JToggleButton();
				final int row = i;
				final int column = j;
				//If you want to view what number is in every square
//				temp.setText(m.squareStatus[row][column].ordinal()+"");
				temp.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						if (e.getClickCount() == 1) {
							if (SwingUtilities.isRightMouseButton(e)) {
								if (!temp.getText().equals("F")) {
									temp.setText("F");
								} else {
									temp.setText("");
								}

							}

							if (SwingUtilities.isLeftMouseButton(e)) {

								if (!temp.getText().equals("F")) {
									if (temp.isEnabled()) {

										if (m.squareStatus[row][column] == SquareStatus.MINE) {
											System.out.println("Click on mine");
											gameOver();
										}
										
										else {
											if (m.squareStatus[row][column] == SquareStatus.ZERO) {
												System.out.println("Clicked on 0");
												temp.setOpaque(false);
												temp.setEnabled(false);
												temp.setContentAreaFilled(false);
												temp.setBorderPainted(false);
											} else {
												System.out.println("Clicked on square 1-8");
												temp.setText(m.squareStatus[row][column].ordinal()+"");
												temp.setOpaque(false);
												temp.setEnabled(false);
												temp.setContentAreaFilled(false);
												temp.setBorderPainted(false);
											}
										}

									}

								}
							}

						}

					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

				});
				squaresArray[i][j] = temp;
				board.add(temp);
			}
		}
		add(board, BorderLayout.CENTER);

	}


	public void gameOver() {
		System.out.println("Game over - Pressed on mine");
		for (int row = 0; row < m.getHeight(); row++) {
			for (int column = 0; column < m.getWidth(); column++) {
				squaresArray[row][column].setEnabled(false);
			}
		}
		JOptionPane.showMessageDialog(getParent(), "Game over");
	}

	public static void main(String[] args) {
		Minesweeper min = new Minesweeper(9, 9, 10);
		MinesweeperGUI gui = new MinesweeperGUI(min);
		gui.setSize(500, 500);
		gui.setLocationRelativeTo(null);

	}
}
