package sample;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Created by maxhe on 18-3-2018.
 */
public class PerformanceTest
{
    private String thousandWords;
    private String millionWords;

    @Before
    public void before(){

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
    public void writeThousandWords(){
        long startCPUTimeNano = getCpuTime();
        writeFile(thousandWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Write thousand words: " + taskCPUTime/1000000000.0 + " sec");
        readFileThousandWords();
    }

    @Test
    public void writeMillionWords(){
        long startCPUTimeNano = getCpuTime();
        writeFile(millionWords);
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Write million words: " + taskCPUTime/1000000000.0);
        readFileMillionWords();
    }

    private void readFileThousandWords(){
        Main main = new Main();
        long startCPUTimeNano = getCpuTime();
        main.readFile(new Huffman(thousandWords));
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Read thousand words: " + taskCPUTime/1000000000.0);
    }

    private void readFileMillionWords(){
        Main main = new Main();
        long startCPUTimeNano = getCpuTime();
        main.readFile(new Huffman(millionWords));
        long taskCPUTime = getCpuTime() - startCPUTimeNano;
        System.out.println("Read million words: " + taskCPUTime/1000000000.0);
    }

    private void writeFile(String words){
        Main main = new Main();
        Huffman huffman = new Huffman(words);
        main.writeFile(huffman.getBitSet());
    }



    /** Get CPU time in nanoseconds. */
    public long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }

}