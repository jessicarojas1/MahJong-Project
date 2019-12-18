////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class MahJongBoard extends JPanel implements MouseListener
{
	private static int xBounds = 73;
	private static int yBounds = 73;
	private static int xLength = 56;
	private static int yLength = 56;
	private MahJongModel model;
	private Tile firstClickedTile = null;
	private int firstClickedTileX;
	private int firstClickedTileY;
	private int firstClickedTileZ;
	private Tile secondClickedTile = null;
	private int secondClickedTileX;
	private int secondClickedTileY;
	private int secondClickedTileZ;
	private static Fireworks fire;
	public static Stack<Tile> stack;

	public MahJongBoard(long randomSeed)
	{
		//welcome on top of board
		setLayout(null);
		setBackground(Color.BLACK);
		setLocation(0, 0);
		setSize(1100, 700);
		model = new MahJongModel(randomSeed);
		JLabel labelwelcome = new JLabel();
		labelwelcome.setBounds(350, 2, 500, 40);
		add(labelwelcome);
		labelwelcome.setText("Welcome to the MahJong Game! Good Luck.");
		labelwelcome.setForeground(Color.CYAN);
		
		//this displays what number of game they are on wich is selected by a randomizer unles repeated
		setLayout(null);
		setBackground(Color.BLACK);
		setLocation(0, 0);
		setSize(1100, 700);
		model = new MahJongModel(randomSeed);
		JLabel label = new JLabel();
		label.setBounds(650, 650, 500, 25);
		add(label);
		label.setText("The Numbered Game you are on is : " + randomSeed);
		label.setForeground(Color.CYAN);
		fire = new Fireworks(this);

		stack = new Stack<Tile>();
		// top tile
		Tile t = model.topTile;
		t.addMouseListener(this);
		t.setBounds(490, 225, xBounds, yBounds);
		t.xBound = 490;
		t.yBound = 225;
		setComponentZOrder(t, t.zOrder);
		add(t);

		// left most tile
		t = model.leftMostTile;
		t.addMouseListener(this);
		t.setBounds(64, 285, xBounds, yBounds);
		t.xBound = 64;
		t.yBound = 285;
		add(t);

		int xPosition = 144;
		int yPosition = -42;
		for (int z = 4; z >= 0; z--)
		{
			xPosition = xPosition - 16;
			yPosition = yPosition + 16;
			for (int i = 9; i >= 0; i--)
			{
				for (int j = 1; j < 13; j++)
				{
					if (model.tiles[i][j][z] != null)
					{
						t = model.tiles[i][j][z];
						t.setBounds(xPosition + j * xLength, yPosition + i * yLength, xBounds, yBounds);
						t.xBound = xPosition + j * xLength;
						t.yBound = yPosition + i * yLength;
						setComponentZOrder(t, t.getZOrder());
						t.addMouseListener(this);
						add(t);
					}
				}
			}
		}

		// right most tiles
		t = model.rightTileLeft;
		t.addMouseListener(this);
		t.setBounds(792, 285, xBounds, yBounds);
		t.xBound = 792;
		t.yBound = 285;
		add(t);

		t = model.rightTileRight;
		t.addMouseListener(this);
		t.setBounds(792 + xLength, 285, xBounds, yBounds);
		t.xBound = 792 + xLength;
		t.yBound = 285;
		add(t);

		setVisible(true);
	}

	public void mouseClicked(MouseEvent e)
	{
		Tile clickedTile = (Tile) e.getSource();
		int tileX = clickedTile.xPosition;
		int tileY = clickedTile.yPosition;
		int tileZ = clickedTile.zPosition;
		clickedTile.setZOrder(getComponentZOrder(clickedTile));
		boolean tileIsOpen = tileIsOpen(clickedTile, tileX, tileY, tileZ);
		// valid click on an open tile
		if (tileIsOpen)
		{
			if (firstClickedTile == null)
			{
				clickedTile.highlightTile = true;
				firstClickedTile = clickedTile;
				firstClickedTileX = tileX;
				firstClickedTileY = tileY;
				firstClickedTileZ = tileZ;
			} else if (secondClickedTile == null)
			{
				clickedTile.highlightTile = true;
				repaint();
				secondClickedTile = clickedTile;
				secondClickedTileX = tileX;
				secondClickedTileY = tileY;
				secondClickedTileZ = tileZ;
			}
			if (firstClickedTile != null && secondClickedTile != null)
			{
				if (firstClickedTile != secondClickedTile)
				{
					if (firstClickedTile.matches(secondClickedTile))
					{
						removeTile(firstClickedTile, firstClickedTileX, firstClickedTileY, firstClickedTileZ);
						removeTile(secondClickedTile, secondClickedTileX, secondClickedTileY, secondClickedTileZ);
						revalidate();
						repaint();
						MahJong.paintMatches();
					}
				}
				firstClickedTile.highlightTile = false;
				secondClickedTile.highlightTile = false;
				firstClickedTile = null;
				secondClickedTile = null;

			}
		}
		// tile is not open
		else
		{
			if (firstClickedTile != null)
			{
				firstClickedTile.highlightTile = false;
			}
			if (secondClickedTile != null)
			{
				secondClickedTile.highlightTile = false;
			}
			firstClickedTile = null;
			secondClickedTile = null;
		}
		repaint();
		if (model.tiles.length == 0)
		{
			JOptionPane.showMessageDialog(this, "You Win!", "CONGRATS, You ROCK!", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}


	public boolean tileIsOpen(Tile clickedTile, int tileX, int tileY, int tileZ)
	{
		// check if it is a special case tile
		if (clickedTile.equals(model.topTile))
		{
			return true;
		} else if (clickedTile.equals(model.leftMostTile))
		{
			return true;
		} else if (clickedTile.equals(model.rightTileLeft))
		{
			if (model.rightTileRightRemoved)
			{
				return true;
			}
		} else if (clickedTile.equals(model.rightTileRight))
		{
			return true;
		}
		// adjacent to left tile
		else if ((tileX == 4 && tileY == 1 && tileZ == 0) || (tileX == 5 && tileY == 1 && tileZ == 0))
		{
			if (model.leftMostTileRemoved)
			{
				return true;
			}
		}
		// adjacent to the right most left tile
		else if ((tileX == 4 && tileY == 12 && tileZ == 0) || (tileX == 5 && tileY == 12 && tileZ == 0))
		{
			if (model.rightTileLeftRemoved)
			{
				return true;
			}
		}
		// below top tile
		else if ((tileX == 5 && tileY == 6 && tileZ == 3) || (tileX == 4 && tileY == 6 && tileZ == 3)
		        || (tileX == 4 && tileY == 7 && tileZ == 3) || (tileX == 5 && tileY == 7 && tileZ == 3))
		{
			if (model.topTileRemoved)
			{
				return true;
			}
		} else
		{
			// check left and right sides
			if (model.tiles[tileX][(tileY - 1)][tileZ] == null || model.tiles[tileX][(tileY + 1)][tileZ] == null)
			{
				// check if a tile is on top of this tile
				if (model.tiles[tileX][tileY][(tileZ + 1)] == null)
				{
					return true;
				}
			}
		}
		return false;
	}

	public void removeTile(Tile t, int tileX, int tileY, int tileZ)
	{
		if (t.equals(model.topTile))
		{
			model.topTileRemoved = true;
		} else if (t.equals(model.leftMostTile))
		{
			model.leftMostTileRemoved = true;
		} else if (t.equals(model.rightTileLeft))
		{
			if (model.rightTileRightRemoved)
			{
				model.rightTileLeftRemoved = true;
			}
		} else if (t.equals(model.rightTileRight))
		{
			model.rightTileRightRemoved = true;
		}
		// adjacent to left tile
		else if ((tileX == 4 && tileY == 1 && tileZ == 0) || (tileX == 5 && tileY == 1 && tileZ == 0))
		{
			if (model.leftMostTileRemoved)
			{
				// remove tile from tile model
				model.tiles[tileX][tileY][tileZ] = null;
			}
		}
		// adjacent to the right most left tile
		else if ((tileX == 4 && tileY == 12 && tileZ == 0) || (tileX == 5 && tileY == 12 && tileZ == 0))
		{
			if (model.rightTileLeftRemoved)
			{
				// remove tile from tile model
				model.tiles[tileX][tileY][tileZ] = null;
			}
		}
		// below top tile
		else if ((tileX == 5 && tileY == 6 && tileZ == 3) || (tileX == 4 && tileY == 6 && tileZ == 3)
		        || (tileX == 4 && tileY == 7 && tileZ == 3) || (tileX == 5 && tileY == 7 && tileZ == 3))
		{
			if (model.topTileRemoved)
			{
				// remove tile from tile model
				model.tiles[tileX][tileY][tileZ] = null;
			}
		} else
		{
			// check ferter left and ferther right
			if (model.tiles[tileX][(tileY - 1)][tileZ] == null || model.tiles[tileX][(tileY + 1)][tileZ] == null)
			{
				// check if a tile is stacked on top of this tile
				if (model.tiles[tileX][tileY][(tileZ + 1)] == null)
				{
					// remove tile from ti model
					model.tiles[tileX][tileY][tileZ] = null;
				}
			}
		}
		t.setZOrder(getComponentZOrder(t));
		stack.push(t);
		// remove the tile from the JPanel
		remove(t);
	}

	public void undo()
	{
		Tile t = stack.pop();
		model.tiles[t.xPosition][t.yPosition][t.zPosition] = t;
		t.setBounds(t.xBound, t.yBound, xBounds, yBounds);
		add(t, t.getZOrder());
		revalidate();
		repaint();
	}
	ImageIcon img = new ImageIcon(getClass().getResource("images/dragon_bg.png")); //background of the draggone behind the game

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Image image = img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		g.drawImage(new ImageIcon(image).getImage(), 0, 0, this);

	}

}