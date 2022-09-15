/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;
import model.Connect4;

/**
 * FXML Controller class
 *
 * @author JNovNeb
 */
public class RecordarContraseñaController implements Initializable {

    @FXML
    private TextField UserName;
    @FXML
    private TextField correito;
    
    private Connect4 connect4;
    @FXML
    private Button Ok;
    @FXML
    private Text contraseñaRememb;
    @FXML
    private Text codigo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            connect4 = Connect4.getSingletonConnect4();
        } catch (Connect4DAOException ex) {
            ex.printStackTrace();
        }
    }    

    private Stage primaryStage;
    void initStage2(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("RecordarContraseña.fxml");
    }
    public String generateSecurityCode() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();

    return generatedString;
}

    @FXML
    private void RememberContraseña(ActionEvent event) {
        String nombreusuario = UserName.getText();
        String correo = correito.getText();
        
        if (connect4.exitsNickName(nombreusuario)) {
            Player player2 = connect4.getPlayer(nombreusuario);
            if (player2.checkEmail(correo)){
                codigo.setText("Su código de seguridad es: " + generateSecurityCode());
                contraseñaRememb.setText("Su contraseña es: " + player2.getPassword());
            }
            else{
                contraseñaRememb.setText("El correo que ha introducido no es válido");
            }
        }
        else{
            contraseñaRememb.setText("No existe ningún usuario registrado con ese nombre");
        } 
    }

}
