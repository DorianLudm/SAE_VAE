import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurObjet implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;
    private int idv;
    private ModeleVAE modele;

    public ControleurObjet(ModeleVAE mode, int idv, AppliVAE vue){
        this.modele = mode;
        this.idv = idv;
        this.vueVAE = vue;
    }
    
    @Override
    public void handle(ActionEvent actionEvent){
        
        try{
            Objet ob = this.modele.getObjet(this.idv);
            System.out.println(ob.getNom());
        }
        catch(SQLException e){

        }
        this.vueVAE.modeObjets(ob.getNom(),ob.getDescription());
        this.vueVAE.majAffichage();
    }
}
