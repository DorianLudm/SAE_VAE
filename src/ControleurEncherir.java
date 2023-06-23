import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurEncherir implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;
    private ModeleVAE modele;
    private double prixMini;
    private String prixPropose;
    private int idVe;

    public ControleurEncherir(AppliVAE vue, ModeleVAE modele, double prixMini, String prixPropose, int idVe){
        this.vueVAE = vue;
        this.modele = modele;
        this.prixMini = prixMini;
        this.prixPropose = prixPropose;
        this.idVe = idVe;
    }
    
    @Override
    public void handle(ActionEvent actionEvent){
        System.out.println(this.prixMini);
        System.out.println(this.prixPropose);
        if(Double.parseDouble(this.prixPropose) >= this.prixMini){
            try {
                this.modele.changerPrix(this.idVe, Double.parseDouble(this.prixPropose));
            } catch (SQLException e){}
        }
        else{
            this.vueVAE.prixTropBasPopup().showAndWait();
        }
    }
}