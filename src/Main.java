
public class Main{
	
	public static void main(String[] args) {		
		RodaRoda jogoRodaRoda = new RodaRoda();		
		Participante vencedorRodaRoda;
		vencedorRodaRoda = jogoRodaRoda.gerenciadorRodaRoda();				
		ShowMilhao jogo = new ShowMilhao(vencedorRodaRoda);
		jogo.iniciarShowMilhao();				
	}
}