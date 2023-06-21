import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class GestionConnexion implements EventHandler<ActionEvent>{
    private ConnexionIHM appli;
    private ConnexionBD sql;
    
    public GestionConnexion(ConnexionIHM appli, ConnexionBD connexion){
        this.appli = appli;
        this.sql = connexion;
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
                }
                else{
                    this.appli.erreurConnexion().showAndWait();
                }
            }
            catch(NullPointerException excption1){
                System.out.println(excption1.getMessage());
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
                Utilisateur newUser = methode.insererUtilBD(this.appli.getNomUt(), this.appli.getPassword(), this.appli.getMail());
                this.appli.mainPage(newUser);
            }
            catch(ChampVideException exception1){
                this.appli.champVidePopup().showAndWait();
            }
            catch(SQLException exception){
                this.appli.erreurSQL().showAndWait();
            }
        }
    }
}