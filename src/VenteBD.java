import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VenteBD {
    private ConnexionBD laConnexion;
    private Statement st;
    
    public VenteBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumVente() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idve) from VENTE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int insererVenteBD(Vente vente) throws SQLException{
        vente.setId(maxNumVente()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into VENTE values (?,?,?,?,?,?,?)");
        s.setInt(1, vente.getId());
        s.setDouble(2, vente.getPrixBase());
        s.setDouble(3, vente.getPrixMin());
        s.setString(4, vente.getDebutVente());
        s.setString(5, vente.getFinVente());
        s.setInt(6, vente.getObjet().getId());
        s.setString(7, vente.getStatut());
        s.executeUpdate();
        return vente.getId();
    }

    public void effacerVenteBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from VENTE where idUt = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public void majVenteBD(Vente vente) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update VENTE SET idve=?, prixbase=?, prixmin=?, debutve=?, finve=?, idob=?, idst=?");
        s.setInt(1, vente.getId());
        s.setDouble(2, vente.getPrixBase());
        s.setDouble(3, vente.getPrixMin());
        s.setString(4, vente.getDebutVente());
        s.setString(5, vente.getFinVente());
        s.setInt(6, vente.getObjet().getId());
        s.setString(7, vente.getStatut());
        s.executeUpdate();
    }
}
