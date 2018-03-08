
public class Palindrome
{
    public boolean isPalindrome(String word){
        
    }
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            res.addLast(word.charAt(i)); 
        }
        return res;
    }
}
