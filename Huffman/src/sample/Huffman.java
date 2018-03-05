package sample;

import sun.reflect.generics.tree.Tree;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

/**
 * Created by maxhe on 5-3-2018.
 */
public class Huffman
{
    private Map<Character,Integer> sorted;
    private LinkedList<Node> nodes;
    private char[] characters;

    public Huffman(String text){
        nodes = new LinkedList<>();

        this.characters = text.toCharArray();
        this.sorted = frequencySorted(characters);

        buildTree();

        for(Node node : nodes){
            System.out.println(node);
        }

    }

    private void buildTree(){
        for(Map.Entry<Character,Integer> entry : sorted.entrySet()){
            sortNodes(entry.getKey(),entry.getValue());
        }

        Node leftChild;
        Node rightChild;

        while (nodes.size() >= 2 ){

            leftChild = nodes.get(0);
            rightChild = nodes.get(1);
            nodes.remove(0);
            nodes.remove(0);
            Node newNode = new Node(leftChild,rightChild);
            sortNodes(newNode.getKey(),newNode.getFrequency());
        }
    }

    private Map<Character,Integer> frequencySorted(char[] characters){
        Map<Character, Integer> unsorted = new HashMap<>();
        for(char c : characters){
            if(unsorted.get(c) == null){
                unsorted.put(c,1);
            }
            else {
                unsorted.put(c,unsorted.get(c)+1);
            }
        }

        return sortValues(unsorted);
    }

    /**
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

    private void sortNodes(char key, int frequency){

        Node node = new Node(key, frequency);

        nodes.add(node);

        /*for(int i = 0; 0 < this.nodes.size();i++){
            if(nodes.size() != 0 && node.getFrequency() <= nodes.get(i).getFrequency()){
                nodes.add(i,node);
                break;
            }
            else {
                nodes.add(node);
                break;
            }
        }*/
    }

}
