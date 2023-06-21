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
