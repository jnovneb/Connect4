/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import static connect4.Connect4.player1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;
import static connect4.Connect4.player2;
import javafx.scene.image.Image;
/**
 *
 * @author JNovNeb
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button InitSes;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    private Connect4 connect4;
    
    String nombre = "PlayfulPaco";
    String email = "pgarcia@gmail.com";
    String contra = "passConecta4";
    LocalDate fecha = LocalDate.now();
    int puntos = 0;
    
    String nombre2 = "Paco1234";
    String email2 = "paquito@gmail.com";
    String contra2 = "paquito1234";
    LocalDate fecha2 = LocalDate.now();
    int puntos2 = 0;


    @FXML
    private Button ContraButton;
    String userName, password;
    @FXML
    private Button registerButton;
    @FXML
    private Button modificarPerfil;
    @FXML
    private Button estadísticasButton;
    
    public void prueba1(){
        try{
            Player result;
            result = connect4.registerPlayer(nombre, email, contra, fecha, puntos);
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
    }
    
    public void prueba2(){
        try{
            Player result2 = connect4.registerPlayer(nombre2, email2, contra2, fecha2, puntos2);
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try{
            connect4 = Connect4.getSingletonConnect4();
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
    }    
    private void changeScreen(ActionEvent event){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("CentralPage.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.CentralPageController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 555);
            scene.getStylesheets().add("background.css");
            stage.setScene(scene);
            stage.setTitle("Conecta4");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.setResizable(false);
            stage.show(); 
        }catch (IOException e){
            e.printStackTrace();
        }
    }
        private void exitScene(ActionEvent event){
            Node mynode = (Node) event.getSource();
            mynode.getScene().getWindow().hide();
        }
        
    private Stage primaryStage;
    void initStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("CentralPage.fxml");
         //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void handle(ActionEvent event) {
            userName = userNameText.getText();
            password = passwordText.getText();
            
            if (connect4.exitsNickName(userName)) {
                player1 = connect4.getPlayer(userName);
                player2 = connect4.getPlayer(userName);
                if (player1.getPassword().equals(password)) {
                    changeScreen(event);
                    exitScene(event);
                }
                else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Diálogo de excepción");
                alert.setHeaderText("Contraseña incorrecta");
                alert.setContentText("La contraseña introducida es incorrecta");
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
                GridPane.setVgrow(textArea,Priority.ALWAYS);
                GridPane.setHgrow(textArea,Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);
                alert.getDialogPane().setExpandableContent(expContent);
                alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Diálogo de excepción");
                alert.setHeaderText("Usuario no registrado");
                alert.setContentText("No se encuentra ese usuario. Por favor pruebe de nuevo");
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
                GridPane.setVgrow(textArea,Priority.ALWAYS);
                GridPane.setHgrow(textArea,Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);
                alert.getDialogPane().setExpandableContent(expContent);
                alert.showAndWait();
            }
        }
    @FXML
    private void RememberContraseña(ActionEvent event) {
        changeScreen2(event);

    }

    private void changeScreen2(ActionEvent event){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("RecordarContraseña.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.RecordarContraseñaController>getController().initStage2(stage);
            Scene scene = new Scene(root, 650, 400);
            stage.setScene(scene);
            stage.setTitle("Recordar Contraseña");
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
    private void changeRegister(ActionEvent event) {
        changeToRegist(event);
    }
    
    private void changeToRegist(ActionEvent event){
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.RegisterController>getController().initStage(stage);
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.setTitle("Registrarse");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.setResizable(false);
            stage.show(); 
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    
    private void changeScreen3(ActionEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ModificarPerfil.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.ModificarPerfilController>getController().initStage(stage);
            Scene scene = new Scene(root, 650, 400);
            stage.setScene(scene);
            stage.setTitle("ModificarPerfil");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show(); 
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void modPerfil(ActionEvent event) {
        changeScreen3(event);
    }

    @FXML
    private void estadisticas(ActionEvent event) {
        changeScreen4(event);
    }
    
    private void changeScreen4(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("tablasPartidas.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<connect4.TablasPartidasController>getController().initStage(stage);
            Scene scene = new Scene(root, 650, 400);
            stage.setScene(scene);
            stage.setTitle("Estadísticas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("CircAzul.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


