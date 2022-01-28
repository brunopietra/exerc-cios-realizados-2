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

    public Serie(String nome, String formato, String duracao, String pais, String idioma, String emissora,
            String transmOriginal, int temporadas, int eps) {
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
        this.nome = newline;

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
        System.out.printf("%s %s %s %s %s %s %s %d %d\n", getNome(), getFormato(), getDuracao(), getPais(), getIdioma(),
                getEmissora(), getTransmOriginal(), getTemporadas(), getEps());
    }

}

// mesma lista mostrada nas aulas, mas adaptada para receber series ao inves de
// numeros inteiros
class Lista {
    public Serie[] array;
    public int n, comp, mud;

    Lista() {
        this(10000);

    }

    Lista(int tamanho) {
        array = new Serie[tamanho];
        n = comp = mud = 0;

    }

    void inserirInicio(String x) throws Exception {
        if (n < array.length) {
            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1];

            }

            array[0] = new Serie();
            array[0].ler(x);
            n++;

        }

    }

    void inserirFim(String x) throws Exception {
        // System.out.printf("chegada em inserirFim\n");
        if (n < array.length) {
            // System.out.printf("entrando no if\n");
            array[n] = new Serie();
            // System.out.printf("serie criada\n");
            array[n].ler(x);
            // System.out.printf("chegada em inserirFim\n");
            n++;

        }

    }

    void inserir(String x, int pos) throws Exception {
        if (!(n >= array.length || pos < 0 || pos > n)) {
            for (int i = n; i > pos; i--) {
                array[i] = array[i - 1];
            }

            array[pos] = new Serie();
            array[pos].ler(x);
            n++;

        }

    }

    void removerInicio() throws Exception {
        if (n != 0) {
            for (int i = 0; i < n - 1; i++) {
                array[i] = array[i + 1];
            }

            array[n - 1] = null;
            n--;
        }

    }

    void removerFim() throws Exception {
        if (n != 0) {
            array[n - 1] = null;
            n--;

        }

    }

    void remover(int pos) throws Exception {
        if (n != 0) {
            for (int i = pos; i < n - 1; i++) {
                array[i] = array[i + 1];
            }

            array[n - 1] = null;
            n--;

        }
    }

    void mostrar(String[] objRemovidos, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("(R) " + objRemovidos[i]);

        }

        for (int i = 0; i < n; i++) {
            try {
                array[i].imprimir();

            } catch (Exception e) {
            }
        }

    }

    void mostrar() {
        for (int i = 0; i < n; i++) {
            try {
                array[i].imprimir();

            } catch (Exception e) {
            }
        }
    }

}

public class q10 {

    // metodo para organizar, em ordem crescente, as series (usando como chave
    // primaria o valor doidao)
    

    public static void swap(Lista lista, int i, int j) {
        Serie temp = lista.array[i];
        lista.array[i] = lista.array[j];
        lista.array[j] = temp;

        lista.mud++;
    }

    public static void sort(Lista lista) {
        int arr[] = new int[lista.n];
        for (int i = 0; i < lista.n; i++) {
            arr[i] = lista.array[i].getEps() * 1000 + lista.array[i].getTemporadas();
        }

        radixSort(lista, arr);

    }

    public static int getMaior(Lista lista, int arr[]) {
        int maior = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maior) {
                maior = arr[i];
            }
            lista.comp++;
        }

        return maior;
    }

    public static int[] countingSort(Lista lista, int array[], int expo) {
        Serie output[] = new Serie[lista.n];
        int numOutput[] = new int[lista.n];
        int count[] = new int[10];

        for (int i = 0; i < 10; count[i] = 0, i++)
            ;

        for (int i = 0; i < lista.n; count[(array[i] / expo) % 10]++, i++)
            ;

        for (int i = 1; i < 10; count[i] += count[i - 1], i++)
            ;

        for (int i = lista.n - 1; i >= 0; i--) {
            numOutput[count[(array[i] / expo) % 10] - 1] = array[i];
            output[count[(array[i] / expo) % 10] - 1] = lista.array[i];

            count[(array[i] / expo) % 10]--;
        }

        for (int i = 0; i < lista.n; i++) {
            lista.array[i] = output[i];

            // Ordenando com base no nome caso valores sejam iguais
            if (i - 1 >= 0 && numOutput[i] == numOutput[i - 1]
                    && lista.array[i].getNome().compareTo(lista.array[i - 1].getNome()) < 0) {
                swap(lista, i, i - 1);
                lista.comp++;
            }
        }

        return numOutput;
    }

    public static void radixSort(Lista lista, int arr[]) {
        int maior = getMaior(lista, arr);

        for (int expo = 1; maior / expo > 0; expo *= 10) {
            arr = countingSort(lista, arr, expo);
        }
    }

    // metodo para gerar arquivo
    public static void gerarArq(long time, Lista lista) throws Exception {
        try {
            PrintWriter writer = new PrintWriter("matricula_radixsort.txt", "UTF-8");
            writer.printf(
                    "matricula: 726911\ttempo de execucao: %d milissegundos\tnumero de comparacoes: %d\tnumero de mudancas: %d",
                    time / 1000000, lista.comp, lista.mud);

            writer.close();
        } catch (IOException e) {

        }

    }

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        Scanner sc = new Scanner(System.in);
        Lista lista = new Lista();

        String nomeArquivo = sc.nextLine();

        while (!nomeArquivo.equals("FIM")) {
            try {

                lista.inserirFim(nomeArquivo);

            } catch (Exception e) {
            }

            nomeArquivo = sc.nextLine();
        }

        // a seguir, looping q ira ordenar o array
        sort(lista);

        lista.mostrar();

        long elapsedTime = System.nanoTime() - startTime;

        gerarArq(elapsedTime, lista);

        sc.close();
    }
}