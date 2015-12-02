package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import br.ufms.bean.Caixa;

public class daoCaixa {

	private final Connection connection;
	private Caixa cx;
	private int codFunc;

	public daoCaixa(Caixa cx, int codf) {
		connection = new ConnectionFactory().getConnection();
		this.cx = cx;
		this.codFunc = codf;
	}
	

	public boolean abrir() {

		try {
			
			Statement stmt = connection.createStatement();
			String consulta =  "SELECT * FROM Caixa where idCaixa = " + cx.getIdCaixa() + ";";
			ResultSet rs = stmt.executeQuery(consulta);
			if (!rs.next()) {
				return false;
			}else{			
				stmt.close();
				rs.close();
				
				String sqlInsert = "UPDATE Caixa SET Funcionario_idFuncionario = ?, valorCaixaAbertura = ?, " +
						"horaAbertura = ?, horaFechamento = ?, valorCaixaFechamento = ?, statusCX = ? where idCaixa = ?;";
				PreparedStatement ps = connection.prepareStatement(sqlInsert);
				ps.setInt(1, this.getCodFunc());
				ps.setDouble(2, cx.getValorCaixaAbertura());
				ps.setTimestamp(3,  new Timestamp(cx.getHoraAbertura().getTime()));
				//ps.setDate(3, (Date) cx.getHoraAbertura());
				ps.setTimestamp(4, new Timestamp(cx.getHoraFechamento().getTime()));
				ps.setDouble(5, cx.getValorCaixaFechamento());
				ps.setString(6, "A");
				ps.setInt(7, cx.getIdCaixa());
				
				ps.execute();
				ps.close();
				
				return true;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public int getCodFunc() {
		return codFunc;
	}


	public void setCodFunc(int codFunc) {
		this.codFunc = codFunc;
	}


	public boolean fechar(Caixa cx) {
		this.cx = cx;
		try {

			String sqlInsert = "UPDATE Caixa SET horaFechamento = ?, valorCaixaFechamento = ?, statusCX = ? where idCaixa = ?;";
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setTimestamp(1, new Timestamp(cx.getHoraFechamento().getTime()));
			ps.setDouble(2, cx.getValorCaixaFechamento());
			ps.setString(3, "F");
			ps.setInt(4, cx.getIdCaixa());
			
			ps.execute();
			ps.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

/*	public void atualizarValorAtual(Caixa cx) {
		this.cx = cx;
		try {

			String sqlInsert = "UPDATE Caixa SET valorCaixaAtual = ? where idCaixa = ?;";
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setDouble(1, cx.getValorCaixaAtual());
			ps.setInt(2, cx.getIdCaixa());
			
			ps.execute();
			ps.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Double retornaValorAtual(Caixa cx){
		this.cx = cx;
		try {
			String sqlInsert = "Select valorCaixaAtual from Caixa where idCaixa = ? AND statusCX = ?;";
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setInt(1, cx.getIdCaixa());
			ps.setString(2, "A");
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}*/
}
