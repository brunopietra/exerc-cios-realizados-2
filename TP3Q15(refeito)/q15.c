#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_FIELD_SIZE 100

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






//                                  parte da pilha

#define MAXTAM    100
#define bool      short
#define true      1
#define false     0

Serie array[MAXTAM+1];   // Elementos da pilha 
int primeiro;          // Remove do indice "primeiro".
int ultimo;             // Insere no indice "ultimo".


/**
 * Inicializacoes
 */
void start(){
   primeiro = ultimo = 0;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 * @Se a fila estiver cheia.
 */
void inserir(Serie x) {

   array[ultimo] = x;
   ultimo = (ultimo + 1);
}


/**
 * Remove um elemento da primeira posicao da fila e movimenta 
 * os demais elementos para o primeiro da mesma.
 * @return resp int elemento a ser removido.
 * @Se a fila estiver vazia.
 */
Serie remover() {

   //validar remocao
   if (primeiro == ultimo) {
      printf("Erro ao remover!");
      exit(1);
   }

   Serie resp = array[ultimo - 1];
   ultimo = ultimo - 1;
   return resp;
}


/**
 * Mostra os array separados.
 */
void mostrar (){
    int i;

    for(i = ultimo - 1; i >= primeiro; i--) {
        print_serie(&array[i]);
    }

}


/**
 * Retorna um bool indicando se a fila esta vazia
 * @return bool indicando se a fila esta vazia
 */
bool isVazia() {
   return (primeiro == ultimo); 
}


//                                  fim da parte da pilha

int main() {
    
    Serie serie;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        free(html);
        inserir(serie);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }

    //printf("%d  ||  %d\n", primeiro, ultimo);
    //printf("%s  ||  %s\n", array[primeiro].nome, array[ultimo - 1].nome);
    
    int nOperacoes;

    scanf("%d", &nOperacoes);

    for(int i = 0; i < nOperacoes; i++){
        char aux[30];
        char aux2[30];

        scanf(" %s", aux);


        if(aux[0] == 'I'){
            //printf("%s\n", aux);
            scanf(" %s", aux2);
            //printf("%s\n", aux2);
            
            char aux3[60] = PREFIXO;
            strcat(aux3, aux2);
            //printf("%s\n", aux3);

            char *html = ler_html(aux3);
            ler_serie(&serie, html);
            free(html);
            inserir(serie);

        }

        else if(aux[0] == 'R'){
            Serie sRemovida = remover();
            printf("(R) %s\n", sRemovida.nome);

        }

    }

    mostrar();


    return EXIT_SUCCESS;
}