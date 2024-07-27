package database.principal;

import database.ConnectionFactory;
import database.dao.UsuarioDAO;
import database.model.UsuarioModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestCRUD {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.getConnection("localhost", "5432", "crud", "postgres", "root");
        UsuarioModel usuarioModel = new UsuarioModel(1, "admin", "admin");
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        UsuarioModel usuarioUpdate = new UsuarioModel(2, "james", "admin");
            usuarioDAO.update(usuarioUpdate);
        ArrayList<UsuarioModel> usuarios = usuarioDAO.selectAll();
        for (UsuarioModel usuario : usuarios) {
            System.out.println(usuario);
        }

        connection.close();
        if (connection != null) {
            System.out.println("Conexão realizada com sucesso!");
        }
        else {
            System.out.println("Falha na conexão!");
        }

    }
}
