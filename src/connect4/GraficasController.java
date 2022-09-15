/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.DayRank;
import model.Player;

/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class GraficasController implements Initializable {

    @FXML
    private Button victButton;
    @FXML
    private Button partidasButton;
    @FXML
    private DatePicker init;
    @FXML
    private DatePicker fin;
    @FXML
    private TextField usuario;
    @FXML
    private StackedBarChart<String, String> barras;
    @FXML
    private BarChart<String, Number> barChart;
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
        init.setValue(LocalDate.now().minusDays(30));
        fin.setValue(LocalDate.now());
    }    
    private Stage primaryStage;
    void initStage(Stage stage) {
       primaryStage = stage;
       primaryStage.setTitle("graficas.fxml"); 
    }

    @FXML
    private void victorias(ActionEvent event) {
        String user = usuario.getText();
        LocalDate inicio = init.getValue();
        LocalDate end = fin.getValue();
        if (checkName(user)) {
            barras.getData().clear();
            barChart.getData().clear();

            XYChart.Series perdidas = new XYChart.Series<>();
            perdidas.setName("Perdidas");
            XYChart.Series ganadas = new XYChart.Series<>();
            ganadas.setName("Ganadas");

            XYChart.Series oponentes = new XYChart.Series<>();
            oponentes.setName("Nº oponentes");
            
            Player player = connect4.getPlayer(user);
            if(user != null){
            TreeMap<LocalDate, DayRank> dayRanks = connect4.getDayRanksPlayer(player);
            dayRanks.forEach((LocalDate day, DayRank rank) -> {
                if (inicio.compareTo(day) <= 0 && end.compareTo(day) >= 0) {
                    ganadas.getData().add(new XYChart.Data(day.toString(), rank.getWinnedGames()));
                    perdidas.getData().add(new XYChart.Data(day.toString(), rank.getLostGames()));
                    oponentes.getData().add(new XYChart.Data(day.toString(), rank.getOponents()));
                }
            });

            barras.getData().addAll(perdidas, ganadas);
            barChart.getData().add(oponentes);
        }
        }
    }

    @FXML
    private void matchesButton(ActionEvent event) {
        changeScreen3();
    }
    
    public void mostrarGraficas(String username, LocalDate initial, LocalDate ending){
        
        
        barras.getData().clear();
        barChart.getData().clear();
        
        XYChart.Series perdidas = new XYChart.Series<>();
        perdidas.setName("Perdidas");
        XYChart.Series ganadas = new XYChart.Series<>();
        ganadas.setName("Ganadas");
        
        XYChart.Series oponentes = new XYChart.Series<>();
        oponentes.setName("Nº oponentes");
        
        TreeMap<LocalDate, DayRank> dayRanks = connect4.getDayRanksPlayer
        (connect4.getPlayer(username));
        dayRanks.forEach((LocalDate day, DayRank rank) ->{
            if(initial.compareTo(day) <= 0 && ending.compareTo(day) >= 0){
                ganadas.getData().add(new XYChart.Data(day.toString(),rank.getWinnedGames()));
                perdidas.getData().add(new XYChart.Data(day.toString(),rank.getLostGames()));
                oponentes.getData().add(new XYChart.Data(day.toString(),rank.getOponents()));
            }
        });
        
        barras.getData().addAll(perdidas, ganadas);
        barChart.getData().add(oponentes);
        
    }
    
    private boolean checkName(String name){
        boolean res = false;
        if(!connect4.exitsNickName(name)){usuario.setPromptText("El usuario no existe");}
        else{res = true;}
        return res;
    }
    
        public void changeScreen3(){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("graficaPartidas.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.GraficaPartidasController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("GraficaPartidas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show();
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
    