package byog.TileEngine;

public class Position implements java.io.Serializable {
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

    public void changeX(int p) { this.x = p;}

    public void changeY(int p) {this.y = p;}

    public static Position calculateMidPoint(Position corner1, Position corner2){
        int x1; int y1;
        if (corner1.getY() == corner2.getY()){
            x1 = (corner2.getX() + corner1.getX())/2;
            y1 = corner1.getY();
        }
        else{
            x1 = corner1.getX();
            y1 = (corner2.getY() + corner1.getY())/2;
        }
        return new Position(x1,y1);
    }

    public boolean equals(Object o){
        if (o == null){return false;}
        if (o == this){return true;}
        if (!(o instanceof Position)){return false;}
        Position a = (Position) o;
        return this.x == a.x && this.y == a.y;
    }
}
