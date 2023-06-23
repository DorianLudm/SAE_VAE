import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAccueil implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;

    public ControleurAccueil(AppliVAE vue){
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent){
        try{
           this.vueVAE.modeAccueil(); 
           this.vueVAE.majAffichage();
        }
        catch(SQLException e){
            
        }
        this.vueVAE.majAffichage();
    }
}