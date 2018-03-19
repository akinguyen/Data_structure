package byog.TileEngine;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Position copy(Position p){
        return new Position(p.getX(),p.getY());
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x1){
        x += x1;
    }

    public void setY(int y1){
        y += y1;
    }

    public void incrementX(){
        x += 1;
    }

    public void incrementY(){
        y += 1;
    }

    public void decreaseX(){
        x -= 1;
    }

    public void decreaseY(){
        y -= 1;
    }
}
