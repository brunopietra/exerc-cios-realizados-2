import java.io.*;
import java.util.Scanner;

class Serie {
    private String nome;
    private String formato;
    private String duracao;
    private String pais;
    private String idioma;
    private String emissora;
    private String transmOriginal;
    private int temporadas;
    private int eps;

    Serie() throws Exception {
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

    // Metodos set

    public void setNome(String line) {
        String newline = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '_') {
                newline += ' ';

            } else if (line.charAt(i) == '.') {
                i = line.length();
            } else {
                newline += line.charAt(i);
            }
        }

        if (newline.charAt(0) == ' ' && newline.charAt(newline.length() - 1) == ' ') {
            this.nome = newline.substring(1, newline.length() - 1);
        } else if (newline.charAt(0) == ' ') {
            this.nome = newline.substring(1, newline.length());
        } else if (newline.charAt(newline.length() - 1) == ' ') {
            this.nome = newline.substring(0, newline.length() - 1);
        } else {
            this.nome = newline;
        }

    }

    public void setFormato(String line) {
        line = removeTags(line);
        if (line.charAt(0) == ' ') {
            line = line.substring(1);
        }
        if (line.charAt(line.length() - 1) == ' ') {
            line = line.substring(0, line.length() - 1);
        }
        this.formato = line;

    }

    public void setDuracao(String line) {
        line = removeTags(line);
        if (line.charAt(0) == ' ') {
            line = line.substring(1);
        }
        if (line.charAt(line.length() - 1) == ' ') {
            line = line.substring(0, line.length() - 1);
        }
        this.duracao = line;

    }

    public void setPais(String line) {
        line = removeTags(line);
        if (line.charAt(0) == ' ') {
            line = line.substring(1);
        }
        if (line.charAt(line.length() - 1) == ' ') {
            line = line.substring(0, line.length() - 1);
        }
        this.pais = line;

    }

    public void setEmissora(String line) {
        line = removeTags(line);
        if (line.charAt(0) == ' ') {
            line = line.substring(1);
        }
        if (line.charAt(line.length() - 1) == ' ') {
            line = line.substring(0, line.length() - 1);
        }
        this.emissora = line;

    }

    public void setIdioma(String line) {
        line = removeTags(line);
        if (line.charAt(0) == ' ') {
            line = line.substring(1);
        }
        if (line.charAt(line.length() - 1) == ' ') {
            line = line.substring(0, line.length() - 1);
        }
        this.idioma = line;

    }

    public void setTransmOriginal(String line) {
        line = removeTags(line);
        if (line.charAt(0) == ' ') {
            line = line.substring(1);
        }
        if (line.charAt(line.length() - 1) == ' ') {
            line = line.substring(0, line.length() - 1);
        }
        this.transmOriginal = line;

    }

    public void setTemporadas(String line) {
        String temp = removeTags(line);
        String newTemp = "";

        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) > 47 && temp.charAt(i) < 58) {
                newTemp += temp.charAt(i);
            } else if (temp.charAt(i) == '(' || temp.charAt(i) == ' ') {
                i = temp.length();
            }

        }

        this.temporadas = Integer.parseInt(newTemp);

    }

    public void setEps(String line) {
        String ep = removeTags(line);
        String newEp = "";

        for (int i = 0; i < ep.length(); i++) {
            if (ep.charAt(i) > 47 && ep.charAt(i) < 58) {
                newEp += ep.charAt(i);
            } else if (ep.charAt(i) == '(' || ep.charAt(i) == ' ') {
                i = ep.length();
            }

        }

        int resp = Integer.parseInt(newEp);

        this.eps = resp;

    }

    // Fim de metodos set

    // Metodos get

    public String getNome() {
        return this.nome;
    }

    public String getFormato() {
        return this.formato;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public String getPais() {
        return this.pais;
    }

    public String getEmissora() {
        return this.emissora;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public String getTransmOriginal() {
        return this.transmOriginal;
    }

    public int getTemporadas() {
        return this.temporadas;
    }

    public int getEps() {
        return this.eps;
    }

    // Fim de metodos get

    // metodo para estrair o conteudo solicitado no arquivo html
    String removeTags(String line) {
        String newline = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '<') {
                i++;
                while (line.charAt(i) != '>') {
                    i++;
                }
            } else if (line.charAt(i) == '\n') {
                i++;
            } else if (line.charAt(i) == '&') {
                i++;
                while (line.charAt(i) != ';')
                    i++;

            } else {
                newline += line.charAt(i);
            }
        }

        return (newline);
    }

    // metodo para ler o arquivo html
    void ler(String nomeArquivo) throws Exception {

        InputStreamReader isr = new InputStreamReader(new FileInputStream("/tmp/series/" + nomeArquivo));
        BufferedReader br = new BufferedReader(isr);

        setNome(nomeArquivo);

        while (!br.readLine().contains("Formato"))
            ;// ler formato

        setFormato(br.readLine());

        while (!br.readLine().contains("Duração"))
            ;// ler duracao

        setDuracao(br.readLine());

        while (!br.readLine().contains("País de origem"))
            ;// ler pais

        setPais(br.readLine());

        while (!br.readLine().contains("Idioma"))
            ;// ler idioma

        setIdioma(br.readLine());

        while (!br.readLine().contains("Emissora"))
            ;// ler emissora

        setEmissora(br.readLine());

        while (!br.readLine().contains("Transmissão original"))
            ;// ler transmOriginal

        setTransmOriginal(br.readLine());

        while (!br.readLine().contains("N.º de temporadas"))
            ;// ler temporadas

        setTemporadas(br.readLine());

        while (!br.readLine().contains("N.º de episódios"))
            ;// ler eps

        setEps(br.readLine());

        br.close();

    }

    // metodo para mostrar os dados da serie
    void imprimir() {
        System.out.printf("%s %s %s %s %s %s %s %d %d\n", getNome(), getFormato(),
                getDuracao(), getPais(), getIdioma(), getEmissora(), getTransmOriginal(),
                getTemporadas(), getEps());
    }

}

