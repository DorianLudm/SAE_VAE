import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VenteBD {
    private ConnexionBD laConnexion;
    private Statement st;
    
    /**
     * Constructeur de base de la classe VenteBD
     * @param laConnexion
     */
    public VenteBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    /**
     * 
     * @return le nombre de ventes existantes dans la base de données
     * @throws SQLException
     */
    public int maxNumVente() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idve) from VENTE");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    /**
     * 
     * @param vente
     * @return l'identifiant de la vente que l'on vient d'ajouter dans la base de données
     * @throws SQLException
     */
    public int insererVenteBD(Vente vente) throws SQLException{
        vente.setId(maxNumVente()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into VENTE values (?,?,?,?,?,?,?)");
        s.setInt(1, vente.getId());
        s.setDouble(2, vente.getPrixBase());
        s.setDouble(3, vente.getPrixMin());
        s.setString(4, vente.getDebutVente());
        s.setString(5, vente.getFinVente());
        s.setInt(6, vente.getObjet().getId());
        s.setInt(7, vente.getStatut().getId());
        s.executeUpdate();
        return vente.getId();
    }

    /**
     * Permet d'effacer une vente de la base de données
     * @param id
     * @throws SQLException
     */
    public void effacerVenteBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from VENTE where idUt = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    /**
     * Permet de mettre à jour une vente de la base de données
     * @param vente
     * @throws SQLException
     */
    public void majVenteBD(Vente vente) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update VENTE SET idve=?, prixbase=?, prixmin=?, debutve=?, finve=?, idob=?, idst=?");
        s.setInt(1, vente.getId());
        s.setDouble(2, vente.getPrixBase());
        s.setDouble(3, vente.getPrixMin());
        s.setString(4, vente.getDebutVente());
        s.setString(5, vente.getFinVente());
        s.setInt(6, vente.getObjet().getId());
        s.setInt(7, vente.getStatut().getId());
        s.executeUpdate();
    }

    /**
     * 
     * @param id
     * @return la vente que l'on cherchait et ses informations
     * @throws SQLException
     */
    public Vente getVenteBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE natural join OBJET natural join UTILISATEUR natural join CATEGORIE natural join ROLE natural join STATUT where idve="+id.toString());
        while (rs.next()){
            int idSt = rs.getInt(1);
            int idRole = rs.getInt(2);
            int idCat = rs.getInt(3);
            int idUt = rs.getInt(4);
            int idOb = rs.getInt(5);
            int idVe = rs.getInt(6);
            double prixBase = rs.getDouble(7);
            double prixMin = rs.getDouble(8);
            String debutVe = rs.getString(9);
            String finVe = rs.getString(10);
            String nomOb = rs.getString(11);
            String descriptionOb = rs.getString(12);
            String pseudoUt = rs.getString(13);
            String emailUt = rs.getString(14);
            String mdpUt = rs.getString(15);
            String activeUt = rs.getString(16);
            String nomCat = rs.getString(17);
            String nomRole = rs.getString(18);
            String nomSt = rs.getString(19);

            Statut statut = new Statut(idSt, nomSt);
            Role role = new Role(idRole, nomRole);
            Categorie cat = new Categorie(idCat, nomCat);
            Utilisateur util = new Utilisateur(idUt, pseudoUt, emailUt, mdpUt, activeUt, role);
            Objet objet = new Objet(idOb, nomOb, descriptionOb, util, cat);
            Vente vente = new Vente(idVe, debutVe, finVe, prixMin, prixBase, objet, statut);
            return vente;
        }
        return null;
    }
}
