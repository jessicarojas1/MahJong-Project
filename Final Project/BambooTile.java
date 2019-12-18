////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

//each bamboo stick must have some physical decoration beyond being a simple rectange
//and must have some color highlights. each stick, except 8 must follow collor conevetion shown in wiki
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JFrame;

public class BambooTile extends RankTile
{

	public BambooTile(int rank)
	{
		super(rank);
		this.setToolTipText("BambooTile"); // added lab 5
	}

	class Bamboo extends BambooTile // lab 5
	{

		public int x;
		public int y;
		public Color color;

		public Bamboo(int rank)
		{
			super(rank);
			this.setToolTipText("Bambo");
		}

		public void draw(int x, int y, Color color, Graphics g) // lab 5
		{
			this.x = x;
			this.y = y;
			this.color = color;

			g.setColor(color);
			g.fillArc(x, y, 8, 10, 0, 180);
			g.fillArc(x, y + 7, 8, 10, 0, 180);
			g.fillArc(x, y + 14, 8, 10, 0, 180);
			g.fillRect(x + 3, y + 5, 4, 14);
			g.setColor(Color.WHITE);
			g.drawLine(x + 4, y + 2, x + 4, y + 6);
			g.drawLine(x + 4, y + 12, x + 4, y + 16);
		}

	}

	@Override
	public void paintComponent(Graphics g) // added lab 5
	{
		super.paintComponent(g);

		Bamboo b = new Bamboo(this.rank);

		switch (this.rank)
		{
			case 2:
				b.draw(41, 10, Color.BLUE, g);
				b.draw(41, 30, Color.GREEN, g);
				break;
			case 3:
				b.draw(41, 10, Color.BLUE, g);
				b.draw(30, 30, Color.GREEN, g);
				b.draw(52, 30, Color.GREEN, g);
				break;
			case 4:
				b.draw(30, 10, Color.BLUE, g);
				b.draw(52, 10, Color.GREEN, g);

				b.draw(30, 30, Color.GREEN, g);
				b.draw(52, 30, Color.BLUE, g);
				break;
			case 5:
				b.draw(24, 10, Color.GREEN, g);
				b.draw(58, 10, Color.BLUE, g);

				b.draw(41, 20, Color.RED, g);

				b.draw(24, 30, Color.BLUE, g);
				b.draw(58, 30, Color.GREEN, g);
				break;
			case 6:
				b.draw(24, 10, Color.GREEN, g);
				b.draw(41, 10, Color.GREEN, g);
				b.draw(58, 10, Color.GREEN, g);

				b.draw(24, 30, Color.BLUE, g);
				b.draw(41, 30, Color.BLUE, g);
				b.draw(58, 30, Color.BLUE, g);
				break;
			case 7:
				b.draw(41, 0, Color.RED, g);

				b.draw(24, 20, Color.GREEN, g);
				b.draw(41, 20, Color.BLUE, g);
				b.draw(58, 20, Color.GREEN, g);

				b.draw(24, 40, Color.GREEN, g);
				b.draw(41, 40, Color.BLUE, g);
				b.draw(58, 40, Color.GREEN, g);
				break;
			case 8:
				b.draw(24, 0, Color.GREEN, g);
				b.draw(41, 0, Color.GREEN, g);
				b.draw(58, 0, Color.GREEN, g);

				b.draw(32, 20, Color.RED, g);
				b.draw(49, 20, Color.RED, g);

				b.draw(24, 40, Color.BLUE, g);
				b.draw(41, 40, Color.BLUE, g);
				b.draw(58, 40, Color.BLUE, g);
				break;
			case 9:
				b.draw(24, 0, Color.RED, g);
				b.draw(24, 20, Color.RED, g);
				b.draw(24, 40, Color.RED, g);

				b.draw(41, 0, Color.BLUE, g);
				b.draw(41, 20, Color.BLUE, g);
				b.draw(41, 40, Color.BLUE, g);

				b.draw(58, 0, Color.GREEN, g);
				b.draw(58, 20, Color.GREEN, g);
				b.draw(58, 40, Color.GREEN, g);
				break;
			default:
		}
	}

	public String toString() // changed for lab 5
	{
		if (rank < 2 || rank > 9)
		{
			return "Invalid Bamboo Tile. CHeck the BambooTile.java file please";
		}
		return "Bamboo " + rank;

	}

	public static void main(String[] args) // provided by professor
	{
		JFrame frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bamboo Tiles");

		frame.add(new BambooTile(2));
		frame.add(new BambooTile(3));
		frame.add(new BambooTile(4));
		frame.add(new BambooTile(5));
		frame.add(new BambooTile(6));
		frame.add(new BambooTile(7));
		frame.add(new BambooTile(8));
		frame.add(new BambooTile(9));

		frame.pack();
		frame.setVisible(true);
	}

}
