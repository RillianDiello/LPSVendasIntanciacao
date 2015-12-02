package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.ufms.bean.ItemProduto;
import br.ufms.bean.Venda;

public class daoVenda {
	
	private final Connection connection;
	private Venda v;
	private int codigoFuncionario;
	private int codigoCliente;
	private ArrayList<ItemProduto> listaP;
	 
	public daoVenda(Venda v, int codigoFuncionario, int codigoCliente, ArrayList<ItemProduto> listaP){
		connection = new ConnectionFactory().getConnection();
		this.v = v;	
		this.codigoFuncionario = codigoFuncionario;
		this.codigoCliente = codigoCliente;
		this.listaP = listaP;
	}
	
	public boolean inserir (){
		try {
			
			String sqlInsert = "INSERT INTO Venda (Funcionario_idFuncionario, Cliente_idCliente, dataVenda, horaVenda, valorVenda, tipoPagamento, valorPago) VALUES(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setInt(1, getCodigoFuncionario());
			ps.setInt(2, getCodigoCliente());
			ps.setString(3, v.getDataVenda());
			ps.setString(4, v.getHoraVenda());
			ps.setDouble(5, v.getValorVenda());
			ps.setString(6, v.getTipoPagamento());
			ps.setDouble(7, v.getValorPago());
			
			ps.execute();
			ps.close();

			daoItemProduto ip = new daoItemProduto(listaP, getCodigoCliente(), getId());
			ip.inserir();
			
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getId(){
		try {
			//Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Venda where dataVenda = '" + v.getDataVenda() + "' and horaVenda = '" + v.getHoraVenda() + "';";
			PreparedStatement statement = connection.prepareStatement(sqlConsulta);
			ResultSet result = statement.executeQuery(); 
			
					
			if(result != null){				
				if(result.next()){
					int cod = result.getInt("idVenda");
					System.out.println("codigo = " + cod);
					result.close();
					statement.close();
					return cod;
				}
			}
			return 0;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
