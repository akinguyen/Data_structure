package byog.Core;

import byog.TileEngine.Position;
import byog.TileEngine.TETile;
import java.util.ArrayList;

class SaveData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    TETile[][] saveWorld;
    GameItems gameItems;
    MapGenerator map;
    int keyCount;
    int keyTotal;
    int seed;
    MapType mapType;
    int stage;
    int live;
    boolean lightState;
    ArrayList<Position> obstacle;
    SaveData(TETile[][] saveWorld, MapGenerator map, GameItems gameItems, int keyCount,
             int keyTotal, int seed, int stage, int live, boolean lightState,
             ArrayList<Position> obstacle, MapType mapType){
        this.saveWorld = saveWorld;
        this.gameItems = gameItems;
        this.map = map;
        this.keyCount = keyCount;
        this.keyTotal = keyTotal;
        this.seed = seed;
        this.stage = stage;
        this.mapType = mapType;
        this.live = live;
        this.lightState = lightState;
        this.obstacle = obstacle;

    }
}
