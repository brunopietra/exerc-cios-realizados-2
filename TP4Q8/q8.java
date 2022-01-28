import java.io.*;
import java.util.Scanner;

class Serie{
    private String nome;
    private String formato;
    private String duracao;
    private String pais;
    private String idioma;
    private String emissora;
    private String transmOriginal;
    private int temporadas;
    private int eps;

    Serie() throws Exception{
        nome = "";
        formato = "";
        duracao = "";
        pais = "";
        idioma = "";
        emissora = "";
        transmOriginal = "";
        temporadas = 0;
        eps = 0;
    }

    public Serie(String nome, String formato, String duracao, String pais, String idioma, 
    String emissora, String transmOriginal, int temporadas, int eps) {
        nome = this.nome;
        formato = this.formato;
        duracao = this.duracao;
        pais = this.pais;
        idioma = this.idioma;
        emissora = this.emissora;
        transmOriginal = this.transmOriginal;
        temporadas = this.temporadas;
        eps = this.eps;
    }

    //Metodos set

    public void setNome(String line){
        String newline = "";

        
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '_'){
                newline += ' ';
        
            }else if(line.charAt(i) == '.'){
                i = line.length();
            }
            else{
                newline += line.charAt(i);
            }
        }

        if(newline.charAt(0) == ' ' && newline.charAt(newline.length() - 1) == ' '){
            this.nome = newline.substring(1, newline.length() - 1);
        }
        else if(newline.charAt(0) == ' '){
            this.nome = newline.substring(1, newline.length());
        }
        else if(newline.charAt(newline.length() - 1) == ' '){
            this.nome = newline.substring(0, newline.length() - 1);
        }
        else{
            this.nome = newline;
        }

    }

    public void setFormato(String line){
        line = removeTags(line);
        if(line.charAt(0) == ' '){
            line = line.substring(1);
        }
        if(line.charAt(line.length() - 1) == ' '){
            line = line.substring(0, line.length() - 1);
        }
        this.formato = line;

    }

    public void setDuracao(String line){
        line = removeTags(line);
        if(line.charAt(0) == ' '){
            line = line.substring(1);
        }
        if(line.charAt(line.length() - 1) == ' '){
            line = line.substring(0, line.length() - 1);
        }
        this.duracao = line;

    }

    public void setPais(String line){
        line = removeTags(line);
        if(line.charAt(0) == ' '){
            line = line.substring(1);
        }
        if(line.charAt(line.length() - 1) == ' '){
            line = line.substring(0, line.length() - 1);
        }
        this.pais = line;

    }

    public void setEmissora(String line){
        line = removeTags(line);
        if(line.charAt(0) == ' '){
            line = line.substring(1);
        }
        if(line.charAt(line.length() - 1) == ' '){
            line = line.substring(0, line.length() - 1);
        }
        this.emissora = line;

    }

    public void setIdioma(String line){
        line = removeTags(line);
        if(line.charAt(0) == ' '){
            line = line.substring(1);
        }
        if(line.charAt(line.length() - 1) == ' '){
            line = line.substring(0, line.length() - 1);
        }
        this.idioma = line;

    }

    public void setTransmOriginal(String line){
        line = removeTags(line);
        if(line.charAt(0) == ' '){
            line = line.substring(1);
        }
        if(line.charAt(line.length() - 1) == ' '){
            line = line.substring(0, line.length() - 1);
        }
        this.transmOriginal = line;

    }

    public void setTemporadas(String line){
        String temp = removeTags(line);
        String newTemp = "";
        
        for(int i = 0; i < temp.length(); i++){
            if(temp.charAt(i) > 47 && temp.charAt(i) < 58){
                newTemp += temp.charAt(i);
            }
            else if(temp.charAt(i) == '(' || temp.charAt(i) == ' '){
                i = temp.length();
            }
            

            
        }

        this.temporadas = Integer.parseInt(newTemp);

    }

    public void setEps(String line){
        String ep = removeTags(line);
        String newEp = "";

        
        for(int i = 0; i < ep.length(); i++){
            if(ep.charAt(i) > 47 && ep.charAt(i) < 58){
                newEp += ep.charAt(i);
            }
            else if(ep.charAt(i) == '(' || ep.charAt(i) == ' '){
                i = ep.length();
            }
            
        }

        int resp = Integer.parseInt(newEp);

        this.eps = resp;
        

    }

    //Fim de metodos set

    //Metodos get

    public String getNome(){
        return this.nome;
    }

    public String getFormato(){
        return this.formato;
    }

    public String getDuracao(){
        return this.duracao;
    }

    public String getPais(){
        return this.pais;
    }

    public String getEmissora(){
        return this.emissora;
    }

    public String getIdioma(){
        return this.idioma;
    }

    public String getTransmOriginal(){
        return this.transmOriginal;
    }

    public int getTemporadas(){
        return this.temporadas;
    }

    public int getEps(){
        return this.eps;
    }

    //Fim de metodos get


    //metodo para estrair o conteudo solicitado no arquivo html
    String removeTags(String line){
        String newline = "";

        
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '<'){
                i++;
                while(line.charAt(i) != '>'){
                    i++;
                }
            }
            else if(line.charAt(i) == '\n'){
                i++;
            }else if(line.charAt(i) == '&'){
                i++;
                while(line.charAt(i) != ';') i++;
        
            }
            else{
                newline += line.charAt(i);
            }
        }
        

        return(newline);
    }


    //metodo para ler o arquivo html
    void ler(String nomeArquivo) throws Exception {
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream
            ("/tmp/series/" + nomeArquivo));
            BufferedReader br = new BufferedReader(isr);


            setNome(nomeArquivo);


            while(!br.readLine().contains("Formato"));//ler formato

            setFormato(br.readLine());

            while(!br.readLine().contains("Duração"));//ler duracao

            setDuracao(br.readLine());

            while(!br.readLine().contains("País de origem"));//ler pais

            setPais(br.readLine());


            while(!br.readLine().contains("Idioma"));//ler idioma

            setIdioma(br.readLine());

            while(!br.readLine().contains("Emissora"));//ler emissora

            setEmissora(br.readLine());


            while(!br.readLine().contains("Transmissão original"));//ler transmOriginal

            setTransmOriginal(br.readLine());


            while(!br.readLine().contains("N.º de temporadas"));//ler temporadas

            setTemporadas(br.readLine());


            while(!br.readLine().contains("N.º de episódios"));//ler eps

            setEps(br.readLine());

            br.close();
        
        } catch (Exception e) {}
        
        

    }

    //metodo para mostrar os dados da serie
    void imprimir(){
        System.out.printf("%s %s %s %s %s %s %s %d %d\n", getNome(), getFormato(), 
        getDuracao(), getPais(), getIdioma(), getEmissora(), getTransmOriginal(), 
        getTemporadas(), getEps());
    }
    
}

