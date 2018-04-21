package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"Excellento!", "Amazing!",
                                                   "You got it!","NAIL IT!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }
        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40,seed);
        game.startGame();
    }

    private MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        this.rand = new Random(seed);
        this.round = 1;
        this.gameOver = false;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    private String generateRandomString(int n) {
        String random = "";
        for (int i = 0; i < n; i++){
            random += CHARACTERS[rand.nextInt(CHARACTERS.length)];
        }
        return random;
    }

    private void drawFrameCenter(String s){
        drawFrame(s,20,20);
    }
    private void drawFrame(String s,int x, int y) {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x,y,s);
        StdDraw.show();

    }

    private void flashSequence(String letters,int round) {
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 0; i < letters.length(); i++){
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.text(20,20,"" + letters.charAt(i) + "");
            gameBar(round);
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear(StdDraw.BLACK);
            gameBar(round);
            StdDraw.show();
            StdDraw.pause(500);

        }

    }

    private String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String result = "";
        while (n > 0){
            if (StdDraw.hasNextKeyTyped()){
                result += StdDraw.nextKeyTyped();
                n--;
            }
        }
        StdDraw.clear(StdDraw.BLACK);
        return result;
    }

    private void gameBar(int round){
        drawFrame("Round: " + round,6,38);
        StdDraw.line(0,36,40,36);

    }

    private void currentStatus(String s, int round){
        gameBar(round);
        drawFrameCenter(s);
        StdDraw.pause(2000);
        StdDraw.clear(StdDraw.BLACK);
    }
    private void startGame() {
        //TODO: Set any relevant variables before the game starts
        int wordLength = round;
        currentStatus("Welcome to the Memory Game !!!", round);
        while (!gameOver){
            currentStatus("Get ready for round " + round + "...", round);
            String answer = generateRandomString(wordLength);
            flashSequence(answer, round);
            currentStatus("What is the word ?", round);
            String userAnswer = solicitNCharsInput(wordLength);
            currentStatus("Your answer is: " + userAnswer, round);
            currentStatus("Let's check the answer", round);
            if (!answer.equals(userAnswer)){
                gameOver = true;
                currentStatus("Game over YO", round);
            }
            else {
                if (round == 5){
                    currentStatus("Congratulation, you win the game !!!", round);
                    gameOver = true;
                }
                else{
                    currentStatus(ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)], round);
                    wordLength += 1;
                    round += 1;
                }
            }
        }
    }

}
