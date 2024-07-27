package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(
            final String endereçoIP,
            final String porta,
            final String banco,
            final String usuario,
            final String senha
    ) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://"+endereçoIP+":"+porta+"/"+banco,usuario,senha
        );
    }
}
