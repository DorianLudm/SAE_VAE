import java.sql.*;

public class ConnexionBD {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnexionBD() throws ClassNotFoundException{
		Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter() throws SQLException {
		this.mysql = DriverManager.getConnection("jdbc:mysql://servinfo-mariadb:3306/DBludmann", "ludmann", "ludmann");                                                                                                                                                           
		this.connecte=this.mysql!=null;
    }
	
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}

    public boolean isConnecte() {return this.connecte;}
	
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
	public Connection getCon(){
        return this.mysql;
    }
}