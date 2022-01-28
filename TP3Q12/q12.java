import java.io.*;

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
class Celula{
    public Serie elemento;
    public Celula prox;
    public Celula() throws Exception{
        this("nulo");
    }
    public Celula(String x) throws Exception{
        this.elemento = new Serie();
        this.elemento.ler(x);
        this.prox = null;
    }
}

//mesma lista mostrada nas aulas, mas adaptada para receber series ao inves de numeros inteiros
class Pilha{
    private Celula topo;
    public Pilha() throws Exception{
        topo = null;
    }
    
    public void inserir(String x) throws Exception{
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }
    
    public String remover()throws Exception{
        if(topo == null){
            throw new Exception("Erro!");
        }
        String saida = topo.elemento.getNome();
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;

        return saida;
    }
    
    
    public void mostrar(String[] objRemovidos, int count)throws Exception{
        for (int i = 0; i < count; i++) {
            System.out.println("(R) " + objRemovidos[i]);

        }

        for(Celula i = topo; i != null; i = i.prox){
            i.elemento.imprimir();
        }

    }
}

public class q12{
    //metodo para atender comandos dos usuarios
    public static void atenderComandos(int nComandos, Pilha pilha) throws Exception {
        String comando = "";
        String[] objRemovidos = new String[1000];//esse array sera util para exibir todos os arquivos apagados
        int count = 0;

        for (int i = 0; i < nComandos; i++) {
            comando = MyIO.readLine();

            if (comando.indexOf("I") == 0) {
                try {
                    StringBuilder sb = new StringBuilder(comando);//a seguir, encontra-se um metodo para extrair apenas o nome do arquivo, q sera enviado para a funcao ler de serie
                    sb.delete(0, 2);
                    comando = sb.toString();
                    pilha.inserir(comando);
                } catch (Exception e) {}

            } else if (comando.indexOf("R") == 0) {
                try {
                    objRemovidos[count] = pilha.remover();
                    count++;

                } catch (Exception e) {}

            } 

        }

        pilha.mostrar(objRemovidos, count);

    }

    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();


        String nomeArquivo = MyIO.readLine();

        while (!nomeArquivo.equals("FIM")) {
            try {
                pilha.inserir(nomeArquivo);
                

            } catch (Exception e) {}

            nomeArquivo = MyIO.readLine();
        }

        int nComandos = MyIO.readInt();

        atenderComandos(nComandos, pilha);

    }
}