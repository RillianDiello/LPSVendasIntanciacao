package br.ufms.bean;

public class mainInstanciacao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Categoria cat1 = new Categoria();
		cat1.setDescricaoCat("bebida alcoolica");		
		Categoria cat2 = new Categoria();
		cat2.setDescricaoCat("refrigerante");
		Categoria cat3 = new Categoria();
		cat3.setDescricaoCat("biscoitos");
		Categoria cat4 = new Categoria();
		cat4.setDescricaoCat("salgados");
		Categoria cat5 = new Categoria();
		cat5.setDescricaoCat("doces");
		Categoria cat6 = new Categoria();
		cat6.setDescricaoCat("laticinio");
		
		Produto pro1 = new Produto("cerveja skol", "skol", 2.15, 2, 1);
		Produto pro2 = new Produto("biscoito recheado", "mabel", 1.25, 1.09, 3);
		Produto pro3 = new Produto("coca-cola 2L", "coca-cola", 5.60, 5.38, 2);
		Produto pro4 = new Produto("batata ruffles 200 grms", "ruffles", 3.50, 3.23, 4);
		Produto pro5 = new Produto("leite 1 litro", "piracanjuba", 2.47, 2.38, 6);
		
		Cliente cl = new Cliente("defaut", "00000001", "default", "99999999900", "F");
		cl.cadastrarCliente();
		
	
		
		
		
	}

}
