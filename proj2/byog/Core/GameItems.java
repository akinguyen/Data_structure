package byog.Core;
import byog.TileEngine.TETile;

import java.io.Serializable;

public class GameItems implements Serializable{
    Player player;
    Player door;
    Player enemies;
    boolean isLive = true;
    TETile playerImage;
}
