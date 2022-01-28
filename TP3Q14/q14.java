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
        this.nome = newline;

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
        try{
            InputStreamReader isr = new InputStreamReader(new FileInputStream("tmp/series/" + nomeArquivo));
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

    //metodo para mostrar os dados da serie
    void imprimir(){
        System.out.printf("%s %s %s %s %s %s %s %d %d\n", getNome(), getFormato(), 
        getDuracao(), getPais(), getIdioma(), getEmissora(), getTransmOriginal(), 
        getTemporadas(), getEps());
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

            n++;
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

            n--;
        }

        return saida;
    }
    public void mostrar()throws Exception{
       
        CelulaDupla i = primeiro.prox;
        for(int j = 0; j < n - 1; j++, i = i.prox){
            try{
                i.elemento.imprimir();
            }catch(Exception e){}

        }

    }

    public CelulaDupla pegarElemento(int pos)throws Exception{
        CelulaDupla i = primeiro.prox;
        for(int j = 0; j < pos - 1; j++, i = i.prox);

        return i;
    }
}




public class q14{
    //metodo para organizar, em ordem alfabetica, as series (usando como bases primarias e secundarias, respectivamente, pais e nome)
    public static void quickSort(int esq, int dir, Lista lista) throws Exception{
        int i = esq, j = dir;
        CelulaDupla pivo = lista.pegarElemento((dir + esq) / 2);

        while(i <= j){
            while(lista.pegarElemento(i).elemento.getPais().compareTo(pivo.elemento.getPais()) < 0 ||
            (lista.pegarElemento(i).elemento.getPais().compareTo(pivo.elemento.getPais()) == 0 &&
            lista.pegarElemento(i).elemento.getNome().compareTo(pivo.elemento.getNome()) < 0 )){
                i++;
            }

            //System.out.println("antes do swap: " + lista.array[i].getNome()  + " | " + lista.array[j].getNome()  + " | " + pivo.getNome() + "\n");
            
            while(lista.pegarElemento(j).elemento.getPais().compareTo(pivo.elemento.getPais()) > 0 ||
            (lista.pegarElemento(j).elemento.getPais().compareTo(pivo.elemento.getPais()) == 0 &&
            lista.pegarElemento(j).elemento.getNome().compareTo(pivo.elemento.getNome()) > 0 )){
                j--;
            }

            //System.out.println(i + " " + j + "\n"); 

            if(i <= j){
                
                Serie aux = lista.pegarElemento(i).elemento; lista.pegarElemento(i).elemento = lista.pegarElemento(j).elemento; 
                lista.pegarElemento(j).elemento = aux;
                
                i++; j--;
            }

        }

        

        if(esq < j){
            quickSort(esq, j, lista);

        }
        if(i < dir){
            quickSort(i, dir, lista);

        }


    }
    

    
    //metodo para gerar arquivo
    public static void gerarArq(long time, int comparacoes, int mudancas) throws Exception{
        try{
            PrintWriter writer = new PrintWriter("matricula_quicksort.txt", "UTF-8");
            writer.printf("matricula: 726911\ttempo de execucao: %d milissegundos\tnumero de comparacoes: %d\tnumero de mudancas: %d", time/1000000, comparacoes, mudancas);
            
            writer.close();
        }
        catch(IOException e){

        }

    }
    public static void main(String[] args)throws Exception{
        long startTime = System.nanoTime();
        Scanner sc = new Scanner(System.in);
        Lista lista = new Lista();
        int comp = 0, mud = 0;
        

        String nomeArquivo = sc.nextLine();

        while (!nomeArquivo.equals("FIM")) {
            try {

                lista.inserirFim(nomeArquivo);

            } catch (Exception e) {}

            nomeArquivo = sc.nextLine();
        }


        //a seguir, looping q ira ordenar o array
        quickSort(0,lista.n-1, lista);



        lista.mostrar();

        long elapsedTime = System.nanoTime() - startTime;

        gerarArq(elapsedTime, comp, mud);


        sc.close();
    }
}