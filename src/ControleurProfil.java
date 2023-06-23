import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurProfil implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;

    public ControleurProfil(AppliVAE vue){
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vueVAE.modeProfil();
        this.vueVAE.majAffichage();
    }
}