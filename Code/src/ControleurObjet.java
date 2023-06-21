import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurObjet implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;

    public ControleurObjet(AppliVAE vue){
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vueVAE.modeObjets();
        this.vueVAE.majAffichage();
    }
}
