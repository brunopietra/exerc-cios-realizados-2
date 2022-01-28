import java.io.*;
import java.util.Arrays;

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
        this.nome = removeTags(line);

    }

    public void setFormato(String line){
        this.formato = removeTags(line);

    }

    public void setDuracao(String line){
        this.duracao = removeTags(line);

    }

    public void setPais(String line){
        this.pais = removeTags(line);

    }

    public void setEmissora(String line){
        this.emissora = removeTags(line);

    }

    public void setIdioma(String line){
        this.idioma = removeTags(line);

    }

    public void setTransmOriginal(String line){
        this.transmOriginal = removeTags(line);

    }

    public void setTemporadas(String line){
        String temp = removeTags(line);
        String newTemp = "";
        for(int i = 0; i < temp.length(); i++){
            if(temp.charAt(i) > 47 && temp.charAt(i) < 58){
                newTemp += temp.charAt(i);
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
            
        }

        int resp = Integer.parseInt(newEp);

        this.eps = resp;

    }

    //Fim de metodos set

    //Metodos get

    String getNome(){
        return this.nome;
    }

    String getFormato(){
        return this.formato;
    }

    String getDuracao(){
        return this.duracao;
    }

    String getPais(){
        return this.pais;
    }

    String getEmissora(){
        return this.emissora;
    }

    String getIdioma(){
        return this.idioma;
    }

    String getTransmOriginal(){
        return this.transmOriginal;
    }

    int getTemporadas(){
        return this.temporadas;
    }

    int getEps(){
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
        
        

        while(!br.readLine().contains("infobox_v2"));//ler nome

        br.readLine();
        setNome(br.readLine());


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
//mesma lista mostrada nas aulas, mas adaptada para receber series ao inves de numeros inteiros
class Lista {
    Serie[] array;
    int n;

    Lista() {
        this(10000);

    }

    Lista(int tamanho) {
        array = new Serie[tamanho];
        n = 0;

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
        if (n < array.length) {
            array[n] = new Serie();
            array[n].ler(x);
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

            } catch (Exception e) {}
        }

    }

}

public class alocSeq {
    //metodo para atender comandos dos usuarios
    public static void atenderComandos(int nComandos, Lista lista) {
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
                    objRemovidos[count] = lista.array[0].getNome();
                    count++;
                    lista.removerInicio();
                } catch (Exception e) {}

            } else if (comando.indexOf("RF") == 0) {
                try {
                    objRemovidos[count] = lista.array[lista.n - 1].getNome();
                    count++;
                    lista.removerFim();

                } catch (Exception e) {}

            } else if (comando.indexOf("R*") == 0) {
                try {
                    String[] elements = comando.split(" ");
                    int pos = Integer.parseInt(elements[1]);
                    objRemovidos[count] = lista.array[pos].getNome();
                    count++;
                    lista.remover(pos);

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