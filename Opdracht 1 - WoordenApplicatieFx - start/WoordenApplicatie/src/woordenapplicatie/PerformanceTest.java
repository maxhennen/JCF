package woordenapplicatie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by maxhe on 18-2-2018.
 */
public class PerformanceTest
{
    private String thousandWords = "";
    private String millionWords = "";
    private Calculate calc = new Calculate();

    @Before
    public void setMillionWords(){
        File file;
        Scanner scanner = null;

        try
        {
            file = new File("E:\\ThousandWords.txt");
            scanner = new Scanner(file);
            thousandWords = scanner.useDelimiter("\\Z").next();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            scanner.close();
        }

        for(int i = 1; i< 10; i++){
            millionWords = millionWords + thousandWords;
        }
    }

    @Test
    public void totalOfWords() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.totalOfWords(thousandWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Total of words thousand: " + taskCPUTime + " nanosec");
    }

    @Test
    public void totalOfWordsMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.totalOfWords(millionWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Total of words million: " + taskCPUTime + " nanosec");
    }

    @Test
    public void uniqueNumberOfWords() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.uniqueNumberOfWords(thousandWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Unique number of words thousand: " + taskCPUTime + " nanosec");
    }

    @Test
    public void uniqueNumberOfWordsMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.uniqueNumberOfWords(millionWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Unique number of words million: " + taskCPUTime + " nanosec");
    }

    @Test
    public void sort() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.sort(thousandWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Sort thousand words: " + taskCPUTime + " nanosec");
    }

    @Test
    public void sortMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.sort(millionWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Sort million words: " + taskCPUTime + " nanosec");
    }

    @Test
    public void freguentie() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.freguentie(thousandWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Frequentie of thousand words: " + taskCPUTime + " nanosec");
    }

    @Test
    public void freguentieMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.freguentie(millionWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Frequentie of million words: " + taskCPUTime + " nanosec");
    }

    @Test
    public void concordantie() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.concordantie(thousandWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Concordantie of thousand words: " + taskCPUTime + " nanosec");
    }

    @Test
    public void concordantieMillion() throws Exception
    {
        long startCPUTimeNano = getCpuTime();
        calc.concordantie(millionWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Frequentie of million words: " + taskCPUTime + " nanosec");
    }

    /** Get CPU time in nanoseconds. */
    public long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }

}