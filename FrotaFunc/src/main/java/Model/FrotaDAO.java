package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrotaDAO {

    public void inserir(Frota frota) throws SQLException {
        String sql = "INSERT INTO frota (modelo, placa, ano) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, frota.getModelo());
            stmt.setString(2, frota.getPlaca());
            stmt.setInt(3, frota.getAno());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    frota.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Frota> listar() throws SQLException {
        List<Frota> lista = new ArrayList<>();
        String sql = "SELECT * FROM frota";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Frota f = new Frota();
                f.setId(rs.getInt("id"));
                f.setModelo(rs.getString("modelo"));
                f.setPlaca(rs.getString("placa"));
                f.setAno(rs.getInt("ano"));
                lista.add(f);
            }
        }
        return lista;
    }

    public void atualizar(Frota frota) throws SQLException {
        String sql = "UPDATE frota SET modelo = ?, placa = ?, ano = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, frota.getModelo());
            stmt.setString(2, frota.getPlaca());
            stmt.setInt(3, frota.getAno());
            stmt.setInt(4, frota.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM frota WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
