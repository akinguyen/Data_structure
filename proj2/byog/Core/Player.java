package byog.Core;

import byog.TileEngine.Position;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player implements java.io.Serializable {
    private Position p;

    Player(Position p){
        this.p = p;
    }

    int getPosX(){
        return p.getX();
    }

    int getPosY(){
        return p.getY();
    }

    void setPosX(int x1){
        p.setX(x1);
    }

    void setPosY(int y1){
        p.setY(y1);
    }

    void changePosX(int x) {p.changeX(x);}

    void changePosY(int y) {p.changeY(y);}

    boolean isCollide(char userInput, TETile[][] world, TETile object){
        int x = getPosX();
        int y = getPosY();
        if (userInput == 'w'){
            y += 1;
            if (object.equals(Tileset.ENEMIES)){
                y+= 1;
            }
        }
        else if (userInput == 'a'){
            x -= 1;
            if (object.equals(Tileset.ENEMIES)){
                x-= 1;
            }

        }
        else if (userInput == 's'){
            y -= 1;
            if (object.equals(Tileset.ENEMIES)){
                y -= 1;
            }
        }
        else if (userInput == 'd'){
            x += 1;
            if (object.equals(Tileset.ENEMIES)){
                x += 1;
            }
        }
        return world[x][y].equals(object);
    }
}
