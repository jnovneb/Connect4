/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import static connect4.Connect4.player1;
import static connect4.Connect4.player2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Connect4;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class CentralPageController implements Initializable {

    @FXML
    private Button initSession;
    @FXML
    private Label Name1;
    @FXML
    private Label name2;
    
    private Connect4 connect;
    @FXML
    private Button closeSession;
    @FXML
    private Button Play;
    @FXML
    private TextField winText;
    private boolean win = false;
    private int turno;
    @FXML
    private Button initSessionplayer;
    @FXML
    private Button closeSesion1player;
    @FXML
    private GridPane gameBoard;
    
    private static int NUM_ROWS = 7;
    private static int NUM_COL = 8;
    private int[][]juego;
    private VBox[][] layout;
    int jugador;
    final int EMPTY_NODE = 0;
    final int PLAYER_NODE = 1;
    final int PLAYER_NODE2 = 2;
    private Image circRojo = new Image(getClass().getResourceAsStream("CircRojo.png"));
    private Image circAzul = new Image(getClass().getResourceAsStream("CircAzul.png"));
    ImageView img = new ImageView(circRojo);
    ImageView img2 = new ImageView(circAzul);
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    @FXML
    private Label pointsP2;
    @FXML
    private Label pointsP1;
    @FXML
    private Button exitbutton;
    @FXML
    private MenuItem modifyButton;
    @FXML
    private Button rankButton;
    @FXML
    private Button Play1;
    @FXML
    private Button colorButton;
    @FXML
    private VBox background;
    
    public static BooleanProperty toggle = new SimpleBooleanProperty(false);
    @FXML
    private MenuItem initSecondPlayer;
    @FXML
    private MenuItem initFirstPlayer;
    @FXML
    private MenuItem Gráficas;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PlayerNames();
        try {
            buttonImage();
        } catch (FileNotFoundException ex) {
            System.out.println("Sa liao");
        }
        try{
            connect = Connect4.getSingletonConnect4();
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
        
    }    
    
    private void buttonImage() throws FileNotFoundException{
        FileInputStream input = new FileInputStream("src/icons/mas.png");
        Image image = new Image(input);
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(25);
        imgView.setFitWidth(29);
        initSessionplayer.setGraphic(imgView);
        
        FileInputStream input3 = new FileInputStream("src/icons/mas.png");
        Image image3 = new Image(input3);
        ImageView imgView3 = new ImageView(image3);
        imgView3.setFitHeight(25);
        imgView3.setFitWidth(29);
        initSession.setGraphic(imgView3);
        
        FileInputStream input2 = new FileInputStream("src/icons/apagar.png");
        Image img = new Image(input2);
        ImageView imgV = new ImageView(img);
        imgV.setFitHeight(25);
        imgV.setFitWidth(29);
        closeSesion1player.setGraphic(imgV);
        
        FileInputStream input4 = new FileInputStream("src/icons/apagar.png");
        Image img4 = new Image(input4);
        ImageView imgV4 = new ImageView(img4);
        imgV4.setFitHeight(25);
        imgV4.setFitWidth(29);
        closeSession.setGraphic(imgV4);
    }
    private Stage primaryStage;
    void initStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("FXMLDocument.fxml");
        //To change body of generated methods, choose Tools | Templates.
    }
    public void changeScreen2(){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.FXMLDocumentController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Iniciar Sesión");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show(); 
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void closeSesion(ActionEvent event) {

        exitScene(event);
        changeScreen2();
    }
    
    private Stage secondaryStage;
    void SecondStage(Stage stage){
        secondaryStage = stage;
        secondaryStage.setTitle("InitSession2.fxml");
    }
    
    public void changeScreen3(){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("InitSession2.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.InitSession2Controller>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Iniciar Sesión");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show(); 
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void exitScene(ActionEvent event){
        Node mynode = (Node) event.getSource();
        mynode.getScene().getWindow().hide();
    }

    @FXML
    private void iniciarSesion2ndPlayer(ActionEvent event) {
        exitScene(event);
        changeScreen3();
    }
    
    @FXML
    private void closeSesion2(ActionEvent event) {
        exitScene(event);
        changeScreen3(); 
    }
       public boolean win(int jugador){
        return winVertical(jugador) || winHorizontal(jugador) || winDiagonal(jugador);
        }
    
    public void PlayerNames(){
        Name1.setText(player1.getNickName());  
        name2.setText(player2.getNickName());     
    }
    
    public void setPuntos(){
        player1Name.setText(player1.getNickName());
        player2Name.setText(player2.getNickName());
        pointsP1.setText("Puntos: " + player1.getPoints());
        pointsP2.setText("Puntos: " + player2.getPoints());
    }
    
    @FXML
    private void Game(ActionEvent event) {
        resetGame(gameBoard);
        setPuntos();
        validatePlayer(event);
        gameBoard.setDisable(false);
        gamePlay();
    }
    
    
    private boolean winVertical(int jugador){
        boolean result = false;
        for(int i =0; i < (NUM_COL-3);i++){
            try {
                for (int j = 0; j < NUM_ROWS; j++) {
                    try {
                        result = result || (juego[i][j] == jugador && juego[i + 1][j] == jugador &&
                                juego[i + 2][j] == jugador && juego[i + 3][j] == jugador);
                    }catch (NullPointerException e){
                       // e.printStackTrace();
                    }
                }
            }catch (NullPointerException e){
               // e.printStackTrace();
            }
        }
        return result;
    }
    
    private boolean winHorizontal(int jugador){
        boolean result = false;
        for(int i =0; i < NUM_COL;i++){
            try {
                for (int j = 0; j < (NUM_ROWS-3); j++) {
                    try {
                        //ganar en columna
                        result = result || (juego[i][j] == jugador && juego[i][j + 1] == jugador &&
                                juego[i][j + 2] == jugador && juego[i][j + 3] == jugador);
                    }catch (NullPointerException e){}
                }
            }catch (NullPointerException e){}
        }
        return result;
    }
    
    private boolean winDiagonal(int jugador){
        boolean result = false;
        for(int i =0; i < (NUM_COL-3);i++){
            try {
                for (int j = 0; j < (NUM_ROWS-3); j++) {
                    try {
                        //diagonal principal
                        result = result || (juego[i][j] == jugador && juego[i+1][j + 1] == jugador &&
                                juego[i+2][j + 2] == jugador && juego[i+3][j + 3] == jugador);
                        //diagonal inversa
                        result = result || (juego[i][j+3] == jugador && juego[i+1][j + 2] == jugador &&
                                juego[i+2][j + 1] == jugador && juego[i+3][j] == jugador);
                    }catch (NullPointerException e){}
                }
            }catch (NullPointerException e){;}
        }
        return result;
    }


    @FXML
    private void closeSesion1stPlayer(ActionEvent event) {
        exitScene(event);
        changeScreen2();
    }
    
    private void validatePlayer(ActionEvent event){
        if(player1.getNickName().equals(player2.getNickName())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Diálogo de excepción");
            alert.setHeaderText("Falta 1 jugador");
            alert.setContentText("Introduzca al segundo jugador");
            Exception ex = new FileNotFoundException("Detalles del error");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();
            Label label = new Label("Excepción:");
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
            exitScene(event);
            changeScreen3();
        }
    }
    
    public int higherCol(int col){
        int row = -1;
        for(int i = 0; i<NUM_ROWS;i++){
            if(juego[col][i]==EMPTY_NODE){
                row = i;
            }
        }
        return row;
    }

 
    public void gamePlay() {
        this.juego = new int[NUM_COL][NUM_ROWS];
        this.layout = new VBox[NUM_COL][NUM_ROWS];
        turno = 2;
        for (int col = 0; col < NUM_COL; col++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                VBox vbox = new VBox();
                juego[col][row] = EMPTY_NODE;
                vbox.setAlignment(Pos.CENTER);
                int defcol = col;
                vbox.setOnMouseClicked((MouseEvent event) -> {
                    int rowdef = higherCol(defcol);
                    if (turno % 2 == 0) {
                        if (rowdef >= 0 && (circRojo != null || circAzul != null)) {
                            juego[defcol][rowdef] = PLAYER_NODE;

                            ImageView img = new ImageView(circAzul);
                            img.setFitHeight(vbox.getHeight());
                            img.setFitWidth(vbox.getWidth());
                            layout[defcol][rowdef].getChildren().add(img);

                            animation(img);
                            animation2(img);
                            turno++;

                            boolean victory = win(PLAYER_NODE);
                            if (victory) {
                                winText.setText("ENHORABUENA JUGADOR1.SI QUIERES JUGAR VUELVE A PRESIONAR EL BOTON");
                                gameBoard.setDisable(true);
                                setPuntos();
                                try{
                                    player1.plusPoints(50);
                                    connect.regiterRound(LocalDateTime.now(), player1, player2);
                                } catch(Connect4DAOException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else{
                        if (rowdef >= 0 && (circRojo != null || circAzul != null)) {
                            juego[defcol][rowdef] = PLAYER_NODE2;    
                            ImageView img = new ImageView(circRojo);
                            img.setFitHeight(vbox.getHeight());
                            img.setFitWidth(vbox.getWidth());
                            layout[defcol][rowdef].getChildren().add(img);

                            animation(img);
                            animation2(img);
                            turno++;

                            boolean victory = win(PLAYER_NODE2);
                            if (victory) {
                                gameBoard.setDisable(true);
                                winText.setText("ENHORABUENA JUGADOR2. SI QUIERES JUGAR VUELVE A PRESIONAR EL BOTON");
                                setPuntos();
                                try{
                                    player2.plusPoints(50);
                                    connect.regiterRound(LocalDateTime.now(), player2, player1);
                                } catch(Connect4DAOException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
                layout[col][row] = vbox;
                gameBoard.add(vbox, col, row);
            }
        }
    }
    public void animation(ImageView img){
        double currentYPosition = img.translateYProperty().getValue();
        int desiredDuration = 100;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(desiredDuration), img);
        translateTransition.setToY(currentYPosition+100);
        translateTransition.play();    
    }
    public void animation2(ImageView img){
        double currentYPosition = img.translateYProperty().getValue();
        int duration2 = 500;
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(duration2), img);
        translateTransition2.setToY(currentYPosition);
        translateTransition2.play();
    }

    
    private void resetGame(GridPane gridpane){
        gridpane.getChildren().retainAll(gridpane.getChildren().get(0));
        winText.setText("");
    }

    @FXML
    private void ModifyPerfil(ActionEvent event) {
        changeScreen4(event);
        exitScene(event);
    }
    public void changeScreen4(ActionEvent event){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ModificarPerfil.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.ModificarPerfilController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Modificar Perfil");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show();
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void changeScreen5(){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("rankings.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.RankingsController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Rankings");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show(); 
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void changeScreen6(){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("tablasPartidas.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.TablasPartidasController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("TablasPartidas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show();
            stage.setResizable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void ranked(ActionEvent event) {
        changeScreen5();
    }

    @FXML
    private void partidas(ActionEvent event) {
        changeScreen6();
    }

    @FXML
    private void changeColor(ActionEvent event) {
        background.getStyleClass().clear();
        if(toggle.get()){
            background.getStyleClass().add("bodybg2");
            toggle.set(!toggle.get());
        }
        else{
            background.getStyleClass().add("bodybg");
            toggle.set(!toggle.get());
        }
    }

    @FXML
    private void secondPlayer(ActionEvent event) {
        changeScreen3();
    }

    @FXML
    private void firstPlayer(ActionEvent event) {
        changeScreen2();
    }

    @FXML
    private void changeEstadisticas(ActionEvent event) {
        changeScreen6();
    }

    

}
