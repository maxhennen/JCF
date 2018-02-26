package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import woordenapplicatie.Calculate;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {
    
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
    
    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    private Calculate calc = new Calculate();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }
    
    @FXML
    private void aantalAction(ActionEvent event) {
     taOutput.setText("Total words: " + String.valueOf(calc.totalOfWords(taInput.getText())) + '\n' +
             "Number of  unique words " + String.valueOf(calc.uniqueNumberOfWords(taInput.getText()))
     );
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
     taOutput.setText(String.join(" ",calc.sort(taInput.getText())));
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
     Map<String, Integer> map = calc.freguentie(taInput.getText());
     StringBuilder text = new StringBuilder();
     for(String word : map.keySet()){
      String line = word + ": " + map.get(word).toString() + "\n";
      text.append(line);
     }

     taOutput.setText(text.toString());
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
         Map<String,ArrayList<Integer>> concordatie = calc.concordantie(taInput.getText());
         StringBuilder text = new StringBuilder();

         for(String word : concordatie.keySet()){
          ArrayList<Integer> points = concordatie.get(word);
          StringBuilder line = new StringBuilder();
          line.append(word + ": " + "[" );
          for(int point : points){
           line.append(point + ",");
          }
          line.append("]\n");
          text.append(line);
         }

     taOutput.setText(text.toString());
    }
   
}
