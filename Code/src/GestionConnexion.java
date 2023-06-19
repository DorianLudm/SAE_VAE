import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class GestionConnexion implements EventHandler<ActionEvent>{
    private ConnexionIHM appli;
    private ConnexionBD sql;
    
    public GestionConnexion(ConnexionIHM appli){
        this.appli = appli;
        try{
            this.sql = new ConnexionBD();
        }
        catch(ClassNotFoundException e){
            System.out.println("Lien avec la base de donnée non réussi! (Constructeur)");
        }
    }
    
    public void handle(ActionEvent e){
        Button button = (Button) (e.getSource());
        if(button.getText().equals("Démarrer!")){
            try{
                this.sql.connecter();
                this.appli.setConnexion(sql);
            }
            catch(SQLException sqlE){
                this.appli.erreurSQL();
            }
        }
        if(button.getText().equals("Connexion")){
            UtilisateurBD methode = new UtilisateurBD(this.sql); 
            try{
                Utilisateur user = methode.getUser(this.appli.getNomUt(), this.appli.getPassword());
                if(!user.equals(null)){
                    this.appli.mainPage(user);
                }
                else{
                    this.appli.erreurConnexion();
                }
            }
            catch(SQLException exception){
                this.appli.erreurSQL();
            }
            
        }
        if(button.getText().equals("Inscription")){
             UtilisateurBD methode = new UtilisateurBD(this.sql); 
            try{
                Utilisateur newUser = methode.insererUtilBD(this.appli.getNomUt(), this.appli.getPassword(), this.appli.getMail());
                this.appli.mainPage(newUser);
            }
            catch(SQLException exception){
                this.appli.erreurSQL();
            }
        }
    }
}