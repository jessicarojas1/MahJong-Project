////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//changed a lot for lab 5
public class CharacterTile extends Tile
{
	private static final Map<Character, String> validSymbols;
	private static final Map<Character, String> validSymbolNames;

	// In a static initializer block, add all the English to Chinese character
	// mappings
	static
	{
		validSymbols = new HashMap<>();
		// Initialize English characters to Chinese characters
		validSymbols.put('1', "\u4E00");
		validSymbols.put('2', "\u4E8C");
		validSymbols.put('3', "\u4E09");
		validSymbols.put('4', "\u56DB");
		validSymbols.put('5', "\u4E94");
		validSymbols.put('6', "\u516D");
		validSymbols.put('7', "\u4E03");
		validSymbols.put('8', "\u516B");
		validSymbols.put('9', "\u4E5D");
		validSymbols.put('N', "\u5317");
		validSymbols.put('E', "\u6771");
		validSymbols.put('W', "\u897F");
		validSymbols.put('S', "\u5357");
		validSymbols.put('C', "\u4E2D");
		validSymbols.put('F', "\u767C");
		validSymbols.put('w', "\u842C");
		validSymbolNames = new HashMap<>();
		for (int i = 1; i < 10; i++)
		{
			validSymbolNames.put(Character.forDigit(i, 10), "Character " + i);
		}
		validSymbolNames.put('N', "North Wind");
		validSymbolNames.put('E', "East Wind");
		validSymbolNames.put('W', "West Wind");
		validSymbolNames.put('S', "South Wind");
		validSymbolNames.put('C', "Red Dragon");
		validSymbolNames.put('F', "Green Dragon");
	}
	char symbol;

	public CharacterTile(char symbol) // lab 4
	{
		super();
		this.symbol = symbol;
		this.setToolTipText("CharacterTile");// added for lab 5
	}

	public boolean matches(Tile other) // lab 4
	{

		return super.matches(other) && this.symbol == ((CharacterTile) other).symbol;
	}

	@Override
	public void paintComponent(Graphics g) // lab 5
	{
		super.paintComponent(g);

		Font f2_0 = g.getFont();
		f2_0 = f2_0.deriveFont(f2_0.getSize2D() * 2.0F); // enlarges font

		Font f0_5 = g.getFont();
		f0_5 = f0_5.deriveFont(f0_5.getSize2D() * 0.5F); // enlarges font

		Font f2_75 = g.getFont();
		f2_75 = f2_75.deriveFont(f2_75.getSize2D() * 2.75F);

		Font f1_0 = g.getFont();
		f1_0 = f1_0.deriveFont(f1_0.getSize2D() * 1.0F);

		FontMetrics fm = g.getFontMetrics();
		String temp = "";
		int wid = 0;

		g.setColor(Color.BLACK);
		g.setFont(f2_0);

		switch (this.symbol)
		{
			case '1':
				temp = Character.toString('\u4E00'); // Chinese 1
				break;
			case '2':
				temp = Character.toString('\u4E8C');
				break;
			case '3':
				temp = Character.toString('\u4E09');
				break;
			case '4':
				temp = Character.toString('\u56DB');
				break;
			case '5':
				temp = Character.toString('\u4E94');
				break;
			case '6':
				temp = Character.toString('\u516D');
				break;
			case '7':
				temp = Character.toString('\u4E03');
				break;
			case '8':
				temp = Character.toString('\u516B');
				break;
			case '9':
				temp = Character.toString('\u4E5D');
				break;
			case 'N':
				temp = Character.toString('\u5317');
				break;
			case 'E':
				temp = Character.toString('\u6771');
				break;
			case 'S':
				temp = Character.toString('\u5357');
				break;
			case 'W':
				g.setColor(Color.BLACK);
				temp = Character.toString('\u897F');
				break;
			case 'C':
				g.setColor(Color.RED);
				temp = Character.toString('\u4E2D');
				break;
			case 'F':
				g.setColor(Color.GREEN);
				temp = Character.toString('\u767C');
				break;
			default:
				break;
		}

		wid = fm.stringWidth(temp);

		switch (this.symbol)
		{
			case 'N':
			case 'E':
			case 'W':
			case 'S':
			case 'C':
			case 'F':
				g.setFont(f2_75);
				g.drawString(temp, ((Tile.S9 - wid) / 2) - (S1 / 2), Tile.S5);
		}

		switch (this.symbol)
		{
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				g.drawString(temp, ((Tile.S10 - wid) / 2) - S1, Tile.S3 - Tile.S1);
				g.setColor(Color.RED);
				g.setFont(f2_0);
				temp = Character.toString('\u842C');
				wid = fm.stringWidth(temp);
				g.drawString(temp, ((Tile.S10 - wid) / 2) - S1, Tile.S6 - 1);
			case 'N':
			case 'E':
			case 'W':
			case 'S':
			case 'C':
			case 'F':
				g.setColor(Color.BLACK); //i changed the color to black from red becasue it hurts my eyes to read the leters
				g.setFont(f1_0);
				g.drawString(Character.toString(this.symbol), (Tile.S6 + (Tile.S2 / 2)), Tile.S2); // draw red char
		}
	}

	// returns the Chinese character that corresponds to the tile's symbol or
	// English character.
	public String toChinese()
	{
		switch (symbol)
		{
			case '1':
				return "\u4E00"; // Chinese 1
			case '2':
				return "\u4E8C"; // Chinese 2
			case '3':
				return "\u4E09"; // Chinese 3
			case '4':
				return "\u56DB"; // Chinese 4
			case '5':
				return "\u4E94"; // Chinese 5
			case '6':
				return "\u516D"; // Chinese 6
			case '7':
				return "\u4E03"; // Chinese 7
			case '8':
				return "\u516B "; // Chinese 8
			case '9':
				return "\u4E5D "; // Chinese 9
			case 'N':
				return "\u5317"; // Chinese N (North wind)
			case 'E':
				return "\u6771"; // Chinese E (east wind)
			case 'W':
				return "\u897F"; // Chinese W (west wind)
			case 'S':
				return "\u5357"; // Chinese S (south wind)
			case 'C':
				return "\u4E2D"; // Chinese C (the red dragon)
			case 'F':
				return "\u767C"; // Chinese F (the green dragon)

			default:
				return "Character " + symbol; // would return the string of "Character 1023" something like this instead
				                              // of just chinese character
		}
	}

	public static void main(String[] args) // professor provided lab 5
	{

		JFrame frame = new JFrame();
		JPanel tiles = new JPanel();
		JScrollPane scroller = new JScrollPane(tiles);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Character Tiles");
		frame.add(scroller);

		tiles.add(new CharacterTile('1'));
		tiles.add(new CharacterTile('2'));
		tiles.add(new CharacterTile('3'));
		tiles.add(new CharacterTile('4'));
		tiles.add(new CharacterTile('5'));
		tiles.add(new CharacterTile('6'));
		tiles.add(new CharacterTile('7'));
		tiles.add(new CharacterTile('8'));
		tiles.add(new CharacterTile('9'));
		tiles.add(new CharacterTile('N'));
		tiles.add(new CharacterTile('E'));
		tiles.add(new CharacterTile('W'));
		tiles.add(new CharacterTile('S'));
		tiles.add(new CharacterTile('C'));
		tiles.add(new CharacterTile('F'));

		frame.pack();
		frame.setVisible(true);

	}

}