class No {
    public boolean cor;
    public String arquivo;
    public Serie elemento;
    public No esq, dir;

    public No() throws Exception {
        this(null, null, false, null, null);
    }

    public No(String arquivo) throws Exception {
        this(arquivo, null, false, null, null);
    }

    public No(String arquivo, boolean cor) throws Exception {
        this(arquivo, null, cor, null, null);
    }

    public No(Serie elemento) throws Exception{
        this(null, elemento, false, null, null);
    }

    public No(String arquivo, Serie elemento, boolean cor, No esq, No dir) throws Exception {
        this.cor = cor;
        this.arquivo = arquivo;
        if(arquivo != null){
            this.elemento = new Serie();
            this.elemento.ler(arquivo);
        }else{
            this.elemento = elemento;
        }
        
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
     * 
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String x, Comp comp) {
        System.out.printf("raiz ");
        return pesquisar(x, raiz, comp);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String x, No i, Comp comp) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (x.compareTo(i.elemento.getNome()) == 0) {// x.compareTo(i.elemento.getNome()) == 0
            resp = true;

        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            System.out.printf("esq ");
            resp = pesquisar(x, i.esq, comp);

        } else {
            System.out.printf("dir ");
            resp = pesquisar(x, i.dir, comp);
        }
        comp.addComp(1);

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
     * 
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
     * 
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
     * 
     * @param i No em analise.
     */
    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
    }

    public void inserir(String nomeArquivo) throws Exception {
        Serie aux = new Serie();
        aux.ler(nomeArquivo);

        inserir(nomeArquivo, aux.getNome());
    }

    private String gerarNome(String elemento) throws Exception {
        Serie aux = new Serie();
        aux.ler(elemento);

        return (aux.getNome());
    }

    private Serie gerarElemento(String elemento) throws Exception {
        Serie serie = new Serie();
        serie.ler(elemento);

        return serie;
    }

    private void inserir(String x, String xNome) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new No(x);
            // System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento +
            // ").");

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (xNome.compareTo(raiz.elemento.getNome()) < 0) {
                raiz.esq = new No(x);
                // System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e
                // esq(" + raiz.esq.elemento + ").");
            } else {
                raiz.dir = new No(x);
                // System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e
                // dir(" + raiz.dir.elemento + ").");
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (xNome.compareTo(raiz.elemento.getNome()) < 0) {
                raiz.esq = new No(x);
                // System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else if (x.compareTo(raiz.dir.elemento.getNome()) < 0) {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = gerarElemento(x);
                // System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = gerarElemento(x);
                // System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (x.compareTo(raiz.elemento.getNome()) > 0) {
                raiz.dir = new No(x);
                // System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else if (x.compareTo(raiz.esq.elemento.getNome()) > 0) {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = gerarElemento(x);
                // System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = gerarElemento(x);
                // System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            // System.out.println("Arvore com tres ou mais elementos...");
            inserir(x, null, null, null, raiz);
        }
        raiz.cor = false;
    }

   private void balancear(No bisavo, No avo, No pai, No i) {
      // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if (pai.cor == true) {
         // 4 tipos de reequilibrios e acoplamento
         if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
            if (i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }
         } else { // rotacao a direita ou esquerda-direita
            if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }
         if (bisavo == null) {
            raiz = avo;
         } else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
            bisavo.esq = avo;
         } else {
            bisavo.dir = avo;
         }
         // reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
         
      } // if(pai.cor == true)
   }

    private void inserir(String elemento, No bisavo, No avo, No pai, No i) throws Exception {
        String eNome = gerarNome(elemento);

        if (i == null) {
            if (eNome.compareTo(pai.elemento.getNome()) < 0) {
                i = pai.esq = new No(elemento, true);
            } else {
                i = pai.dir = new No(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (eNome.compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (eNome.compareTo(i.elemento.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                //System.out.println("Erro inserir (elemento repetido)!");
            }
        }
    }

    private No rotacaoDir(No no) {
        // System.out.println("Rotacao DIR(" + no.elemento + ")");
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        // System.out.println("Rotacao ESQ(" + no.elemento + ")");
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

class Comp {
    private int comp;

    Comp() {
        this.comp = 0;
    }

    public void addComp(int x) {
        this.comp += x;
    }

    public int getComp() {
        return this.comp;
    }
}

public class q4 {

    // metodo para gerar arquivo
    public static void gerarArq(long time, Comp comp) throws Exception {
        try {
            PrintWriter writer = new PrintWriter("matricula_avinegra.txt", "UTF-8");
            writer.printf("matricula: 726911\ttempo de execucao: %d milissegundos\tnumero de comparacoes: %d",
                    time / 1000000, comp.getComp());

            writer.close();
        } catch (IOException e) {

        }

    }

    public static void pesquisando(ArvoreBinaria arvore, Scanner sc, Comp comp) {
        String comando2 = sc.nextLine();

        while (!comando2.equals("FIM")) {

            if (arvore.pesquisar(comando2, comp)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            comando2 = sc.nextLine();

        }
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner sc = new Scanner(System.in);
        Comp comp = new Comp();

        String nomeArquivo = sc.nextLine();

        while (!nomeArquivo.equals("FIM")) {
            try {
                arvore.inserir(nomeArquivo);

            } catch (Exception e) {
            }

            nomeArquivo = sc.nextLine();
        }

        // atenderComandos(arvore, sc);

        pesquisando(arvore, sc, comp);

        long elapsedTime = System.nanoTime() - startTime;

        gerarArq(elapsedTime, comp);

        sc.close();

    }
}