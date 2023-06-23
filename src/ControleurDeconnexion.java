import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurDeconnexion implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;

    public ControleurDeconnexion(AppliVAE vue){
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vueVAE.afficheFenetreConexion();
        this.vueVAE.majAffichage();
    }
}
