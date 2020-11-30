package dariomorgrane.emphasoft;

import dariomorgrane.emphasoft.dto.ExchangeResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.Map;

@SpringBootApplication
public class EmphasoftApplication {

	final static String DB_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/aunqchll";
	final static String DB_Driver = "org.postgresql.Driver";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EmphasoftApplication.class, args);
		/*Class.forName(DB_Driver);
		Connection connection = DriverManager.getConnection(DB_URL, "aunqchll", "Omf0Eva6Ttg9HiNNfRsBzOj1uhRSoKHV");
		System.out.println("Connection to DB is done!");
		String nl = "\n";
		Statement statement = connection.createStatement();

        String query1 = "Select * from exchange_operations;";
        printResultSet(query1, statement);
		connection.close();*/
	}

	static void printResultSet(String SQLQuery, Statement statement) throws Exception {
		ResultSet rs = statement.executeQuery(SQLQuery);
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		for (int i = 1; i<= columnCount; i++) {
			System.out.print(resultSetMetaData.getColumnName(i) + "   ");
		}
		System.out.println( "\n" + "----------");

		while (rs.next()) {
			int count = 1;
			while (count <= columnCount){
				System.out.print(rs.getString(count) + "    ");
				count++;
			}
			System.out.println();
		}
		System.out.println();
	}



}

