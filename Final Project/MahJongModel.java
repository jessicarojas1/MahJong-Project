////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import java.util.ArrayList;
import java.util.Random;

public class MahJongModel
{
	public ArrayList<Tile> tileList = new ArrayList<Tile>();
	public Tile[][][] tiles = new Tile[12][14][5];
	public Tile leftMostTile;
	public boolean leftMostTileRemoved = false;
	public Tile rightTileLeft;
	public boolean rightTileLeftRemoved = false;
	public Tile rightTileRight;
	public boolean rightTileRightRemoved = false;
	public Tile topTile;
	public boolean topTileRemoved = false;

	public MahJongModel(long randomSeed)
	{
		// add character tiles to array list
		for (int i = 1; i < 16; i++)
		{
			char initializer = '1';
			if (i <= 9) { initializer = Character.forDigit(i, 10); } 
			else if (i == 10) { initializer = 'N'; } 
			else if (i == 11) { initializer = 'E'; } 
			else if (i == 12) { initializer = 'W'; } 
			else if (i == 13) { initializer = 'S'; } 
			else if (i == 14) { initializer = 'C'; } 
			else if (i == 15) { initializer = 'F'; }
			
			// 4 instances of each tile
			for (int j = 0; j < 4; j++) { tileList.add(new CharacterTile(initializer)); }
		}
		
		// add white dragon tiles to array list
		for (int i = 0; i < 4; i++)
		{ tileList.add(new WhiteDragonTile()); }
			// 4 tiles

		// add bamboo tiles to array list
		for (int i = 1; i < 10; i++)
		{
			for (int j = 0; j < 4; j++)
				// 4 instances of each tile
			{
				// create bamboo1tile
				if (i == 1)
				{ tileList.add(new Bamboo1Tile()); } 
				else
				{ tileList.add(new BambooTile(i)); }
			}
		}
		// add circle tiles to array list
		for (int i = 1; i < 10; i++)
		{
			// 4 instances of each tile
			for (int j = 0; j < 4; j++) { tileList.add(new CircleTile(i)); }
		}
		// add flower tiles to array list, 1 of each

		tileList.add(new FlowerTile("Chrysanthemum"));
		tileList.add(new FlowerTile("Orchid"));
		tileList.add(new FlowerTile("Plum"));
		tileList.add(new FlowerTile("Bamboo"));

		// add season tiles to array list, 1 of each
		tileList.add(new SeasonTile("Spring"));
		tileList.add(new SeasonTile("Summer"));
		tileList.add(new SeasonTile("Fall"));
		tileList.add(new SeasonTile("Winter"));

		ArrayList<Tile> randomizedList = new ArrayList<Tile>();
		Random random = new Random(randomSeed);
		while (tileList.size() > 0)
		{
			Tile t = tileList.remove(random.nextInt(tileList.size()));
			randomizedList.add(t);
		}
		tileList = randomizedList;

		// add tileList to Tile[][][]
		// layer 1
		// row 1 tiles 1-12
		for (int i = 0; i < 12; i++)
		{
			tiles[1][i + 1][0] = tileList.remove(0);
			tiles[1][i + 1][0].yPosition = i + 1;
			tiles[1][i + 1][0].xPosition = 1;
			tiles[1][i + 1][0].zPosition = 0;
			tiles[1][i + 1][0].zOrder = 0;
		}
		// row2 tiles 13 - 20
		for (int i = 2; i < 10; i++)
		{
			tiles[2][i + 1][0] = tileList.remove(0);
			tiles[2][i + 1][0].yPosition = i + 1;
			tiles[2][i + 1][0].xPosition = 2;
			tiles[2][i + 1][0].zPosition = 0;
			tiles[2][i + 1][0].zOrder = 0;
		}
		// row 3 tiles 21 - 30
		for (int i = 1; i < 11; i++)
		{
			tiles[3][i + 1][0] = tileList.remove(0);
			tiles[3][i + 1][0].yPosition = i + 1;
			tiles[3][i + 1][0].xPosition = 3;
			tiles[3][i + 1][0].zPosition = 0;
			tiles[3][i + 1][0].zOrder = 0;
		}
		// row 4 tiles 31 - 42
		for (int i = 0; i < 12; i++)
		{
			tiles[4][i + 1][0] = tileList.remove(0);
			tiles[4][i + 1][0].yPosition = i + 1;
			tiles[4][i + 1][0].xPosition = 4;
			tiles[4][i + 1][0].zPosition = 0;
			tiles[4][i + 1][0].zOrder = 0;
		}
		// row 5 tiles 43 - 54
		for (int i = 0; i < 12; i++)
		{
			tiles[5][i + 1][0] = tileList.remove(0);
			tiles[5][i + 1][0].yPosition = i + 1;
			tiles[5][i + 1][0].xPosition = 5;
			tiles[5][i + 1][0].zPosition = 0;
			tiles[5][i + 1][0].zOrder = 0;
		}
		// row 6 tiles 55 - 64
		for (int i = 1; i < 11; i++)
		{
			tiles[6][i + 1][0] = tileList.remove(0);
			tiles[6][i + 1][0].yPosition = i + 1;
			tiles[6][i + 1][0].xPosition = 6;
			tiles[6][i + 1][0].zPosition = 0;
			tiles[6][i + 1][0].zOrder = 0;
		}
		// row 7 tiles 65 - 72
		for (int i = 2; i < 10; i++)
		{
			tiles[7][i + 1][0] = tileList.remove(0);
			tiles[7][i + 1][0].yPosition = i + 1;
			tiles[7][i + 1][0].xPosition = 7;
			tiles[7][i + 1][0].zPosition = 0;
			tiles[7][i + 1][0].zOrder = 0;
		}
		// row 8 tiles 73 - 84
		for (int i = 0; i < 12; i++)
		{
			tiles[8][i + 1][0] = tileList.remove(0);
			tiles[8][i + 1][0].yPosition = i + 1;
			tiles[8][i + 1][0].xPosition = 8;
			tiles[8][i + 1][0].zPosition = 0;
			tiles[8][i + 1][0].zOrder = 0;
		}
		// layer 2
		// row 1 tiles 85 - 90
		for (int i = 0; i < 6; i++)
		{
			tiles[2][i + 4][1] = tileList.remove(0);
			tiles[2][i + 4][1].yPosition = i + 4;
			tiles[2][i + 4][1].xPosition = 2;
			tiles[2][i + 4][1].zPosition = 1;
			tiles[2][i + 4][1].zOrder = 1;
		}
		// row 2 tiles 91 - 96
		for (int i = 0; i < 6; i++)
		{
			tiles[3][i + 4][1] = tileList.remove(0);
			tiles[3][i + 4][1].yPosition = i + 4;
			tiles[3][i + 4][1].xPosition = 3;
			tiles[3][i + 4][1].zPosition = 1;
			tiles[3][i + 4][1].zOrder = 1;
		}
		// row 3 tiles 97 - 102
		for (int i = 0; i < 6; i++)
		{
			tiles[4][i + 4][1] = tileList.remove(0);
			tiles[4][i + 4][1].yPosition = i + 4;
			tiles[4][i + 4][1].xPosition = 4;
			tiles[4][i + 4][1].zPosition = 1;
			tiles[4][i + 4][1].zOrder = 1;
		}
		// row 4 tiles 103 - 108
		for (int i = 0; i < 6; i++)
		{
			tiles[5][i + 4][1] = tileList.remove(0);
			tiles[5][i + 4][1].yPosition = i + 4;
			tiles[5][i + 4][1].xPosition = 5;
			tiles[5][i + 4][1].zPosition = 1;
			tiles[5][i + 4][1].zOrder = 1;
		}
		// row 5 tiles 109 - 114
		for (int i = 0; i < 6; i++)
		{
			tiles[6][i + 4][1] = tileList.remove(0);
			tiles[6][i + 4][1].yPosition = i + 4;
			tiles[6][i + 4][1].xPosition = 6;
			tiles[6][i + 4][1].zPosition = 1;
			tiles[6][i + 4][1].zOrder = 1;
		}
		// row 6 tiles 115 - 120
		for (int i = 0; i < 6; i++)
		{
			tiles[7][i + 4][1] = tileList.remove(0);
			tiles[7][i + 4][1].yPosition = i + 4;
			tiles[7][i + 4][1].xPosition = 7;
			tiles[7][i + 4][1].zPosition = 1;
			tiles[7][i + 4][1].zOrder = 1;
		}

		// layer 3
		// row 1 tiles 121 - 124
		for (int i = 0; i < 4; i++)
		{
			tiles[3][i + 5][2] = tileList.remove(0);
			tiles[3][i + 5][2].yPosition = i + 5;
			tiles[3][i + 5][2].xPosition = 3;
			tiles[3][i + 5][2].zPosition = 2;
			tiles[3][i + 5][2].zOrder = 2;
		}
		// row 2 tiles 125 - 128
		for (int i = 0; i < 4; i++)
		{
			tiles[4][i + 5][2] = tileList.remove(0);
			tiles[4][i + 5][2].yPosition = i + 5;
			tiles[4][i + 5][2].xPosition = 4;
			tiles[4][i + 5][2].zPosition = 2;
			tiles[4][i + 5][2].zOrder = 2;
		}
		// row 3 tiles 129 - 132
		for (int i = 0; i < 4; i++)
		{
			tiles[5][i + 5][2] = tileList.remove(0);
			tiles[5][i + 5][2].yPosition = i + 5;
			tiles[5][i + 5][2].xPosition = 5;
			tiles[5][i + 5][2].zPosition = 2;
			tiles[5][i + 5][2].zOrder = 2;
		}
		// row 4 tiles 133 - 136
		for (int i = 0; i < 4; i++)
		{
			tiles[6][i + 5][2] = tileList.remove(0);
			tiles[6][i + 5][2].yPosition = i + 5;
			tiles[6][i + 5][2].xPosition = 6;
			tiles[6][i + 5][2].zPosition = 2;
			tiles[6][i + 5][2].zOrder = 2;
		}

		// layer 4
		// row 1 tiles 137 - 138
		tiles[4][6][3] = tileList.remove(0);
		tiles[4][6][3].yPosition = 6;
		tiles[4][6][3].xPosition = 4;
		tiles[4][6][3].zPosition = 3;
		tiles[4][6][3].zOrder = 3;
		tiles[4][7][3] = tileList.remove(0);
		tiles[4][7][3].yPosition = 7;
		tiles[4][7][3].xPosition = 4;
		tiles[4][7][3].zPosition = 3;
		tiles[4][7][3].zOrder = 3;
		
		// row 2 tiles 139 - 140
		tiles[5][6][3] = tileList.remove(0);
		tiles[5][6][3].yPosition = 6;
		tiles[5][6][3].xPosition = 5;
		tiles[5][6][3].zPosition = 3;
		tiles[5][6][3].zOrder = 3;
		tiles[5][7][3] = tileList.remove(0);
		tiles[5][7][3].yPosition = 7;
		tiles[5][7][3].xPosition = 5;
		tiles[5][7][3].zPosition = 3;
		tiles[5][7][3].zOrder = 3;
		
		// special case tiles the werd tiles
		leftMostTile = tileList.remove(0);
		rightTileLeft = tileList.remove(0);
		rightTileRight = tileList.remove(0);
		topTile = tileList.remove(0);

	}

	public static void main(String[] args)
	{
		// nothing here remember
	}
}