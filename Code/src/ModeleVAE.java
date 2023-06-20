import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.layout.VBox;

public class ModeleVAE {
    private ConnexionBD laConnexion;
    private Utilisateur user;
    private Statement st;

    public ModeleVAE(ConnexionBD laConnexion, Utilisateur user){
        this.laConnexion = laConnexion;
        this.user = user;
    }

    public List<VBox> getEncheresLiked(){
        List<VBox> res = new ArrayList<>();
        for(Enchere elem: this.user.getEncheresLiked()){
            res.add(elem.toVBox());
        }
        return res;
    }

    public List<VBox> getEncheresRecentes(int nombreDencheres) throws SQLException{
        List<VBox> res = new ArrayList<>();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select montant, finVe, debutVe, imgPh, nomOb from ENCHERIR NATURAL JOIN VENTE NATURAL JOIN OBJET NATURAL JOIN PHOTO NATURAL JOIN STATUT where idSt = 2 order by debutVe ASC");
        int i = nombreDencheres;
        while(i > 0 && rs.next()){
            VBox boxElem = new VBox();

            boxElem.getChildren().addAll();
            res.add(boxElem);
            i -= 1;
        }
        return res;
    }
}