import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ObjetBD {
    private ConnexionBD laConnexion;
    private Statement st;

    public ObjetBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumObjet() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idob) from OBJET");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int insererObjetBD(Objet obj) throws SQLException{
        obj.setId(maxNumObjet()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into OBJET values (?,?,?,?,?)");
        s.setInt(1, obj.getId());
        s.setString(2, obj.getNom());
        s.setString(3, obj.getDescription());
        s.setInt(4, obj.getUtilisateur().getId());
        s.setInt(5, obj.getCat().getId());
        s.executeUpdate();
        return obj.getId();
    }

    public void effacerObjetBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from OBJET where idob = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public void majObjetBD(Objet obj) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update OBJET SET idob=?, nomob=?, descriptionob=?, idut=?, idcat=?");
        s.setInt(1, obj.getId());
        s.setString(2, obj.getNom());
        s.setString(3, obj.getDescription());
        s.setInt(4, obj.getUtilisateur().getId());
        s.setInt(5, obj.getCat().getId());
        s.executeUpdate();
    }

    public Objet getObjetBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from OBJET natural join CATEGORIE natural join UTILISATEUR natural join ROLE where idob="+ id.toString());
        while (rs.next()){
            int idRole = rs.getInt(1);
            int idUt = rs.getInt(2);
            int idCat = rs.getInt(3);
            int idOb = rs.getInt(4);
            String nomOb = rs.getString(5);
            String descriptionOb = rs.getString(6);
            String nomCat = rs.getString(7);
            String pseudoUt = rs.getString(8);
            String emailUt = rs.getString(9);
            String mdpUt = rs.getString(10);
            String activeUt = rs.getString(11);
            String nomRole = rs.getString(12);

            Role role = new Role(idRole, nomRole);
            Categorie cat = new Categorie(idCat, nomCat);
            Utilisateur util = new Utilisateur(idUt, pseudoUt, emailUt, mdpUt, activeUt, role);
            Objet objet = new Objet(idOb, nomOb, descriptionOb, util, cat);
            return objet;
        }
        return null;
    }
}
