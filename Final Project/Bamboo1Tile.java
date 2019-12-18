////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import javax.swing.*; //lab 6
import java.awt.*;
import java.net.URL;

public class Bamboo1Tile extends PictureTile
{

	private static Image image; // lab 6

	public Bamboo1Tile() // lab 4
	{
		super("sparrow");
	}

	@Override
	public String toString() // lab 4
	{
		return "Bamboo 1";
	}

	@Override
	public void paintComponent(Graphics g)
	{ // for lab 6
		super.paintComponent(g);
		URL url = Bamboo1Tile.class.getResource("images/Sparrow.png");
		image = Toolkit.getDefaultToolkit().getImage(url);
		g.drawImage(image, 19, 4, 53, 54, this);
	}

// following for lab 5
	public static void main(String[] args) // provided by professor
	{
		JFrame frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bamboo 1 Tile");

		frame.add(new Bamboo1Tile());

		frame.pack();
		frame.setVisible(true);
	}
}
