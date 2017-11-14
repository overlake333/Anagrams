// Taylor Olson
// CSE 143 C with Justin Morgan
// Homework 6
// Application logic for finding the  
// anagrams (words or phrases that are made by
// rearranging the letters of another word or 
// phrase) within a given user phrase. This 
// program uses recursive backtracking to finds
// all possible anagrams within the inputed 
// phrase and prints all possible anagaram 
// combinations with a given word limit.  
import java.util.*;

public class Anagrams {
   private Set<String> dict;
   
   // initializes a new anagram solver with a given dictionary
   //      @param dictionary : a Set<String> containings words in alphabetical order
   public Anagrams(Set<String> dictionary) {
      if (dictionary == null) 
         throw new IllegalArgumentException("ERROR: Passed a null dictionary");
      this.dict = new HashSet<String>();
      this.dict.addAll(dictionary); 
   }
   
   // returns a set containiing every word from the dictionary that 
   // can be made up of the letters in the given phrase
   //      @param phrase : user's inputed String
   //      @return Set<String> : every word that can be made from phrase
   public Set<String> getWords(String phrase) {
      if (phrase.equals(null))
         throw new IllegalArgumentException("ERROR Passed a null phrase");
      Set<String> result = new TreeSet<String>();
      LetterInventory letters = new LetterInventory(phrase);
      for (String s : this.dict) {
         if (letters.contains(s))
            result.add(s);
      }
      return result;
   }
   
   // prints all anagrams that can be produced from a given phrase
   //      @param phrase : user's inputed String
   public void print(String phrase) {
      print(phrase, Integer.MAX_VALUE);
   }
   
   // prints all anagrams that can be produced from a given phrase
   // that includes at most the inputed max number of words
   //      @param phrase : user's inputed String
   //      @param max : maximum number of words for a given anagram
   public void print(String phrase, int max) {
      if (phrase.equals(null)) {
         throw new IllegalArgumentException("ERROR: phrase is null");
      } else if (!phrase.isEmpty()) {
         LetterInventory letters = new LetterInventory(phrase);
         Set<String> choices = this.getWords(phrase);
         if (max == 0) 
            max = Integer.MAX_VALUE;
         print(letters, max, choices , new Stack<String>());
      } 
   }
   
   // prints all anagrams that can be produced from a given phrase's
   // letters that includes at most the inputed max number of words.
   //      @param letters : LetterInventory object that contains remaining letters
   //      @param max : maximum number of words for a given anagram
   //      @param choices : Set<String> of all possible words that can be created from phrase
   //      @param chosen : Set<String> of chosen words  
   private void print(LetterInventory letters, int max, Set<String> choices, 
      Stack<String> chosen) {
      if (letters.size() == 0) {
         System.out.println(chosen);
      } else if (max > 0) {
         for (String word : choices) {
            if (letters.size() >= word.length() && letters.contains(word)) {
               letters.subtract(word);
               chosen.push(word);
               print(letters, max - 1, choices, chosen);
               chosen.pop();
               letters.add(word);
            }
         }
      }
   }
}