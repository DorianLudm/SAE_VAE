import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAccueil implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;

    public ControleurAccueil(AppliVAE vue){
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vueVAE.modeAccueil();
        this.vueVAE.majAffichage();
    }
}