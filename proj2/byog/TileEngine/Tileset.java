package byog.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    private final static String filepath = "C:\\Users\\Nqk\\61B\\Data_structure\\proj2\\byog\\Core\\";
    public static TETile HORSE = new TETile('♞', Color.white, Color.black, "horse");
    public static TETile ROOK = new TETile('♜', Color.white, Color.black, "rook");
    public static TETile QUEEN = new TETile('♚', Color.white, Color.black, "rook");
    public static TETile YING_YANG= new TETile('☯', Color.white, Color.black, "ying yang");
    public static TETile COMMUNIST= new TETile('☭', Color.white, Color.black, "ying yang");

    public static TETile KING = new TETile('♛', Color.white, Color.black, "king");

    public static TETile PLAYER = new TETile('@', Color.white, Color.black, "player");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue.brighter(), Color.blue.darker().darker(),"ocean");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('■', Color.yellow.brighter(), Color.black,
            "locked door");
    public static final TETile DESERT = new TETile('▓', new Color(214,83,3), Color.orange.darker(), "desert");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('·', Color.black,Color.orange, "sand");
    public static final TETile DESERT_SAND = new TETile('·', new Color(192,86,5),Color.orange.darker(), "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");
    public static final TETile ROCK = new TETile('▦', Color.orange.darker().darker(),Color.black,"rock");
    public static final TETile DESERT_ROCK = new TETile('▦', new Color(141,65,5),Color.black,"rock");
    public static final TETile CLOUD = new TETile('▦', Color.white ,Color.black,"cloud border");
    public static final TETile SKY = new TETile(' ', Color.blue.darker(), Color.blue.darker().darker(), "sky");
    public static final TETile CLOUD_FLOOR = new TETile('·',Color.blue.darker(), Color.white.darker(), "cloud");
    public static final TETile FOREST = new TETile('♠', Color.green.darker(), new Color(3,77,34), "forest");
    public static final TETile FOREST_ROCK = new TETile('▦', new Color(141,65,6),Color.black,"wood");
    public static final TETile FOREST_SAND = new TETile('·',Color.green.darker().darker().darker(), new Color(192,86,5), "sand");
    public static final TETile APO_GROUND = new TETile(' ', Color.white, Color.white, "scary ground", filepath + "apo_floor.png");
    public static final TETile APO_FLOOR = new TETile('·', new Color(29,29,29), new Color(138,131,109),"floor");
    public static final TETile APO_WALL = new TETile('▦', new Color(85,62,2), Color.black, "space wall");
    public static TETile SHOTER = new TETile(' ', Color.white, Color.white, "shoter",filepath+ "shoter.png");
    public static final TETile ENEMIES = new TETile(' ', Color.white, Color.white, "enemy", filepath + "enemy.png");
    public static TETile SNOWMAN = new TETile('⛇', Color.RED, Color.yellow, "snowman");
    public static final TETile KEY = new TETile('⚿', Color.yellow, Color.black, "key");
    public static TETile TELEPORT = new TETile('☢', Color.white, Color.black, "teleport");
}


