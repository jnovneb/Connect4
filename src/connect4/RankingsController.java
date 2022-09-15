/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Player;
import model.Connect4;



/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class RankingsController implements Initializable {
    private Connect4 connect4;
    @FXML
    private GridPane ranking;
    private Stage primaryStage;
    @FXML
    private TextField search;
    @FXML
    private Button searchButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            connect4 = Connect4.getSingletonConnect4();
        }catch(Connect4DAOException e){
            e.printStackTrace();
        }
        showrankingdef();
    }
    
    public void showrankingdef() {
        ArrayList<Player> player = connect4.getConnect4Ranking();
        for (int i = 0; i < player.size(); i++) {
            Player nuevesito = player.get(i);
            Label labelName = new Label(nuevesito.getNickName());
            labelName.setStyle("-fx-text-alignment: center;");
            Label labelPoints = new Label(nuevesito.getPoints() + "");
            labelPoints.setStyle("-fx-text-alignment: center;");

            ranking.add(labelName, 1, 1 + i);
            ranking.add(labelPoints, 2, 1 + i);
        }
    }

    public void showranking(List<Player> searchPlayer, Map<String, Integer> searchPlayerPos){
        //ArrayList<Player> player = connect4.getConnect4Ranking();
        int counter = 0;
        for(int i = 0; i<searchPlayer.size()&& i < 9;i++){
            Player nuevesito = searchPlayer.get(i);
            Label labelName = new Label(nuevesito.getNickName());
            labelName.setStyle("-fx-text-alignment: center;");
            Label labelPoints = new Label(nuevesito.getPoints() + "");
            labelPoints.setStyle("-fx-text-alignment: center;");
            Label labelPos = new Label(""+counter);
            ranking.add(labelPoints,0,1+i);
            ranking.add(labelName, 1, 1+i);
            ranking.add(labelPoints, 2, 1+i);
            counter++;
        }
    }

    void initStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("RecordarContraseña.fxml");
    }

    @FXML
    private void search(ActionEvent event) {
        
        String userName = search.getText();
        if(!userName.equals("")){
            cleanPane();
            busca(userName);
        }
        else{
            showrankingdef();
        }
    }
    
    public void busca(String busqueda){
        if(!busqueda.equals("")){
            List<Player> searchPlayers = new LinkedList<>();
            Map<String, Integer> searchPlayersPosition = new HashMap<>();
            int pos = 1;
            for(Player player : connect4.getConnect4Ranking()){
                if(player.getNickName().toLowerCase(Locale.ROOT).contains(busqueda.toLowerCase(Locale.ROOT))){
                    searchPlayers.add(player);
                    searchPlayersPosition.put(player.toString(), pos);
                }
                pos++;
            }
            showranking(searchPlayers,searchPlayersPosition);
        }
       
    }
    
    private void cleanPane() {
        ArrayList<Node> remove = new ArrayList<Node>();
        for (int i = 0; i < ranking.getChildren().size(); i++) {
            Node node = ranking.getChildren().get(i);
            try {
                if (GridPane.getRowIndex(node) != 0) {
                        remove.add(node);
                }
            } catch (NullPointerException e) {
            }
        }
        ranking.getChildren().removeAll(remove);

    }
    
}
