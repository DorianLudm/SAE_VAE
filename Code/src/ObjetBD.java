import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ObjetBD {
    private ConnexionBD laConnexion;
    private Statement st;

    /**
     * Constructeur de base de la classe ObjetBD
     * @param laConnexion
     */
    public ObjetBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    /**
     * 
     * @return le nombre d'objets existants dans la base de données
     * @throws SQLException
     */
    public int maxNumObjet() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idob) from OBJET");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    /**
     * 
     * @param obj
     * @return l'identifiant de l'objet que l'on vient d'insérer dans la base de données
     * @throws SQLException
     */
    public int insererObjetBD(Objet obj) throws SQLException{
        obj.setId(maxNumObjet()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into OBJET values (?,?,?,?,?)");
        s.setInt(1, obj.getId());
        s.setString(2, obj.getNom());
        s.setString(3, obj.getDescription());
        s.setInt(4, obj.getUtilisateur().getId());
        s.setInt(5, obj.getCat().getId());
        s.executeUpdate();
        return obj.getId();
    }

    /**
     * Permet d'effacer un objet de la base de données
     * @param id
     * @throws SQLException
     */
    public void effacerObjetBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from OBJET where idob = ?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    /**
     * Permet de mettre à jour un objet de la base de données
     * @param obj
     * @throws SQLException
     */
    public void majObjetBD(Objet obj) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update OBJET SET idob=?, nomob=?, descriptionob=?, idut=?, idcat=?");
        s.setInt(1, obj.getId());
        s.setString(2, obj.getNom());
        s.setString(3, obj.getDescription());
        s.setInt(4, obj.getUtilisateur().getId());
        s.setInt(5, obj.getCat().getId());
        s.executeUpdate();
    }

    /**
     * 
     * @param id
     * @return l'objet que nous cherchions et ses informations
     * @throws SQLException
     */
    public Objet getObjetBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from OBJET natural join CATEGORIE natural join UTILISATEUR natural join ROLE natural join PHOTO where idob="+ id.toString());
        Objet objet = null;
        while (rs.next()){
            if (rs.getInt(1) == id){
                int idOb = rs.getInt(1);
                int idRole = rs.getInt(2);
                int idUt = rs.getInt(3);
                int idCat = rs.getInt(4);
                String nomOb = rs.getString(5);
                String descriptionOb = rs.getString(6);
                String nomCat = rs.getString(7);
                String pseudoUt = rs.getString(8);
                String emailUt = rs.getString(9);
                String mdpUt = rs.getString(10);
                String activeUt = rs.getString(11);
                String nomRole = rs.getString(12);
                int idPh = rs.getInt(13);
                String titrePh = rs.getString(14);
                String urlImg = rs.getString(15);

                Role role = new Role(idRole, nomRole);
                Categorie cat = new Categorie(idCat, nomCat);
                Utilisateur util = new Utilisateur(idUt, pseudoUt, emailUt, mdpUt, activeUt, role);
                objet = new Objet(idOb, nomOb, descriptionOb, util, cat);
                Photo photo = new Photo(idPh, titrePh, urlImg, objet);
                objet.ajoutePhoto(photo);
            }
            return objet;
        }
        return null;
    }
}
