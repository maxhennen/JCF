package woordenapplicatie;


import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by maxhe on 6-2-2018.
 */
public class Calculate
{

    /**
     *
     * @param collection that will be returned with the words of the text
     * @param text the that text that will be convert from string to collection with words
     * @return collection with the convert string to a map collection with words
     */
    public Collection<String> stringToWordConverter(Collection collection, String text){
        StringBuilder word = new StringBuilder();
        for(char character : (text + ',').toLowerCase().toCharArray())
        {

            if (character == ' ' || character == ',' || character == '.' || character == '\n' || character == '\r')
            {
                if (word.length() > 0)
                {
                    collection.add(word.toString());
                    word = new StringBuilder();
                }
            } else
            {
                word.append(character);
            }
        }
        return collection;
    }

    /**
     *
     * @param text the text of which you want to know the number of words
     * @return the count of the words of the text
     * Complexity: O(N)
     */
    public int totalOfWords(String text){
        return stringToWordConverter(new ArrayList(),text).size();
    }

    /**
     *
     * @param text the text of wich you want to know the number of unique words
     * @return the count of unique words of the text
     * Complexity: O(N)
     */
    public int uniqueNumberOfWords(String text){
        return stringToWordConverter(new HashSet(),text).size();
    }

    /**
     *
     * @param text the text you wanna sort in alphabetically order
     * @return a list with the sort words
     * Complexity: O(N) * O(LogN) + O(N)
     */
    public List<String> sort(String text){

        PriorityQueue<String> queue = new PriorityQueue<>(
                new Comparator<String>( ) {
                    // overriding the compare method
                    public int compare(String i, String j) {
                        return j.compareToIgnoreCase(i);
                    }
                }
        );

        for(String str : stringToWordConverter(new PriorityQueue(),text)){
            queue.offer(str);
        }

        String word = null;
        ArrayList<String> list = new ArrayList<>();

        while((word = queue.poll()) != null){
            if(!list.contains(word)){
                list.add(word);
            }
        }

        return list;
    }

    /**
     *
     * @param text the text where you want count the frequentie of the words
     * @return a map with a words and there frequentie
     * Complexity: O(N) * O(N^2) = O(N^3)
     */
    public Map<String, Integer> freguentie(String text){

        Map<String, Integer> frequentie = new TreeMap<>();

        for(String word : stringToWordConverter(new ArrayList(),text)){
            frequentie.put(word,frequentie.getOrDefault(word,0)+1);
        }

        return sortValues(frequentie);
    }

    /**
     *
     * @param unsortMap a map with unsorted values
     * @return a map sorted by there values
     * Complexity: O(N^2)
     */
    private Map<String, Integer> sortValues(Map<String, Integer> unsortMap)
    {
        TreeMap<String, Integer> sorted = (TreeMap<String, Integer>) unsortMap;

        return sorted;
    }

    /**
     *
     * @param text the text where you wanna know where the words stand
     * @return a map with words and there points where they are standing
     * Complexity: O(N^4)
     */
    public Map<String,Collection<Integer>> concordantie(String text){

        HashMap<String,Collection<Integer>> concordantie = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        int line = 1;

        for(char c : (text + ".").toLowerCase().toCharArray()){
            if(c == ' ' || c == ',' || c == '.' || c == '\n' || c == '\r'){
                if(c == '\n' || c == '\r'){
                    line++;
                }
                if (builder.length() > 0)
                {
                    String word = builder.toString();
                    System.out.println(word);
                    if(!concordantie.containsKey(word)){
                        concordantie.put(word,new ArrayList<>());
                    }
                    concordantie.get(word).add(line);
                }
                builder = new StringBuilder();
            }
            else {
                builder.append(c);
            }
        }
        return concordantie;
    }
}
