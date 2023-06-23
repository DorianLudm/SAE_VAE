import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAjoutObjet implements EventHandler<ActionEvent> {
    private AppliVAE vueVAE;
    private ModeleVAE modele;

    public ControleurAjoutObjet(AppliVAE vue, ModeleVAE modele){
        this.vueVAE = vue;
        this.modele = modele;
    }
    
    @Override
    public void handle(ActionEvent actionEvent){
        
        try{
            int idcat = 1;
            if(this.vueVAE.getCategorie().equals("Vêtement")){
                idcat = 1;
            }
            else if(this.vueVAE.getCategorie().equals("Ustensile Cuisine")){
                idcat = 2;
            }
            else if(this.vueVAE.getCategorie().equals("Meuble")){
                idcat = 3;
            }
            else if(this.vueVAE.getCategorie().equals("Outil")){
                idcat = 4;
            }
            else if(this.vueVAE.getCategorie().equals("Multimédia")){
                idcat = 5;
            }

            Objet ob = new Objet(this.modele.maxNumObjet()+1, this.vueVAE.getNom(), this.vueVAE.getDescription(), this.modele.getUser(), new Categorie(idcat, this.vueVAE.getCategorie()));

            int prixMin = Integer.parseInt(this.vueVAE.getPrixVenteMinimum());

            int prixBase = Integer.parseInt(this.vueVAE.getPrixDeDepart());

            this.modele.ajoutVenteBD(ob,prixMin,prixBase);
            this.vueVAE.modeAccueil();
            this.vueVAE.majAffichage();


        }
        catch(SQLException e){

        }
        
    }
}
