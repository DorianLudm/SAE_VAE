import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnchereBD {
    private ConnexionBD laConnexion;
    private Statement st;

    public EnchereBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public void insererEnchereBD(Enchere enchere) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(enchere.getDateHeure());
        PreparedStatement s = this.laConnexion.prepareStatement("insert into ENCHERIR values (?,?,?,?)");
        s.setInt(1, enchere.getUtilisateur().getId());
        s.setInt(2, enchere.getVente().getId());
        s.setDate(3, date);
        s.setDouble(4, enchere.getMontant());
        s.executeUpdate();
    }

    public void effacerEnchereBD(int idUt, int idVe, String dateheure) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(dateheure);
        PreparedStatement s = this.laConnexion.prepareStatement("delete from ENCHERIR where idUt = ? and idVe = ? and dateheure = ?");
        s.setInt(1, idUt);
        s.setInt(2, idVe);
        s.setDate(3, date);
        s.executeUpdate();
    }

    public void majEnchereBD(Enchere enchere) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(enchere.getDateHeure());
        PreparedStatement s = this.laConnexion.prepareStatement("update ENCHERIR SET idut=?, idve=?, dateheure=?, montant=?");
        s.setInt(1, enchere.getUtilisateur().getId());
        s.setInt(2, enchere.getVente().getId());
        s.setDate(3, date);
        s.setDouble(4, enchere.getMontant());
        s.executeUpdate();
    }
}
