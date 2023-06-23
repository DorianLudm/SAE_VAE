import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class GestionSwapConnexion implements EventHandler<ActionEvent>{
    private ConnexionIHM appli;
    
    public GestionSwapConnexion(ConnexionIHM appli){
        this.appli = appli;
    }
    
    public void handle(ActionEvent e){
        Button button = (Button) (e.getSource());
        if(button.getText().contains("Inscrivez")){
            this.appli.inscription();
        }
        else{
            this.appli.connection();
        }
    }
}