import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatutBD {
    private ConnexionBD laConnexion;
    private Statement st;

    public StatutBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumStatut() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idst) from STATUT");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int insererStatutBD(Statut statut) throws SQLException{
        statut.setId(maxNumStatut()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into STATUT values (?,?)");
        s.setInt(1, statut.getId());
        s.setString(2, statut.getNom());
        s.executeUpdate();
        return statut.getId();
    }

    public void effacerStatutBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from STATUT SET where idst=?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public Statut getStatutBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from STATUT where idst="+id.toString());
        while (rs.next()){
            int idst = rs.getInt(1);
            String nomst = rs.getString(2);

            Statut statut = new Statut(idst, nomst);
            return statut;
        }
        return null;
    }
}
