#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <stdbool.h>

typedef struct
{
    char nome[100];
    char formato[100];
    char duracao[100];
    char pais[100];
    char idioma[100];
    char emissora[100];
    char transmOriginal[100];
    int temporadas;
    int eps;
} Serie;

//funcao para remover o \n adquirido por removeTags()
char *removeNBar(char Line[100])
{
    int len = strlen(Line);

    if (Line[len - 1] == '\n')
        Line[--len] = 0;

    return Line;
}

//funcao para localizar o conteudo solicitado no arquivo html
char *removeTags(char *old)
{
    char *newLine = (char *)malloc(100000 * sizeof(strlen(old)));
    int i = 0, j = 0;
    while (i < strlen(old))
    {
        if (old[i] == '<')
        {
            i++;
            while (old[i] != '>')
                i++;
        }
        else if (old[i] == '&')
        {
            i++;
            while (old[i] != ';')
                i++;
        }
        else
        {
            newLine[j] = old[i];
            j++;
        }
        i++;
    }

    newLine = removeNBar(newLine);

    return newLine;
}

//funcoes set

/*
void setNome(Serie* serie, char buf[2000]){
    strcpy(serie->nome, removeTags(buf));
}
*/
void *setNome(Serie *serie, char line[100])
{
    int i = 0, j = 0;
    char newline[100] = "";
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == '_')
        {
            newline[j]= ' ';
            j++;
        }
        else if (line[i] == '.')
        {
            i = strlen(line);
        }
        else
        {
            newline[j] = line[i];
            j++;
        }
    }

    strcpy(serie->nome, newline);
}

void setFormato(Serie *serie, char buf[2000])
{
    strcpy(serie->formato, removeTags(buf));
}

void setDuracao(Serie *serie, char buf[2000])
{
    strcpy(serie->duracao, removeTags(buf));
}

void setPais(Serie *serie, char buf[2000])
{
    strcpy(serie->pais, removeTags(buf));
}

void setIdioma(Serie *serie, char buf[2000])
{
    strcpy(serie->idioma, removeTags(buf));
}

void setEmissora(Serie *serie, char buf[2000])
{
    strcpy(serie->emissora, removeTags(buf));
}

void setTransmissao(Serie *serie, char buf[2000])
{
    strcpy(serie->transmOriginal, removeTags(buf));
}

void setTemps(Serie *serie, char buf[2000])
{
    serie->temporadas = strtol(removeTags(buf), NULL, 10);
}

void setEps(Serie *serie, char buf[2000])
{
    serie->eps = strtol(removeTags(buf), NULL, 10);
}

//fim das funcoes set

//funcao para ler o arquivo html
void ler(char nomeArquivo[100], Serie *serie)
{
    
    char file[] = "/tmp/series/";
    strcat(file, nomeArquivo);
    FILE *fp = fopen(file, "r");
    if (!file)
    {
        fprintf(stderr, "Falha ao abrir arquivo\n");
        exit(1);
    }
    setNome(serie, nomeArquivo);
    
    char buf[2000];
    fgets(buf, 2000, fp);
    
    //descobrir nome
    
    //setNome(serie, nomeArquivo);
    //printf("| saida 0 |\n");

    //descobrir formato
    while (strstr(buf, "Formato") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setFormato(serie, buf);

    //descobrir duracao
    while (strstr(buf, "Duração") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setDuracao(serie, buf);

    //descobrir pais
    while (strstr(buf, "País de origem") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setPais(serie, buf);

    //descobrir idioma
    while (strstr(buf, "Idioma original") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setIdioma(serie, buf);

    //descobrir emissora
    while (strstr(buf, "Emissora de televisão original") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setEmissora(serie, buf);

    //descobrir transmissao
    while (strstr(buf, "Transmissão original") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setTransmissao(serie, buf);

    //descobrir n de temps
    while (strstr(buf, "N.º de temporadas") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setTemps(serie, buf);

    //descobrir n de eps
    while (strstr(buf, "N.º de episódios") == NULL)
    {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);

    setEps(serie, buf);

    fclose(fp);
}

//funcoes get

char *getNome(Serie serie)
{
    char *nome = serie.nome;
    return nome;
}

char *getFormato(Serie serie)
{
    char *formato = serie.formato;
    return formato;
}

char *getDuracao(Serie serie)
{
    char *duracao = serie.duracao;
    return duracao;
}

char *getPais(Serie serie)
{
    char *pais = serie.pais;
    return pais;
}

char *getIdioma(Serie serie)
{
    char *idioma = serie.idioma;
    return idioma;
}

char *getEmissora(Serie serie)
{
    char *emissora = serie.emissora;
    return emissora;
}

char *getTransmissao(Serie serie)
{
    char *transmOriginal = serie.transmOriginal;
    return transmOriginal;
}

int getTemps(Serie serie)
{
    return serie.temporadas;
}

int getEps(Serie serie)
{
    return serie.eps;
}

//fim das funcoes get

//metodo para mostrar os dados da serie
void imprimir(Serie serie)
{
    printf("%s %s %s %s %s %d %d\n", getNome(serie), getFormato(serie),
           getDuracao(serie), getPais(serie),
           getTransmissao(serie), getTemps(serie), getEps(serie));
}

bool ehFIM(char palavra[])
{
    bool resp = false;
    if (strlen(palavra) == 3 && palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M')
        resp = true;

    return resp;
}

int main()
{

    Serie pilha[100];
    char nomeArquivo[100];
    int i = 0;

    scanf(" %s", nomeArquivo);

    while (!ehFIM(nomeArquivo))
    {
        ler(nomeArquivo, &pilha[i]);
        imprimir(pilha[i]);
        scanf(" %s", nomeArquivo);
    }

    return 0;
}
