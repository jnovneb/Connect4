/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;
import static connect4.Connect4.nuevo;
import static java.time.temporal.ChronoUnit.DAYS;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class ModificarPerfilController implements Initializable {

    @FXML
    private TextField nameText;
    @FXML
    private TextField newPass;
    @FXML
    private TextField newMail;
    @FXML
    private DatePicker newDate;
    @FXML
    private Button Confirmed;
    private Connect4 connect4;
    @FXML
    private Button avatar4;
    @FXML
    private Button avatar3;
    @FXML
    private Button avatar2;
    @FXML
    private Button avatar1;
    

    /**
     * Initializes the controller class.
     */
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      try {
            // TODO
            newDate.setValue(LocalDate.now());
            connect4 = model.Connect4.getSingletonConnect4();
        } catch (Connect4DAOException ex) {
            ex.printStackTrace();
        }
    }  
    
        private Stage primaryStage;
    void initStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("ModificarPerfil.fxml");
    }

    @FXML
    private void modify(ActionEvent event) {
        String user = nameText.getText();
        String pass = newPass.getText();
        String mail = newMail.getText();
        LocalDate date = newDate.getValue();
        boolean def = true;
        
        if(connect4.exitsNickName(user)){
            Player nuevo = connect4.getPlayer(user);
            nameText.setText("Rellene su nueva información");
        }else{
            alert();
            def = false;
        }
        if(nuevo.checkPassword(pass) == false){
            newPass.setText("Escriba otra contraseña");
            def = false;
        }
        if(nuevo.checkEmail(mail) == false){
            newMail.setText("Escriba un correo válido");
            def = false;
        }
        if(checkDate(date) == false){
            def = false;
            System.out.println("PEGI +12");
        }
        
        if(nuevo.checkPassword(pass) == true && nuevo.checkEmail(mail) == true && checkDate(date) == true){
            try{
               Player mod = connect4.registerPlayer(user, mail, pass, date, 0);
                exitScene(event);
                
            }
            catch(Connect4DAOException e){
                e.printStackTrace();
            }
        } 
        
    }
    private void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Diálogo de excepción");
                alert.setHeaderText("Usuario incorrecto");
                alert.setContentText("El usuario establecido no existe. Pruebe otra vez.");
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
    private boolean checkDate(LocalDate date) {
        boolean fecha;
        LocalDate now = LocalDate.now();
        long days = DAYS.between(date, now);
        if (days < 4380) {
            fecha = false;
        } else {
            fecha = true;
        }
        return fecha;
    }
      private void exitScene(ActionEvent event) {
        Node mynode = (Node) event.getSource();
        mynode.getScene().getWindow().hide();
    }

    /*private void changeAvatar4(ActionEvent event) throws FileNotFoundException {
        String user = nameText.getText();
        FileInputStream input = new FileInputStream("src/connect4/avatar4.png");
        avatar = new Image(input);
        if(connect4.exitsNickName(user)){
            Player nuevo = connect4.getPlayer(user);
        }else{
            alert();
            nameText.setText("Por favor introduzca un usuario válido");
        }
    }

    private void changeAvatar3(ActionEvent event) throws FileNotFoundException {
        String user = nameText.getText();
        FileInputStream input = new FileInputStream("src/connect4/avatar3.png");
        avatar = new Image(input);
        if(connect4.exitsNickName(user)){
            Player nuevo = connect4.getPlayer(user);
        }else{
            alert();
            nameText.setText("Por favor introduzca un usuario válido");
        }
    }

    private void changeAvatar2(ActionEvent event) throws FileNotFoundException {
        String user = nameText.getText();
        FileInputStream input = new FileInputStream("src/connect4/avatar2.png");
        avatar = new Image(input);
        if(connect4.exitsNickName(user)){
            Player nuevo = connect4.getPlayer(user);
        }else{
            alert();
            nameText.setText("Por favor introduzca un usuario válido");
        }

    }

    private void changeAvatar1(ActionEvent event) throws FileNotFoundException {
        String user = nameText.getText();
        FileInputStream input = new FileInputStream("src/connect4/avatar1.png");
        avatar = new Image(input);
        if(connect4.exitsNickName(user)){
            Player nuevo = connect4.getPlayer(user);
        }else{
            alert();
            nameText.setText("Por favor introduzca un usuario válido");
        }
    }
*/

       
}
