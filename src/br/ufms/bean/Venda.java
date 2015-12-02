package br.ufms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.ufms.dao.daoVenda;

public class Venda {

	private String dataVenda;
	private String horaVenda;
	private double valorVenda;
	private String tipoPagamento;
	private double valorPago;
	private int codigoCliente;
	private int codigoFuncionario;
	private daoVenda dv;
	private ArrayList<ItemProduto> listaP;
	
	public Venda(int codigoCliente, int codigoFuncionario) {
		this.codigoCliente = codigoCliente;
		this.codigoFuncionario = codigoFuncionario;

		iniciarVenda();
		System.out.println("Deseja confirmar venda? \n 1 - Sim \n 2 - Não \n");
		Scanner s = new Scanner(System.in);
		int op = s.nextInt();
		switch (op) {
		case 1:
			//dinheiro			
			registrarPagamento();
			registrarVenda();
			imprimirNota();
			break;
		case 2:
			cancelarVenda();
			break;
		}
		
	}
	//Venda para um cliente default 0
	public Venda( int codigoFuncionario) {
		this.codigoCliente = 0;
		this.codigoFuncionario = codigoFuncionario;

		iniciarVenda();
		System.out.println("Deseja confirmar venda? \n 1 - Sim \n 2 - Não \n");
		Scanner s = new Scanner(System.in);
		int op = s.nextInt();
		switch (op) {
		case 1:
			//dinheiro			
			registrarPagamento();
			registrarVenda();
			imprimirNota();
			break;
		case 2:
			cancelarVenda();
			break;
		}
		
	}

	public void iniciarVenda() {

		listaP = new ArrayList<ItemProduto>();
		Scanner s = new Scanner(System.in);
		this.valorVenda = 0;
		System.out.println("Incluir novo Produto? \n 1 - Sim \n 2 - Não \n");
		while (s.nextInt() == 1) {
			ItemProduto ip = new ItemProduto();
			System.out.print("Codigo do Produto: ");
			int codigoP = s.nextInt();
			System.out.print("Quantidade: ");
			int qtdP = s.nextInt();
			if (!ip.incluirItem(codigoP, qtdP)) {
				// deu ruim
				System.out.println("Produto não cadastrado");
			} else {
				listaP.add(ip);
				this.valorVenda += ip.getValorItem();
			}
			System.out.println("Incluir novo Produto? \n 1 - Sim \n 2 - Não \n");	
		}

		Date d = new Date();
		setDataVenda(d.getDay() + "/" + d.getMonth() + "/" + d.getYear());
		setHoraVenda(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
		
		
	}

	public void registrarPagamento() {
		Scanner s = new Scanner(System.in);
		System.out.println("Qual tipo de pagamento? \n (1) Dinheiro \n " +
				"(2) Cartão de crédito \n (3) Cartão de débito \n (4) Cheque \n");
		int tipoP = s.nextInt();
		switch (tipoP) {
		case 1:
			this.tipoPagamento = "DI";
			System.out.print("Digite o valor Pago: ");
			this.valorPago = s.nextDouble();
			System.out.println("Troco: " + calcularTroco());
			break;
		case 2:
			//A implementar
			this.tipoPagamento = "CC";
			break;
		case 3:
			//A implementar
			this.tipoPagamento = "CD";
			break;
		case 4:
			//A implementar
			this.tipoPagamento = "CH";
			break;
		}
		
		
	}

	public void cancelarVenda() {
		this.dataVenda = "";
		this.horaVenda = "";
		this.valorVenda = 0.0;
		this.tipoPagamento = "";
		this.valorPago = 0.0;
	}

	public void registrarVenda() {
		dv = new daoVenda(this, getCodigoFuncionario(), getCodigoCliente(), listaP);
		if(dv.inserir())
			System.out.println("Venda Registrada com Sucesso");
		else
			System.out.println("Não foi possivel registrar a venda, consulte suporte.");
	}

	public void imprimirNota() {
		// Pegar lista de produtos e imprimir
		System.out.println("CUPOM FISCAL");
		System.out.println("Cliente: " + getCodigoCliente());
		System.out.println("Funcionario: " + getCodigoFuncionario());
		for (int i = 0; i < listaP.size(); i++) {
			System.out.println("Codigo: " + listaP.get(i).getP().getCodigo()
					+ " Descricao: " + listaP.get(i).getP().getDescricao()
					+ " Quantidade: " + listaP.get(i).getQtdProdutos()
					+ " Preco: " + listaP.get(i).getValorItem());
		}
		System.out.println("Pagamento em: " + this.getTipoPagamento());
		System.out.println("Preço total: " + this.getValorVenda());
		System.out.println("Volte Sempre Obrigado.");
	}

	public int getCodigoVenda() {
		return dv.getId();
	}

	public double calcularTroco() {
		return (this.valorPago - this.valorVenda);
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getHoraVenda() {
		return horaVenda;
	}

	public void setHoraVenda(String horaVenda) {
		this.horaVenda = horaVenda;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

}
