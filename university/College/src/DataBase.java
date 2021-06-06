import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DataBase {

	Connection con = null;
	
	public static Connection dataConnction()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:E:\\uni.db");
			JOptionPane.showMessageDialog(null,"Connected");
			return dbConnection;
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,e);
			}
		return null;	
	}
	
	
}
