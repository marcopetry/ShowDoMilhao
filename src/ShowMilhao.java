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
	//Aqui começa as funções de jogabilidade
	
	public void iniciarShowMilhao() {
		int contadorPerguntas = 0;
		int opcaoEscolhida = 0;
		Scanner resposta = new Scanner(System.in);
		System.out.println("\n\n");		
		System.out.println("O jogador vencedor que vai participar do Show do Milhão é " + getJogador().getNome());
		
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
						System.out.println("Coloque um número inteiro referente a opção.");
						resposta.nextLine();
						opcaoValida = false;
					}
				} while(opcaoValida == false);
					
				if(opcaoEscolhida < 0 || opcaoEscolhida > 4)
					System.out.println("Opção inválida.");
			}while(opcaoEscolhida < 0 || opcaoEscolhida > 4);
						
			if(opcaoEscolhida == 0) {
				System.out.println("Você parou e ganhou " + getJogador().getPontosShowMilhao() + " reais.");
				break;
			}else if(opcaoEscolhida == getQuestao().getRespostaCerta()) {
				System.out.println("Você acertou a resposta da pergunta.");
				getJogador().setPontosShowMilhao(getValorQuestao());				
				if(contadorPerguntas == ShowMilhao.valorPerguntas.length - 1) {
					System.out.println("Vamos para a última pergunta que vale o prêmio máximo.");
				} else	if(contadorPerguntas < ShowMilhao.valorPerguntas.length) {
						System.out.println("Vamos para próxima questão.");						
				}		
				else {
					System.out.println("Você acertou todas as respostas.");
					System.out.println("Parabéns!!!");
				}
			}else {
				getJogador().setPontosShowMilhao(0);
				System.out.println("Você errou e perdeu tudo.");
				break;
			}			
			System.out.println();
			System.out.println();
		}
		System.out.println("O jogo acabou e você ganhou " + getJogador().getPontosShowMilhao());
	}
	
	public void imprimirQuestao(int contador) {
		System.out.println();
		System.out.println("Questão " + contador);
		System.out.println("Valendo " + getValorQuestao() + " reais.");
		System.out.println("Pergunta: " + getQuestao().getPergunta());
		System.out.println("Opção 1: " + getQuestao().getResposta1());
		System.out.println("Opção 2: " + getQuestao().getResposta2());
		System.out.println("Opção 3: " + getQuestao().getResposta3());
		System.out.println("Opção 4: " + getQuestao().getResposta4());
		System.out.println("Opção 0: Desistir e ficar com o valor acumulado.");
	}
	

}
