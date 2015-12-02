package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import br.ufms.bean.Produto;

public class daoProduto {

	/**
	 * @param args
	 */
	private final Connection connection;
	public daoProduto(Produto pr) {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean inserir(Produto pr) {
		try {

			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM produto where descricao LIKE'"
					+ pr.getDescricao() + "%';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (rs.next()) {
				return false;
			}else{
				//nova string sql com o campo novo
				String sqlInsert = "INSERT INTO Produto (descricao, fabricante, precoVendaAtac, precoVendaVare) VALUES(?, ?, ?, ?, ?);";
				PreparedStatement ps = connection.prepareStatement(sqlInsert);
				ps.setString(1, pr.getDescricao());
				ps.setString(2, pr.getFabricante());
				ps.setDouble(3, pr.getPrecoVendaAtacado());
				ps.setDouble(4, pr.getPrecoVendaVarejo());
				ps.setInt(5, pr.getCodigoCategoria());//novo campo no produto
	
				ps.execute();
				ps.close();
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public boolean excluir(Produto pr) {

		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Produto where idProduto = '"
					+ pr.getCodigo() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (!rs.next()) {
				return false;
			} else {

				String sqlDelete = "DELETE FROM Produto WHERE idProduto = ?;";
				PreparedStatement ps = connection.prepareStatement(sqlDelete);
				ps.setInt(1, pr.getCodigo());		
				ps.execute();			

				return true;
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void imprimeErro(String string, String message) {
		// TODO Auto-generated method stub

	}

	public Vector<Produto> buscar(int idCod) {

		Vector<Produto> resultados = new Vector<Produto>();
		ResultSet rs;
		try {

			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM produtos where idProduto = '"
					+ idCod + "';";
			rs = stmt.executeQuery(sqlConsulta);

			while (rs.next()) {
				Produto temp = new Produto();
				// pega todos os atributos do produto
				temp.setCodigo(rs.getInt("idCodigo"));
				temp.setDescricao(rs.getString("descricao"));
				temp.setFabricante(rs.getString("fabricante"));
				temp.setPrecoVendaAtacado(rs.getFloat("precoVendaAtac"));
				temp.setPrecoVendaVarejo(rs.getFloat("precoVendaVare"));
				temp.setCodigoCategoria(rs.getInt("codigoCategoria")); //novo campo no produto
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar produto", e.getMessage());
			return null;
		}

	}

	public Produto buscarPorCod(int idCod) {
		try {			
			String sqlConsulta = "SELECT * FROM Produto where idProduto = '" + idCod + "';";
			PreparedStatement statement = connection.prepareStatement(sqlConsulta);
			ResultSet result = statement.executeQuery(); 
			Produto temp = null;
			if(result != null){
				temp = new Produto();
				if(result.next()){
					temp.setCodigo(result.getInt("idProduto"));
					temp.setDescricao(result.getString("descricao"));
					temp.setFabricante(result.getString("fabricante"));
					temp.setPrecoVendaAtacado(result.getFloat("precoVendaAtac"));
					temp.setPrecoVendaVarejo(result.getFloat("precoVendaVare"));
					temp.setCodigoCategoria(result.getInt("codigoCategoria")); //novo campo no produto
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
	//Retorna um vetor com todos os produtos com a mesma descriçao
	public Vector<Produto> buscarPorDescricao(String descricao) {

		Vector<Produto> resultados = new Vector<Produto>();
		ResultSet rs;
		try {
			
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM produtos where descricao LIKE'"
					+ descricao + "%';";
			rs = stmt.executeQuery(sqlConsulta);
			
			while (rs.next()) {
				Produto temp = new Produto();
				// pega todos os atributos do produto
				temp.setCodigo(rs.getInt("idCodigo"));
				temp.setDescricao(rs.getString("descricao"));
				temp.setFabricante(rs.getString("fabricante"));
				temp.setPrecoVendaAtacado(rs.getFloat("precoVendaAtac"));
				temp.setPrecoVendaVarejo(rs.getFloat("precoVendaVare"));
				temp.setCodigoCategoria(rs.getInt("codigoCategoria")); //novo campo no produto
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar produto", e.getMessage());
			return null;
		}

	}

}
