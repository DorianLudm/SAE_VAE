import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleBD{
    private ConnexionBD laConnexion;
    private Statement st;

    public RoleBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumRole() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idRole) from ROLE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }
    
    public int insererRoleBD(Role role) throws SQLException{
        role.setId(maxNumRole()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into ROLE values (?,?)");
        s.setInt(1, role.getId());
        s.setString(2, role.getNom());
        s.executeUpdate();
        return role.getId();
    }

    public void effacerRoleBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from ROLE where idRole = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public void majRoleBD(Role role) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update ROLE SET idRole=?, nomRole=?");
        s.setInt(1, role.getId());
        s.setString(2, role.getNom());
        s.executeUpdate();
    }
}