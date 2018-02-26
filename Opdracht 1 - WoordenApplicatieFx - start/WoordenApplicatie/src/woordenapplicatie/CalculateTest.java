package woordenapplicatie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by maxhe on 18-2-2018.
 */
public class CalculateTest
{
    private String text = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";

    private String millionWords = "";
    private Calculate calc = new Calculate();

    @Before
    public void setMillionWords(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 1000000; i++){
            builder.append("text,");
        }
        millionWords = builder.toString();
    }

    @Test
    public void totalOfWords() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals(15,calc.totalOfWords(text));
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void totalOfWordsMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals(1000000,calc.totalOfWords(millionWords));
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void uniqueNumberOfWords() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals(7,calc.uniqueNumberOfWords(text));
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void uniqueNumberOfWordsMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals(1,calc.uniqueNumberOfWords(millionWords));
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void sort() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals("[vier, van, twee, papier, hoedje, een, drie]",calc.sort(text).toString());
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void sortMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals("[text]",calc.sort(millionWords).toString());
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void freguentie() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals("{papier=1, twee=2, drie=2, vier=2, een=2, hoedje=3, van=3}",calc.freguentie(text).toString());
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void freguentieMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals("{text=1000000}",calc.freguentie(text).toString());
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void concordantie() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals("{hoedje=java.awt.Point[x=4,y=1], van=java.awt.Point[x=4,y=2], twee=java.awt.Point[x=3,y=3], drie=java.awt.Point[x=3,y=5], vier=java.awt.Point[x=3,y=7], papier=java.awt.Point[x=4,y=3], een=java.awt.Point[x=3,y=1]}",calc.concordantie(text).toString());
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    @Test
    public void concordantieMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        Assert.assertEquals("{text=}",calc.concordantie(millionWords).toString());
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println(taskCPUTime);
    }

    /** Get CPU time in nanoseconds. */
    public long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }

}