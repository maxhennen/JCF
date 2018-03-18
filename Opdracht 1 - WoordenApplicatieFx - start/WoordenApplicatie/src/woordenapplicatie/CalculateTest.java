package woordenapplicatie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by maxhe on 18-3-2018.
 */
public class CalculateTest
{

    private String text = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";
    private String empty = "";
    private String nul = null;
    private Calculate calculate;

    @Before
    public void before(){
        calculate = new Calculate();
    }

    //Tests stringToWordConverter
    @Test
    public void stringToWordConverter() throws Exception
    {
        assertEquals(15,calculate.stringToWordConverter(new ArrayList(),text).size());
    }

    @Test
    public void stringToWordConverterEmpty() throws Exception{
        assertEquals(0,calculate.stringToWordConverter(new ArrayList(),empty).size());
    }

    @Test
    public void stringToWordConvertNull() throws Exception{
        assertEquals(1,calculate.stringToWordConverter(new ArrayList(),nul).size());
    }

    //Tests totalOfWords
    @Test
    public void totalOfWords() throws Exception
    {
        assertEquals(15,calculate.totalOfWords(text));
    }

    //Tests uniqueNumberOfWords
    @Test
    public void uniqueNumberOfWords() throws Exception
    {
        assertEquals(7,calculate.uniqueNumberOfWords(text));
    }

    //Tests sort
    @Test
    public void sort() throws Exception
    {
        assertEquals("[vier, van, twee, papier, hoedje, een, drie]",calculate.sort(text).toString());
    }

    //Tests freguentie
    @Test
    public void freguentie() throws Exception
    {
        assertEquals("{drie=2, een=2, hoedje=3, papier=1, twee=2, van=3, vier=2}",calculate.freguentie(text).toString());
    }

    //Tests concordantie
    @Test
    public void concordantie() throws Exception
    {
        assertEquals("{hoedje=[2, 2, 4], van=[2, 3, 4], twee=[1, 3], drie=[1, 3], vier=[2, 4], papier=[4], een=[1, 3]}",calculate.concordantie(text).toString());
    }


}