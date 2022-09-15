/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import static connect4.Connect4.nuevo;
import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Connect4;
import model.DayRank;
import model.Player;
import model.Round;
/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class TablasPartidasController implements Initializable {

    @FXML
    private Button partidasRealizadas;
    @FXML
    private Button partidasPlayer;
    @FXML
    private Button winsPlayer;
    @FXML
    private Button losePlayer;
    @FXML
    private Button matchGraphic;
    @FXML
    private DatePicker initDate;
    @FXML
    private DatePicker finalDate;
    @FXML
    private TextField nickName;
    
    private Connect4 connect4;
    
    private GridPane tablita;
    @FXML
    private ListView<String> rankingList;
    
    private ObservableList<String> datos = null;
    @FXML
    private Button limpiarButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        datos = FXCollections.observableArrayList();
        rankingList.setItems(datos);
        try{
            connect4 = Connect4.getSingletonConnect4();
            
        }
        catch(Connect4DAOException e){
            e.printStackTrace();
        }
        initDate.setValue(LocalDate.now().minusDays(30));
        finalDate.setValue(LocalDate.now());
    }    
    
    private Stage primaryStage;
    void initStage(Stage stage) {
       primaryStage = stage;
       primaryStage.setTitle("tablasPartidas.fxml"); 
    }

    @FXML
    private void matchesDone(ActionEvent event) {
        cleanPane();
        if (checkDate()) {
            String nombre = nickName.getText();
            LocalDate init = initDate.getValue(); //puede ser null si no se inicializa
            LocalDate fin = finalDate.getValue();//puede ser null si no se inicializa
            Player jugador = connect4.getPlayer(nombre);
            TreeMap<LocalDate, List<Round>> data = connect4.getRoundsPerDay();
            data.forEach((LocalDate day, List<Round> rank) -> {
                if (day.compareTo(init) >= 0 && day.compareTo(fin) <= 0) {
                    List<Round> rounds = data.get(day);
                    for (int i = 0; i < rounds.size(); i++) {

                        Round ronda = rank.get(i);
                        Player winner = ronda.getWinner();
                        String namew = winner.getNickName();
                        Player loser = ronda.getLoser();
                        String namel = loser.getNickName();
                        LocalDateTime time = ronda.getTimeStamp();;

                        String res = new String("Ganador: " + namew + " Perdedor: " + namel + " Día: " + time.getDayOfMonth() + "/" +time.getMonth() + " Hora: " + time.getHour() + ":" + time.getMinute());
                        datos.add(res);
                        rankingList.setItems(datos);
                    }
                }
            });
            
        }
    }

    @FXML
    private void matchPlayer(ActionEvent event) {
        cleanPane();
        if (checkDate()) {
            if (checkName()) {
                String nombre = nickName.getText();
                LocalDate init = initDate.getValue(); //puede ser null si no se inicializa
                LocalDate fin = finalDate.getValue();//puede ser null si no se inicializa
                Player jugador = connect4.getPlayer(nombre);
                ArrayList<Round> data = connect4.getRoundsPlayer(jugador);
                for (int i = 0; i < data.size(); i++) {
                    Round ronda = data.get(i);
                    Player winner = ronda.getWinner();
                    String namew = winner.getNickName();
                    Player loser = ronda.getLoser();
                    String namel = loser.getNickName();
                    LocalDateTime timer = ronda.getTimeStamp();
                    LocalDate time = ronda.getLocalDate();

                    if (init.compareTo(time) <= 0 && fin.compareTo(time) >= 0) {
                        String res = new String("Ganador: " + namew + " Perdedor: " + namel + " Día: " + timer.getDayOfMonth() + "/" +time.getMonth()+" Hora: " + timer.getHour() + ":" + timer.getMinute());
                        datos.add(res);
                        rankingList.setItems(datos);
                    }

                }
            }
        }

    }

    @FXML
    private void winButton(ActionEvent event) {
        cleanPane();
        if (checkDate()) {
            if (checkName()) {
                String nombre = nickName.getText();
                LocalDate init = initDate.getValue(); //puede ser null si no se inicializa
                LocalDate fin = finalDate.getValue();//puede ser null si no se inicializa
                Player jugador = connect4.getPlayer(nombre);
                ArrayList<Round> data = connect4.getWinnedRoundsPlayer(jugador);
                for (int i = 0; i < data.size(); i++) {
                    Round ronda = data.get(i);
                    LocalDateTime timer = ronda.getTimeStamp();
                    Player winner = ronda.getWinner();
                    String namew = winner.getNickName();
                    Player loser = ronda.getLoser();
                    String namel = loser.getNickName();
                    LocalDate time = ronda.getLocalDate();

                    if (init.compareTo(time) <= 0 && fin.compareTo(time) >= 0) {
                        String res = new String("Ganador: " + namew + " Perdedor: " + namel + " Día: " + timer.getDayOfMonth() +"/" +time.getMonth()+ " Hora: " + timer.getHour() + ":" + timer.getMinute());
                        datos.add(res);
                        rankingList.setItems(datos);
                    }
                }
            }
        }

    }

    @FXML
    private void loseButton(ActionEvent event) {
        cleanPane();
        if (checkDate()) {
            if (checkName()) {
                String nombre = nickName.getText();
                LocalDate init = initDate.getValue(); //puede ser null si no se inicializa
                LocalDate fin = finalDate.getValue();//puede ser null si no se inicializa
                Player jugador = connect4.getPlayer(nombre);
                ArrayList<Round> data = connect4.getLostRoundsPlayer(jugador);
                for (int i = 0; i < data.size(); i++) {
                    Round ronda = data.get(i);
                    Player winner = ronda.getWinner();
                    String namew = winner.getNickName();
                    Player loser = ronda.getLoser();
                    String namel = loser.getNickName();
                    LocalDate time = ronda.getLocalDate();
                    LocalDateTime timer = ronda.getTimeStamp();
                    if (init.compareTo(time) <= 0 && fin.compareTo(time) >= 0) {

                        String res = new String("Ganador: " + namew + " Perdedor: " + namel + " Día: " + timer.getDayOfMonth() +"/" +time.getMonth() + " Hora: " + timer.getHour() + ":" + timer.getMinute());
                        datos.add(res);
                        rankingList.setItems(datos);
                    }
                }
            }
        }

    }

    @FXML
    private void partidaGrafica(ActionEvent event) {
        changeScreen();
        /*String nombre = nickName.getText();
        LocalDate init = initDate.getValue();
        LocalDate fin = finalDate.getValue();
        if(connect4.exitsNickName(nombre)){
            nuevo = connect4.getPlayer(nombre);
        }
        else{
            nickName.setPromptText("No existe dicho usuario");
        }
        TreeMap<LocalDate, Integer> numberMatch = connect4.getRoundCountsPerDay();
        for(LocalDate date = init; date.isBefore(fin); date = init.plusDays(1)){
            
        }
*/

    }

    
    
    private boolean checkDate(){
        boolean res = false;
        if(initDate.getValue().equals(null)){initDate.setPromptText("Escriba una fecha");}
        else if(finalDate.getValue().equals(null)){finalDate.setPromptText("Escriba una fecha");}
        else{res = true;}
        return res;
    }
    
    private boolean checkName(){
        String name = nickName.getText();
        boolean res = false;
        if(!connect4.exitsNickName(name)){nickName.setText("El usuario no existe");}
        else{res = true;}
        return res;
    }
    
    private void cleanPane(){
        datos.removeAll(rankingList.getSelectionModel().getSelectedItems());
        rankingList.getItems().clear();
    }
    
        public void changeScreen(){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("graficas.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.GraficasController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Gráficas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.hide();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show(); 
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        cleanPane();
    }
    
}
