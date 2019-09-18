import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShowMilhao extends Roda{
	private static final String nomeArquivo = "questoes.txt";
	private static final int qtdStringArquivo = 15;
	private static final int []valorPerguntas = {1000, 5000, 10000, 25000, 50000, 100000, 250000, 500000, 1000000}; //9valores
	private Participante jogador;
	private Questao questaoRodada;
	private ArrayList<Questao> questoesRespondidas = new ArrayList<Questao>();
	
	public ShowMilhao(Participante jogador) {
		this.jogador = jogador;		
	}
	
	public void pegarQuestoes() {
		Questao questaoRodada;
		boolean questaoValida;
		do {
			questaoRodada = new Questao(obterStringInteira(ShowMilhao.nomeArquivo, ShowMilhao.qtdStringArquivo));
			questaoValida = validarQuestao(questaoRodada);
		} while(questaoValida == false);
		
		questoesRespondidas.add(questaoRodada);		
		this.questaoRodada = questaoRodada;
	}
	
	public Participante getJogador() {
		return jogador;
	}
	
	public Questao getQuestao() {
		return questaoRodada;
	}
	
	public int getValorQuestao() {
		return valorPerguntas[questoesRespondidas.size()-1];
	}
	
	public boolean validarQuestao(Questao novaQuestao) {		
		for(int i=0; i<questoesRespondidas.size(); i++) {			
			if(questoesRespondidas.get(i).getPergunta().equals(novaQuestao.getPergunta())) {				
				return false;
			}
				
		}
		return true;
	}
	
	//-------------------------------------------------------------------------------------------
	//Aqui come�a as fun��es de jogabilidade
	
	public void iniciarShowMilhao() {
		int contadorPerguntas = 0;
		int opcaoEscolhida = 0;
		Scanner resposta = new Scanner(System.in);
		System.out.println("\n\n");		
		System.out.println("O jogador vencedor que vai participar do Show do Milh�o � " + getJogador().getNome());
		
		while(contadorPerguntas < ShowMilhao.valorPerguntas.length) {
			pegarQuestoes();
			contadorPerguntas++;
			pararExecucao();
			
			imprimirQuestao(contadorPerguntas);
			
			do {				
				boolean opcaoValida;
				do {
					try {						
						opcaoEscolhida = resposta.nextInt();					
						opcaoValida = true;
					} catch (InputMismatchException erro) {
						System.out.println("Coloque um n�mero inteiro referente a op��o.");
						resposta.nextLine();
						opcaoValida = false;
					}
				} while(opcaoValida == false);
					
				if(opcaoEscolhida < 0 || opcaoEscolhida > 4)
					System.out.println("Op��o inv�lida.");
			}while(opcaoEscolhida < 0 || opcaoEscolhida > 4);
						
			if(opcaoEscolhida == 0) {
				System.out.println("Voc� parou e ganhou " + getJogador().getPontosShowMilhao() + " reais.");
				break;
			}else if(opcaoEscolhida == getQuestao().getRespostaCerta()) {
				System.out.println("Voc� acertou a resposta da pergunta.");
				getJogador().setPontosShowMilhao(getValorQuestao());				
				if(contadorPerguntas == ShowMilhao.valorPerguntas.length - 1) {
					System.out.println("Vamos para a �ltima pergunta que vale o pr�mio m�ximo.");
				} else	if(contadorPerguntas < ShowMilhao.valorPerguntas.length) {
						System.out.println("Vamos para pr�xima quest�o.");						
				}		
				else {
					System.out.println("Voc� acertou todas as respostas.");
					System.out.println("Parab�ns!!!");
				}
			}else {
				getJogador().setPontosShowMilhao(0);
				System.out.println("Voc� errou e perdeu tudo.");
				break;
			}			
			System.out.println();
			System.out.println();
		}
		System.out.println("O jogo acabou e voc� ganhou " + getJogador().getPontosShowMilhao());
	}
	
	public void imprimirQuestao(int contador) {
		System.out.println();
		System.out.println("Quest�o " + contador);
		System.out.println("Valendo " + getValorQuestao() + " reais.");
		System.out.println("Pergunta: " + getQuestao().getPergunta());
		System.out.println("Op��o 1: " + getQuestao().getResposta1());
		System.out.println("Op��o 2: " + getQuestao().getResposta2());
		System.out.println("Op��o 3: " + getQuestao().getResposta3());
		System.out.println("Op��o 4: " + getQuestao().getResposta4());
		System.out.println("Op��o 0: Desistir e ficar com o valor acumulado.");
	}
	

}
