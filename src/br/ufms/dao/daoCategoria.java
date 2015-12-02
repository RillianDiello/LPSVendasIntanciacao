package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.ufms.bean.Categoria;
import br.ufms.bean.Produto;

public class daoCategoria {
	private final Connection connection;

	public daoCategoria(Categoria cat) {
		connection = new ConnectionFactory().getConnection();

	}

	public boolean inserir(Categoria cat) {
		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM categoria where descricao LIKE'"
					+ cat.getDescricaoCat() + "%';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (rs.next()) {
				return false;
			} else {
				String sqlInsert = "INSERT INTO categoria (descricao) VALUES(?);";
				PreparedStatement ps = connection.prepareStatement(sqlInsert);
				ps.setString(1, cat.getDescricaoCat());

				ps.execute();
				ps.close();
				return true;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void imprimeErro(String string, String message) {
		// TODO Auto-generated method stub

	}

	public boolean excluir(Categoria cat) {
		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM categoria where idCategoria = '"
					+ cat.getCodigoCategoria() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (!rs.next()) {
				return false;
			} else {

				String sqlDelete = "DELETE FROM categoria WHERE idCategoria = ?;";
				PreparedStatement ps = connection.prepareStatement(sqlDelete);
				ps.setInt(1, cat.getCodigoCategoria());
				ps.execute();

				ps.execute();
				ps.close();
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Categoria buscarCategoria(int idCat) {
		try {
			String sqlConsulta = "SELECT * FROM categoria where idCategoria = '"
					+ idCat + "';";
			PreparedStatement statement = connection
					.prepareStatement(sqlConsulta);
			ResultSet result = statement.executeQuery();
			Categoria temp = null;
			if (result != null) {
				temp = new Categoria();
				if (result.next()) {
					temp.setCodigoCategoria(result.getInt("idCategoria"));
					temp.setDescricaoCat(result.getString("descricao"));
				}
			}
			result.close();
			statement.close();
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar produto", e.getMessage());
			return null;
		}

	}

}
