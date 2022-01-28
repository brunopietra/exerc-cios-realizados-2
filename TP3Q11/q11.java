import java.io.*;

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
        try{
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
        }catch(IOException e){}

    }

    // metodo para mostrar os dados da serie
    void imprimir() {
        System.out.printf("%s %s %s %s %s %s %s %d %d\n", getNome(), getFormato(), getDuracao(), getPais(), getIdioma(),
                getEmissora(), getTransmOriginal(), getTemporadas(), getEps());
    }

}

//codigo de uma celula
class CelulaDupla{
    public Serie elemento;
    public CelulaDupla prox, ant;
    public CelulaDupla() throws Exception{
        this("nulo");
    }
    public CelulaDupla(String x) throws Exception{
        this.elemento = new Serie();
        this.elemento.ler(x);
        this.prox = this.ant = null;
    }
}

//mesma lista mostrada nas aulas, mas adaptada para receber series ao inves de numeros inteiros
class Lista{
    private CelulaDupla primeiro, ultimo;
    int n;
    public Lista() throws Exception{
        primeiro = new CelulaDupla();
        ultimo = primeiro;
        n = 0;
    }
    public void inserirInicio(String x) throws Exception{
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo){
            ultimo = tmp;
        }
        else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
        n++;
    }
    public void inserirFim(String x) throws Exception{
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
        n++;
    }
    public String removerInicio()throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro!");
        }
        CelulaDupla tmp = primeiro;
        String saida = primeiro.prox.elemento.getNome();
        primeiro = primeiro.prox;
        tmp = tmp.prox = primeiro.ant = null;
        n--;

        return saida;
    }
    public String removerFim()throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro!");
        }
        String saida = ultimo.elemento.getNome();
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        n--;

        return saida;
    }
    public void inserir(String x, int pos)throws Exception{
        if(pos < 0 || pos > n){
            throw new Exception("Erro!");
        }
        else if(pos == 0){
            inserirInicio(x);
        }
        else if(pos == n){
            inserirFim(x);
        }
        else{
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }
    public String remover(int pos)throws Exception{
        String saida = null;

        if(primeiro == ultimo || pos < 0 || pos >= n){
            throw new Exception("Erro!");
        }
        else if(pos == 0){
            removerInicio();
        }
        else if(pos == n - 1){
            removerFim();
        }
        else{
            CelulaDupla i = primeiro;
            for(int j = 0; j <= pos; j++, i = i.prox);
            saida = i.elemento.getNome();
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            i.prox = i.ant = null;
            i = null;
        }

        return saida;
    }
    public void mostrar(String[] objRemovidos, int count)throws Exception{
        for (int i = 0; i < count; i++) {
            System.out.println("(R) " + objRemovidos[i]);

        }

        CelulaDupla i = primeiro.prox;
        for(int j = 0; j < n - 1; j++, i = i.prox){
            try{
                i.elemento.imprimir();
            }catch(Exception e){}

        }

    }
}


public class q11 {
    //metodo para atender comandos dos usuarios
    public static void atenderComandos(int nComandos, Lista lista) throws Exception {
        String comando = "";
        String[] objRemovidos = new String[1000];//esse array sera util para exibir todos os arquivos apagados
        int count = 0;

        for (int i = 0; i < nComandos; i++) {
            comando = MyIO.readLine();

            if (comando.indexOf("II") == 0) {
                try {
                    StringBuilder sb = new StringBuilder(comando);//a seguir, encontra-se um metodo para extrair apenas o nome do arquivo, q sera enviado para a funcao ler de serie
                    sb.delete(0, 3);
                    comando = sb.toString();
                    lista.inserirInicio(comando);

                } catch (Exception e) {}

            } else if (comando.indexOf("IF") == 0) {
                try {
                    StringBuilder sb = new StringBuilder(comando);//a seguir, encontra-se um metodo para extrair apenas o nome do arquivo, q sera enviado para a funcao ler de serie
                    sb.delete(0, 3);
                    comando = sb.toString();
                    lista.inserirFim(comando);
                } catch (Exception e) {}

            } else if (comando.indexOf("I*") == 0) {
                try {
                    String[] elements = comando.split(" ");//a seguir, encontra-se um metodo para extrair o nome do arquivo e a posicao
                    int pos = Integer.parseInt(elements[1]);
                    comando = elements[2];
                    lista.inserir(comando, pos);
                } catch (Exception e) {}

            } else if (comando.indexOf("RI") == 0) {
                try {
                    objRemovidos[count] = lista.removerInicio();
                    count++;
                    
                } catch (Exception e) {}

            } else if (comando.indexOf("RF") == 0) {
                try {
                    objRemovidos[count] = lista.removerFim();
                    count++;

                } catch (Exception e) {}

            } else if (comando.indexOf("R*") == 0) {
                try {
                    String[] elements = comando.split(" ");
                    int pos = Integer.parseInt(elements[1]);
                    objRemovidos[count] = lista.remover(pos);
                    count++;

                } catch (Exception e) {}

            }

        }

        lista.mostrar(objRemovidos, count);

    }

    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();


        String nomeArquivo = MyIO.readLine();

        while (!nomeArquivo.equals("FIM")) {
            try {
                lista.inserirFim(nomeArquivo);
                

            } catch (Exception e) {}

            nomeArquivo = MyIO.readLine();
        }

        int nComandos = MyIO.readInt();

        atenderComandos(nComandos, lista);

    }
}