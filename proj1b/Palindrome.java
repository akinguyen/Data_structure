
public class Palindrome
{
    private boolean isPalindrome(Deque a, int i){
        if (i > 1){
            if (a.removeFirst().equals(a.removeLast())){
                return isPalindrome(a,i - 2);
            }
            return false;
        }
            return true;
        }

    public boolean isPalindrome(String word){
        if (word.length() < 2){
            return true;
        }
        return word.charAt(0) == word.charAt(word.length()-1) && isPalindrome(word.substring(1,word.length()-1));
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> a = wordToDeque(word);
        for (int i = a.size(); i > 1; i -= 2){
            if(!cc.equalChars(a.removeFirst(),a.removeLast())){
                return false;
            }
        }
        return true;
    }
    
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            res.addLast(word.charAt(i)); 
        }
        return res;
    }

}
