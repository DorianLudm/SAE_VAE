import java.sql.*;

/**
 * Cette classe représente une connexion à une base de données.
 * Elle permet de se connecter à la base de données, de créer des statements et de gérer la fermeture de la connexion.
 */
public class ConnexionBD {
	/**Connexion à la base de données */
	private Connection mysql=null;
	/**Indique si la connexion est établie.*/
	private boolean connecte=false;


	/**
     * Constructeur de la classe ConnexionBD.
     * @throws ClassNotFoundException Si le driver JDBC n'est pas trouvé.
     */
	public ConnexionBD() throws ClassNotFoundException{
		Class.forName("org.mariadb.jdbc.Driver");
	}


	/**
     * Établit la connexion à la base de données.
     * @throws SQLException En cas d'erreur lors de la connexion.
     */
	public void connecter() throws SQLException {
		this.mysql = DriverManager.getConnection("jdbc:mysql://servinfo-mariadb:3306/DBludmann", "ludmann", "ludmann");                                                                                                                                                           
		this.connecte=this.mysql!=null;
    }
	

	/**
     * Ferme la connexion à la base de données.
     * @throws SQLException En cas d'erreur lors de la fermeture de la connexion.
     */
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}


	/**
     * Vérifie si la connexion est établie.
     * @return true si la connexion est établie, false sinon.
     */
    public boolean isConnecte() {return this.connecte;}
	


	/**
     * Crée un Statement pour exécuter des requêtes SQL.
     * @return Un objet Statement.
     * @throws SQLException En cas d'erreur lors de la création du Statement.
     */
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}


	/**
     * Prépare une requête SQL avec des paramètres.
     * @param requete La requête SQL à préparer.
     * @return Un objet PreparedStatement.
     * @throws SQLException En cas d'erreur lors de la préparation de la requête.
     */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	

	/**
     * Récupère la connexion à la base de données.
     * @return L'objet Connection représentant la connexion à la base de données.
     */
	public Connection getCon(){
        return this.mysql;
    }
}