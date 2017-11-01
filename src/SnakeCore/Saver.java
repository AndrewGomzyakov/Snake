package SnakeCore;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.awt.Point;
import java.io.*;
import java.util.List;

public class Saver {

	@SuppressWarnings("unchecked")
	public static void save2(GameState game)
	{
		JSONObject gameObj = new JSONObject();
		JSONArray arrMap = new JSONArray();
		
		char[][] arr = game.getMap();
		for (int i = 0; i < arr.length; i++)
		{
			JSONArray mapString = new JSONArray();
			for (int j = 0 ; j < arr[i].length; j++)
			{
				mapString.add(arr[i][j]);
			}
			arrMap.add(mapString);
		}
		gameObj.put("map", arrMap);
		
	}
	
	public static void save(String path, GameState game)
	{
		StringBuffer rezultStr = new StringBuffer();
		int telCounter = 0;
		int hedgCounter = 0;
		int foodCounter  = 0;
		int pilCounter = 0;
		char[][] a = game.getMap();
		rezultStr.append(Integer.toString(a[1].length - 4) + ' ' + Integer.toString(a.length) + '\n');
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
			{
				switch(a[i][j]) {	
					case('A'):
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('W'):
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('S'):
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('D'):
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('%'):
						rezultStr.append('.');
						pilCounter++;
						break;
					case('P'):
						rezultStr.append('.');
						telCounter++;
						break;
					case('T'):
						rezultStr.append('.');
						telCounter++;
						break;
					case('*'):
						rezultStr.append('.');
						foodCounter++;
						break;
					case('.'):
						rezultStr.append('.');
						break;
					case('#'):
						rezultStr.append('#');
						break;
					case('+'):
						rezultStr.append('+');
						break;
					case('@'):
						rezultStr.append('.');
						break;
				}
			}
			rezultStr.append('\n');
		}
		List<Point> snake = game.getSnake();
		for (int i= 0; i < snake.size(); i++)
		{
			int x = (int)snake.get(i).getX();
			int y = (int)snake.get(i).getY();
			rezultStr.append(Integer.toString(x) + ' '  + Integer.toString(y) + ' ');
		}
		rezultStr.append('\n');
		rezultStr.append(Integer.toString(game.getSnakeDir().getDirN()) + '\n');
		if (pilCounter != 0) 
		{
			rezultStr.append("Pillow " + Integer.toString(pilCounter) + '\n');
		}
		if (foodCounter != 0) 
		{
			rezultStr.append("Food " + Integer.toString(foodCounter) + '\n');
		}
		if (telCounter != 0)
		{
			rezultStr.append("Teleport " + Integer.toString(telCounter) + '\n');
		}
		if (hedgCounter != 0)
		{
			rezultStr.append("Hedg " + Integer.toString(hedgCounter) + '\n');
		}
		try {
			FileWriter writer = new FileWriter(path, false);
			writer.write(rezultStr.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
