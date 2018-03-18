package sample;

import org.apache.commons.lang.ObjectUtils;
import org.dom4j.tree.BackedList;
import sun.reflect.generics.tree.Tree;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.util.*;

/**
 * Created by maxhe on 5-3-2018.
 */
public class Huffman
{

    private Node root;
    private List<BitSet> bitSet;
    private Map<BitSet,Character> swappedMap;
    private Map<Character, Integer> frequentie;
    private Map<Character,List<Boolean>> map;

    public Map<Character, List<Boolean>> getMap()
    {
        return map;
    }

    public Map<Character, Integer> getFrequentie()
    {
        return frequentie;
    }

    public Node getRoot()
    {
        return root;
    }

    public Map<BitSet, Character> getSwappedMap()
    {
        return swappedMap;
    }

    public List<BitSet> getBitSet()
    {
        return bitSet;
    }

    public Huffman(){

    }

    public Huffman(String text) {
        makeTree(text);
    }

    public void makeTree(String text) {
        frequentie = getFrequentie(text);

        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2)
            {
                return o1.getFrequency() - o2.getFrequency();
            }
        });

        for(Map.Entry<Character,Integer> entry : frequentie.entrySet()){
            Node node = new Node(entry.getKey(),entry.getValue());
            queue.offer(node);
        }


        while (queue.size() > 1) {
            Node leftChild = queue.poll();
            Node rightChild = queue.poll();

            Node parent = new Node(leftChild, rightChild);

            queue.offer(parent);
        }

        root = queue.poll();
        bitSet = generateBitmap(text);
    }

    public Map<Character, Integer> getFrequentie(String text) {
        Map<Character, Integer> frequentieMap = new HashMap<>();
        for (char w : text.toCharArray()){
            Integer n = frequentieMap.get(w);
            if (n == null) {
                frequentieMap.put(w, 1);
            } else {
                frequentieMap.replace(w, n+1);
            }
        }
        return frequentieMap;
    }

    public List<BitSet> generateBitmap(String text) {
        List<BitSet> bitSets = new LinkedList<>();

        map = new HashMap<>();
        fillBitmap(map, root);

        Map<Character,BitSet> bitSetMap = new HashMap<>();

        bitSets = new LinkedList<>();

        for(char c: text.toCharArray()){
            List<Boolean> bits = map.get(c);
            int counter = 0;
            BitSet bitSet = new BitSet();
            for(boolean b : bits){
                bitSet.set(counter,b);
                counter++;
            }
            bitSetMap.put(c,bitSet);
            bitSets.add(bitSet);
        }

        swappedMap = new HashMap<>();

        for(Map.Entry<Character,BitSet> entry : bitSetMap.entrySet()){
            swappedMap.put(entry.getValue(),entry.getKey());
        }

        return bitSets;
    }

    public void fillBitmap(Map<Character, List<Boolean>> map, Node node) {
        fillBitmap(map, node,  new ArrayList<>(), 0);
    }

    public void fillBitmap(Map<Character, List<Boolean>> map, Node node, List<Boolean> bits, int index) {
        if (node.isParent()) {
            char character = node.getKey();
            map.put(character, new ArrayList<>(bits));
            return;
        }

        if (node.getLeftChild() != null) {
            Node leftNode = node.getLeftChild();
            bits.add(false);
            fillBitmap(map, leftNode, bits, index + 1);
            bits.remove(index);
        }

        if (node.getRightChild() != null) {
            Node rightNode = node.getRightChild();
            bits.add(true);
            fillBitmap(map, rightNode, bits, index + 1);
            bits.remove(index);
        }

    }

    public String decode(List<BitSet> bitSets){

        StringBuilder builder = new StringBuilder();

        for(BitSet bitSet : bitSets){
            builder.append(swappedMap.get(bitSet));
        }

        return builder.toString();
    }


}
