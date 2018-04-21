package byog.Core;

import byog.TileEngine.Position;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.ArrayList;

public class MenuGenerator {
    private TERenderer ter = new TERenderer();
    private int width;
    private int height;

    private TETile[][] world;
    private MapGenerator map;
    private GameItems items = new GameItems();
    private int keyCount = 0;
    private int keyTotal = 5;
    private int live = 3;
    private int seed;
    private int stage = 1;
    private boolean lightState;
    private MapType mapType;
    private ArrayList<Position> obstacles;

    private static final String filepath = "C:\\Users\\Nqk\\61B\\Data_structure\\proj2\\byog\\Core\\";
    private static final MapType RegularTheme = new MapType(Tileset.FLOOR, Tileset.WALL, Tileset.NOTHING);
    private static final MapType OceanTheme = new MapType(Tileset.SAND, Tileset.ROCK, Tileset.WATER);
    private static final MapType DesertTheme = new MapType(Tileset.DESERT_SAND, Tileset.DESERT_ROCK, Tileset.DESERT);
    private static final MapType SkyTheme = new MapType(Tileset.CLOUD_FLOOR, Tileset.CLOUD, Tileset.SKY);
    private static final MapType ForestTheme = new MapType(Tileset.FOREST_SAND, Tileset.FOREST_ROCK, Tileset.FOREST);
    private static final MapType ApocalypseTheme = new MapType(Tileset.APO_FLOOR,Tileset.APO_WALL, Tileset.APO_GROUND);
    private static final String[] mapTypeName = {"R","A","D","F","O","S"};

    MenuGenerator(int width, int height){
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    private void writeText(String s, int fontSize, Color c, Position p){
        Font font = new Font("Monaco", Font.BOLD, fontSize);
        StdDraw.setFont(font);
        StdDraw.setPenColor(c);
        StdDraw.text(p.getX(), p.getY(), s);
    }
    private void menuBar(){
        writeText("61B: GAME", 40, Color.white, new Position(width/2, height/2+6));
        writeText("New Game (N)", 25, Color.white, new Position(width/2, height/2));
        writeText("Load Game (L)", 25, Color.white, new Position(width/2, height/2-2));
        writeText("Quit (Q)", 25, Color.white, new Position(width/2, height/2-4));
    }
    private String enterStringSeed(){
        String seed = "";
        char userInput;
        while (true){
            if (StdDraw.hasNextKeyTyped()){
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 's' || userInput == 'S'){
                    break;
                }
                seed = seed + userInput;
                }
            }
        return seed;
    }
    private int seedMenu(){
        StdDraw.clear(Color.black);
        writeText("Enter the seed and press (S):", 30, Color.white, new Position(width/2, height/2+6));
        StdDraw.show();
        String seed = enterStringSeed();
        StdDraw.clear(Color.black);
        writeText("Enter the seed and press (S):", 30, Color.white, new Position(width/2, height/2+6));
        writeText( seed, 25, Color.white, new Position(width/2, height/2+4));
        StdDraw.show();
        StdDraw.pause(1350);
        StdDraw.clear(Color.black);
        return Integer.parseInt(seed);
    }

