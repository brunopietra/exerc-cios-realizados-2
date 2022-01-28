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

    }

    //metodo para mostrar os dados da serie
    void imprimir(){
        System.out.printf("%s %s %s %s %s %s %s %d %d\n", getNome(), getFormato(), 
        getDuracao(), getPais(), getIdioma(), getEmissora(), getTransmOriginal(), 
        getTemporadas(), getEps());
    }
    
}

class No {
    public String arquivo;
    public Serie elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @throws Exception
     */
    public No(String arquivo) throws Exception {
        this(arquivo, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     * @throws Exception
     */
    public No(String arquivo, No esq, No dir) throws Exception {
        this.arquivo = arquivo;
        this.elemento = new Serie();
        this.elemento.ler(arquivo);
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreBinaria() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x, int comp) {
        System.out.printf(" raiz ");
		return pesquisar(x, raiz, comp);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No i, int comp) {
      boolean resp;
		if (i == null) {
         resp = false;

      } else if (x.compareTo(i.elemento.getNome()) == 0) {//x.compareTo(i.elemento.getNome()) == 0
         resp = true;

      } else if (x.compareTo(i.elemento.getNome()) < 0) {
        System.out.printf("esq ");
         resp = pesquisar(x, i.esq, comp);

      } else {
        System.out.printf("dir ");
         resp = pesquisar(x, i.dir, comp);
      }
      comp ++;

      return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(String nomeArquivo) throws Exception {
        //System.out.printf("inserindo %s:\n", nomeArquivo);

        Serie aux = new Serie();
        aux.ler(nomeArquivo);
        
        //System.out.println("    serie aux criada || " + aux.getNome());


		raiz = inserir(nomeArquivo, aux.getNome(), raiz);
        
        //System.out.println("fim de insercao");
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(String x, String xNome, No i) throws Exception {
        //System.out.println("        dentro do metodo recursivo");
		if (i == null) {
         i = new No(x);

      } else if (xNome.compareTo(i.elemento.getNome()) < 0) {
         i.esq = inserir(x, xNome, i.esq);

      } else if (xNome.compareTo(i.elemento.getNome()) > 0) {
         i.dir = inserir(x, xNome, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return i;
	}

	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover(String x) throws Exception {
		raiz = remover(x, raiz);
	}

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private No remover(String x, No i) throws Exception {

		if (i == null) {

      } else if (x.compareTo(i.elemento.getNome()) < 0) {
         i.esq = remover(x, i.esq);

      } else if (x.compareTo(i.elemento.getNome()) > 0) {
         i.dir = remover(x, i.dir);

      // Sem no a direita.
      } else if (i.dir == null) {
         i = i.esq;

      // Sem no a esquerda.
      } else if (i.esq == null) {
         i = i.dir;

      // No a esquerda e no a direita.
      } else {
         i.esq = maiorEsq(i, i.esq);
		}

		return i;
	}

	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No maiorEsq(No i, No j) {

      // Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

      // Existe no a direita.
		} else {
         // Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

	/**
	 * Metodo que retorna o maior elemento da árvore
	 * @return int maior elemento da árvore
	 */
   public Serie getMaior(){
      Serie resp = null;

      if(raiz != null){
         No i;
         for(i = raiz; i.dir != null; i = i.dir);
         resp = i.elemento;
      }

      return resp;
   }


	/**
	 * Metodo que retorna o menor elemento da árvore
	 * @return int menor elemento da árvore
	 */
   public Serie getMenor(){
      Serie resp = null;

      if(raiz != null){
         No i;
         for(i = raiz; i.esq != null; i = i.esq);
         resp = i.elemento;
      }

      return resp;
   }


	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
   public int getAltura(){
      return getAltura(raiz, 0);
   }


	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
   public int getAltura(No i, int altura){
      if(i == null){
         altura--;
      } else {
         int alturaEsq = getAltura(i.esq, altura + 1);
         int alturaDir = getAltura(i.dir, altura + 1);
         altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
      }
      return altura;
   }

   public Serie getRaiz() throws Exception {
      return raiz.elemento;
   }

   public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
      return igual(a1.raiz, a2.raiz);
   }

   private static boolean igual (No i1, No i2){
      boolean resp;
      if(i1 != null && i2 != null){
         resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
      } else if(i1 == null && i2 == null){
         resp = true;
      } else {
         resp = false; 
      }
      return resp;
   }

}

public class q1{
    
    //metodo para gerar arquivo
    public static void gerarArq(long time, int comparacoes) throws Exception{
        try{
            PrintWriter writer = new PrintWriter("matricula_binaria.txt", "UTF-8");
            writer.printf("matricula: 726911\ttempo de execucao: %d milissegundos\tnumero de comparacoes: %d", time/1000000, comparacoes);
            
            writer.close();
        }
        catch(IOException e){

        }

    }

    public static void atenderComandos(ArvoreBinaria arvore, Scanner sc) throws Exception{

        int nComandos = sc.nextInt();
        sc.nextLine();

        String comando = "";

        for(int i = 0; i < nComandos; i++){
            comando = sc.nextLine();

            if(comando.indexOf("I") == 0){
                StringBuilder sb = new StringBuilder(comando);
                sb.delete(0, 2);
                comando = sb.toString();
                arvore.inserir(comando);

            }else if(comando.indexOf("R") == 0){
                StringBuilder sb = new StringBuilder(comando);
                sb.delete(0, 2);
                comando = sb.toString();
                arvore.remover(comando);

            }


        }
    }

    public static void pesquisando(ArvoreBinaria arvore, Scanner sc, int comp){
        String comando2 = sc.nextLine();

        while(!comando2.equals("FIM")){

            if (arvore.pesquisar(comando2, comp)){
                System.out.println("SIM");
            }
            else{
                System.out.println("NAO");
            }


            comando2 = sc.nextLine();
            
        }
    }
    public static void main (String []args) throws Exception{
        long startTime = System.nanoTime();
        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner sc = new Scanner(System.in);
        int comp = 0;


        String nomeArquivo = sc.nextLine();
        
        while(!nomeArquivo.equals("FIM")){
            try{
                arvore.inserir(nomeArquivo);

            }catch (Exception e){}

            nomeArquivo = sc.nextLine();
        }

        atenderComandos(arvore, sc);

        pesquisando(arvore, sc, comp);

        long elapsedTime = System.nanoTime() - startTime;

        gerarArq(elapsedTime, comp);


        sc.close();

    }
}