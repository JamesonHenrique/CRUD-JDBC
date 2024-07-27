package database.dao;

import database.model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    private String commandSelectAll = "SELECT * FROM public.usuarios";
    private PreparedStatement pstSelectAll;
    private String commandInsert = "INSERT INTO public.usuarios (usuario, senha) VALUES (?, ?)";
    private PreparedStatement pstInsert;
    private String commandUpdate = "UPDATE public.usuarios SET usuario = ?, senha = ? WHERE id = ?";
    private PreparedStatement pstUpdate;
    private String commandDelete = "DELETE FROM public.usuarios WHERE id = ?";
    private PreparedStatement pstDelete;

    // Implementar os métodos de CRUD
    public UsuarioDAO(final Connection connection) throws SQLException {
        pstSelectAll = connection.prepareStatement(commandSelectAll);
        pstInsert = connection.prepareStatement(commandInsert);
        pstUpdate = connection.prepareStatement(commandUpdate);
        pstDelete = connection.prepareStatement(commandDelete);
    }

    public int insert(UsuarioModel usuario) throws SQLException {
        pstInsert.clearParameters();
        pstInsert.setString(1, usuario.usuario());
        pstInsert.setString(2, usuario.senha());
        return pstInsert.executeUpdate();
    }

    public int update(UsuarioModel usuario) throws SQLException {
        pstUpdate.setString(1, usuario.usuario());
        pstUpdate.setString(2, usuario.senha());
        pstUpdate.setInt(3, usuario.id());
        return pstUpdate.executeUpdate();

    }

    public void delete(UsuarioModel usuario) throws SQLException {
        pstDelete.setInt(1, usuario.id());
        pstDelete.executeUpdate();
    }

    public ArrayList<UsuarioModel> selectAll() throws SQLException {
        ArrayList<UsuarioModel> usuarios = new ArrayList<>();
        var rs = pstSelectAll.executeQuery();
        while (rs.next()) {
            usuarios.add(new UsuarioModel(rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("senha")));
        }
        return usuarios;
    }
}