    private TETile playerOpt(){
        char userInput;
        TETile playerImage = null;
        boolean selected = false;
        StdDraw.clear(Color.black);
        writeText("Choose your player", 40, Color.white, new Position(width / 2, height / 2+4));
        writeText(" @ AT (A)", 25, Color.white, new Position(width / 2, height / 2 + 1));
        writeText("☭ COMMUNIST (C)", 25, Color.white, new Position(width / 2, height / 2 - 1));
        writeText("♞ HORSE (H)", 25, Color.white, new Position(width / 2, height / 2 - 3));
        writeText("♛ KING (K)", 25, Color.white, new Position(width / 2, height / 2 - 5));
        writeText("♚ QUEEN (Q)", 25, Color.white, new Position(width / 2, height / 2 -7));
        writeText("♜ ROOK (R)", 25, Color.white, new Position(width / 2, height / 2 - 9));
        writeText("☯ YING YANG (Y)", 25, Color.white, new Position(width / 2, height / 2 - 11));

        StdDraw.show();
        while (!selected){
            if (StdDraw.hasNextKeyTyped()){
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 'a' || userInput == 'A'){
                    selected = true;
                    playerImage = Tileset.PLAYER;
                }
                else if (userInput == 'c' || userInput == 'C'){
                    selected = true;
                    playerImage = Tileset.COMMUNIST ;
                }
                else if (userInput == 'h' || userInput == 'H'){
                    selected = true;
                    playerImage = Tileset.HORSE ;
                }
                else if (userInput == 'k' || userInput == 'K'){
                    selected = true;
                    playerImage = Tileset.KING ;
                }
                else if (userInput == 'q' || userInput == 'Q'){
                    selected = true;
                    playerImage = Tileset.QUEEN;
                }
                else if (userInput == 'r' || userInput == 'R'){
                    selected = true;
                    playerImage = Tileset.ROOK;
                }
                else if (userInput == 'y' || userInput == 'Y'){
                    selected = true;
                    playerImage = Tileset.YING_YANG;
                }
            }
        }
        return playerImage;
    }
    private boolean isLightState(){
        char userInput;
        boolean lightMode = false;
        boolean selected = false;
        StdDraw.clear(Color.black);
        writeText("Choose the map state", 40, Color.white, new Position(width / 2, height / 2));
        writeText("Light mode (A)", 25, Color.white, new Position(width / 2, height / 2 -3));
        writeText("Dark mode (B)", 25, Color.white, new Position(width / 2, height / 2 - 5));
        StdDraw.show();
        while (!selected){
            if (StdDraw.hasNextKeyTyped()){
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 'a' || userInput == 'A'){
                    selected = true;
                    lightMode = true;
                }
                else if (userInput == 'b' || userInput == 'B'){
                    selected = true;
                }
            }
        }
        return lightMode;
    }
    private MapType getMapType(String nameType){
        if (nameType.equals("R")){
            return RegularTheme;
        }
        else if (nameType.equals("A")){
            return ApocalypseTheme;
        }
        else if (nameType.equals("D")){
            return DesertTheme;
        }
        else if (nameType.equals("F")){
            return ForestTheme;
        }
        else if (nameType.equals("O")){
            return OceanTheme;
        }
        return SkyTheme;
    }
    private void drawOpt(Position center, String mapName, String image, ArrayList<Position> OptList){
        writeText(mapName, 15, Color.white, new Position(center.getX(), center.getY()+5));
        StdDraw.picture(center.getX(), center.getY(), filepath + image);
        OptList.add(center);
    }
    private String userClick(int width, int height, String[] name, ArrayList<Position> Opts){
        if (StdDraw.isMousePressed()) {
            while (StdDraw.isMousePressed()) ;
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            for (int i = 0; i < name.length; i++) {
                if (x < Opts.get(i).getX() + width/2 && x > Opts.get(i).getX() - width/2
                        && y < Opts.get(i).getY() + height/2 && y > Opts.get(i).getY() - height/2) {
                    return name[i];
                }
            }
        }
        return null;
    }
    private void drawAllMapOpt(ArrayList<Position> mapOptList){
        drawOpt(new Position(width / 2 - 11, height / 2 + 10),
                "Regular map", "regular_theme.png", mapOptList);
        drawOpt(new Position(width / 2 + 11, height / 2 + 10),
                "Apocalypse map", "apo_theme.png",mapOptList);
        drawOpt(new Position(width / 2 - 11, height / 2 - 2),
                "Desert map", "desert_theme.png",mapOptList);
        drawOpt(new Position(width / 2 + 11, height / 2 - 2),
                "Forest map","forest_theme.png",mapOptList);
        drawOpt(new Position(width / 2 - 11, height / 2 - 14),
                "Ocean map","ocean_theme.png",mapOptList);
        drawOpt(new Position(width / 2 + 11, height / 2 - 14),
                "Sky map","sky_theme.png",mapOptList);
    }
    private MapType mapOption(){
        boolean end = false;
        MapType chosenMapType = null;
        ArrayList<Position> mapOptList = new ArrayList<>();
        String chosenMap;
        while (!end) {
            StdDraw.clear(Color.black);
            writeText("Choose the map", 30, Color.white, new Position(width / 2, height / 2 + 17));
            drawAllMapOpt(mapOptList);
            chosenMap = userClick(16,4,mapTypeName,mapOptList);
            if (chosenMap != null){
                chosenMapType = getMapType(chosenMap);
                end = true;
            }
            StdDraw.show();
        }
        return chosenMapType;
    }
    private void enterGame(int seed){
        mapType = mapOption();
        lightState = isLightState();
        items.playerImage = playerOpt();
        nextStageBar();
        ter.initialize(80,40);
        createMap(seed, mapType);
        imageModifiedToMap(mapType);
        addItems();
        userControl();
    }
    private void imageModifiedToMap(MapType mapType){
        items.playerImage.changeTextColor(Color.black);
        Tileset.TELEPORT.changeTextColor(Color.black);
        if (mapType.equals(RegularTheme)){
            items.playerImage.changeTextColor(Color.white);
            Tileset.TELEPORT.changeTextColor(Color.white);
        }
        items.playerImage.changeBackgroundColor(mapType.getFloor().getBackgroundColor());
        Tileset.TELEPORT.changeBackgroundColor(mapType.getFloor().getBackgroundColor());

    }
    private void createMap(int seed, MapType mapType){
        map = new MapGenerator(80,37, new Position(10,15),
                mapType);
        world = map.generateWorld(seed,900);
        obstacles = map.obstaclePositions;
    }


    private void lightSpot(TETile[][] world, int width, int height){
        for (int i = 0 ; i < world.length; i++){
            for (int j = 0; j < world[0].length; j++){
                if (!(i > items.player.getPosX()- width/2 && i < items.player.getPosX()+ width/2
                        && j > items.player.getPosY()- height/2 && j < items.player.getPosY()+ height/2)){
                    world[i][j] = Tileset.NOTHING;
                }
            }
        }
    }
    private TETile[][] darkMode(TETile[][] world){
        TETile[][] copyWorld = TETile.copyOf(world);
        lightSpot(copyWorld,16,16);
        return copyWorld;

    }


    private Position randomFloorPosition(){
        return map.floorPositions.remove(map.rand.nextInt(map.floorPositions.size()));
    }
    private Position randWallPosition(){
        return map.wallPositions.remove(map.rand.nextInt(map.wallPositions.size()));
    }


    private void addItems(){
        addPlayer();
        addDoor();
        addKeys(keyTotal);
        addEnemies();
        addTeleportWall();
    }
    private void addPlayer(){
        items.player = new Player(randomFloorPosition());
        world[items.player.getPosX()][items.player.getPosY()] = items.playerImage;
    }
    private void addDoor(){
        items.door = new Player(randWallPosition());
        world[items.door.getPosX()][items.door.getPosY()] = Tileset.LOCKED_DOOR;
    }
    private void addKeys(int keyTotal){
        for (int i = 0; i < keyTotal; i++){
            Position rp = randomFloorPosition();
            world[rp.getX()][rp.getY()] = Tileset.KEY;
            obstacles.add(rp);
        }
    }
    private void addTeleportWall(){
        for (int i = 0; i < 8; i++){
            Position rp = randWallPosition();
            world[rp.getX()][rp.getY()] = Tileset.TELEPORT;
        }
    }
    private void addEnemies(){
        items.enemies = new Player(randomFloorPosition());
        world[items.enemies.getPosX()][items.enemies.getPosY()] = Tileset.ENEMIES;
    }


    private Position getPathWorld(ArrayList<Position> obstacles){
        Position chasePos = getPathGrid(convertPosGrid(obstacles));
        if (chasePos == null){
            return null;
        }
        return convertSinglePosWorld(chasePos);
    }
    private Position convertSinglePosGrid(Player o){
        return new Position(36-o.getPosY(), o.getPosX());
    }
    private Position convertSinglePosWorld(Position p){
        return new Position(p.getY(), 36 - p.getX());
    }
    private ArrayList<Position> convertPosGrid(ArrayList<Position> obs){
        ArrayList<Position> obsG = MapGenerator.copyOf(obs);
        for (Position o: obsG){
            int x = o.getX();
            o.changeX(36 - o.getY());
            o.changeY(x);
        }
        return obsG;
    }
    private Position getPathGrid(ArrayList<Position> obstacles){
        return AStar.findPath(37,81, convertSinglePosGrid(items.enemies), convertSinglePosGrid(items.player),obstacles);
    }


    void startMenu() {
        char userInput;
        menuBar();
        StdDraw.show();
        boolean selected = false;
        while (!selected) {
            if (StdDraw.hasNextKeyTyped()) {
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 'n' || userInput == 'N') {
                    selected = true;
                    seed = seedMenu();
                    items.isLive = true;
                    enterGame(seed);
                } else if (userInput == 'l' || userInput == 'L') {
                    selected = true;
                    loadGame();
                } else if (userInput == 'q' || userInput == 'Q') {
                    System.exit(0);
                }
            }
        }
    }
    private void userControl(){
        char userInput;
        /*
        for (int j = 1; j < path.size()-1;j++){
            world[path.get(j).getX()][path.get(j).getY()] = Tileset.FLOWER;
        }*/
        while (true){
            if (StdDraw.hasNextKeyTyped()){
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 'q' || userInput == 'Q') {
                    System.exit(0);
                }
                else if (userInput == 'h' || userInput == 'H'){
                    saveGame();
                }
                else if (userInput == 'm' || userInput == 'M'){
                    StdDraw.clear(Color.black);
                    keyCount = 0;
                    keyTotal = 5;
                    stage = 1;
                    live = 3;
                    ter.initialize(40,40);
                    startMenu();
                }
                else{
                    updatePlayer(userInput);
                }
            }
            else{
                if (lightState) {
                    ter.renderFrame(world, keyCount, keyTotal, stage, live);
                }
                else{
                    ter.renderFrame(darkMode(world), keyCount, keyTotal, stage, live);
                }
            }
        }
    }
    private void loseLives(){
        live--;
        if (live == 0){
            ter.initialize(40,40);
            loseGame();
        }
    }
    private void updatePlayer(char userInput){
        if (!(items.player.isCollide(userInput,world, map.getWall()) || items.player.isCollide(userInput,world, Tileset.LOCKED_DOOR))){
            if (items.player.isCollide(userInput,world,Tileset.KEY)){
                keyCount ++;
            }

            if (items.player.isCollide(userInput,world,Tileset.UNLOCKED_DOOR)){
                keyCount = 0;
                keyTotal += 2;
                stage += 1;
                seed *= 2;
                if (stage > 5){
                    ter.initialize(40,40);
                    winGame();
                }
                items.isLive = true;
                nextStageBar();

                ter.initialize(80,40);
                createMap(seed, mapType);
                imageModifiedToMap(mapType);
                addItems();
                userControl();
            }

            world[items.player.getPosX()][items.player.getPosY()] = map.getFloor();
            if (items.player.isCollide(userInput,world,Tileset.TELEPORT)){
                items.player = new Player(randomFloorPosition());
                userInput = ' ';
            }
            if (userInput == 'w') {
                items.player.setPosY(1);
            } else if (userInput == 'a') {
                items.player.setPosX(-1);
            } else if (userInput == 's') {
                items.player.setPosY(-1);
            } else if (userInput == 'd') {
                items.player.setPosX(1);
            }

            world[items.player.getPosX()][items.player.getPosY()] = items.playerImage;
            obstacles.remove(new Position(items.player.getPosX(),items.player.getPosY()));
            if (items.isLive){
                enemyMove();
            }
        }
        if (lightState) {
            ter.renderFrame(world, keyCount, keyTotal, stage, live);
        }
        else{
            ter.renderFrame(darkMode(world), keyCount, keyTotal, stage, live);
        }

        if (keyCount == keyTotal){
            world[items.door.getPosX()][items.door.getPosY()] = Tileset.UNLOCKED_DOOR;
        }
    }
    private void enemyMove(){
        Position path = getPathWorld(obstacles);
        if (path == null) {
            if (items.player.getPosX() == items.enemies.getPosX()
                    && items.player.getPosY() == items.enemies.getPosY()) {
                loseLives();
                items.isLive = false;
            }
        }
        else {
            if (items.player.getPosX() == path.getX() && items.player.getPosY() == path.getY()) {
                world[items.enemies.getPosX()][items.enemies.getPosY()] = map.getFloor();
                loseLives();
                items.isLive = false;
            } else {
                world[items.enemies.getPosX()][items.enemies.getPosY()] = map.getFloor();
                items.enemies.changePosX(path.getX());
                items.enemies.changePosY(path.getY());
                world[items.enemies.getPosX()][items.enemies.getPosY()] = Tileset.ENEMIES;
            }
        }
    }


    private void nextStageBar(){
        ter.initialize(40,40);
        writeText("Level " + stage, 40, Color.white, new Position(width/2, height/2));
        StdDraw.show();
        StdDraw.pause(1500);
    }
    private void winBar(){
        writeText("YOU NAIL THE GAME!", 40, Color.white, new Position(width/2, height/2+6));
        writeText("Back To Menu (M)", 25, Color.white, new Position(width/2, height/2));
        writeText("Quit (Q)", 25, Color.white, new Position(width/2, height/2-2));
    }
    private void loseBar(){
        writeText("GAME OVER", 40, Color.white, new Position(width/2, height/2+6));
        writeText("Back To Menu (M)", 25, Color.white, new Position(width/2, height/2));
        writeText("Quit (Q)", 25, Color.white, new Position(width/2, height/2-2));
    }
    private void loseGame(){
        char userInput;
        loseBar();
        StdDraw.show();
        boolean selected = false;
        while (!selected){
            if (StdDraw.hasNextKeyTyped()){
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 'm' || userInput == 'M'){
                    selected = true;
                    StdDraw.clear(Color.black);
                    keyCount = 0;
                    keyTotal = 5;
                    stage = 1;
                    live = 3;
                    ter.initialize(40,40);
                    startMenu();
                }
                else if (userInput == 'q' || userInput == 'Q') {
                    System.exit(0);
                }
            }
        }
    }
    private void winGame(){
        char userInput;
        winBar();
        StdDraw.show();
        boolean selected = false;
        while (!selected){
            if (StdDraw.hasNextKeyTyped()){
                userInput = StdDraw.nextKeyTyped();
                if (userInput == 'm' || userInput == 'M'){
                    selected = true;
                    StdDraw.clear(Color.black);
                    keyCount = 0;
                    keyTotal = 5;
                    stage = 1;
                    live = 3;
                    ter.initialize(40,40);
                    startMenu();
                }
                else if (userInput == 'q' || userInput == 'Q') {
                    System.exit(0);
                }
            }
        }
    }
    private void saveGame(){
        SaveData data = new SaveData(world,map,items, keyCount, keyTotal, seed, stage,
                live, lightState, obstacles, mapType);
        try{
            ResourceManager.save(data, "MyGame.save");
        }
        catch (Exception e){
            System.out.println("Couldn't save " + e.getMessage());
        }
    }
    private void loadGame(){
        try {
            SaveData data = (SaveData) ResourceManager.load("MyGame.save");
            ter.initialize(80,40);
            transferData(data);
            imageModifiedToMap(mapType);
            userControl();
        }
        catch (Exception e){
            System.out.println("Couldn't load " + e.getMessage());
        }
    }
    private void transferData(SaveData data){
        world = data.saveWorld;
        items = data.gameItems;
        map = data.map;
        keyCount = data.keyCount;
        keyTotal = data.keyTotal;
        seed = data.seed;
        mapType = data.mapType;
        stage = data.stage;
        live = data.live;
        lightState = data.lightState;
        obstacles = data.obstacle;
    }

}
