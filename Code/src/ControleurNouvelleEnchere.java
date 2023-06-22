import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurNouvelleEnchere implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;

    public ControleurNouvelleEnchere(AppliVAE vue){
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            this.vueVAE.modeAjout();
        } catch (SQLException e) {
        }
        this.vueVAE.majAffichage();
    }
}
