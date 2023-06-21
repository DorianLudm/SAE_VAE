import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class GestionConnexion implements EventHandler<ActionEvent>{
    private ConnexionIHM appli;
    private AppliVAE vueVAE;
    private ConnexionBD sql;
    
    public GestionConnexion(ConnexionIHM appli, ConnexionBD connexion, AppliVAE vueVAE){
        this.appli = appli;
        this.sql = connexion;
        this.vueVAE = vueVAE;

    }
    
    public void handle(ActionEvent e){
        Button button = (Button) (e.getSource());
        boolean gotInLoop = false;
        if(button.getText().equals("Démarrer!")){

            gotInLoop = true;
            this.appli.connection();
                
        }
        if(!gotInLoop && button.getText().equals("Connexion")){
            gotInLoop = true;
            UtilisateurBD methode = new UtilisateurBD(this.sql); 
            try{
                Utilisateur user = methode.getUser(this.appli.getNomUt(), this.appli.getPassword());
                if(!user.equals(null)){
                    this.appli.mainPage(user);
                    this.vueVAE.afficheApp();
                }
                else{
                    this.appli.clearMdp();
                    this.appli.erreurConnexion().showAndWait();
                }
            }
            catch(NullPointerException excption1){
                System.out.println(excption1.getMessage());
                this.appli.clearMdp();
                this.appli.erreurConnexion().showAndWait();
            }
            catch(SQLException exception2){
                System.out.println(exception2.getMessage());
                this.appli.erreurSQL().showAndWait();
            }
            
        }
        if(!gotInLoop && button.getText().equals("Inscription")){
            UtilisateurBD methode = new UtilisateurBD(this.sql); 
            try{
                int idNewUser = methode.insererUtilBD(this.appli.getNomUt(), this.appli.getMail(), this.appli.getPassword(), "O", 2);
                Utilisateur newUser = methode.getUser(this.appli.getNomUt(), this.appli.getPassword());
                this.appli.mainPage(newUser);
                this.vueVAE.afficheApp();


            }
            catch(ChampVideException exception1){
                this.appli.champVidePopup().showAndWait();
            }
            catch(UtilisateurExistant exception2){
                this.appli.UtilisateurExistantPopup().showAndWait();
            }
            catch(SQLException exception){
                this.appli.erreurSQL().showAndWait();
            }

        }
    }
}