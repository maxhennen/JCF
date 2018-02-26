package sample;

import sun.plugin.net.protocol.jar.CachedJarURLConnection;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by maxhe on 26-2-2018.
 */
public class Compression
{
    public Map<Character,Integer> frequencyChar(String text){
        char[] characters = text.toLowerCase().toCharArray();

        TreeMap<Character,Integer> frequency = new TreeMap<>();

        for(char character: characters){
            frequency.put(character,frequency.getOrDefault(character,0)+1);
        }

        System.out.println(frequency);
        return sortValues(frequency);
    }

    public static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    /**
     *
     * @param unsortMap a map with unsorted values
     * @return a map sorted by there values
     */
    private Map<Character, Integer> sortValues(Map<Character, Integer> unsortMap)
    {

        List<Map.Entry<Character, Integer>> list = new LinkedList(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>()
        {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<Character, Integer> sortedMap = new LinkedHashMap();
        for (Map.Entry<Character, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
