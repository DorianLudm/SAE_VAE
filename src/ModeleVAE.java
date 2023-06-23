import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ModeleVAE {
    private ConnexionBD laConnexion;
    private Utilisateur user;
    private Statement st;
    private AppliVAE app;


    public ModeleVAE(ConnexionBD laConnexion, AppliVAE app){
        // try{
        //     this.laConnexion = new ConnexionBD();
        //     this.laConnexion.connecter();
        // }
        // catch(SQLException sqlE){
        //     System.out.println("Erreur lors du chargement de la base");
        // }
        // catch(ClassNotFoundException sqlE){
        //     System.out.println("Erreur lors du chargement de la base");
        // }
        this.laConnexion = laConnexion;
        this.app = app;
    }

    public void setUser(Utilisateur user){
        this.user = user;
    }

    public Utilisateur getUser(){
        return this.user;
    }

    // public List<VBox> getEncheresLiked(){
    //     List<VBox> res = new ArrayList<>();
    //     for(Enchere elem: this.user.getEncheresLiked()){
    //         res.add(elem.toVBox());
    //     }
    //     return res;
    // }



    public List<Button> getEncheresRecentes(int nombreDencheres, String couleur) throws SQLException{
        List<Button> res = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT nomob, IFNULL(finve, '0'), idve, IFNULL(montantMax, 0) as montantMax, prixbase FROM OBJET NATURAL LEFT JOIN VENTE NATURAL LEFT JOIN MONTANTENCH ORDER BY debutve DESC");
        int i = nombreDencheres;
        while(i > 0 && rs.next()){
            if (!rs.getString(2).equals("0")){    
                VBox vButton = new VBox();


                int montant = rs.getInt(4);
                if (montant == 0){
                    montant = rs.getInt(5);
                }
                
                    
                
                

                String finVe = rs.getString(2);
                String nomOb = rs.getString(1);


                ImageView image = new ImageView(new Image("file:img/app_photo.png", 200,200, true, true));
                
                
            
                Label labelObjet = new Label(nomOb);
                labelObjet.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
                labelObjet.setTextFill(Color.web("#FFFFFF"));

                HBox boxPrix = new HBox();

                Label labelPrix = new Label(String.valueOf(montant));

                labelPrix.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
                labelPrix.setTextFill(Color.web("#FFFFFF"));
                ImageView logoPrix = new ImageView(new Image("file:img/euro.png", 30, 30, true, true));

                boxPrix.getChildren().addAll(labelPrix, logoPrix);
                boxPrix.setSpacing(10);
                boxPrix.setAlignment(Pos.CENTER_LEFT);


                HBox boxTemps = new HBox();
                Label tempsRestant = new Label(finVe);

                tempsRestant.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
                tempsRestant.setTextFill(Color.web("#FFFFFF"));
                ImageView logoTemps = new ImageView(new Image("file:img/chrono.png", 30, 30, true, true));
                boxTemps.getChildren().addAll(tempsRestant, logoTemps);
                boxTemps.setSpacing(10);
                boxTemps.setAlignment(Pos.CENTER_LEFT);
                vButton.getChildren().addAll(image,labelObjet,boxPrix,boxTemps);
                vButton.setSpacing(10);

                Button button = new Button();
                button.setGraphic(vButton);


                button.setStyle("-fx-background-color: #"+couleur+"; -fx-background-radius: 25px");
                button.setOnAction(new ControleurObjet(this,rs.getInt(3),this.app));

                button.setPrefSize(270, 320);
                res.add(button);
            }
 

            i -= 1;


            
        }
        return res;
    }








    public String getMontantObjet(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select montantMax, prixbase from OBJET natural join VENTE natural join MONTANTENCH where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){    
            int montant = rs.getInt(1);
            if (montant == 0){
                montant = rs.getInt(2);
            }
            res = String.valueOf(montant);
        }
        return res;

    }



    public String getDebutVente(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select debutve from OBJET natural join VENTE where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){
            res = rs.getString(1);
        }
        return res;
    }

    public String getFinVente(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select finve from OBJET natural join VENTE where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){
            res = rs.getString(1);
        }
        return res;
    }

    public String getPrixBase(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select prixbase from OBJET natural join VENTE where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){
            res = String.valueOf(rs.getInt(1));
        }
        return res;
    }



    public Objet getObjet(int idv) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select idut,pseudout,emailut,mdput,activeut,idrole,nomrole,idob,nomob,descriptionob,idcat,nomcat from UTILISATEUR natural join ROLE natural join OBJET natural join VENTE natural join CATEGORIE where idve="+String.valueOf(idv));
        Objet obj = null;
        while(rs.next()){
            Role role = new Role(rs.getInt(6), rs.getString(7));
            Utilisateur user = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), role);
            Categorie cat = new Categorie(rs.getInt(11), rs.getString(12));
            obj = new Objet(rs.getInt(8), rs.getString(9), rs.getString(10), user,cat);
        }
        return obj;
    }

    

    public List<String> getCategorie() throws SQLException{
        List<String> listeCat = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select nomcat from CATEGORIE");
        while(rs.next()){
            listeCat.add(rs.getString(1));
        }
        return listeCat;
    }

    public void changerPrix(int idVe, double prixPropose) throws SQLException{
        PreparedStatement ps = this.laConnexion.prepareStatement("INSERT INTO ENCHERIR VALUES (?, ?, ?, ?)");
		ps.setInt(1, this.user.getId());
		ps.setInt(2, idVe);
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
        String timeFormated = time.format(formatter);
        java.sql.Timestamp dateFormat = java.sql.Timestamp.valueOf(timeFormated);
		ps.setTimestamp(3, dateFormat);
        ps.setDouble(4, prixPropose);
        ps.execute();
    }
}