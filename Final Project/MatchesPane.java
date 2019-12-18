////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import java.util.*;
import java.awt.*;
import javax.swing.*;
//will display matches mad when you click on button to show you must clicl option to actually view the matched panels when undo it will take tiles of of this
public class MatchesPane extends JScrollPane
{

	private JPanel[] discard = new JPanel[2];
	private int width = 75; //width and hight of the oard that will display the matched made
	private int height = 75;
	private int count = 0;

	public MatchesPane()
	{
		setPreferredSize(new Dimension(((count + 2) * width) + 20, 2 * height + 33));
		setBorder(BorderFactory.createRaisedBevelBorder());

		discard[0] = new JPanel(new GridLayout());
		discard[1] = new JPanel(new GridLayout());
		discard[0].setPreferredSize(new Dimension(0, height));
		discard[1].setPreferredSize(new Dimension(0, height));

		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel(new BorderLayout());
		setViewportView(panel);

		panel.add(discard[0], BorderLayout.NORTH);
		panel.add(discard[1], BorderLayout.SOUTH);

		discard[0].setBackground(new Color(0,0,0)); //black backround all three layours.
		discard[1].setBackground(new Color(0,0,0));
		panel.setBackground(new Color(0, 0, 0));
	}

	public void clearMatches()
	{
		discard[0].removeAll();
		discard[0].revalidate();
		discard[0].repaint();
		discard[1].removeAll();
		discard[1].revalidate();
		discard[1].repaint();
	}

	public void addToUndo()
	{

		Dimension size = new Dimension(++count * width, height + 6);
		discard[0].setPreferredSize(size);
		discard[1].setPreferredSize(size);

		//most recently added tiles on the right and scrolls
		Stack<Tile> tiles = MahJongBoard.stack;
		for (int i = 0; i < tiles.size(); i += 2)
		{
			discard[0].add(tiles.get(i));
			discard[1].add(tiles.get(i + 1));
		}

		Rectangle r = new Rectangle(count * width, 0, width, height + 6);
		getViewport().scrollRectToVisible(r);

		revalidate();
		repaint();
	}

	public static void main(String[] args)
	{
		MatchesPane demo = new MatchesPane();
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(demo);
		frame.setSize(400, 250);
		frame.setVisible(true);
	}
}