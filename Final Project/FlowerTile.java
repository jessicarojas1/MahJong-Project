////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import javax.swing.*;
import java.awt.*;

public class FlowerTile extends PictureTile
{
	public FlowerTile(String name) // lab 4
	{
		super(name);
	}

	public static void main(String[] args) // provided by professor lab 5
	{
		JFrame frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Flower Tiles");

		frame.add(new FlowerTile("Chrysanthemum"));
		frame.add(new FlowerTile("Orchid"));
		frame.add(new FlowerTile("Plum"));
		frame.add(new FlowerTile("Bamboo"));

		frame.pack();
		frame.setVisible(true);
	}
}
