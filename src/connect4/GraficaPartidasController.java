/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;
import model.Connect4;

/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class GraficaPartidasController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private Button realizedMatch;
    @FXML
    private DatePicker initDate;
    @FXML
    private DatePicker finalDate;
    private TextField usuario;
    private Connect4 connect4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try{
             connect4 = Connect4.getSingletonConnect4();
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
        initDate.setValue(LocalDate.now().minusDays(30));
        finalDate.setValue(LocalDate.now());
    }    

    @FXML
    private void matches(ActionEvent event) {
        LocalDate inicio = initDate.getValue();
        LocalDate fin = finalDate.getValue();

        if (checkDate()) {
            lineChart.getData().clear();
            XYChart.Series matches = new XYChart.Series<>();
            matches.setName("Nº partidas");
            XYChart.Series days = new XYChart.Series<>();
            days.setName("Días");

            TreeMap<LocalDate, Integer> partidillas = connect4.getRoundCountsPerDay();
            partidillas.forEach((LocalDate day, Integer number) -> {
                if (inicio.compareTo(day) <= 0 && fin.compareTo(day) >= 0) {
                    matches.getData().add(new XYChart.Data(day.toString(),number.intValue()));
                    days.getData().add(new XYChart.Data(day.toString(), day.getChronology()));
                }
            });
            lineChart.getData().add(matches);
        }
    }

     private Stage primaryStage;
    void initStage(Stage stage) {
       primaryStage = stage;
       primaryStage.setTitle("graficaPartidas.fxml"); 
    }
    

    private boolean checkDate() {
        boolean res = false;
        if (initDate.getValue().equals(null)) {
            initDate.setPromptText("Escriba una fecha");
        } else if (finalDate.getValue().equals(null)) {
            finalDate.setPromptText("Escriba una fecha");
        } else {
            res = true;
        }
        return res;
    }
    
    

    
}
