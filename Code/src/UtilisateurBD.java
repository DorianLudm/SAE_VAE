import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilisateurBD{
    private ConnexionBD laConnexion;
    private Statement st;
    
    public UtilisateurBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumUtilisateur() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(pseudo) from UTILISATEUR");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int insererUtilBD(Utilisateur util) throws SQLException{
        util.setId(maxNumUtilisateur()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into UTILISATEUR values (?,?,?,?,?,?)");
        s.setInt(1, util.getId());
        s.setString(2, util.getPseudo());
        s.setString(3, util.getEmail());
        s.setString(4, util.getMDP());
        s.setString(5, util.getActive());
        s.setInt(6, util.getRole().getId());
        s.executeUpdate();
        return util.getId();
    }

    public void effacerUtilBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from UTILISATEUR where idUt = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public void majUtilBD(Utilisateur util) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update UTILISATEUR SET idut=?, pseudout=?, emailut=?, mdput=?, activeut=?, idrole=?");
        s.setInt(1, util.getId());
        s.setString(2, util.getPseudo());
        s.setString(3, util.getEmail());
        s.setString(4, util.getMDP());
        s.setString(5, util.getActive());
        s.setInt(6, util.getRole().getId());
        s.executeUpdate();
    }
}