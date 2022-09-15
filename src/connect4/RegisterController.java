/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Connect4;
import static connect4.Connect4.nuevo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nick;
    @FXML
    private TextField contras;
    @FXML
    private TextField correo;
    @FXML
    private DatePicker BirthDate;
    @FXML
    private Button confirmButton;
    private Connect4 connect4;
    @FXML
    private Button avatar1;
    @FXML
    private Button avatar2;
    @FXML
    private Button avatar3;
    @FXML
    private Button avatar4;
    Image avatar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      
        try{
            connect4 = model.Connect4.getSingletonConnect4();
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
    }    
    private Stage primaryStage;
    public void initStage(Stage stage) {
        
        primaryStage = stage;
        primaryStage.setTitle("Register.fxml");         
    } 
    

    @FXML
    private void Date(ActionEvent event) {
        LocalDate date = BirthDate.getValue();
        System.out.println(date);
    }
    private boolean passCheck(String pass){
        int numCount = 0;
        int capCount = 0;
        int minCount = 0;
        int specCount = 0;
        boolean res = true;
        for(int i = 0; i < pass.length(); i++){
            if(pass.charAt(i) >= 48 && pass.charAt(i) <= 57){ numCount++;}
            if(pass.charAt(i) >= 65 && pass.charAt(i) <= 90){ capCount++;}
            if(pass.charAt(i) >= 97 && pass.charAt(i) <= 122){ minCount++;}
            if(pass.charAt(i) >= 33 && pass.charAt(i) <= 45 || pass.charAt(i) == 64
                    || pass.charAt(i) == 61){ specCount++;}
        }
        if( numCount == 0 || capCount == 0 || minCount == 0 || specCount == 0){res = false;}
        else{res = true;}
        return res;
    }
    private boolean checkEmail(String mail){
        String subStr = "@gmail.com";
        String subStr2 = "@hotmail.com";
        boolean contains = mail.contains(subStr) || mail.contains(subStr2);
        return contains;
    }
    private boolean checkDate(LocalDate date){
        boolean fecha;
        LocalDate now = LocalDate.now();
        long days = DAYS.between(date, now);
        if(days < 4380) {
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


    @FXML
    private void registrar(ActionEvent event) throws FileNotFoundException{
        boolean def = true;
        String name = nick.getText();
        String contra = contras.getText();
        String mail = correo.getText();
        LocalDate fecha = BirthDate.getValue();
        
        
        if(name.length() < 6 || name.length() > 15){
            nick.setPromptText("El nombre debe tener entre 6 y 15 caracteres");
            nick.setStyle("-fx-text-fill: red;");
            def = false;
        }
        for(int i = 0; i < name.length(); i++){
            if(name.charAt(i) == ' '){
                nick.setPromptText("El nombre no puede tener espacios");
                //nick.setStyle("-fx-text-fill: red; ");
                def = false;

            }
        }
        
        if(contra.length() < 8 || contra.length() > 20){
            contras.setPromptText("La long de la contraseña debe ser entre 8 y 15 caracteres");
            //contras.setStyle("-fx-text-fill: red;");
            def = false;

        }
        if(passCheck(contra) == false){ 
            contras.setPromptText("Incluir mayus, minus, números y caract especiales");
            //contras.setStyle("-fx-text-fill: red;");
            def = false;
        }
        if(checkEmail(mail) == false){ 
            correo.setPromptText("El correo introducido no es válido");
            //correo.setStyle("-fx-text-fill: red;");
            def = false;
        }
        if(checkDate(fecha) == false){ 
            System.out.println("PEGI +12");
            BirthDate.setPromptText("Juego PEGI +12");
            def = false;
        }
        if(def){
            try{  
            FileInputStream file = new FileInputStream("src/connect4/default.png");
            avatar = new Image(file);
            nuevo = connect4.registerPlayer(name, mail, contra,avatar, fecha, 0);
            exitScene(event);
        } catch(Connect4DAOException e){
            e.printStackTrace();
        }
  
        }
    }


   @FXML
    private void changeavatar1(ActionEvent event) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/connect4/avatar1.png");
        avatar = new Image(input);
    }

    @FXML
    private void changeavatar2(ActionEvent event) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/connect4/avatar2.png");
        avatar = new Image(input);
    }

    @FXML
    private void changeavatar3(ActionEvent event) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/connect4/avatar3.png");
        avatar = new Image(input);
    }

    @FXML
    private void changeavatar4(ActionEvent event) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/connect4/avatar4.png");
        avatar = new Image(input);
    }

}
    
