package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
		  
<<<<<<< HEAD
	public Connection conexao() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:db/engenharia_software.db");
		} catch (SQLException e) {
			System.err.println("Erro na conex�o!");
		}
=======
public Connection conexao() {
	Connection con = null;
	try {
		con = DriverManager.getConnection("jdbc:sqlite:db/engenharia_software.db");
	} catch (SQLException e) {
		System.err.println("Erro na conex�o!");
	}
>>>>>>> origin/master
		return con;
	}
}