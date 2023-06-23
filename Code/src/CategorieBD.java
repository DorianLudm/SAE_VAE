import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Cette classe représente une gestionnaire de catégories dans la base de données.
 * Elle permet d'effectuer des opérations telles que l'insertion, la suppression et la mise à jour des catégories.
 * La classe utilise une instance de ConnexionBD pour interagir avec la base de données.
 */

public class CategorieBD {
    /**Instance de ConnexionBD utilisée pour se connecter à la base de données. */
    private ConnexionBD laConnexion;

    /**Statement utilisé pour exécuter des requêtes SQL. */
    private Statement st;


    /**
     * Constructeur de la classe CategorieBD.
     * @param laConnexion L'instance de ConnexionBD à utiliser pour se connecter à la base de données.
     */
    public CategorieBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }


    /**
     * Récupère le nombre maximum de catégories dans la base de données.
     * @return Le nombre maximum de catégories.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public int maxNumCategorie() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idcat) from CATEGORIE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }


    /**
     * Insère une catégorie dans la base de données.
     * @param cat La catégorie à insérer.
     * @return L'ID de la catégorie insérée.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
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
     * Supprime une catégorie de la base de données.
     * @param id L'ID de la catégorie à supprimer.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public void effacerCatBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from CATEGORIE where idcat = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }


    /**
     * Met à jour une catégorie dans la base de données.
     * @param cat La catégorie mise à jour.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public void majCatBD(Categorie cat) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update CATEGORIE SET idcat=?, nomcat=?");
        s.setInt(1, cat.getId());
        s.setString(2, cat.getNom());
        s.executeUpdate();
    }
}
