////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java
//did in class
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MahJongTest extends JFrame
{

	public MahJongTest()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new TestPanel());
		setSize(500, 500);
		setVisible(true);
	}

	public class TestPanel extends JPanel
	{
		public TestPanel()
		{
			setLayout(null); // must setSize in Tile

			Tile t;

			t = new SeasonTile("Spring");
			t.setLocation(200, 100);
			add(t);
			System.out.println(this.getComponentZOrder(t));

			t = new SeasonTile("Summer");
			t.setLocation(200 - Tile.S2, 100 + Tile.S2);
			add(t);
			System.out.println(this.getComponentZOrder(t));

			t = new SeasonTile("Fall");
			t.setLocation(200 - (Tile.S2 * 2), 100 + (Tile.S2 * 2));
			add(t);
			System.out.println(this.getComponentZOrder(t));

			t = new SeasonTile("Winter");
			t.setLocation(200 - (Tile.S2 * 3), 100 + (Tile.S2 * 3));
			add(t);
			System.out.println(this.getComponentZOrder(t));
		}
	}

	public static void main(String[] args)
	{
		new MahJongTest();
	}

}