class Comp{
    private int comp;

    Comp(){
        this.comp = 0;
    }

    public void addComp(int x){
        this.comp += x;
    }

    public int getComp(){
        return this.comp;
    }
}

class HashIndiretoLista {
    Lista tabela[];
    int tamanho;
    final int NULO = -1;
 
    public HashIndiretoLista() throws Exception {
       this(21);
    }
 
    public HashIndiretoLista(int tamanho) throws Exception {
       this.tamanho = tamanho;
       tabela = new Lista[tamanho];
       for (int i = 0; i < tamanho; i++) {
          tabela[i] = new Lista();
       }
    }
 
    private int convToASCII(String x){
        int resp = 0;

        for(int i = 0; i < x.length(); i++){
            resp += (int)x.charAt(i);
        }
        
        return resp;
    }

    private int h(String elemento) {
        int numElemento = convToASCII(elemento.trim());
        return numElemento % 21;
    }
 
    boolean pesquisar(String elemento, Comp comp) {
       int pos = h(elemento);
       return tabela[pos].pesquisar(elemento, comp);
    }
 
    public void inserirInicio(String elemento) throws Exception {
       int pos = h(elemento);
       tabela[pos].inserirInicio(elemento);
    }
 
 }

/**
 * Celula simplesmente encadeada
 * @author Joao Paulo Domingos Silva
 * @version 1.1 02/2012
 */
class Celula {
	public Serie elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.

	/**
	 * Construtor da classe.
	 * @param elemento Elemento inserido na celula.
	 * @throws Exception
	 */
	Celula(String arquivo) throws Exception {
		this.elemento = new Serie();
        this.elemento.ler(arquivo);
		this.prox = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Elemento inserido na celula.
	 * @param prox Aponta a celula prox.
	 * @throws Exception
	 */
	Celula(String arquivo, Celula prox) throws Exception {
		this.elemento = new Serie();
        this.elemento.ler(arquivo);
		this.prox = prox;
	}
}


class Lista {
	private Celula primeiro; // Primeira celula: SEM elemento valido.
	private Celula ultimo; // Ultima celula: COM elemento valido.

	/**
	 * Construtor da classe: Instancia uma celula (primeira e ultima).
	 * @throws Exception
	 */
	public Lista() throws Exception {
		primeiro = new Celula(" ");
		ultimo = primeiro;
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x, Comp comp) {
		boolean retorno = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if(i.elemento.getNome().compareTo(x) == 0){
                retorno = true;
                i = ultimo;
            }
            comp.addComp(1);
		}
		return retorno;
	}

	/**
	 * Insere um elemento na primeira posicao da sequencia.
	 * @param elemento Elemento a inserir.
	 * @throws Exception
	 */
	public void inserirInicio(String elemento) throws Exception {
		Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
        tmp = null;
	}

}

public class q8{
    
    //metodo para gerar arquivo
    public static void gerarArq(long time, Comp comparacoes) throws Exception{
        try{
            PrintWriter writer = new PrintWriter("matricula_hashIndireta.txt", "UTF-8");
            writer.printf("matricula: 726911\ttempo de execucao: %d milissegundos\tnumero de comparacoes: %d", time/1000000, comparacoes.getComp());
            
            writer.close();
        }
        catch(IOException e){

        }

    }

    public static void pesqSeries(HashIndiretoLista tabela, Scanner sc, Comp comp){

        String nomeArquivo = sc.nextLine();
        
        while(!nomeArquivo.equals("FIM")){
            if(tabela.pesquisar(nomeArquivo, comp)){
                System.out.println(" SIM");
            }
            else{
                System.out.println(" NAO");
            }

            nomeArquivo = sc.nextLine();
        }
    }
    public static void main (String []args) throws Exception{
        long startTime = System.nanoTime();
        HashIndiretoLista tabela = new HashIndiretoLista();
        Scanner sc = new Scanner(System.in);
        Comp comp = new Comp();


        String nomeArquivo = sc.nextLine();
        
        while(!nomeArquivo.equals("FIM")){
            try{
                tabela.inserirInicio(nomeArquivo);

            }catch (Exception e){}

            nomeArquivo = sc.nextLine();
        }

        pesqSeries(tabela, sc, comp);

        long elapsedTime = System.nanoTime() - startTime;

        gerarArq(elapsedTime, comp);


        sc.close();

    }
}