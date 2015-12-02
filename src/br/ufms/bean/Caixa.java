package br.ufms.bean;

import java.util.Date;

import br.ufms.dao.daoCaixa;

public class Caixa {
	
	public int idCaixa;
	private Date horaAbertura;
	private Date horaFechamento;
	private double valorCaixaAbertura;
	private double valorCaixaFechamento;
	//private double valorCaixaAtual;
	private String statusCX;	
	private int funCod;
	private daoCaixa dc;
	
	public Caixa(){		
	}
	
	public void abrirCaixa(int func, int idCaixa, double valorCaixaAbertura){
		Date d = new Date();
		this.setHoraAbertura(d);
		this.setValorCaixaAbertura(valorCaixaAbertura);
		this.setHoraFechamento(d);
		this.setValorCaixaFechamento(valorCaixaAbertura);
		this.setFunCod(func);
		this.setIdCaixa(idCaixa);
		
		dc = new daoCaixa(this, func);
		if(dc.abrir()){
			System.out.println("Caixa aberto com sucesso!");
		}else{
			System.out.println("Erro ao abrir caixa");
		}
	}
	
	public void FecharCaixa(double valorCaixaFechamento){
		Date d = new Date();
		setHoraFechamento(d);
		this.setValorCaixaFechamento(valorCaixaFechamento);		
		if(dc.fechar(this)){
			System.out.println("Caixa fechado com sucesso!");
		}else{
			System.out.println("Erro ao fechar caixa");
		}
	}
	
	
	public Date getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(Date horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public Date getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(Date horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public double getValorCaixaAbertura() {
		return valorCaixaAbertura;
	}
	public void setValorCaixaAbertura(double valorCaixaAbertura) {
		this.valorCaixaAbertura = valorCaixaAbertura;
	}
	public double getValorCaixaFechamento() {
		return valorCaixaFechamento;
	}
	public void setValorCaixaFechamento(double valorCaixaFechamento) {
		this.valorCaixaFechamento = valorCaixaFechamento;
	}
	public double getFunCod() {
		return funCod;
	}
	public void setFunCod(int funCod) {
		this.funCod = funCod;
	}

	public String getStatusCX() {
		return statusCX;
	}

	public void setStatusCX(String statusCX) {
		this.statusCX = statusCX;
	}
	
	public int getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(int idCaixa) {
		this.idCaixa = idCaixa;
	}	

}
