import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategorieBD {
    private ConnexionBD laConnexion;
    private Statement st;

    /**
     * Constructeur de base de la classe CategorieBD
     * @param laConnexion
     */
    public CategorieBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    /**
     * 
     * @return le nombre de catégories présentes dans la base de données
     * @throws SQLException
     */
    public int maxNumCategorie() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idcat) from CATEGORIE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    /**
     * 
     * @param cat
     * @return l'identifiant de la catégorie que l'on vient d'insérer dans la base de données
     * @throws SQLException
     */
    public int insererCatBD(Categorie cat) throws SQLException{
        cat.setId(maxNumCategorie()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into CATEGORIE values (?,?)");
        s.setInt(1, cat.getId());
        s.setString(2, cat.getNom());
        s.executeUpdate();
        return cat.getId();
    }

    /**
     * Permet d'effacer une catégorie de la base de données
     * @param id
     * @throws SQLException
     */
    public void effacerCatBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from CATEGORIE where idcat = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    /**
     * Permet de mettre à jour une catégorie de la base de données
     * @param cat
     * @throws SQLException
     */
    public void majCatBD(Categorie cat) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update CATEGORIE SET idcat=?, nomcat=?");
        s.setInt(1, cat.getId());
        s.setString(2, cat.getNom());
        s.executeUpdate();
    }

    /**
     * 
     * @param id
     * @return la catégorie que nous cherchions et ses informations
     * @throws SQLException
     */
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
