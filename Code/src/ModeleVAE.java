import java.util.ArrayList;
import java.util.List;
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
    /**La connexion */
    private ConnexionBD laConnexion;

    /**L'utilisateur de l'application */
    private Utilisateur user;

    private Statement st;

    /**L'application */
    private AppliVAE app;


    /**
     * Constructeur de base de la classe ModeleVAE
     * @param laConnexion
     * @param app
     */
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

    /**
     * Rentre les informations de l'utilisateur qui se connecte à l'application
     * @param user
     */
    public void setUser(Utilisateur user){
        this.user = user;
    }

    /**
     * @return l'utilisateur de l'application
     */
    public Utilisateur getUser(){
        return this.user;
    }


    /**
     * 
     * @param nombreDencheres
     * @param couleur
     * @return La liste des boutons représentant les enchères qui ont récemment été ajoutées
     * @throws SQLException
     */
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

                HBox boxTemps = new HBox();

                Label tempsRestant = new Label(finVe);

                tempsRestant.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
                tempsRestant.setTextFill(Color.web("#FFFFFF"));
                ImageView logoTemps = new ImageView(new Image("file:img/chrono.png", 30, 30, true, true));
                boxTemps.getChildren().addAll(tempsRestant, logoTemps);
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

    /**
     * @param ob
     * @throws SQLException
     * @return le montant de l'objet en chaîne de caractères
     */
    public String getMontantObjet(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select IFNULL(montantMax, 0) as montantMax, prixbase from OBJET natural join VENTE natural left join MONTANTENCH where idob="+String.valueOf(ob.getId()));
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

    /**
     * @param ob
     * @throws SQLException
     * @return la date de début de la vente en chaînes de caractères
     */
    public String getDebutVente(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select debutve from OBJET natural join VENTE where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){
            res = rs.getString(1);
        }
        return res;
    }

    /**
     * 
     * @param ob
     * @throws SQLException
     * @return la date de fin de la vente en chaînes de caractères
     */
    public String getFinVente(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select finve from OBJET natural join VENTE where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){
            res = rs.getString(1);
        }
        return res;
    }

    /**
     * 
     * @param ob
     * @throws SQLException
     * @return le prix de base en chaînes de caractères
     */
    public String getPrixBase(Objet ob) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select prixbase from OBJET natural join VENTE where idob="+String.valueOf(ob.getId()));
        String res = "";
        while(rs.next()){
            res = String.valueOf(rs.getInt(1));
        }
        return res;
    }


    /**
     * 
     * @param idv
     * @throws SQLException
     * @return l'objet grâce à l'identifiant de la vente
     */
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

    /**
     * 
     * @throws SQLException
     * @return la liste des catégories présentes dans la base de données
     */
    public List<String> getCategorie() throws SQLException{
        List<String> listeCat = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select nomcat from CATEGORIE");
        while(rs.next()){
            listeCat.add(rs.getString(1));
        }
        return listeCat;
    }


    public int maxNumVente() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select max(idve) from VENTE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int maxNumObjet() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select max(idob) from OBJET");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }


    public void ajoutVenteBD(Objet obj, double prixMin, double prixBase) throws SQLException {
        try {
            // Insérer l'objet dans la table OBJET
            PreparedStatement s = this.laConnexion.prepareStatement("INSERT INTO OBJET VALUES (?,?,?,?,?)");
            s.setInt(1, obj.getId());
            s.setString(2, obj.getNom());
            s.setString(3, obj.getDescription());
            s.setInt(4, obj.getUtilisateur().getId());
            s.setInt(5, obj.getCat().getId());
            s.executeUpdate();
            

            // Obtention de la date et l'heure actuelles
            LocalDateTime dateTime = LocalDateTime.now();

            // Formatage de la date et l'heure selon le format souhaité

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateTimeFormatted = dateTime.format(formatter);





            // Obtention de la date et l'heure actuelles
            // LocalDateTime dateTime2 = LocalDateTime.now();

            // Ajout de deux semaines à la date et l'heure actuelles
            LocalDateTime dateTimePlusTwoWeeks = dateTime.plusWeeks(2);

            // Formatage de la date et l'heure selon le format souhaité
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateTimeFormatted2 = dateTimePlusTwoWeeks.format(formatter2);





            Vente vente = new Vente(this.maxNumVente()+1, dateTimeFormatted, dateTimeFormatted2, prixMin, prixBase , obj, new Statut(2, "En cours"));

            java.sql.Timestamp dateDebut = java.sql.Timestamp.valueOf(vente.getDebutVente());
            java.sql.Timestamp dateFin = java.sql.Timestamp.valueOf(vente.getFinVente());


            // Insérer la vente dans la table VENTE
            PreparedStatement s2 = this.laConnexion.prepareStatement("insert into VENTE values (?,?,?,?,?,?,?)");
            s2.setInt(1, vente.getId());
            s2.setDouble(2, vente.getPrixBase());
            s2.setDouble(3, vente.getPrixMin());
            s2.setTimestamp(4, dateDebut);
            s2.setTimestamp(5, dateFin);

            s2.setInt(6, vente.getObjet().getId());
            s2.setInt(7, vente.getStatut().getId());
            s2.executeUpdate();
            

        } catch (SQLException e) {

        }
    }
    
    public List<Button> getMesVentes(int nombreDencheres, String couleur) throws SQLException{
        List<Button> res = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT nomob, IFNULL(finve, '0'), idve, IFNULL(montantMax, 0) as montantMax, prixbase FROM UTILISATEUR NATURAL LEFT JOIN OBJET NATURAL LEFT JOIN VENTE NATURAL LEFT JOIN MONTANTENCH WHERE idut="+String.valueOf(this.getUser().getId()) +" ORDER BY debutve DESC");
        int i = nombreDencheres;
        while(i > 0 && rs.next()){
            if (!rs.getString(2).equals("0")){    
                HBox hButton = new HBox();


                int montant = rs.getInt(4);
                if (montant == 0){
                    montant = rs.getInt(5);
                }
                
                String finVe = rs.getString(2);
                String nomOb = rs.getString(1);


                ImageView image = new ImageView(new Image("file:img/app_photo.png", 200,200, true, true));

                VBox vButton = new VBox();

                Label labelObjet = new Label(nomOb);
                labelObjet.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
                labelObjet.setTextFill(Color.web("#FFFFFF"));

                HBox boxPrix = new HBox();

                Label labelPrix = new Label(String.valueOf(montant));

                labelPrix.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
                labelPrix.setTextFill(Color.web("#FFFFFF"));
                ImageView logoPrix = new ImageView(new Image("file:img/euro.png", 30, 30, true, true));

                boxPrix.getChildren().addAll(labelPrix, logoPrix);


                HBox boxTemps = new HBox();

                Label tempsRestant = new Label(finVe);

                tempsRestant.setFont(Font.font("Ubuntu", FontWeight.BOLD, 18));
                tempsRestant.setTextFill(Color.web("#FFFFFF"));
                ImageView logoTemps = new ImageView(new Image("file:img/chrono.png", 30, 30, true, true));
                boxTemps.getChildren().addAll(tempsRestant, logoTemps);

                vButton.getChildren().addAll(labelObjet,boxPrix,boxTemps);
                vButton.setSpacing(10);
                vButton.setAlignment(Pos.CENTER);

                hButton.getChildren().addAll(image,vButton);
                hButton.setAlignment(Pos.CENTER);
                hButton.setSpacing(10);

                Button button = new Button();
                button.setGraphic(hButton);


                button.setStyle("-fx-background-color: #"+couleur+"; -fx-background-radius: 25px");
                button.setOnAction(new ControleurObjet(this,rs.getInt(3),this.app));

                button.setPrefSize(450, 220);
                res.add(button);
            }
 

            i -= 1;


            
        }
        return res;
    }


}