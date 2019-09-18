
public class Questao {
	private String Pergunta;
	private String Resposta1;
	private String Resposta2;
	private String Resposta3;
	private String Resposta4;
	private int RespostaCerta;
	
	public Questao(String QuestaoInteiraArquivo) {
		String []vetor = QuestaoInteiraArquivo.split(",");
		this.Pergunta = vetor[0];
		this.Resposta1 = vetor[1];
		this.Resposta2 = vetor[2];
		this.Resposta3 = vetor[3];
		this.Resposta4 = vetor[4];		
		this.RespostaCerta = Integer.parseInt(vetor[5]); //Preciso ver o que ele entrega		
	}	
	
	public String getPergunta() {
		return Pergunta;
	}
	
	public String getResposta1() {
		return Resposta1;
	}
	
	public String getResposta2() {
		return Resposta2;
	}
	
	public String getResposta3() {
		return Resposta3;
	}
	
	public String getResposta4() {
		return Resposta4;
	}
	
	public int getRespostaCerta() {
		return RespostaCerta;
	}

}
