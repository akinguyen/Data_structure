public class OffByOne implements CharacterComparator{
    public boolean equalChars(char x, char y){
        return (Math.abs(x-y) == 1);
    }
}
