import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Contrôleur à activer lorsque l'on clique sur le bouton info
 */
public class ControleurPopUp implements EventHandler<ActionEvent> {

    private AppliVAE vueVAE;

    /**
     * @param p vue du jeu
     */
    public ControleurPopUp(AppliVAE vueVAE) {
        this.vueVAE = vueVAE;
    }

    /**
     * L'action consiste à afficher une fenêtre popup précisant les règles du jeu.
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vueVAE.popUpIndisponible().showAndWait();
    }
}