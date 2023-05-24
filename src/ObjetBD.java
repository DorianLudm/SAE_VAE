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
        Statement s = this.laConnexion.createStatement();
        ResultSet rs = s.executeQuery("select count(idob) from OBJET");
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
}
