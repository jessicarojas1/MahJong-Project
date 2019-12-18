////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

public class CircleTile extends RankTile
{

	public CircleTile(int rank) // lab 4
	{
		super(rank);
		this.setToolTipText("CircleTile"); // lab 5
	}

	class Circle extends CircleTile
	{
		public int x;
		public int y;
		public Color color;

		public Circle(int rank)
		{
			super(rank);
			this.setToolTipText("Circle");
		}

		public void draw(int x, int y, Color color, Graphics g)
		{
			this.x = x;
			this.y = y;
			this.color = color;

			int diameter = 0;
			if (this.rank == 1 || this.rank == 7 || this.rank == 8)
			{
				diameter = 12;
			} else if (this.rank == 2 || this.rank == 4)
			{
				diameter = 24;
			} else
			{
				// extra credit done here for the CircleTiel class
				diameter = 18;// 12 makes it smaller can allternate this to make bigger or smaller
			}

			final int DIAMETER = diameter;
			final int RADIUS = DIAMETER / 2;
			final int S = DIAMETER / 4;

			g.setColor(color);
			g.fillOval(x, y, DIAMETER, DIAMETER);
			g.setColor(Color.WHITE);
			g.drawLine(x + S, y + S, x + DIAMETER - S, y + DIAMETER - S); // \ in circle
			g.drawLine(x + DIAMETER - S, y + S, x + S, y + DIAMETER - S); // / in circle
			g.drawLine(x + RADIUS, y + S, x + RADIUS, y + DIAMETER - S); // | in circle
			g.drawLine(x + S, y + RADIUS, x + DIAMETER - S, y + RADIUS); // - in circle
		}
	}

	class ct extends CircleTile
	{

		public ct(int rank)
		{
			super(rank);
			this.setToolTipText("ct");
		}

		public void draw(Graphics g)
		{
			g.setColor(Color.BLUE); // outer ring
			g.fillOval(20, 5, 49, 51); // outer ring

			g.setColor(Color.GREEN); // inner big circle
			g.fillOval(23, 8, 43, 45);// inner circle

			g.setColor(Color.WHITE);// innner small circle ring color
			g.fillOval(42, 13, 6, 6); // t
			g.fillOval(42, 44, 6, 6); // b
			g.fillOval(27, 29, 6, 6); // l
			g.fillOval(57, 29, 6, 6); // r

			g.fillOval(31, 18, 6, 6); // top l
			g.fillOval(31, 39, 6, 6); // bottom l
			g.fillOval(53, 18, 6, 6); // top r
			g.fillOval(53, 39, 6, 6); // bottom r
		}
	}

	@Override
	public void paintComponent(Graphics g) // lab 5
	{
		super.paintComponent(g);

		Circle c = new Circle(this.rank);
		ct p = new ct(this.rank);

		switch (this.rank)
		{
			case 1:
				p.draw(g);
				// extra credit here
				c.draw(37, 25, Color.WHITE, g);// small white wring under red with x for circle tile 1
				c.draw(41, 25, Color.WHITE, g);
				c.draw(39, 27, Color.WHITE, g);
				c.draw(39, 23, Color.WHITE, g);

				c.draw(39, 25, Color.RED, g);// small red with x in circle 1

				break;
			case 2:
				c.draw(30, 5, Color.GREEN, g);
				c.draw(30, 32, Color.RED, g);
				break;
			case 3:
				c.draw(17, 2, Color.BLUE, g);
				c.draw(35, 21, Color.RED, g);
				c.draw(53, 40, Color.GREEN, g);
				break;
			case 4:
				c.draw(19, 5, Color.BLUE, g);
				c.draw(19, 32, Color.GREEN, g);
				c.draw(47, 5, Color.GREEN, g);
				c.draw(47, 32, Color.BLUE, g);
				break;
			case 5:
				c.draw(17, 2, Color.BLUE, g);
				c.draw(35, 22, Color.RED, g);
				c.draw(53, 40, Color.BLUE, g);
				c.draw(17, 40, Color.GREEN, g);
				c.draw(53, 2, Color.GREEN, g);
				break;
			case 6:
				c.draw(24, 2, Color.GREEN, g);
				c.draw(24, 21, Color.RED, g);
				c.draw(24, 40, Color.RED, g);
				c.draw(48, 2, Color.GREEN, g);
				c.draw(48, 21, Color.RED, g);
				c.draw(48, 40, Color.RED, g);
				break;
			case 7:
				c.draw(19, 4, Color.GREEN, g);
				c.draw(38, 8, Color.GREEN, g);
				c.draw(57, 14, Color.GREEN, g);

				c.draw(24, 29, Color.RED, g);
				c.draw(52, 29, Color.RED, g);

				c.draw(24, 42, Color.RED, g);
				c.draw(52, 42, Color.RED, g);
				break;
			case 8:
				c.draw(24, 3, Color.BLUE, g);
				c.draw(52, 3, Color.BLUE, g);

				c.draw(24, 17, Color.BLUE, g);
				c.draw(52, 17, Color.BLUE, g);

				c.draw(24, 31, Color.BLUE, g);
				c.draw(52, 31, Color.BLUE, g);

				c.draw(24, 44, Color.BLUE, g);
				c.draw(52, 44, Color.BLUE, g);

				break;
			case 9:
				c.draw(16, 2, Color.GREEN, g);
				c.draw(35, 2, Color.GREEN, g);
				c.draw(54, 2, Color.GREEN, g);
				c.draw(16, 21, Color.RED, g);
				c.draw(35, 21, Color.RED, g);
				c.draw(54, 21, Color.RED, g);
				c.draw(16, 40, Color.BLUE, g);
				c.draw(35, 40, Color.BLUE, g);
				c.draw(54, 40, Color.BLUE, g);
				break;
			default:
		}
	}

	@Override
	public String toString() // lab 4
	{
		if (rank > 9 || rank < 1)
			return "Invalid circle tile. check circleTile.java";
		return "Circle " + rank;
	}

	public static void main(String[] args) // lab 5
	{ // professor gave us this
		JFrame frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circle Tiles");

		frame.add(new CircleTile(1));
		frame.add(new CircleTile(2));
		frame.add(new CircleTile(3));
		frame.add(new CircleTile(4));
		frame.add(new CircleTile(5));
		frame.add(new CircleTile(6));
		frame.add(new CircleTile(7));
		frame.add(new CircleTile(8));
		frame.add(new CircleTile(9));

		frame.pack();
		frame.setVisible(true);
	}
}
