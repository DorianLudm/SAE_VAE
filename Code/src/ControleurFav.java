import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurFav implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;
    private ModeleVAE modele;
    private int idv;

    public ControleurFav(AppliVAE vue, ModeleVAE modele, int idv){
        this.vueVAE = vue;
        this.modele = modele;
        this.idv = idv;
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {
        for (Vente vente : this.modele.getUser().getListeFav()) {
            if (vente.getId() == this.idv) {
                this.modele.getUser().addListeFav(vente);
            }
        }
    }
}
