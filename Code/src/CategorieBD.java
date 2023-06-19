import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategorieBD {
    private ConnexionBD laConnexion;
    private Statement st;

    public CategorieBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumCategorie() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idcat) from CATEGORIE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int insererCatBD(Categorie cat) throws SQLException{
        cat.setId(maxNumCategorie()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into CATEGORIE values (?,?)");
        s.setInt(1, cat.getId());
        s.setString(2, cat.getNom());
        s.executeUpdate();
        return cat.getId();
    }

    public void effacerCatBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from CATEGORIE where idcat = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public void majCatBD(Categorie cat) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update CATEGORIE SET idcat=?, nomcat=?");
        s.setInt(1, cat.getId());
        s.setString(2, cat.getNom());
        s.executeUpdate();
    }

    public Categorie getCategorieBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from CATEGORIE where idcat="+ id.toString());
        while (rs.next()){
            int idCat = rs.getInt(1);
            String nomCat = rs.getString(2);

            Categorie cat = new Categorie(idCat, nomCat);
            return cat;
        }
        return null;
    }
}
