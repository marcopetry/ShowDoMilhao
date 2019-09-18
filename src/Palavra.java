
public class Palavra extends Roda{
	private String palavra;
	private String dica;
	
	public Palavra(String completa) {
		String separada[] = completa.split(",");
		this.palavra = separada[0];
		this.dica = separada[1];
	}
	
	public String getPalavraString() {
		return palavra;
	}
	
	public String getDica() {
		return dica;
	}
	
	public void setPalavraString(String palavra) {
		this.palavra = palavra;
	}
	
	public void setDica(String dica) {
		this.dica = dica;
	}
}
