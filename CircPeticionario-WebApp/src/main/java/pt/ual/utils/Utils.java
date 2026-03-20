package pt.ual.utils;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Utils {
	public String getToken() {
		try {

            Context context = new InitialContext();

            String token = (String) context.lookup("TOKEN_SERVICE");

            return token;
        } catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Error Check: TOKEN_SERVICE JNDI not found.");
            return null;
        }
    }

	public static Connection getConnectionStock() throws Exception {
		Context c = new InitialContext();
		try {
			DataSource d = (DataSource)c.lookup("jdbc/app_cpet");
			return d.getConnection();
		} catch (Exception ex) {
			throw new Exception("Unable to connect database Circuito Peticionário");
		} 
   }
 }