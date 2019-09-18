import java.util.Scanner;

public class ListaCircular {
	private Participante participante;	
	private int contador;
	private static final int QTDJOGADORES = 3;
	
	public ListaCircular() {
        this.participante = null;
        contador = 0;        
	}
	
	public void adicionarParticipante(Participante novo) {		
        if (this.participante == null) {
        	this.participante = novo;
        	participante.setProx(novo);            
        } 
        else {
        	novo.setProx(this.participante);
        	Participante auxiliar = this.getParticipanete();
        	while(auxiliar.getProx() != this.participante) {
        		auxiliar = auxiliar.getProx();
        	}
        	auxiliar.setProx(novo);	                     
        }
        contador++;
    }
	public Participante getParticipanete() {
		return participante;
	}
	
	public void PassarJogador() {
		this.participante = participante.getProx();
	}
	
	public void iniciarJogadores() {				
		Scanner entrada = new Scanner(System.in);
		
		for(int i=0; i<QTDJOGADORES; i++) { //Entrar com os três jogadores
			boolean nomeValido;
			String nome;
			System.out.println("Digite o nome do partipante que vai adicionar ao Roda Roda.:");
			do {
				nome = entrada.nextLine();
				nomeValido = validarNomeJogador(nome);
				if(nomeValido == false)
					System.out.println("Nome inválido. Digite novamente.");
			} while(nomeValido == false);
				
			Participante pessoa = new Participante(nome);			
			this.adicionarParticipante(pessoa);			
		}
		//entrada.close();		
	}
	
	public boolean validarNomeJogador(String nome) {
		boolean verificador = true;
		int contadorLetra = 0;
		if(nome.isEmpty()) {
			System.out.println("Você não digitou nada.");
			return false;
		}else {
			for(int i = 0; i<nome.length(); i++) {				
				if(contadorLetra > 0 && nome.charAt(i) == ' ') {
					contadorLetra=0;
					continue;
				}
				
				if( validarCaracterAcentuado(nome.charAt(i)) ==  true) {
					contadorLetra++;
					continue;
				}
				
				contadorLetra++;
				if(nome.charAt(i) < 65 || nome.charAt(i) > 122 || nome.charAt(i) > 90 && nome.charAt(i) < 97) {
					contadorLetra = 0;
					verificador = false;
				}
			}
		}
		return verificador;
	}
	
	public boolean validarCaracterAcentuado(char caracter) {		
		
		if(caracter == 'Á'  || caracter == 'á'  || caracter == 'Ã' || caracter == 'ã' || caracter == 'Â' || caracter == 'â'
				|| caracter == 'É' || caracter == 'é' || caracter == 'Ê' || caracter == 'ê' || caracter == 'í' || caracter == 'Í'
				|| caracter == 'Î' || caracter == 'î' || caracter == 'Ó' || caracter == 'ó' || caracter == 'Ô' || caracter == 'ô'
				|| caracter == 'Õ' || caracter == 'õ' || caracter == 'Ú' || caracter == 'ú' || caracter == 'Û' || caracter == 'û' || caracter == 'ç') {
			return true;
		} else {
			return false;
		}
	}

}
