
public class Palindrome
{
    
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            res.addLast(word.charAt(i)); 
        }
        return res;
    }
}
