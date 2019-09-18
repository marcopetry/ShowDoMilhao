import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RodaRoda extends Roda{
	private static final String nomeArquivo = "palavras.txt";
	private static final int qtdStringsArquivo = 5;
	private static final int roleta[] = {0, 1, 100, 200, 400, 500, 700, 900}; // tamanho 8. O "1" é pra passar a vez
	private static final int qtdPartidas = 2; //Número de partidas que serão jogadas
	private static int valorRodada; //Valor da roleta
	private ListaCircular jogador;
	private Palavra palavraRodada;
	private String controle = ""; // vai imprimir as underlines na tela, quando sair uma letra, ela muda para a letra
	private ArrayList<Palavra> palavrasUsadasJogo = new ArrayList<Palavra>();
	private ArrayList<Character> letrasDisponiveis = new ArrayList<Character>();
	
	public RodaRoda() { //Vai iniciar todo o jogo
		ListaCircular jogadores = new ListaCircular();
		jogadores.iniciarJogadores();
		this.jogador = jogadores;		
	}
	
	public void iniciarAtributosPartida() {
		Palavra palavraRodada;
		boolean palavraValida;
		do {
			palavraRodada = new Palavra(obterStringInteira(RodaRoda.nomeArquivo, RodaRoda.qtdStringsArquivo));
			palavraValida = verificarPalavra(palavraRodada);
		}while(palavraValida == false);

		this.palavraRodada = palavraRodada;
		this.controle = iniciarStringControle(this.palavraRodada.getPalavraString());	
		letrasDisponiveis.clear();
		this.iniciarArrayLetrasDisponiveis();
		this.palavrasUsadasJogo.add(palavraRodada);		
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public int getQtdStrings() {
		return qtdStringsArquivo;
	}
	
	public int getQtdPartidas() {
		return RodaRoda.qtdPartidas;
	}
	
	private void getRoleta() { 	
		System.out.println(getJogador().getParticipanete().getNome() + " rodou a roleta.");
		RodaRoda.valorRodada = roleta[retornarValorSorteado() % 8];
		System.out.println("O valor sorteado da roleta é: ");
		if(RodaRoda.valorRodada == 0) {
			System.out.println("Você passa a vez e perde todos os seus pontos.");
			getJogador().getParticipanete().setPontos(0);
			getJogador().PassarJogador();
		} else if(RodaRoda.valorRodada == 1) {
			System.out.println("Você passa a vez.");
			getJogador().PassarJogador();
		}else {
			System.out.println("A jogada está valendo R$ " + RodaRoda.valorRodada);
		}
	}
	
	private ListaCircular getJogador() {
		return jogador;
	}
	
	public Palavra getPalavra() {
		return palavraRodada;
	}
	
	public String getStringControle() {
		return controle;
	}	
	
	public boolean verificarPalavra(Palavra palavraNova) {
		for(int i=0; i<palavrasUsadasJogo.size(); i++) {
			if(palavrasUsadasJogo.get(i).getPalavraString().equals(palavraNova.getPalavraString()))
				return false;
		}
		return true;
	}
	
	public void iniciarArrayLetrasDisponiveis() {
		letrasDisponiveis.add('a');
		letrasDisponiveis.add('b');
		letrasDisponiveis.add('c');
		letrasDisponiveis.add('d');
		letrasDisponiveis.add('e');
		letrasDisponiveis.add('f');
		letrasDisponiveis.add('g');
		letrasDisponiveis.add('h');
		letrasDisponiveis.add('i');
		letrasDisponiveis.add('j');
		letrasDisponiveis.add('k');
		letrasDisponiveis.add('l');
		letrasDisponiveis.add('m');
		letrasDisponiveis.add('n');
		letrasDisponiveis.add('o');
		letrasDisponiveis.add('p');
		letrasDisponiveis.add('q');
		letrasDisponiveis.add('r');
		letrasDisponiveis.add('s');
		letrasDisponiveis.add('t');
		letrasDisponiveis.add('u');
		letrasDisponiveis.add('v');
		letrasDisponiveis.add('w');
		letrasDisponiveis.add('x');
		letrasDisponiveis.add('y');
		letrasDisponiveis.add('z');		
	}
	
	public void removerLetraDisponivel(char letra) { //Testar
		for(int i=0; i< letrasDisponiveis.size(); i++) {
			if(letrasDisponiveis.get(i).charValue() == letra) {
				letrasDisponiveis.remove(i);
				break;
			}
				
		}
	}
	
	public String iniciarStringControle(String palavraJogo){
		String retorno = "";
		int j=1;
		for(int i=0; i<palavraJogo.length(); i++) {
			if(palavraJogo.substring(i, j).equals(" "))
				retorno += " ";
			else				
				retorno += "_ ";
			j++;
		}		
		return retorno;
	}
	
	//-------------------------------------------------------------------
	//Daqui pra baixo começa a jogabilidade do RodaRoda
	
	public boolean imprimirOpcoesCaracteres() {
		System.out.println();
		System.out.println("As letras disponíveis são: ");
		for(int i=0; i< letrasDisponiveis.size(); i++) {
			System.out.print(letrasDisponiveis.get(i) + "  ");
		}
		System.out.println();
		return escolherLetra();
	}
		
	public boolean escolherLetra() {
		boolean jogadaValida = false;
		Scanner lerLetra = new Scanner(System.in);
		char letra;
		do {			
			System.out.println("Escolha uma letra que tenha na palavra:");
			letra = lerLetra.next().charAt(0);					
			if(letrasDisponiveis.contains(letra)) {
				jogadaValida = true;
			}
			else {
				System.out.println("A letra escolhida já foi usada ou é inválida.");
			}			
			
		}while(jogadaValida == false);		

		removerLetraDisponivel(letra);		
		return substituirCaracteresStringControle(letra);
	}	
	
	public boolean substituirCaracteresStringControle(char letraPalavra) { //Vai substituir as letras e retornar verdadeira se tinha ou falso se não tinha na palavra
		int contadorLetra=0;
		String controle = "";
		boolean temLetraPalavra = false;
		
		for(int i=0; i< getPalavra().getPalavraString().length(); i++) {			
			if(getPalavra().getPalavraString().charAt(i) == letraPalavra) {
				controle += letraPalavra;
				controle += ' ';
				contadorLetra++;
				temLetraPalavra = true;
			}
			else if(getPalavra().getPalavraString().charAt(i) == ' ' ) {
				controle += ' ';
			}else if(!letrasDisponiveis.contains(getPalavra().getPalavraString().charAt(i))){				
				controle += getPalavra().getPalavraString().charAt(i);
				controle += ' ';				
			}else {
				controle += "_ ";
			}
		}
		
		
		if(contadorLetra > 0) {
			System.out.println("Tem " + contadorLetra + " letra(s) do caracter " + letraPalavra + ".");
			getJogador().getParticipanete().setPontos(contadorLetra * RodaRoda.valorRodada); //Altera a pontuação
		}else {
			System.out.println("Não tinha a letra que você escolheu na palavra.");
		}
			 
		this.controle = controle;		
		
		return temLetraPalavra;
		
	}
	
	public void imprimirDicaStringControle() {
		System.out.println("A dica para palavra é: " + getPalavra().getDica());
		System.out.println("A palavra no momento está " + getStringControle());
	}
	
	
	public Participante jogoRodaRoda() { //Esse método vai rodar o jogo e retornar o jogador que vai disputar o jogo do milhão
		Participante maisPontos = getJogador().getParticipanete();
		while(getStringControle().contains("_")) {
			System.out.println("É a vez do " + getJogador().getParticipanete().getNome() + " rodar a roleta.");
			System.out.println("Ele tem " + getJogador().getParticipanete().getPontos() + " pontos.");
			System.out.println();
			getRoleta();
			
			pararExecucao();	
			
			while(getStringControle().contains("_") && RodaRoda.valorRodada != 0 && RodaRoda.valorRodada != 1) {
				imprimirPainelPontos();
				
				imprimirDicaStringControle();
				if(imprimirOpcoesCaracteres() == false)
					break;
				
				if(getJogador().getParticipanete().getPontos() > maisPontos.getPontos()) {					
					maisPontos = getJogador().getParticipanete();				
				}				
				
				if(getStringControle().contains("_")) {
					System.out.println("Você joga novamente.");
					pararExecucao();
					System.out.println();
					getRoleta();
				}
				System.out.println("O jogador com mais pontos é " + maisPontos.getNome() + " com " + maisPontos.getPontos() + " pontos.");
			}
			
			if(getStringControle().contains("_") && RodaRoda.valorRodada != 0 && RodaRoda.valorRodada != 1){
				System.out.println("Você passa a vez.");
				System.out.println();
				getJogador().PassarJogador();
			}
			pararExecucao();
			System.out.println();
		}
		System.out.println("Você completou a palavra e vai ganhar mais 1000 pontos.");
		getJogador().getParticipanete().setPontos(1000);
		if(getJogador().getParticipanete().getPontos() > maisPontos.getPontos()) {					
			maisPontos = getJogador().getParticipanete();				
		}
		System.out.println("O jogador com mais pontos é " + maisPontos.getNome() + " com " + maisPontos.getPontos() + " pontos.");
		imprimirPainelPontos();				
		System.out.println("A palavra completa é:  " + getStringControle());
		pararExecucao();
		propagandaJequiti();
		
		return maisPontos;
	}
	
	public Participante gerenciadorRodaRoda() {
		
		Participante vencedorRodaRoda = getJogador().getParticipanete();
		int contadorPartidas = 0;

		while(contadorPartidas < RodaRoda.qtdPartidas) {
			iniciarAtributosPartida();
			contadorPartidas++;
			vencedorRodaRoda = jogoRodaRoda();
		}

		return vencedorRodaRoda;
		}
	
	public void imprimirPainelPontos() {		
		System.out.println("\n\n");
		
		Participante participante = getJogador().getParticipanete();
		painelPontos(participante);
		getJogador().PassarJogador();
		while(getJogador().getParticipanete() != participante) {
			painelPontos(getJogador().getParticipanete());
			getJogador().PassarJogador();
		}
			
		System.out.println();
	}
	
	public void painelPontos(Participante jogador) {
		System.out.println("O jogador " + jogador.getNome() + " tem " + jogador.getPontos() + " pontos.");
	}	
	
	public void propagandaJequiti() {
		System.out.println("Compre produtos JEQUITI e concorra a prêmios e quem sabe você seja o próximo a vir aqui. ");
		System.out.println("Encontre uma consultora Jequiti.");
		pararExecucao();
	}
}
