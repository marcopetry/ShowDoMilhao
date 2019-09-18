
public class Participante {
	private String nome; //N�o coloquei m�todo setNome pois n�o usarei.
	private int pontos;
	private int pontosShowMilhao;
	private Participante prox;
	
	public Participante(String nome) {
		this.nome = nome;
		this.pontos = 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public int getPontosShowMilhao() {
		return pontosShowMilhao;
	}
	
	public Participante getProx() {
		return prox;
	}
	
	public void setPontos(int pontos) {
		if(pontos == 0) {			
			System.out.println("Voc� perdeu todos os seus pontos.");
			this.pontos = pontos;
		}
		else {
			System.out.println("Voc� ganhou " + pontos + " pontos.");
			this.pontos +=pontos;
			System.out.println("Seus pontos agora s�o " + this.pontos + ".");
		}
		
	}
	
	public void setProx(Participante prox) {
		this.prox = prox;
	}
	
	public void setPontosShowMilhao(int valor) {
		this.pontosShowMilhao = valor;
		if(valor > 0) {
			System.out.println("Voc� ganhou " + valor + " reais no Show do Milh�o.");
		}
			
	}
}
