import java.sql.*;

public class ConnexionBD {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnexionBD() throws ClassNotFoundException{
		Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		// si tout c'est bien passé la connexion n'est plus nulle
		Connection c;
		try {
			c = DriverManager.getConnection("jdbc:mysql://"+nomBase,nomLogin,motDePasse);
			this.mysql=c;
		} catch ( SQLException ex ) {
			System.out.println("Msg : " + ex.getMessage() + ex.getErrorCode());
		}

		this.connecte=this.mysql!=null;
	}
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}

    	public boolean isConnecte() { return this.connecte;}
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}