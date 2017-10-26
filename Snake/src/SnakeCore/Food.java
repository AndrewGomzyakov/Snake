package SnakeCore;

import java.awt.Point;

public final class Food implements IObject {

	private Point loc;
	private GameState game;
	
	public Food(GameState game,Point[] p) {
		loc=p[0];
		this.game=game;
	}
	
	public Food(GameState game) {
		this.game=game;
		loc=game.getRndFreePoint();
	}
	
	/*private void setFood() {
		loc=new Point(rnd.nextInt(game.width), rnd.nextInt(game.height));
		while (game.getCell(loc)!='.')
			loc=new Point(rnd.nextInt(game.width), rnd.nextInt(game.height));
	}*/
	
	@Override
	public Point[] getLocs() {
		return new Point[] {loc};
	}

	@Override
	public char getIcon() {
		return '*';
	}

	@Override
	public void tick() {}

	@Override
	public boolean interact(Point p) {
		game.feedSnake(1);
		loc=game.getRndFreePoint();
		return false;
	}

}
