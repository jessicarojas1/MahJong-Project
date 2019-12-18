////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

//this is what the jar file will refur to.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class MahJong extends JFrame
{
	private MahJongBoard board;
	private long randomSeed;

	public MahJong()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MahJong Final Project");
		setSize(1000, 740);
		setLayout(null);
		randomSeed = ThreadLocalRandom.current().nextInt(1, 100000);
		board = new MahJongBoard(randomSeed);
		add(board);
		makeMenu();
		setVisible(true);
	}

	public static void paintMatches()
	{
		matchPane.addToUndo();
		matchPane.revalidate();
		matchPane.repaint();
	}

	public static MatchesPane matchPane = new MatchesPane();

	private void showMatches()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(matchPane);
		frame.setSize(400, 250);
		frame.setTitle("Matched Tiles");
		frame.setVisible(true);

	}
//mandatory for lab 7
	private void makeMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		menuBar.add(gameMenu);
		
		//mandatory for lab 7 6/6
		JMenuItem play = new JMenuItem("Play new game", 'P');
		play.setToolTipText("Start a new game");
		play.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		gameMenu.add(play);
		play.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				play();
			}
		});
		
		//mandatory item for lab 7 3/6
		JMenuItem restart = new JMenuItem("Restart Game", 'R');
		restart.setToolTipText("Restart the current game");
		restart.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		gameMenu.add(restart);
		restart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				restart();
			}
		});
		//mandatory item for lab 7 4/6
		JMenuItem numbered = new JMenuItem("Numbered Game", 'N');
		numbered.setToolTipText("Play a numbered game");
		numbered.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		gameMenu.add(numbered);
		numbered.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				numbered();
			}
		});
//mandatory for lab 7
		JMenuItem matches = new JMenuItem("Tiles Matched", 'M');
		matches.setToolTipText("Opens a window with all the matched tiles.");
		matches.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));
		gameMenu.add(matches);
		matches.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showMatches();
			}
		});
//mandatory for lab 7
		JMenu moveMenu = new JMenu("Move");
		moveMenu.setMnemonic('M');
		menuBar.add(moveMenu);
//mandatory for lab 7
		JMenuItem undo = new JMenuItem("Undo", 'U');
		undo.setToolTipText("Undo last move");
		undo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		moveMenu.add(undo);
		undo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				undo();
			}
		});
		//required for lab 7 sound on off -- need ot check sound to make sure it works
		JMenu soundMenu = new JMenu("Sound");
		soundMenu.setMnemonic('S');
		menuBar.add(soundMenu);

		JMenuItem soundOff = new JMenuItem("Sound off", 'O');
		soundOff.setToolTipText("Turn Sound Off");
		soundOff.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		soundMenu.add(soundOff);
		soundOff.setSelected(true); // Allows sounds to be turned off
		soundOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Sounds.setSoundon(((JCheckBoxMenuItem) e.getSource()).isSelected());
			}
		});
//mandatory for lab 7. havent figured it out all the way yet
		JMenuItem soundOn = new JMenuItem("Sound On", 'N');
		soundOn.setToolTipText("Turn Sound On");
		soundOn.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		soundMenu.add(soundOn);
		soundOn.setSelected(true); // Allows sounds to be turned on
		soundOn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Sounds.setSoundon(((JCheckBoxMenuItem) e.getSource()).isSelected());
			}
		});
		//required for lab 7
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		menuBar.add(helpMenu);

		JMenuItem gameRules = new JMenuItem("Game Rules", 'G');
		gameRules.setToolTipText("undo last move");
		gameRules.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		helpMenu.add(gameRules); // Displays Rules when clicked
		gameRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Help("Rules.html", "Rules");
			}
		});
		//required for lab 7
		JMenuItem Operations = new JMenuItem("Operation", 'O');
		Operations.setToolTipText("Game operation");
		Operations.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		helpMenu.add(Operations); // Displays Operations when clicked
		Operations.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Help("Operations.html", "Operations");
			}
		});
	}

	public void play()
	{
		//mandatory for lab 7 6/6
		Object[] options = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(this, "Are you sure you want to start a new game?", "New game",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (n == JOptionPane.YES_OPTION)
		{
			randomSeed = ThreadLocalRandom.current().nextInt(1, 100000);
			remove(board);
			board = new MahJongBoard(randomSeed);
			matchPane.clearMatches();
			add(board);
			repaint();
		}
	}
	//mandatory part of lab 7 3/6
	public void restart()
	{
		Object[] options = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(this, "Are you sure you want to restart the current game?", "Restart game",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (n == JOptionPane.YES_OPTION)
		{
			remove(board);
			board = new MahJongBoard(randomSeed);
			add(board);
			matchPane.clearMatches();
			repaint();
		}
	}
	//mandatory item for lab 7 4/6
	public void numbered()
	{
		String string = JOptionPane.showInputDialog(this, "Enter a number between 1 and 100,000.", "Numbered Game",
		        JOptionPane.PLAIN_MESSAGE);
		long inputNumber = -1;
		if (string.matches("[0-9]+") && string.length() > 0 && string.length() < 100000)
		{
			inputNumber = Long.parseLong(string);
		} else
		{
			JOptionPane.showMessageDialog(this, "Invalid input. Enter a number between 1 and 100,000.", "Error",
			        JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (inputNumber < 100000 && inputNumber > 0)
		{
			remove(board);
			randomSeed = inputNumber;
			board = new MahJongBoard(inputNumber);
			add(board);
			matchPane.clearMatches();
			repaint();
		} else
		{
			JOptionPane.showMessageDialog(this, "Invalid input. Enter a number between 1 and 100,000.", "Error",
			        JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
//mandatory for lab 7
	public void undo()
	{
		if (MahJongBoard.stack.size() > 0)
		{
			board.undo();
			board.undo();
			revalidate();
			repaint();
		} else
		{
			JOptionPane.showMessageDialog(this, "No moves to undo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String[] args)
	{
		new MahJong();
	}
}
