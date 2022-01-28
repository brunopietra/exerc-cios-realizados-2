#include <stdio.h>
#include <time.h>      
#include <unistd.h>  
#include <stdlib.h>
#include <string.h>

#define MAX_FIELD_SIZE 100

char* substring(char *substring, const char *string, int inicio, int n)
{
    //Copia caractere por caractere até n e envia para a substring
    while (n > 0)
    {
        *substring = *(string + inicio);
 
        substring++;
        string++;
        n--;
    }
 
    //adiciona o null no final
    *substring = '\0';
 
    // retorna substring
    return substring;
}

typedef struct {
    char nome[MAX_FIELD_SIZE];
    char formato[MAX_FIELD_SIZE];
    char duracao[MAX_FIELD_SIZE];
    char pais[MAX_FIELD_SIZE];
    char idioma[MAX_FIELD_SIZE];
    char emissora[MAX_FIELD_SIZE];
    char transmissao[MAX_FIELD_SIZE];
    int num_temporadas;
    int num_episodios;
} Serie;

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) {
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size) {
    return freadline(line, max_size, stdin);
}

void print_serie(Serie *serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie->nome,
        serie->formato,
        serie->duracao,
        serie->pais,
        serie->idioma,
        serie->emissora,
        serie->transmissao,
        serie->num_temporadas,
        serie->num_episodios
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }
    long tam = tam_arquivo(file);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, file);
    fclose(file);
    html[tam] = '\0';
    return html;
}

/**
 * @brief Extrai os textos de uma tag html.
 * 
 * @param html Ponteiro para o caractere que abre a tag '<'.
 * @param texto Ponteiro para onde o texto deve ser colocado.
 * 
 * @return Ponteiro para o texto extraído.
 */
char *extrair_texto(char *html, char *texto) {
    char *start = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *start == ' ' ? start + 1 : start;
}

/**
 * @brief Lê o HTML da série e popula os campos da struct.
 * 
 * @param serie Struct Serie que vai receber os dados.
 * @param html String contendo todo o HTML do arquivo.
 */
void ler_serie(Serie *serie, char *html) {
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);
   

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    int length = strlen(serie->pais);

    if(serie->pais[length - 1] == ' '){
        char aux[MAX_FIELD_SIZE];
        substring(serie->pais, serie->pais, 0, length - 1);
    }

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_episodios);
}

#define MAX_LINE_SIZE 250
#define PREFIXO "tmp/series/"
// #define PREFIXO "../entrada e saida/tp02/series/"


int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}



void matricula(int numMatricula, int operacoes, double tempo){
    FILE *fp;
    fp = fopen("matriculaCountingsort.txt", "w");

    if( !fp){
        exit(0);
    }

    fprintf(fp, "%d\t %f\t %d", numMatricula, tempo, operacoes);

    fclose(fp);
}



int main() {
    int numMatricula = 728764;
    double time = 0.0;  
    clock_t start = clock();
    Serie arrayDeSeries[100];
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];
    int inputCounter = 0;
    int contadorDeRepeticoes = 0;
    int i = 0;

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);

    while (!isFim(line + tam_prefixo)) {
        Serie serie;
        char *html = ler_html(line);
        ler_serie(&arrayDeSeries[inputCounter], html);
        free(html);
        inputCounter++;
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }


    for(int i = inputCounter / 2; i > 0; i /= 2) {

      for(int j = i; j < inputCounter; j++){
        Serie aux = arrayDeSeries[j];
        int k;

        for(k = j; (k >= i && arrayDeSeries[k - i].num_temporadas > aux.num_temporadas ) || (k >= i && arrayDeSeries[k - i].num_temporadas == aux.num_temporadas && strcmp(arrayDeSeries[k - i].nome, aux.nome) > 0 ); k -= i){
            arrayDeSeries[k] = arrayDeSeries[k - i];
            contadorDeRepeticoes++;
        }
        arrayDeSeries[k] = aux;
        contadorDeRepeticoes++;
      }

    }
    

    clock_t end = clock();
    time += (double)(end - start) / CLOCKS_PER_SEC;

    matricula(numMatricula, contadorDeRepeticoes, time);
    for(i = 0; i < inputCounter; i++){
        print_serie(&arrayDeSeries[i]);
    }
    

    return EXIT_SUCCESS;
}


/*
void gerarArq(double time, int comp, int troc){
    FILE *fp;

    fp = fopen("matricula_countingsort.txt","w");

    if (fp != NULL) {
        fprintf(fp, "matricula: 726911      tempo de execucao: %f segundos     numero de comparacoes: %d       numero de mudancas: %d", time, comp, troc);
        
    }
    fclose(fp);
}
*/
