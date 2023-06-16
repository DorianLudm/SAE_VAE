import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class GestionConnexion implements EventHandler<ActionEvent>{
    private ConnexionIHM appli;
    private ConnexionMySQL sql;
    
    public GestionConnexion(ConnexionIHM appli){
        this.appli = appli;
        try{
            this.sql = new ConnexionMySQL();
        }
        catch(ClassNotFoundException e){
            System.out.println("Lien avec la base de donnée non réussi! (Constructeur)");
        }
    }
    
    public void handle(ActionEvent e){
        Button button = (Button) (e.getSource());
        boolean inboucle = false;
        if(button.getText().contains("Inscrivez")){
            this.appli.inscription();
            inboucle = true;
        }
        if(button.getText().equals("Démarrer!")){
            try{
                this.sql.connecter();
            }
            catch(SQLException sqlE){
                System.out.println("Lien avec la base de donnée non réussi! (Handle)");
            }
        }
        if(!inboucle){
            this.appli.connection();
            System.out.println(this.sql.isConnecte());
        }
    }
}