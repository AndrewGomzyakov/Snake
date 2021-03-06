package SnakeCore;

import java.awt.Point;

public class Direction{

	private int dir;
	
	public Direction(int d) {
		setDir(d);
	}
	
	public Direction(Point d) {
		setDir(whatDir(d)); 
	}
	
	private int whatDir(Point p) {
		if(p.equals(new Point(1,0))) return 6;
		else if(p.equals(new Point(-1,0))) return 4;
		else if(p.equals(new Point(0,1))) return 2;
		else if(p.equals(new Point(0,-1))) return 8;
		return -1;//Need exception
	}
	
	public Point getDir(){
		switch (getDirN()) {
		case 6: return new Point(1,0);
		case 4: return new Point(-1,0);
		case 2: return new Point(0,1);
		case 8: return new Point(0,-1);
		default: return new Point(0,1/0);//Need exception
		}
	}

    public boolean IsOpposit(Direction newDir){
        return (this.getDirN()==8 && newDir.getDirN()==2) ||
                (this.getDirN()==2 && newDir.getDirN()==8) ||
                (this.getDirN()==4 && newDir.getDirN()==6) ||
                (this.getDirN()==6 && newDir.getDirN()==4);
    }

	public int getDirN() {
		return dir;
	}
	
	public void setDir(int dir) {
		if(dir==2 || dir==4 || dir==6 || dir==8)		
			this.dir = dir;						//hte java	
		else this.dir = 1/0;
	}
}
