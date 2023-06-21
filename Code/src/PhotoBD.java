import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhotoBD {
    private ConnexionBD laConnexion;
    private Statement st;

    /**
     * Constructeur de base de la classe PhotoBD
     * @param laConnexion
     */
    public PhotoBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    /**
     * 
     * @return le nombre de photos présentes dans la base de données
     * @throws SQLException
     */
    public int maxNumPhoto() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idph) from PHOTO");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    /**
     * Permet d'insérer une photo dans la base de données
     * @param photo
     * @throws SQLException
     */
    public void insererPhotoBD(Photo photo) throws SQLException{
        photo.setId(maxNumPhoto()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into PHOTO values (?,?,?,?)");
        s.setInt(1, photo.getId());
        s.setString(2, photo.getTitre());
        s.setString(3, photo.getURLImg());
        s.setInt(4, photo.getObjet().getId());
        s.executeUpdate();
    }

    /**
     * Permet d'effacer une photo de la base de données
     * @param id
     * @throws SQLException
     */
    public void effacerPhotoBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from PHOTO where idph=?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    /**
     * Permet de mettre à jour une photo de la base de données
     * @param photo
     * @throws SQLException
     */
    public void majPhotoBD(Photo photo) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update PHOTO SET idph=?, titreph=?, imgph=?, idob=?");
        s.setInt(1, photo.getId());
        s.setString(2, photo.getTitre());
        s.setString(3, photo.getURLImg());
        s.setInt(4, photo.getObjet().getId());
        s.executeUpdate();
    }

    /**
     * 
     * @param id
     * @return la photo que nous cherchions et ses informations
     * @throws SQLException
     */
    public Photo getPhotoBD(Integer id) throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from PHOTO natural join OBJET natural join UTILISATEUR natural join ROLE natural join CATEGORIE where idph="+id.toString());
        while (rs.next()){
            int idCat = rs.getInt(1);
            int idRole = rs.getInt(2);
            int idUt = rs.getInt(3);
            int idOb = rs.getInt(4);
            int idPh = rs.getInt(5);
            String titrePh = rs.getString(6);
            String imgPh = rs.getString(7);
            String nomOb = rs.getString(8);
            String descriptionOb = rs.getString(9);
            String pseudoUt = rs.getString(10);
            String emailUt = rs.getString(11);
            String mdpUt = rs.getString(12);
            String activeUt = rs.getString(13);
            String nomRole = rs.getString(14);
            String nomCat = rs.getString(15);

            Role role = new Role(idRole, nomRole);
            Categorie cat = new Categorie(idCat, nomCat);
            Utilisateur util = new Utilisateur(idUt, pseudoUt, emailUt, mdpUt, activeUt, role);
            Objet objet = new Objet(idOb, nomOb, descriptionOb, util, cat);
            Photo photo = new Photo(idPh, titrePh, imgPh, objet);
            return photo;
        }
        return null;
    }
}