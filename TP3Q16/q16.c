#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_FIELD_SIZE 100

typedef struct
{
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

char *remove_line_break(char *line)
{
    while (*line != '\r' && *line != '\n')
        line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file)
{
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size)
{
    return freadline(line, max_size, stdin);
}

void print_serie(Serie *serie)
{
    printf("%s %s %s %s %s %s %s %d %d\n",
           serie->nome,
           serie->formato,
           serie->duracao,
           serie->pais,
           serie->idioma,
           serie->emissora,
           serie->transmissao,
           serie->num_temporadas,
           serie->num_episodios);
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file)
{
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[])
{
    FILE *file = fopen(filename, "r");
    if (!file)
    {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }
    long tam = tam_arquivo(file);
    char *html = (char *)malloc(sizeof(char) * (tam + 1));
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
char *extrair_texto(char *html, char *texto)
{
    char *start = texto;
    int contagem = 0;
    while (*html != '\0')
    {
        if (*html == '<')
        {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd'))
                break;
            else
                contagem++;
        }
        else if (*html == '>')
            contagem--;
        else if (contagem == 0 && *html != '"')
        {
            if (*html == '&')
                html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n')
                *texto++ = *html;
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
void ler_serie(Serie *serie, char *html)
{
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL)
        *(parenteses_ptr - 1) = '\0';

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

int isFim(char line[])
{
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

//                                  lista dupla

#include <stdio.h>
#include <stdlib.h>
#include <err.h>

#define bool short
#define true 1
#define false 0

//TIPO CELULA ===================================================================
typedef struct CelulaDupla
{
    Serie *elemento;          // Elemento inserido na celula.
    struct CelulaDupla *prox; // Aponta a celula prox.
    struct CelulaDupla *ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla *novaCelulaDupla(Serie *elemento)
{
    CelulaDupla *nova = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    nova->elemento = elemento;
    nova->ant = nova->prox = NULL;
    return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla *primeiro;
CelulaDupla *ultimo;
int n;

/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start()
{
    primeiro = novaCelulaDupla(NULL);
    ultimo = primeiro;
    n = 0;
}

/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserir(Serie *x)
{
    ultimo->prox = novaCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho()
{
    int tamanho = 0;
    CelulaDupla *i;
    for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
        ;
    return tamanho;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar()
{
    CelulaDupla *i;

    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        print_serie(i->elemento);
    }
}

CelulaDupla pegarElemento(int pos){
    CelulaDupla *i = primeiro->prox;
    for(int j = 0; j < pos - 1; j++, i = i->prox);

    return *i;
}

//                                  fim da lista dupla



void quickSort(int esq, int dir) 
{
    printf("quicksort (0)\n");
    int i = esq, j = dir;
    CelulaDupla pivo = pegarElemento((dir + esq) / 2);

    
    while (i <= j)
    {
        printf("quicksort (1)\n");
        while (pegarElemento(i).elemento->pais < pivo.elemento->pais ||
               pegarElemento(i).elemento->pais == pivo.elemento->pais &&
                pegarElemento(i).elemento->pais < pivo.elemento->nome)
        {
            printf("quicksort (2)\n");
            i++;
        }

        //System.out.println("antes do swap: " + array[i].getNome->)  + " | " + array[j].getNome->)  + " | " + pivo.getNome() + "\n");

        while (pegarElemento(j).elemento->pais > pivo.elemento->pais||
               pegarElemento(j).elemento->pais == pivo.elemento->pais &&
                pegarElemento(j).elemento->pais > pivo.elemento->nome)
        {
            printf("quicksort (3)\n");
            j--;
        }

        //System.out.println(i + " " + j + "\n");

        if (i <= j)
        {

            Serie *aux = pegarElemento(i).elemento;
            *pegarElemento(i).elemento = *pegarElemento(j).elemento;
            *pegarElemento(j).elemento = *aux;

            i++;
            j--;
        }
    }
    
    /*
    if (esq < j)
    {
        quickSort(esq, j);
    }
    if (i < dir)
    {
        quickSort(i, dir);
    }
    */
}

int main()
{
    start();
    Serie serie;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);

    while (!isFim(line + tam_prefixo))
    {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        free(html);
        inserir(&serie);
        n++;
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }

    quickSort(0, n-1);

    mostrar();

    return 0;
}