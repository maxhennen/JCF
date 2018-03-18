package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

/**
 * Created by maxhe on 13-3-2018.
 */
public class Main extends Application
{

    private static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Heb je dan geen hoedje meer\n" +
            "Maak er één van bordpapier\n" +
            "Eén, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "En als het hoedje dan niet past\n" +
            "Zetten we 't in de glazenkas\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";


    @Override
    public void start(Stage primaryStage) throws Exception{

        Huffman huffman = new Huffman(DEFAULT_TEXT);
        Scanner scanner = new Scanner(System.in);
        String task = scanner.nextLine();

        if (task.equals("write"))
        {
            writeFile(huffman.getBitSet());
        }
        else if (task.equals("read")){
            readFile(huffman);
        }
    }

    public void readFile(Huffman huffman){

        FileInputStream fileInputStream = null;

        try{
            fileInputStream = new FileInputStream("E:\\huffmantree.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            List<BitSet> bitSets = (List<BitSet>)inputStream.readObject();
            System.out.println(huffman.decode(bitSets));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally
        {
            try{
                fileInputStream.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void writeFile(List<BitSet> bitSet)
    {

        ObjectOutputStream objectOutputStream;
        FileOutputStream fileOutputStream = null;


        try
        {
            fileOutputStream = new FileOutputStream("E:\\huffmantree.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(bitSet);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}