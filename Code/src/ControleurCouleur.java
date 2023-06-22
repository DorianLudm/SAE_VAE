import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Controleur du clavier
 */
public class ControleurCouleur  implements EventHandler<ActionEvent> {

    /**
     * modèle du jeu
     */
    private ModeleVAE modele;
    /**
     * vue du jeu
     */
    private AppliVAE vueVAE;
    private ColorPicker changeCouleur;

    /**
     * @param modelePendu modèle du jeu
     * @param vuePendu vue du jeu
     */
    public ControleurCouleur(ModeleVAE modele, AppliVAE vueVAE, ColorPicker changeCouleur){
        this.modele = modele;
        this.vueVAE = vueVAE;
        this.changeCouleur= changeCouleur;

    }

   
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vueVAE.changeCouleur(changeCouleur);
        this.vueVAE.majAffichage();
        

        

}
}