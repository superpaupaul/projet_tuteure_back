package bd_connexion;

import java.sql.*;

public class ConnexionBD {

	public static void main(String[] args) {
		try{ 
			//charger le driver
			Class.forName ("com.mysql.cj.jdbc.Driver"); 

			//créer la connexion
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/projetbd", "root", "");

			//créer un statement pour faire des opérations en sql
			Statement st = connect.createStatement();

			System.out.println("1");
			//la requete sql
			String sql = "SELECT * FROM admin";
			ResultSet rst = st.executeQuery(sql);
			
			System.out.println("Nom : " + rst.getString(2));

			while (rst.next()) {
				System.out.println("Nom : " + rst.getString(0));
			}

		} catch (Exception e) { 
			System.out.println ("Il y a eu un problème : " + e.getMessage());
		}
		// Connection à la base de données
		//System.out.println ("Connexion à la base de données réussie....");
	}

}
