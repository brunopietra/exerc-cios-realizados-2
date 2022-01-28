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


public class pesqBin{
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

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main (String []args) throws Exception{
        MyIO.setCharset("UTF-8");
        Serie[] pilha = new Serie[100];
        int i = 0; //nesse exercicio, i sera igual ao numero de series da pilha
        long startTime = System.nanoTime();

        String nomeArquivo = MyIO.readLine();
        
        
        while(!isFim(nomeArquivo)){
            try{
                pilha[i] = new Serie();
                pilha[i].ler(nomeArquivo);

            }catch (Exception e){}

            i++;

            nomeArquivo = MyIO.readLine();
        }


        //metodo para checar a existencia de series no vetor
        int comparacoes = 0;
        //a criacao de um array contendo somente os nomes das series ira permitir a ordenacao com o Array.sort(String[])
        String nome[] = new String[i]; 
        String resp = "";
       
        
        for (int j = 0; j < i; j ++){
            nome[j] = pilha[j].getNome();
        }
        

        Arrays.sort(nome);


        String checarNome = MyIO.readLine();

        
        while(!isFim(checarNome)){
            resp = "";
            
            int l = 0, r = i - 1;
            
            while (l <= r) {
                int m = l + (r - l) / 2;
    
                int res = checarNome.compareTo(nome[m]);
                
    
                // checar se checarNome esta no meio
                if (res == 0 && checarNome.equals(nome[m])){
                    resp = "SIM";
                    l = r + 1;

                }
    
                // se checarNome for maior q o meio, ignore a parte esquerda
                else{
                    if (res > 0){
                        l = m + 1;

                    }
                    // caso contrario, ignore a direita
                    else{
                        r = m - 1;

                    }
                    resp = "NÃO";
                }

            }
            
            
            

            MyIO.println(resp);

            comparacoes++;

            checarNome = MyIO.readLine();
        }
        



        long elapsedTime = System.nanoTime() - startTime;

        gerarArq(elapsedTime, comparacoes);
        
    }
}