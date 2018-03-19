package byog.lab5;
//import org.junit.Test;
//import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.Position;
import java.util.Random;


/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final long SEED = 2873123;
    private static final Random GEN = new Random(SEED);

    /** calibrate the width for the HexWorld */
    private static int calibrateWidth(int size){ return 11*size-4; }

    /** calibrate the height for the HexWorld */
    private static int calibrateHeight(int size){ return size*10+2; }

    /** Find the first place to put your first hexagon */
    private static Position startPosition(int size){ return new Position(size,8*size);}

    /** Draw the HexWorld */
    public static void DrawWorld(int size){
        TERenderer ter = new TERenderer();

        int WIDTH = calibrateWidth(size);
        int HEIGHT = calibrateHeight(size);

        ter.initialize(WIDTH,HEIGHT);

        TETile[][] world = worldBackGround(WIDTH,HEIGHT);
        drawColumn(randomTile(),ColumnPosition(size,0),world,size,3);
        drawColumn(randomTile(),ColumnPosition(size,1),world,size,4);
        drawColumn(randomTile(),ColumnPosition(size,2),world,size,5);
        drawColumn(randomTile(),ColumnPosition(size,3),world,size,4);
        drawColumn(randomTile(),ColumnPosition(size,4),world,size,3);
        ter.renderFrame(world);
    }

    /** Generate random Tiles for the HexWorld*/
    private static TETile randomTile(){
        int titleNum = GEN.nextInt(4);
        switch (titleNum){
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            default: return Tileset.SAND;
        }
    }

    /** Draw a BackGround for the HexWorld ( Tileset.NOTHING by default ) */
    private static TETile[][] worldBackGround(int width, int height){
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x ++){
            for (int y = 0; y < height; y++){
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    /** Add single hexagon to the HexWorld based on the given position and type */
    private static void addHex(TETile type,Position addPostion,int size, TETile[][] hexWorld){
        int numRow = size*2;
        int halfNumRow = numRow/2+1;
        while (numRow > 0){
            drawRow(type,addPostion,size, hexWorld);
            moveDown(addPostion,numRow,halfNumRow);
            size = changeSize(size,numRow,halfNumRow);
            numRow--;
        }

    }

    /** Draw a sub-row which will create a single hexagon */
    private static void drawRow(TETile type,Position p,int size, TETile[][] world){
        for (int i = p.getX(); i < size+p.getX(); i++){
            world[i][p.getY()] = type;
        }
    }

    /** Move down to the new row when the previous row was finished */
    private static void moveDown(Position p,int numRow, int halfNumRow){
        p.decreaseY();
        if (numRow > halfNumRow){
            p.decreaseX();
        }
        else if (numRow < halfNumRow){
            p.incrementX();
        }
    }

    /** Change the size of each row in order to create a Hexagon shape properly */
    private static int changeSize(int size,int numRow, int halfNumRow){
        if (numRow > halfNumRow){
            size += 2;
        }
        else if (numRow < halfNumRow){
            size -= 2;
        }
        return size;
    }

    /** Draw a Column of Hexagons on the World */
    private static void drawColumn(TETile type,Position p, TETile[][] world,int size, int num_hex){
        for (int i = 0; i < num_hex; i++){
            addHex(type,p,size,world);
            p.decreaseX();
            type = randomTile();
        }
    }

    /** Find the position of the Hexagon Column in order to draw to the world */
    private static Position ColumnPosition(int size, int column){
        Position p = startPosition(size);
        switch(column){
            case 1:
                p.setX(2*size-1);
                p.setY(size);
                break;
            case 2:
                p.setX(4*size-2);
                p.setY(2*size);
                break;
            case 3:
                p.setX(6*size-3);
                p.setY(size);
                break;

            case 4:
                p.setX(8*size-4);
                break;
        }
        return p;

    }

    public static void main(String[] args){
        DrawWorld(4);
    }

}
