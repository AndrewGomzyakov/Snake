package SnakeCore;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
//import java.util.Map;

public class GameState{
    protected Snake snake;
    private char[][] maze;
    private char[][] map;
    private Random rnd;
    public boolean isAlive;
    private ArrayList<IObject> objs=new ArrayList<IObject>();
    public int height;
    public int width;
    public static HashMap<String,IObjFactory> Dic = new HashMap<String,IObjFactory>();
    static {
    	Dic.put("Food",new FoodFactory());
    	Dic.put("Hedg",new HedgFactory());
    	Dic.put("Teleport",new TeleportFactory());
    	Dic.put("Pillow",new PillowFactory());
    }
    /*
    public GameState(String key){
    	SetMap(key);
    	isAlive=true;
    	//objs.add(e)
    	snake=new Snake(new Point[] {new Point(4,3),new Point(5,3)},0);//BBAADD!
    	
    }*/
    public List<Point> getSnake() {
    	return snake.body;
    }
    public GameState(char[][] maze,Point[] snakePos,Direction snakeDir,ArrayList<Tuple<String, Integer>> objsCreators) {
    	isAlive=true;
    	this.maze=maze;
    	map=new char[maze.length][];
    	rnd=new Random();
    	height=maze.length;
    	width=maze[0].length;//bad
    	snake=new Snake(snakePos, snakeDir.getDirN());
    	for (Tuple<String, Integer> tup : objsCreators)
    		for(int i=0;i<tup.y;i++) 
    			getObjsArr().add(Dic.get(tup.x).produce(this));
    }
    
    
    public char[][] getMap(){
    	for(int i=0;i<map.length;i++)
    		map[i]=maze[i].clone();
    	for(int i=0;i<snake.body.size();i++) {
    		Point p=snake.body.get(i);
    		map[p.y][p.x]='@';
    	}
    	
    	for(int i=0;i<getObjsArr().size();i++) {
    		Point[] ps=getObjsArr().get(i).getLocs(); 
    		char ico= getObjsArr().get(i).getIcon();
    		for(int j=0;j<ps.length;j++) {
    			Point p=ps[j];//�������� �� �����������
    			map[p.y][p.x]=ico;
    		}
    	}
    	return map;
    }

    
    public boolean makeTick(){
    	if(!isAlive) return false;
    	for(int i=0;i<getObjsArr().size();i++) {
    		getObjsArr().get(i).tick();
    	}
    	
    	if(!snake.isMoving) return true;
    	Point next=getBoundedCord(snake.getNext());
    	
    	IObject col=objsCollision(next);
    	boolean b=true;
    	for(Point nextTmp=next;b||nextTmp!=next;) {
    		nextTmp=next;
	    	if(col != null && col.interact(next)) return die();
	    	b=false;
    	}
    	next=getBoundedCord(snake.getNext());
    	if (maze[next.y][next.x]=='+' || (maze[next.y][next.x]=='.' && snake.makeStep())) return true;
    	else return die();
    }
    
    public boolean die() {
    	isAlive=false;
    	return false; //cuz datz kool
    }
    
    protected Point getBoundedCord(Point p) {
		if(!(p.x>=0 && p.x<width && p.y>=0 && p.y<height)) {
			p=new Point((p.x+width) % width,(p.y+height) % height);
			snake.setNext(p);
		}
    	return p;
    }
    
    private IObject objsCollision(Point p) {
    	for(IObject obj : getObjsArr())
    		for(Point el: obj.getLocs())
    			if (p.x==el.x && p.y==el.y)
    				return obj;
    	return null;
	}
    
    public void feedSnake(int val) {
    	snake.grow(val); // �������!
    }

    
    public boolean TurnSnake(int dir){
    	return snake.turn(new Direction(dir));
    }
    
    public boolean TurnSnake(Point dir){
    	return snake.turn(new Direction(dir));
    }
    
    protected char getCell(Point p) {
    	if (maze[p.y][p.x]=='#') return'#';
    	if (maze[p.y][p.x]=='+') return'+';
    	for(Point part : snake.body)
    		if(part.x==p.x && part.y==p.y)
    			return'@';
    	IObject obj=objsCollision(p);  
    	if (obj!=null) return obj.getIcon();
    	return '.';
    }
    
    protected Point getRndFreePoint() {
		Point loc=new Point(rnd.nextInt(width), rnd.nextInt(height));
		while (getCell(loc)!='.')
			loc=new Point(rnd.nextInt(width), rnd.nextInt(height));
		return loc;
    }
    
    protected void teleportHead(Point newHead) {
    	snake.setNext(newHead);
    }
    
    public Point getHead() {
    	return snake.getHead();
    }
    
    public Direction getSnakeDir() {
    	return snake.dir;
    }
    
    public int getLenght() {
    	return snake.body.size();
    }

	public void addToObjs(IObject obj) {
		objs.add(obj);
	}

	protected List<IObject> getObjsArr() {
		return objs;
	}

}



