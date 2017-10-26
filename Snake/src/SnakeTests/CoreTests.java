package SnakeTests;
import java.awt.Point;

import org.junit.*;

import SnakeCore.FoodFactory;
import SnakeCore.GameState;
import SnakeCore.HedgFactory;
import SnakeCore.IObject;
import SnakeCore.PillowFactory;
import SnakeCore.StateParser;
import SnakeCore.TeleportFactory;

public class CoreTests extends Assert {
	@Test
	public void Test1_1Dying() throws Exception{
		GameState game = StateParser.makeGame("tests\\T1.txt");
		if (game.makeTick()) {
			throw new Exception();
		};
	}
	
	@Test
	public void Test1_2Moving() throws Exception{
		GameState game = StateParser.makeGame("tests\\T1.txt");
		game.TurnSnake(4);
		if (!game.makeTick() && game.getMap()[2][0]=='@') {
			throw new Exception();
		};
	}
	
	
	@Test
	public void Test2SelfKilling() throws Exception{
		GameState game = StateParser.makeGame("tests\\T2.txt");
		if (game.makeTick()) {
			throw new Exception();
		};
	}
	
	@Test
	public void Test3Tesseract() throws Exception{
		GameState game = StateParser.makeGame("tests\\T3.txt");
		
		if (!(game.makeTick() && game.getMap()[2][0]=='@')){
			throw new Exception();
		};
	}
	
	@Test
	public void Test4Growing() throws Exception{
		GameState game = StateParser.makeGame("tests\\T4.txt");
		IObject f= new FoodFactory().produce(game,new Point[]{new Point(0,1)});
		game.addToObjs(f);
		if (!(game.makeTick() && game.getMap()[0][0]=='@')){
			throw new Exception();
		};
	}
	
	@Test
	public void Test5_1Teleport() throws Exception{
		GameState game = StateParser.makeGame("tests\\T5.txt");
		IObject t= (new TeleportFactory()).produce(game,new Point[]{new Point(0,1),new Point(1,0)});
		game.addToObjs(t);
		if (!(game.makeTick() && game.getHead().x==1 && game.getHead().y==1)){
			throw new Exception();
		};
	}
	
	@Test
	public void Test5_2TeleportTesseract() throws Exception{
		GameState game = StateParser.makeGame("tests\\T5.txt");
		IObject t= (new TeleportFactory()).produce(game,new Point[]{new Point(0,1),new Point(1,0)});
		game.addToObjs(t);
		game.TurnSnake(4);
		if (!(game.makeTick() && game.getHead().x==1 && game.getHead().y==1)){
			throw new Exception();
		};
	}
	

	@Test
	public void Test6Hedg() throws Exception{
		for(int i=0;i<10;i++) {
			GameState game = StateParser.makeGame("tests\\T6.txt");
			IObject t= (new HedgFactory()).produce(game,new Point[]{new Point(0,1),new Point(1,0)});
			game.addToObjs(t);
			if (!(t.getIcon()=='W' == (game.makeTick() && game.getMap()[0][0]=='@'))){
				throw new Exception();
			};
		}
	}
	
	@Test
	public void Test7Pillow() throws Exception{
		GameState game = StateParser.makeGame("tests\\T7.txt");
		IObject t= (new PillowFactory()).produce(game,new Point[]{new Point(0,1)});
		game.addToObjs(t);
		game.makeTick();
		if (!(game.makeTick() && game.getMap()[0][0]=='@')){
			throw new Exception();
		};
	}
	
	@Test
	public void Test8SoftWall() throws Exception{
		GameState game = StateParser.makeGame("tests\\T8.txt");
		game.makeTick();
		if (!(game.makeTick() && game.getMap()[0][0]=='@')){
			throw new Exception();
		};
		game.TurnSnake(6);
		if (!(game.makeTick() && game.getMap()[0][1]=='@')){
			throw new Exception();
		};
	}
}
