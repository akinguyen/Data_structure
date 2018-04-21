package byog.Core;
import byog.TileEngine.Position;
import java.util.*;

public class Room {
    private int width;
    private int height;
    private Position[] corners;
    private HashMap<String,Position> midPoints;

    Room( int width, int height, Position[] corners, HashMap<String,Position> midPoints){
        this.width = width;
        this.height = height;
        this.corners = corners;
        this.midPoints = midPoints;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
    public Position[] getCorners(){ return corners;}
    public HashMap<String,Position> getMidPoints() {return midPoints;}

    public boolean intersect(Room room){
        return !(room.corners[2].getX() > corners[3].getX() || room.corners[2].getY() > corners[0].getY()
                || corners[2].getX() > room.corners[3].getX() || corners[2].getY() > room.corners[0].getY());
    }
}
