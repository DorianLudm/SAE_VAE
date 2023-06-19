import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhotoBD {
    private ConnexionBD laConnexion;
    private Statement st;

    public PhotoBD(ConnexionBD laConnexion){
        this.laConnexion = laConnexion;
    }

    public int maxNumPhoto() throws SQLException{
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idph) from PHOTO");
        rs.next();
        int val = rs.getInt(1);
        return val;
    }

    public int insererPhotoBD(Photo photo) throws SQLException{
        photo.setId(maxNumPhoto()+1);
        PreparedStatement s = this.laConnexion.prepareStatement("insert into PHOTO values (?,?,?,?)");
        s.setInt(1, photo.getId());
        s.setString(2, photo.getTitre());
        s.setString(3, photo.getURLImg());
        s.setInt(4, photo.getObjet().getId());
        s.executeUpdate();
        return photo.getId();
    }

    public void effacerPhotoBD(int id) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("delete from PHOTO where idph=?");
        s.setInt(1, id);
        s.executeUpdate();
    }

    public void majPhotoBD(Photo photo) throws SQLException{
        PreparedStatement s = this.laConnexion.prepareStatement("update PHOTO SET idph=?, titreph=?, imgph=?, idob=?");
        s.setInt(1, photo.getId());
        s.setString(2, photo.getTitre());
        s.setString(3, photo.getURLImg());
        s.setInt(4, photo.getObjet().getId());
        s.executeUpdate();
    }
}