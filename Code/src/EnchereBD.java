import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Cette classe représente un gestionnaire d'enchères dans la base de données.
 * Elle permet d'effectuer des opérations telles que l'insertion, la suppression, la mise à jour et la récupération des enchères.
 * La classe utilise une instance de ConnexionBD pour interagir avec la base de données.
 */
public class EnchereBD {
    /**Instance de ConnexionBD utilisée pour se connecter à la base de données. */
    private ConnexionBD laConnexion;
    /**Statement utilisé pour exécuter des requêtes SQL. */
    private Statement st;


    /**
     * Constructeur de la classe EnchereBD.
     * @param laConnexion L'instance de ConnexionBD à utiliser pour se connecter à la base de données.
     */
    public EnchereBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }


    /**
     * Insère une enchère dans la base de données.
     * @param enchere L'enchère à insérer.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public void insererEnchereBD(Enchere enchere) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(enchere.getDateHeure());
        PreparedStatement s = this.laConnexion.prepareStatement("insert into ENCHERIR values (?,?,?,?)");
        s.setInt(1, enchere.getUtilisateur().getId());
        s.setInt(2, enchere.getVente().getId());
        s.setDate(3, date);
        s.setDouble(4, enchere.getMontant());
        s.executeUpdate();
    }


     /**
     * Supprime une enchère de la base de données.
     * @param idUt L'ID de l'utilisateur associé à l'enchère.
     * @param idVe L'ID de la vente associée à l'enchère.
     * @param dateheure La date et heure de l'enchère.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public void effacerEnchereBD(int idUt, int idVe, String dateheure) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(dateheure);
        PreparedStatement s = this.laConnexion.prepareStatement("delete from ENCHERIR where idUt = ? and idVe = ? and dateheure = ?");
        s.setInt(1, idUt);
        s.setInt(2, idVe);
        s.setDate(3, date);
        s.executeUpdate();
    }


    /**
     * Met à jour une enchère dans la base de données.
     * @param enchere L'enchère mise à jour.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public void majEnchereBD(Enchere enchere) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(enchere.getDateHeure());
        PreparedStatement s = this.laConnexion.prepareStatement("update ENCHERIR SET idut=?, idve=?, dateheure=?, montant=?");
        s.setInt(1, enchere.getUtilisateur().getId());
        s.setInt(2, enchere.getVente().getId());
        s.setDate(3, date);
        s.setDouble(4, enchere.getMontant());
        s.executeUpdate();
    }

    /**
     * Récupère une enchère de la base de données en fonction de son ID.
     * @param id L'ID de l'enchère à récupérer.
     * @return L'enchère récupérée, ou null si aucune enchère correspondante n'a été trouvée.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public Enchere getEnchereBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from ENCHERIR natural join UTILISATEUR natural join VENTE natural join ROLE natural join OBJET natural join CATEGORIE natural join STATUT where idve="+id.toString());
        while (rs.next()){
            int idCat = rs.getInt(1);
            int idUt = rs.getInt(2);
            int idOb = rs.getInt(3);
            int idRole = rs.getInt(4);
            int idVe = rs.getInt(5);
            String date = rs.getString(6);
            double montant = rs.getDouble(7);
            String pseudoUt = rs.getString(8);
            String emailUt = rs.getString(9);
            String mdpUt = rs.getString(10);
            String activeUt = rs.getString(11);
            double prixBase = rs.getDouble(12);
            double prixMin = rs.getDouble(13);
            String debutVe = rs.getString(14);
            String finVe = rs.getString(15);
            int idSt = rs.getInt(16);
            String nomRole = rs.getString(17);
            String nomOb = rs.getString(18);
            String descriptionOb = rs.getString(19);
            String nomCat = rs.getString(20);
            String nomSt = rs.getString(21);

            Categorie cat = new Categorie(idCat, nomCat);
            Role role = new Role(idRole, nomRole);
            Statut statut = new Statut(idSt, nomSt);

            Utilisateur util = new Utilisateur(idUt, pseudoUt, emailUt, mdpUt, activeUt, role);
            Objet objet = new Objet(idOb, nomOb, descriptionOb, util, cat);
            Vente vente = new Vente(idVe, debutVe, finVe, prixMin, prixBase, objet, statut);
            Enchere enchere = new Enchere(util, vente, date, montant);
            return enchere;
        }
        return null;
    }
}
