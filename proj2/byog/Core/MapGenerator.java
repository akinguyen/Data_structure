package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Position;
import javafx.geometry.Pos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MapGenerator implements Serializable {
    private TETile floor;
    private TETile backGround;
    private TETile wall;

    private int WIDTH;
    private int HEIGHT;
    private Position MapCenter;
    Random rand;
    ArrayList<Position> floorPositions = new ArrayList<>();
    ArrayList<Position> wallPositions = new ArrayList<>();
    ArrayList<Position> obstaclePositions = new ArrayList<>();

    MapGenerator(int WIDTH, int HEIGHT, Position MapCenter, MapType mapType){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.MapCenter = MapCenter;
        this.floor = mapType.getFloor();
        this.backGround = mapType.getBackGround();
        this.wall = mapType.getWall();
    }

    private TETile[][] nothingBackGround(int width, int height){
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x ++){
            for (int y = 0; y < height; y++){
                world[x][y] = backGround;
            }
        }
        return world;
    }

    private int randNumGen(int lowerBound, int upperBound){
        return rand.nextInt(upperBound - lowerBound) + lowerBound;
    }

    private HashMap<String, Position> fillMidCorners(Position[] corners){
        HashMap<String, Position> midPoints = new HashMap<>();
        midPoints.put("North", Position.calculateMidPoint(corners[0],corners[1]));
        midPoints.put("South", Position.calculateMidPoint(corners[2],corners[3]));
        midPoints.put("East", Position.calculateMidPoint(corners[1],corners[3]));
        midPoints.put("West", Position.calculateMidPoint(corners[0],corners[2]));
        return midPoints;
    }

    private Room createRandomRoom(int width, int height, Position p){
        int x1 = p.getX();
        int y1 = p.getY();
        Position corner1 = new Position(x1, y1 + height);
        Position corner2 = new Position(x1 + width, y1 + height);
        Position corner3 = new Position(x1, y1);
        Position corner4 = new Position(x1 + width, y1);

        Position[] corners = {corner1,corner2,corner3, corner4};
        HashMap<String, Position> midCorners = fillMidCorners(corners);

        return new Room(width,height,corners, midCorners);
    }

    private void connectWall(TETile[][] world, Position p1, Position p2){
        if (p1.getY() == p2.getY()) {
            for (int i = p1.getX(); i <= p2.getX(); i++) {
                world[i][p1.getY()] = wall;
                obstaclePositions.add(new Position(i, p1.getY()));
                if (i > p1.getX() && i < p2.getX() ) {
                    wallPositions.add(new Position(i, p1.getY()));
                }
            }
        }
        else {
            for (int j = p2.getY(); j <= p1.getY(); j++) {
                world[p1.getX()][j] = wall;
                obstaclePositions.add(new Position(p1.getX(), j));
                if (j > p2.getY() && j < p1.getY()) {
                    wallPositions.add(new Position(p1.getX(), j));
                }
            }
        }
    }

    private Room createFloorArea(Room room){
        Position[] roomCorners = room.getCorners();
        int width = room.getWidth()-2;
        int height = room.getHeight()-2;
        int x1 = roomCorners[2].getX()+1;
        int y1 = roomCorners[2].getY()+1;
        Position corner1 = new Position(x1, y1 + height);
        Position corner2 = new Position(x1 + width, y1 + height);
        Position corner3 = new Position(x1,y1);
        Position corner4 = new Position(x1 + width, y1);
        Position[] floorCorners = {corner1,corner2,corner3,corner4};

        return  new Room(width,height, floorCorners, null);
    }

    private void fillFloor(TETile[][] world, Room room){
        Room floorArea = createFloorArea(room);
        Position[] floorCorners = floorArea.getCorners();
        for (int i = floorCorners[2].getX(); i <= floorCorners[3].getX(); i++){
            for (int j = floorCorners[2].getY(); j <= floorCorners[0].getY(); j++){
                world[i][j] = floor;
                floorPositions.add(new Position(i,j));
            }
        }
    }

    private void drawRoom(TETile[][] world, Room room ){
        Position[] corners = room.getCorners();
        connectWall(world,corners[0],corners[1]);
        connectWall(world,corners[1],corners[3]);
        connectWall(world,corners[2],corners[3]);
        connectWall(world,corners[0],corners[2]);
        fillFloor(world,room);
    }

    private boolean placeable(Room room){
        return room.getCorners()[0].getY() < HEIGHT && room.getCorners()[2].getY() > 0
                && room.getCorners()[2].getX() > 0 && room.getCorners()[3].getX() < WIDTH;
    }

    private boolean placeRandRoom(TETile[][] world, ArrayList<Room> roomList, int w, int h, Position p){
        Room room = createRandomRoom(w,h,p);
        if (placeable(room)){
            if (!roomList.isEmpty()){
                for (Room prevRoom: roomList){
                    if (room.intersect(prevRoom)){
                        return false;
                    }
                }
                drawRoom(world,room);
                roomList.add(room);
                return true;
            }
            else{
                drawRoom(world,room);
                roomList.add(room);
                return true;
            }
        }
        return false;
    }

    private String[] getKeyFromMap(Map<String, Position> midPointsMap){
        return midPointsMap.keySet().toArray(new String[midPointsMap.keySet().size()]);
    }

    private Room chooseRandRoom( ArrayList<Room> roomList){
        return roomList.get(rand.nextInt(roomList.size()));
    }

    private String randDir(Room randRoom){
        Map<String,Position> midPointsMap = randRoom.getMidPoints();
        String[] keyDirs = getKeyFromMap(midPointsMap);
        if (keyDirs.length <= 0) {
            return "No more Points";
        }
        return keyDirs[rand.nextInt(keyDirs.length)];
    }

    private Position updateAddPosition(String dir, int w, int h, Position p1){
        Position p = Position.copy(p1);
        switch (dir){
            case "South":
                p.decreaseX();
                p.setY(-1*h);
                p.decreaseY();
                break;
            case "North":
                p.decreaseX();
                p.incrementY();
                break;
            case "West":
                p.setX(-1*w);
                p.decreaseX();
                p.decreaseY();
                break;
            default:
                p.decreaseY();
                p.incrementX();
                break;
        }
        return p;
    }

    private Position updateClearPosition(String dir, Position b){
        Position p = Position.copy(b);
        switch (dir){
            case "South":
                p.setY(-1);
                break;
            case "North":
                p.setY(1);
                break;
            case "West":
                p.setX(-1);
                break;
            default:
                p.setX(1);
                break;
        }
        return p;
    }


    private void clearWall(TETile[][] finalWorldFrame, Position b){
        finalWorldFrame[b.getX()][b.getY()] = floor;
        wallPositions.remove(b);
        obstaclePositions.remove(b);
        floorPositions.add(b);
    }

    private void connectRoom(TETile[][] finalWorldFrame, String randDir, Position b){
        clearWall(finalWorldFrame, b);
        clearWall(finalWorldFrame,updateClearPosition(randDir,b));
    }

    /**
     * Source: Mike Anderson
     */
     TETile[][] generateWorld(int seed, int iteration){
        TETile[][] finalWorldFrame = nothingBackGround(WIDTH,HEIGHT);
        rand = new Random(seed);
        ArrayList<Room> roomList = new ArrayList<>();

        placeRandRoom(finalWorldFrame,roomList,randNumGen(3,10),
                randNumGen(3,10), MapCenter);

        Room randRoom = roomList.get(0);

        for (int i = 0 ; i < iteration; i++) {
            String randDir = randDir(randRoom);
            if (!randDir.equals("No more Points")) {
                Position clearWall = randRoom.getMidPoints().get(randDir);
                addFeature(finalWorldFrame,roomList, randDir,randRoom,clearWall);
            }
            randRoom = chooseRandRoom(roomList);
        }
        return finalWorldFrame;
    }

    private void addFeature(TETile[][] finalWorldFrame, ArrayList<Room> roomList, String randDir, Room randRoom, Position door ){
        int w = randNumGen(2, 10);
        int h = randNumGen(2, 10);
        if (placeRandRoom(finalWorldFrame, roomList, w, h, updateAddPosition(randDir, w, h, door))) {
            connectRoom(finalWorldFrame, randDir, door);
            randRoom.getMidPoints().remove(randDir);
        }
    }

    private void addFeatures(TETile[][] finalWorldFrame, ArrayList<Room> roomList, String randDir, Room randRoom, Position door ){
        int w = randNumGen(2, 10);
        int h = randNumGen(2, 10);
        int i = rand.nextInt(3);
        switch (i) {
            case 0:
            if (placeRandRoom(finalWorldFrame, roomList, w, h, updateAddPosition(randDir, w, h, door))) {
                connectRoom(finalWorldFrame, randDir, door);
                randRoom.getMidPoints().remove(randDir);
            }
            break;
            case 1:
                if (placeRandRoom(finalWorldFrame, roomList, 2, h, updateAddPosition(randDir, 2, h, door))) {
                    connectRoom(finalWorldFrame, randDir, door);
                    randRoom.getMidPoints().remove(randDir);
                }
                break;
            default:
                if (placeRandRoom(finalWorldFrame, roomList, w, 2, updateAddPosition(randDir, w, 2, door))) {
                    connectRoom(finalWorldFrame, randDir, door);
                    randRoom.getMidPoints().remove(randDir);
                }
                break;
        }
    }

    static ArrayList<Position> copyOf(ArrayList<Position> a){
         ArrayList<Position> copy = new ArrayList<>();
         for (Position p: a){
             copy.add(Position.copy(p));
         }
         return copy;
    }

    TETile getWall(){
         return wall;
    }

    TETile getFloor(){
         return floor;
    }

}
