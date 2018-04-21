package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

class MapType implements Serializable{
    private TETile floor;
    private TETile wall;
    private TETile backGround;

    MapType(TETile floor, TETile wall, TETile backGround){
        this.floor = floor;
        this.wall = wall;
        this.backGround = backGround;
    }
    TETile getFloor(){return floor;}
    TETile getWall(){return wall;}
    TETile getBackGround(){return backGround;}

    boolean equals(MapType a){
        return floor.equals(a.getFloor()) && backGround.equals(a.getBackGround())
                && wall.equals(a.getWall());
    }
}
