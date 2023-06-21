import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleBD{
    private ConnexionBD laConnexion;
    private Statement st;

    /**
     * Constructeur de base de la classe RoleBD
     * @param laConnexion
     */
    public RoleBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    /**
     * 
     * @return le nombre de rôles présents dans la base de données
     * @throws SQLException
     */
    public int maxNumRole() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idRole) from ROLE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    /**
     * 
     * @param role
     * @return l'identifiant du rôle que l'on vient d'ajouter dans la base de données
     * @throws SQLException
     */
    public int insererRoleBD(Role role) throws SQLException{
        role.setId(maxNumRole()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into ROLE values (?,?)");
        s.setInt(1, role.getId());
        s.setString(2, role.getNom());
        s.executeUpdate();
        return role.getId();
    }

    /**
     * Permet d'effacer un rôle de la base de données
     * @param id
     * @throws SQLException
     */
    public void effacerRoleBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from ROLE where idRole = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    /**
     * Permet de mettre à jour un rôle de la base de données
     * @param role
     * @throws SQLException
     */
    public void majRoleBD(Role role) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update ROLE SET idRole=?, nomRole=?");
        s.setInt(1, role.getId());
        s.setString(2, role.getNom());
        s.executeUpdate();
    }

    public Role getRoleBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from ROLE where idrole=" + id.toString());
        while (rs.next()){
            int idRole = rs.getInt(1);
            String nomRole = rs.getString(2);

            Role role = new Role(idRole, nomRole);
            return role;
        }
        return null;
    }
}