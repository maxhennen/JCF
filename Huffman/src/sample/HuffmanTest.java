package sample;

import org.apache.commons.lang.ObjectUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by maxhe on 18-3-2018.
 */
public class HuffmanTest
{

    private String someText = "Some text";
    private String empty = "";
    private String nul = null;
    private Huffman huffman = null;


    //Tests Constructor & makeTree
    @Test
    public void constructor() throws Exception{
        huffman = new Huffman(someText);
        assertNotNull(huffman.getRoot());
    }

    @Test (expected = NullPointerException.class)
    public void constructorEmptyString() throws Exception{
        huffman = new Huffman(empty);
    }

    @Test (expected = NullPointerException.class)
    public void constructorNull() throws Exception{
        huffman = new Huffman(nul);
    }


    //Tests getFrequentie
    @Test
    public void getFrequentie() throws Exception
    {
        huffman = new Huffman(someText);
        huffman.getFrequentie(someText);
        assertNotNull(huffman.getFrequentie());
    }

    @Test
    public void getFrequentieEmpty() throws Exception{
        huffman = new Huffman();
        huffman.getFrequentie(empty);
    }

    @Test (expected = NullPointerException.class)
    public void getFrequentieNull() throws Exception{
        huffman = new Huffman();
        huffman.getFrequentie(nul);
    }

    //Tests generateBitmap
    @Test
    public void generateBitmap() throws Exception
    {
        huffman = new Huffman(someText);
        assertNotNull(huffman.generateBitmap(someText));
    }

    @Test
    public void generateBitmapEmpty() throws Exception{
        huffman = new Huffman(someText);
        huffman.generateBitmap(empty);
    }

    @Test(expected = NullPointerException.class)
    public void generateBitmapNull() throws Exception{
        huffman = new Huffman(someText);
        huffman.generateBitmap(nul);
    }

    //Tests filleBitmap
    @Test
    public void fillBitmap() throws Exception
    {
        huffman = new Huffman(someText);
        huffman.fillBitmap(huffman.getMap(),huffman.getRoot(),new ArrayList<>(),0);

    }

    @Test(expected = NullPointerException.class)
    public void fillBitmapEmptyRoot() throws Exception{
        huffman = new Huffman(someText);
        huffman.fillBitmap(huffman.getMap(),null,new ArrayList<>(),0);
    }

    @Test (expected = NullPointerException.class)
    public void fillBitmapEmptyMap() throws Exception{
        huffman = new Huffman(someText);
        huffman.fillBitmap(null,huffman.getRoot(),new ArrayList<>(),0);
    }

    //Tests decode
    @Test
    public void decode()throws Exception{
        huffman = new Huffman(someText);
        List<BitSet> bitSetList = new LinkedList<>();
        bitSetList.add(new BitSet());
        bitSetList.add(new BitSet());
        bitSetList.add(new BitSet());
        bitSetList.add(new BitSet());
        bitSetList.add(new BitSet());
        bitSetList.add(new BitSet());
        bitSetList.add(new BitSet());

        huffman.decode(bitSetList);
    }

    @Test
    public void decodeEmptyList() throws Exception
    {
        huffman = new Huffman(someText);
        huffman.decode(new LinkedList<>());
    }

    @Test(expected = NullPointerException.class)
    public void decodeNullList() throws Exception{
        huffman = new Huffman(someText);
        huffman.decode(null);
    }

}