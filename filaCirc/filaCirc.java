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
        
        InputStreamReader isr = new InputStreamReader(new FileInputStream
        ("tmp/series/" + nomeArquivo));
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

//classe vista na aula, mas adaptada para, ao inves de receber numeros inteiros, receber series
class Fila {
    Serie[] array;
    int primeiro, ultimo;

    Fila(){
        this(5);
    }

    Fila(int tamanho){
        array = new Serie[tamanho + 1];
        primeiro = ultimo = 0;
    }

    void inserir(String x) throws Exception{
        if(((ultimo + 1) % array.length) == primeiro){
            remover();
        
        }

        array[ultimo] = new Serie();
        array[ultimo].ler(x);
        ultimo = (ultimo + 1) % array.length;

    }

    void remover()throws Exception{
        if (primeiro != ultimo){
            array[primeiro] = null;
            primeiro = (primeiro + 1) % array.length;

        }

    }

    void mostrar(){
        int i = primeiro;
        float mediaTemps = 0, count = 0;

        while(i != ultimo){
            mediaTemps += array[i].getTemporadas();
            i = (i + 1) % array.length;
            count++;

        }
        mediaTemps /= count;
        MyIO.println(Math.round(mediaTemps));
    }

    void contarElem(){
        int i = primeiro, count = 0;

        while(i != ultimo){
            if(array[i] != null){
                count++;
            }

            i = (i + 1) % array.length;
        }

        MyIO.println(count);
    }
}


public class filaCirc{
    //funcao para receber comandos do usuario sobre a fila
    /*
    public static void atenderComandos(int nComandos, Fila fila) throws Exception{
        String comando = "";


        for(int i = 0; i < nComandos; i++){
            comando = MyIO.readLine();

            if(comando.indexOf("I") == 0){//a seguir, encontra-se um metodo para extrair apenas o nome do arquivo, q sera enviado para a funcao ler de serie
                StringBuilder sb = new StringBuilder(comando);
                sb.delete(0, 2);
                comando = sb.toString();
                try{
                    fila.inserir(comando);
                    fila.mostrar();

                }catch (Exception e){}

            }else if(comando.indexOf("R") == 0){
                fila.remover();
                
            }

        }

    }
    */
    public static void main (String []args) throws Exception{
        Fila fila = new Fila(5);

        String nomeArquivo = MyIO.readLine();
        
        while(!nomeArquivo.equals("FIM")){
            try{
                fila.inserir(nomeArquivo);
                fila.mostrar();

            }catch (Exception e){}

            nomeArquivo = MyIO.readLine();
        }

        int nComandos = MyIO.readInt();


        String comando = "";

        for(int i = 0; i < nComandos; i++){
            comando = MyIO.readLine();

            if(comando.indexOf("I") == 0){//a seguir, encontra-se um metodo para extrair apenas o nome do arquivo, q sera enviado para a funcao ler de serie
                StringBuilder sb = new StringBuilder(comando);
                sb.delete(0, 2);
                comando = sb.toString();
                try{
                    fila.inserir(comando);
                    fila.mostrar();

                }catch (Exception e){}

            }else if(comando.indexOf("R") == 0){
                fila.remover();
                
            }

        }

    }
}