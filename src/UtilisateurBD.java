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

    public Utilisateur getUser(String pseudo, String mdp) throws SQLException{
        Utilisateur res = null;
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from UTILISATEUR natural join ROLE where pseudout = '"+ pseudo +"' and mdput = '"+ mdp +"'");
        if(rs.next()){
            Integer idRole = rs.getInt(1);
            Integer id = rs.getInt(2);
            String pseudoUt = rs.getString(3);
            String mail = rs.getString(4);
            String mdpUt = rs.getString(5);
            String active = rs.getString(6);
            String nomRole = rs.getString(7);
            
            res = new Utilisateur(id, pseudoUt, mail, mdpUt, active, new Role(idRole, nomRole));
        }
        return res;
    }

    public int maxNumUtilisateur() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select max(idUt) from UTILISATEUR");
        if(rs.next()){
			int maxNum = rs.getInt(1); //Valeur à la valeur de la première colonne de la dernière ligne éxécutée (ici à l'aide de rs.next())  
			return maxNum;
		}
		return -1;
    }

    public int insererUtilBD(String pseudo, String email, String mdp, String active, int role) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("insert into UTILISATEUR values (?,?,?,?,?,?)");
        s.setInt(1, maxNumUtilisateur()+1);
        s.setString(2, pseudo);
        s.setString(3, email);
        s.setString(4, mdp);
        s.setString(5, active);
        s.setInt(6, role);
        s.executeUpdate();

        Utilisateur util = new Utilisateur(maxNumUtilisateur()+1, pseudo, email, mdp, active, new Role(role, "Utilisateur"));
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