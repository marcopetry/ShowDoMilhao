import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;	

public abstract class Roda {
	
	public int retornarValorSorteado() {
		Random numero = new Random();
		int sorteado;
		sorteado = numero.nextInt();
		if(sorteado < 0)
			sorteado = sorteado * -1;
		return sorteado;
	}
			
	public String leitor(String path, int qtd_strings) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        int i=0;
        int retornoArquivo = 1 + (retornarValorSorteado() % qtd_strings);        
        while (i < retornoArquivo) {            
            linha = buffRead.readLine();
            i++;
        }
        buffRead.close();
        return linha;
    }
		
	public String obterStringInteira(String nomeArquivo, int qtdStrings) {
		boolean erro;
		String stringInteira = "";
		do {			
			try {				
				stringInteira = leitor(nomeArquivo, qtdStrings);
				erro = false;
			}
			catch (IOException e) {
				System.out.println("Deu problema na leitura do arquivo. Tentaremos de novo.");
				erro = true;
			}
		}while(erro == true);
		return stringInteira;
	}
	
	public void pararExecucao() {
		try {
			System.out.println("Tecle ENTER para continuar.");
			System.in.read();
		}catch(IOException e) {
			
		}	
	}
}
