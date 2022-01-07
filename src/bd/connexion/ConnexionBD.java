package bd_connexion;

import java.sql.*;

public class ConnexionBD {

	public static void main(String[] args) {
		try{ 
			//charger le driver
			Class.forName ("com.mysql.cj.jdbc.Driver"); 

			//cr�er la connexion
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/projetbd", "root", "");

			//cr�er un statement pour faire des op�rations en sql
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
			System.out.println ("Il y a eu un probl�me : " + e.getMessage());
		}
		// Connection � la base de donn�es
		//System.out.println ("Connexion � la base de donn�es r�ussie....");
	}

}
