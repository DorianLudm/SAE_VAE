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

public class ModeleVAE {
    private ConnexionBD laConnexion;
    private Utilisateur user;
    private Statement st;

    public ModeleVAE(ConnexionBD laConnexion, Utilisateur user){
        this.laConnexion = laConnexion;
        this.user = user;
    }

    // public List<VBox> getEncheresLiked(){
    //     List<VBox> res = new ArrayList<>();
    //     for(Enchere elem: this.user.getEncheresLiked()){
    //         res.add(elem.toVBox());
    //     }
    //     return res;
    // }

    public List<VBox> getEncheresRecentes(int nombreDencheres) throws SQLException{
        List<VBox> res = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select montant, finVe, imgPh, nomOb from ENCHERIR NATURAL JOIN VENTE NATURAL JOIN OBJET NATURAL JOIN PHOTO NATURAL JOIN STATUT where idSt = 2 order by debutVe ASC");
        int i = nombreDencheres;
        while(i > 0 && rs.next()){
            VBox boxElem = new VBox();
            int montant = rs.getInt(1);
            String finVe = rs.getString(2);
            String imgPh = rs.getString(3);
            String nomOb = rs.getString(4);
            ImageView image = new ImageView(new Image(imgPh));
            Label labelObjet = new Label(nomOb);
            Label labelPrix = new Label(String.valueOf(montant));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(finVe, formatter);
            LocalDateTime dateActuel = LocalDateTime.now();
            Duration tempsDiff = Duration.between((Temporal) dateActuel, dateTime);
            Label tempsRestant = new Label(tempsDiff.toString());
            boxElem.getChildren().addAll(image, labelObjet, labelPrix, tempsRestant);
            res.add(boxElem);
            i -= 1;
        }
        return res;
    }

    public List<VBox> getEncheresActive(int nombreDencheres) throws SQLException{
        List<VBox> res = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select montant, finVe, debutVe, imgPh, nomOb, count(montant) nbBid from ENCHERIR NATURAL JOIN VENTE NATURAL JOIN OBJET NATURAL JOIN PHOTO NATURAL JOIN STATUT where idSt = 2 group by idVe order by nbBid DESC");
        int i = nombreDencheres;
        while(i > 0 && rs.next()){
            VBox boxElem = new VBox();
            int montant = rs.getInt(1);
            String finVe = rs.getString(2);
            String imgPh = rs.getString(3);
            String nomOb = rs.getString(4);
            ImageView image = new ImageView(new Image(imgPh));
            Label labelObjet = new Label(nomOb);
            Label labelPrix = new Label(String.valueOf(montant));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(finVe, formatter);
            LocalDateTime dateActuel = LocalDateTime.now();
            Duration tempsDiff = Duration.between((Temporal) dateActuel, dateTime);
            Label tempsRestant = new Label(tempsDiff.toString());
            boxElem.getChildren().addAll(image, labelObjet, labelPrix, tempsRestant);
            res.add(boxElem);
            i -= 1;
        }
        return res;
    }
}