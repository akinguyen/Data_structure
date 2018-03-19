package synthesizer;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args){
        String keyboard = "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=";
        GuitarString[] note = new GuitarString[keyboard.length()];
        for ( int i = 0; i < keyboard.length(); i++){
            note[i] = new GuitarString((int) Math.round(440*Math.pow(2,(i-24)/12)));
        }

        while (true){
            if (StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                note[keyboard.indexOf(key)].pluck();
            }

            double sample = 0;
            for ( int i = 0; i < keyboard.length(); i++){
                sample += note[i].sample();
            }

            StdAudio.play(sample);

            for ( int i = 0; i < keyboard.length(); i++){
                note[i].tic();
            }

        }
    }
}
