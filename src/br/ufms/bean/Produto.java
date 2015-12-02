package br.ufms.bean;
import br.ufms.dao.daoProduto;

public class Produto {
	    
	    private int codigo;
	    private String descricao;
	    private String fabricante;
	    private double precoVendaVarejo;
	    private double precoVendaAtacado;
	    //novo atributo de produto
	    private int codigoCategoria;
	    
	    private daoProduto dao;
	    
	    public Produto(){
	        this.codigo = 0;
	        this.fabricante = "";
	        this.descricao = "";
	        this.precoVendaAtacado = 0.0;
	        this.precoVendaVarejo = 0.0;
	        this.codigoCategoria = 0;
	        this.dao = new daoProduto(this);
	    }
	    
	    public Produto( String descricao, String fabricante, double precoVarejo, double precoAtacado){
	        
	        this.fabricante = fabricante;
	        this.descricao = descricao;
	        this.precoVendaVarejo = precoVarejo;
	        this.precoVendaAtacado = precoAtacado;
	        this.dao = new daoProduto(this);
	    }
	    
	    //Novo construtor que passa a categoria como um atributo
	    public Produto (String descricao, String fabricante, double precoVarejo, double precoAtacado, int codigoCategoria){
	        
	        this.fabricante = fabricante;
	        this.descricao = descricao;
	        this.precoVendaVarejo = precoVarejo;
	        this.precoVendaAtacado = precoAtacado;
	        this.codigoCategoria = codigoCategoria;
	        this.dao = new daoProduto(this);
	    }
	    
	    public void cadastrarProduto(){
	    	//daoProduto daoProduto = new daoProduto();	    	
	    	if(dao.inserir(this))
	    		System.out.println("Produto inserido com sucesso!");
	    	else 
	    		System.out.println("Produto já cadastrado");
	    }
	    
	    public void excluirProduto(){
	    	//daoProduto daoProduto = new daoProduto();
	    	if(dao.excluir(this)){
	    		System.out.println("Produto excluid com sucesso!");
	    	}
	    	else{
	    		System.out.println("Não foi possivel excluir!");
	    	}
	    }
	    
	    // variabilidades, excluir e editar
	    
	    public void buscarProduto(int codProd) {
	    	//daoProduto daoProduto = new daoProduto();
	    	Produto pt = new Produto();
	    	
	    	pt = dao.buscarPorCod(codProd);
	    	
	    	this.setDescricao(pt.getDescricao());
	    	this.setFabricante(pt.getFabricante());
	    	this.setPrecoVendaAtacado(pt.getPrecoVendaAtacado());
	    	this.setPrecoVendaVarejo(pt.getPrecoVendaVarejo());
	    	
		}
	    
	    public void editarProduto() {
		}

	    /**
	     * @return the codigoProduto
	     */
	    public int getCodigo() {
	        return codigo;
	    }

	    /**
	     * @param codigoProduto the codigoProduto to set
	     */
	    public void setCodigo(int codigoProduto) {
	        this.codigo = codigoProduto;
	    }

	    /**
	     * @return the descricao
	     */
	    public String getDescricao() {
	        return descricao;
	    }

	    /**
	     * @param descricao the descricao to set
	     */
	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }
	    
	    /**
	     * @param descricao the fabricante to set
	     */
	    public void setFabricante(String fabricante) {
	        this.fabricante = fabricante;
	    }
	    
	    /**
	     * @return the fabricante
	     */
	    public String getFabricante() {
	        return fabricante;
	    }

	    /**
	     * @return the precoVarejo
	     */
	    public double getPrecoVendaVarejo() {
	        return precoVendaVarejo;
	    }

	    /**
	     * @param precoVarejo the precoVarejo to set
	     */
	    public void setPrecoVendaVarejo(double precoVarejo) {
	        this.precoVendaVarejo = precoVarejo;
	    }

	    /**
	     * @return the precoAtacado
	     */
	    public double getPrecoVendaAtacado() {
	        return precoVendaAtacado;
	    }

	    /**
	     * @param precoAtacado the precoAtacado to set
	     */
	    public void setPrecoVendaAtacado(double precoAtacado) {
	        this.precoVendaAtacado = precoAtacado;
	    }

	    //metodo novo por instanciação da LPS 
		public int getCodigoCategoria() {
			return codigoCategoria;
		}
		
		//metodo novo por instanciação da LPS 
		public void setCodigoCategoria(int codigoCategoria) {
			this.codigoCategoria = codigoCategoria;
		}
	    
	    
	    
	}

