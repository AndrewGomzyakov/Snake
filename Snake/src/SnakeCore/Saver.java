package SnakeCore;

public class Saver {
	public static void Save(String path, GameState game)
	{
		int telCounter = 0;
		int hedgCounter = 0;
		int foodCounter  = 0;
		int pilCounter = 0;
		char[][] a = game.getMap();
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				switch(a[i][j]) {
					case('A'):
						hedgCounter++;
						break;
					case('W'):
						hedgCounter++;
						break;
					case('S'):
						hedgCounter++;
						break;
					case('D'):
						hedgCounter++;
						break;
					case('%'):
						pilCounter++;
						break;
					case('P'):
						telCounter++;
						break;
					case('T'):
						telCounter++;
						break;
					case('*'):
						foodCounter++;
						break;
				}
		
					
				
	}
}
