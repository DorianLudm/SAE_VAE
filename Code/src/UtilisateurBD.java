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

    public Utilisateur getUser(String mdp, String pseudo) throws SQLException{
        Utilisateur res = null;
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select utilisateur from UTILISATEUR where pseudoUt = " + pseudo + " and mdpUt = " + mdp);
        if(rs.next()){
            Integer id = rs.getInt(1);
            String pseudoUt = rs.getString(2);
            String mail = rs.getString(3);
            String mdpUt = rs.getString(4);
            String active = rs.getString(5);
            Integer idRole = rs.getInt(6);
            res = new Utilisateur(id, pseudoUt, mail, mdpUt, active, idRole);
        }
        return res;
    }

    public int maxNumUtilisateur() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(pseudo) from UTILISATEUR");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public Utilisateur insererUtilBD(String username, String mdp, String mail) throws SQLException{
        int id = maxNumUtilisateur() + 1;
        PreparedStatement s = this.laConnexion.prepareStatement("insert into UTILISATEUR values (?,?,?,?,?,?)");
        s.setInt(1, id);
        s.setString(2, username);
        s.setString(3, mail);
        s.setString(4, mdp);
        s.setString(5, "O");
        s.setInt(6, 2);
        s.executeUpdate();
        return getUser(username, mdp);
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
        s.setInt(6, util.getRole());
        s.executeUpdate();
    }
}