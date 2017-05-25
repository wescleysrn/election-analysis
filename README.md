# ANALISE DE RELAÇÕES ENTRE DOADORES E RECEBEDORES NAS ELEIÇÕES 2014 NO BRASIL

## DESCRIÇÃO GERAL

<p align="justify">No cenário politico brasileiro, as doações de campanha são fontes de recursos importantes para financiar a politica nacional, neste contexto pessoas fisicas e pessoas juridicas são responsáveis por boa parcela do financiamento da campanha. Para alguns orgãos de controle e para a população em geral o conhecimento de quais empresas e setores economicos são responsáveis pelo financiamento de determinados candidatos e partidos é de extrema importância.</p>
<p align="justify">Este projeto visa mostrar em tabelas estruturadas e gráficos as relações entre os doadores e recebedores, permitindo analises que demonstram quais doadores financiaram os dois lados da disputas, exibe casos de doações não baseadas em ideologia, favorecendo a detecção de casos que indicam a necessidade de maior aprofundamento na análise. Por exemplo, com base neste levantamento, a população em geral pode rastrear quanto de retorno essas empresas obtiveram em licitações nos anos seguintes, aqueles candidatos que foram financiados por um determinado setor economico, participam de comissões que atuam em regulações daquele setor ?. Essas e outras indagações podem ser exploradas uma vez que se obtenham informações que indiquem um determinado padrão.</p>
<p align="justify">Para a carga deste projeto foi utilizado dados abertos do Tribunal Superior Eleitoral, que podem ser verificados no seguinte endereço http://www.tse.jus.br/hotSites/pesquisas-eleitorais/prestacao_contas_anos/2014.html.</p>
<p align="justify">O resultado do projeto rodando pode ser observado nas imagens a seguir:</p>

<p align="justify">- Imagem do Dashboard Principal</p>
<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/001.png">
</p>

<p align="justify">- Imagem da tabela de maiores doadores pessoa juridica</p>
<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/002.png">
</p>

<p align="justify">- Imagem do gráfico D3.js de maiores doadores pessoa juridica</p>
<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/003.png">
</p>

<p align="justify">Podemos notar que a visualização das informações se faz de maneira bem intuitiva, possuindo um menu de navegação lateral e áreas de informações bem definidas no dashboard principal, nas sessões seguintes será descrito os passos para carga das informações obtidas de fontes abertas como dito anteriormente, como preparar e realizar carga no banco de dados Neo4J e um pouco da arquitetura desenvolvida para apresentar essas informações.</p>

## PREPARAÇÃO DOS DADOS

<p align="justify">A parte principal do trabalho foi a preparação da carga de dados obtidos do site do TSE em formato .txt, sendo estes arquivos abertos e integrantes da prestação de contas eleitoral dos candidatos e partidos nas eleições 2014, para isso foi realizado um tratamento nestes arquivos .txt com o uso da ferramenta Data Integration da suite Pentaho, com a intenção de separar os dados realmente importantes e gerar um arquivo .csv que é utilizado no banco de dados Neo4J para realização da carga.</p>
<p align="justify">Informaçes sobre o Data Integration utilizado na carga dos dados, podem ser encontradas no seguinte endereço http://community.pentaho.com/projects/data-integration/, onde é possível realizar o download e seguindo as instruções a instalação da ferramenta em seu sistema operacional em uso.</p>
<p align="justify">Uma vez instalado a ferramenta, foi trabalhado a estrutura dos arquivos de prestação de contas eleitorais que se resumem em três arquivos principais, sendo receitas_candidatos_2014_brasil.txt, receitas_comites_2014_brasil.txt, receitas_partidos_2014_brasil.txt no data integration foi criado Transformation para obter cada tipo de arquivo de carga necessário para inserir no banco Neo4J.</p>
<p align="justify">Abaixo é exibido imagens dos Transformation's criados e sua estrutura:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/004.png">
</p>

<p align="justify">É importante notar que as transformações dos dados são realizados por meio dos componentes Data Integration, todos de fácil utilização, sendo utilizados basicamente, Input Stream, Select Value, Regular Expression, Filter Row, Sort Row, Merge Join e Output Stream, com isso foi possível gerar todos os arquivos de carga necessários.</p>
<p align="justify">Abaixo, temos a imagem de um componente de transformação Regex Evalution, que foi utilizado para filtrar na transformação pessoas fisicas e pessoas juridicas.</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/005.png">
</p>

<p align="justify">Alguns tratamentos foram necessários no procedimento de tratamento dos dados para realização da carga, com o modelo proposto, houve a necessidade de simplificar os recebedores em duas entidades, sendo estas Partido e Candidato. Ocorre que os partidos possuem diversos comitês financeiros com inúmeros CNPJ's por todo o país, havendo a necessidade de filtrar essas doações de comitê para uma única entidade no caso o Partido. Isso foi realizado por meio de agregação dos dados e totalização dos comitês pela sigla do partido.</p>
<p align="justify">Outro ponto de tratamento é que nos arquivos .txt do TSE constam doações realizadas entre Candidatos, entre comitê, de Partido para Candidato, etc. Esses tipos de operações não eram importantes para as perguntas que se gostaria de responder com o trabalho, sendo necessário excluir essas transações entre estas entidades, deixando somente doações de Pessoas Fisicas que não eram Candidato e de Empresas que não eram pertencentes a estrutura dos comitês partidários, sendo assim obtido somente doações diretas destes últimos</p>
<p align="justify">Uma vez realizado todos os tratamentos um componente de output é inserido na transformação, gerando a saída de um arquivo .csv, como pode ser verificado na imagem abaixo:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/006.png">
</p>

<p align="justify">Os arquivos que foram usados nas transformações, são disponibilizados a seguir, para que possa ser reproduzido por qualquer pessoa:</p>

[DadosTSE_Candidato.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_Candidato.ktr).</br>
[DadosTSE_Comite.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_Comite.ktr).</br>
[DadosTSE_DoacaoPFCandidato.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_DoacaoPFCandidato.ktr).</br>
[DadosTSE_DoacaoPFPartido.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_DoacaoPFPartido.ktr).</br>
[DadosTSE_DoacaoPJCandidato.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_DoacaoPJCandidato.ktr).</br>
[DadosTSE_DoacaoPJPartido.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_DoacaoPJPartido.ktr).</br>
[DadosTSE_DoadorPessoaFisica.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_DoadorPessoaFisica.ktr).</br>
[DadosTSE_DoadorPessoaJuridica.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_DoadorPessoaJuridica.ktr).</br>
[DadosTSE_Partido.ktr](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/ktl/DadosTSE_Partido.ktr).</br>

<p align="justify">Também estão aqui disponíveis os arquivos .csv gerados pelas transformações, que podem ser utilizadas para realizar a carga no banco Neo4J:</p>

[CANDIDATO.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/CANDIDATO.csv).</br>
[DOACAO_PF_CANDIDATO.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/DOACAO_PF_CANDIDATO.csv).</br>
[DOACAO_PF_PARTIDO.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/DOACAO_PF_PARTIDO.csv).</br>
[DOACAO_PJ_CANDIDATO.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/DOACAO_PJ_CANDIDATO.csv).</br>
[DOACAO_PJ_PARTIDO.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/DOACAO_PJ_PARTIDO.csv).</br>
[DOADORES_PF.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/DOADORES_PF.csv).</br>
[DOADORES_PJ.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/DOADORES_PJ.csv).</br>
[PARTIDOS.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/PARTIDOS.csv).</br>
[UFS.csv](https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/csv/UFS.csv).</br>

<p align="justify">Uma vez com os arquivos preparados, é possível realizar a carga no banco Neo4J por meio de uma ferramenta própria do banco que auxilia exatamente neste procedimento. Na seção a seguir será explicado o passo de carga do banco.</p>

#### AGRADECIMENTO

<p align="justify">No trabalho de conversão da base aberta do STE para arquivos .csv utilizando a ferramenta Data Integration, recebi muita ajuda do meu colega de trabalho [Hebert Filgueiras de Azevedo](https://github.com/hebertfilgueiras), ao qual deixo o meu agradecimento.</p>

## INSTALAÇÃO NEO4J

<p align="justify">Neo4j é um sistema de gerenciamento de banco de dados de gráficos desenvolvido pela Neo Technology, Inc. Descrito por seus desenvolvedores como um banco de dados transacional compatível com ACID com armazenamento e processamento de gráficos nativos, Neo4j é o banco de dados gráfico mais popular de acordo com db-engines.com. O código do projeto pode ser visualizado no Github com suporte no StackOverflow e Google Groups, utilizado por centenas de milhares de companias e organizações em quase todos os mercados. Casos de uso incluem gerenciamento de rede, softwares analiticos, pesquisa cientifica, roteamento, gestão organizacional e de projeto, recomendações, redes sociais e muito mais.</p>
<p align="justify">Para o nosso trabalho foi essencial por possibilitar consultas de relações de forma rápida, exibindo os vinculos de doadores e recebedores com base na carga de dados obtidos da prestação de contas eleitoral brasileira de 2014. Outras relações podem ser exploradas e é possível aplicar funções típicas de grafos para pesquisar padrões ainda não observados.</p>
<p align="justify">A seguir será descrito o processo de instalação do banco:</p>
<p align="justify">Inicialmente foi realizado a instalação do Neo4J, baixando arquivo de instalação no seguinte contexto https://neo4j.com/download/community-edition/, clicando no arquivo baixado será apresentado um assistente de instalação, conforme mostrado na imagem a seguir:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/007.png">
</p>


<p align="justify">O assistente irá instalar o Neo4J Community e ao final irá inicializar, podendo apresentar a seguinte mensagem solicitando a indicação do local do database.</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/008.png">
</p>

<p align="justify">É possível definir uma pasta no diretório de arquivos para armazenar os databases do Neo4J, sendo que neste trabalho foi definido para C://neo4jdatabase. Após a instalação o Neo4J estará disponível no contexto padrão http://localhost:7474/browser/ podendo ser acessado via navegador web.</p>
<p align="justify">A instalação inicial possui um usuário administrador padrão que é “neo4j” e senha “neo4j” sem as aspas duplas, bastando informar para ter acesso, conforme mostrado na imagem a seguir:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/009.png">
</p>

<p align="justify">A primeira ação solicitada ao administrador é a alteração da senha do usuário padrão, por fim será apresentado a tela inicial do Neo4J, onde teremos um conjunto de plataforma de administração com documentação embutida, sendo bastante intuitivo e com recursos de aprendizagem muito bons. A seguir é exibido uma parte da tela inicial do Neo4J:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/010.png">
</p>

<p align="justify">A instalação descrita aqui é uma instalação local, facilitando quem for reproduzir o trabalho de ter tudo necessário para rodar a aplicação. Em um trabalho de publicação, recomendasse a instalação do banco em um servidor, sendo este procedimento de instalação, facilmente executado seguindo a documentação oficial.</p>

## CARGA DOS DADOS

<p align="justify">Uma vez com o banco de dados instalado é possível realizar a carga dos dados preparados em etapa anterior, a própria interface visual do Neo4J disponível no contexto http://localhost:7474/browser/, no caso da instalação local, apresenta terminal de linha de comando que pode ser utilizado para carga dos arquivos .csv`s.</p>
<p align="justify">Os arquivos .csv devem ser copiados para a pasta padrão do banco, dentro de uma sub-pasta chamada 'import', devendo esta ser criada, caso não exista. Assim os comandos de load irão encontrar os arquivos de carga.</p>
<p align="justify">A seguir será descrito os comandos executados para realização da carga:</p>

### CARGA E CRIAÇÃO DE NODES
### NODES
#### State

		USING PERIODIC COMMIT
		LOAD CSV WITH HEADERS FROM "file:///UFS.csv" AS line FIELDTERMINATOR ";"
		CREATE (s:State{id:line.id, name:line.nome });

#### Party

		USING PERIODIC COMMIT
		LOAD CSV WITH HEADERS FROM "file:///PARTIDOS.csv" AS line FIELDTERMINATOR ";"
		CREATE (p:Party{id:line.id, initials:line.sigla, name:line.nome });

		CREATE INDEX ON :Party(initials)

#### Candidate

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///CANDIDATO.csv" AS line FIELDTERMINATOR ";"
		CREATE (c:Candidate { id: line.ID, cnpj: line.CNPJ, name: line.NOME, cpf: line.CPF, number: line.NUMERO, office:line.CARGO })

		CREATE INDEX ON :Candidate(cpf)
		CREATE INDEX ON :Candidate(cnpj)

#### Person

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///DOADORES_PF.csv" AS line FIELDTERMINATOR ";"
		MERGE (p:Person { id: line.id, cpf: line.cpf, name: line.nome})

		CREATE INDEX ON :Person(cpf)

#### Company

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///DOADORES_PJ.csv" AS line FIELDTERMINATOR ";"
		MERGE (c:Company { id: line.ID, cnpj: line.cnpj, name: line.nome, economicSector: line.setor})

		CREATE INDEX ON :Company(cnpj)

<p align="justify">Uma vez que foi criado os nodes, é possível a criação das relações entre eles, realizando a vinculação dos candidatos com os partidos e estados, e as doações que foram realizadas entre pessoa fisíca e jurídica para canditados e partidos.</p>

### RELATIONSHIP

#### Member_Of

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///CANDIDATO.csv" AS line FIELDTERMINATOR ";"
		WITH DISTINCT line
		MATCH (p:Party {initials: line.SIGLA_PARTIDO}), (c:Candidate {cpf: line.CPF})
		CREATE (c)-[r:MEMBER_OF]->(p)

#### Is_Running_In

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///CANDIDATO.csv" AS line FIELDTERMINATOR ";"
		WITH DISTINCT line
		MATCH (s:State {id: line.UF}), (c:Candidate {cpf: line.CPF})
		CREATE (c)-[r:IS_RUNNING_IN]->(s)

#### Donates_To
#### Person_To_Candidate

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///DOACAO_PF_CANDIDATO.csv" AS line FIELDTERMINATOR ";"
		MATCH (c:Candidate{cnpj:line.cpfCandidato})
		MATCH (pf:Person{cpf:line.cpfDoador})
		MERGE (pf)-[:DONATES_TO{ value: toFloat(line.valor), receipt:line.recibo}]->(c)


#### Company_To_Candidate

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///DOACAO_PJ_CANDIDATO.csv" AS line FIELDTERMINATOR ";"
		MATCH (c:Candidate{cnpj:line.cpfCandidato})
		MATCH (cy:Company{cnpj:line.cnpjDoador})
		MERGE (cy)-[:DONATES_TO{ value: toFloat(line.valor), receipt:line.recibo}]->(c)

#### Person_To_Party

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///DOACAO_PF_PARTIDO.csv" AS line FIELDTERMINATOR ";"
		MATCH (p:Party{initials:line.sigla})
		MATCH (pf:Person{cpf:line.cpfDoador})
		MERGE (pf)-[:DONATES_TO{ value: toFloat(line.valor), receipt:line.recibo}]->(p)

#### Company_To_Party

		USING PERIODIC COMMIT 10000
		LOAD CSV WITH HEADERS FROM "file:///DOACAO_PJ_PARTIDO.csv" AS line FIELDTERMINATOR ";"
		MATCH (p:Party{initials:line.sigla})
		MATCH (cy:Company{cnpj:line.cnpjDoador})
		MERGE (cy)-[:DONATES_TO{ value: toFloat(line.valor), receipt:line.recibo}]->(p)

<p align="justify">Até o momento foram criadas os nodes e as relações que nesta abstração, foram suficientes para demonstrar as doações de campanhas, diretas e que foram realizadas por pessoa fisícas e jurídicas para canditatos e partidos.</p>
<p align="justify">A partir daqui, podemos por meio da linguagem Chyper Query realizar consultas em respostas aos questionamentos iniciais.</p>


## CHYPER QUERY'S UTILIZADAS

[Cypher Query](https://neo4j.com/developer/cypher-query-language/) é a linguagem de consulta gráfica aberta do Neo4j. A sintaxe de Cypher fornece uma maneira familiar de combinar padrões de nós e relacionamentos no gráfico. É uma linguagem extremamente poderosas e permite realizar consultas nos nodes e relacionamentos de forma bem intuitiva.
<p align="justify">A seguir será descrito as Chyper Query`s em resposta aos questionamentos:</p>
<p align="justify">No que diz respeito aos candidatos:</p>

#### Candidato
#### Doações de Pessoa para Candidato

		MATCH (c:Candidate)<-[r:DONATES_TO]-(p:Person) RETURN c,r,p

<p align="justify">Ao executar a chyper query é exibido em formato de gráfico o retorno da consulta, como mostrado abaixo:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/011.png">
</p>

#### Maiores doadores Pessoa para Candidato

		MATCH (c:Candidate)<-[r:DONATES_TO]-(p:Person) RETURN p as person, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT 20

#### Doações de Empresa para Candidato

		MATCH (c:Candidate)<-[r:DONATES_TO]-(pj:Company) RETURN c,r,pj

#### Maiores doadores Empresa para Candidato

		MATCH (can:Candidate)<-[r:DONATES_TO]-(c:Company) RETURN c as company, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT 20

<p align="justify">No que diz respeito aos partidos:</p>

#### Partido
#### Doações de Pessoa para Partido

		MATCH (py:Party)<-[r:DONATES_TO]-(p:Person) RETURN py,r,p

#### Maiores doadores Pessoa para Partido

		MATCH (py:Party)<-[r:DONATES_TO]-(p:Person) RETURN p as person, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT 20

#### Doações de Empresa para Partido

		MATCH (py:Party)<-[r:DONATES_TO]-(pj:Company) RETURN py,r,pj

#### Maiores doadores Empresa para Partido

		MATCH (p:Party)<-[r:DONATES_TO]-(c:Company) RETURN c as company, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT 20

<p align="justify">Ao executar a chyper query é exibido em formato de tabela o retorno da consulta, como mostrado abaixo:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/012.png">
</p>

#### Candidatos de um determinado partido

		MATCH (p:Party{initials: 'PT'})<-[r:MEMBER_OF]-(c:Candidate) RETURN p,r,c

<p align="justify">Ao executar a chyper query é exibido em formato de gráfico o retorno da consulta como mostrado abaixo, aqui é demonstrado que a query possue um limit padrão de 300, caso não seja informado nenhum:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/013.png">
</p>

#### Candidatos de um determinado partido para um cargo especifico

		MATCH (p:Party{initials: 'PT'})<-[r:MEMBER_OF]-(c:Candidate{office:'GOVERNADOR'}) RETURN p,r,c

<p align="justify">No que diz respeito aos doadores pessoa fisíca:</p>

#### Person
#### Maiores doadores pessoa fisica

		MATCH (n)<-[r:DONATES_TO]-(p:Person) RETURN p as person, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT 20

<p align="justify">No que diz respeito aos doadores pessoa jurídica:</p>

#### Company
#### Maiores doadores pessoa juridica

		MATCH (n)<-[r:DONATES_TO]-(c:Company) RETURN c as company, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT

<p align="justify">No que diz respeito aos estados:</p>

#### State
#### Candidatos que concorrem por um determinado estado

		MATCH (s:State)<-[r:IS_RUNNING_IN]-(c:Candidate) RETURN s,r,c LIMIT

<p align="justify">Vale ressaltar que as query's aqui descritas são somente exemplificativas, podem existir uma lista com infinitas query`s possíveis para analisar os dados abertos da prestação de contas eleitoral do STE. Assim a ideia do projeto é estigar novas buscas e aplicar essa lógica em futuras prestações de contas.</p>
<p align="justify">Vejam que é possível expandir bastante esse projeto, podendo por exemplo ser dado carga dos beneficios que as empresas receberam nos anos seguintes em licitações públicas, destas licitações quais as que apresentaram aditivos, apresentando valor final bem maior que o inicialmente contratado. Dos doadores pessoa fisíca, quem são estas pessoas ? Possuem vinculo com alguma empresa de um determinado setor economico ? Como podemos montar sub-gráficos de grupos empresariais ? Como podemos ligar essas empresas por sócios ? etc. São algumas possibilidades de expansão deste trabalho.</p>
<p align="justify">A seguir será falado sobre o código fonte do projeto que consome essa base de dados Neo4J e apresenta uma proposta visual para permitir a visualização de algumas informações obtidas atráves da base de dados.</p>


## O PROJETO DE APRESENTAÇÃO

<p align="justify">A arquitetura do projeto foi aqui disponibilizada, visando o interesse de colaboradores e financiadores para sua publicação, qualquer colaboração ou feedback será bem vinda.</p>
<p align="justify">O código fonte do projeto é uma proposta de aplicação consumindo os dados trabalhados no banco de dados Neo4J, como este está disponível será comentado de forma suncinta as camadas, as tecnologias empregadas e os procedimentos necessários para rodar a aplicação.</p>
<p align="justify">Foi utilizado JDK 8 e alguns frameworks. Inicialmente é necessário baixar o código, acessar a pasta /election-analysis/src/main/resources/static/plugins e executar comando bower para baixar as dependências java script, conforme demonstrado abaixo:</p>

		bower install

<p align="justify">O comando irá baixar todas as dependências java script necessárias ao projeto que estão descritas no arquivo bower.json existente na mesma pasta onde o comando foi executado. Ocorrendo sucesso, será possível visualizar o conteúdo gerado na pasta bower_components que será gerada, abaixo imagem de termino da execução:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/014.png">
</p>

<p align="justify">Uma vez gerado as dependências java script, pode-se executar, a partir da pasta raiz do projeto, o comando spring boot para rodar a aplicação, conforme demonstrado abaixo:</p>

		mvn spring-boot:run

<p align="justify">Ocorrendo sucesso será exibido uma tela similar a exibida abaixo, sinalizando que a aplicação se encontra rodando e disponível para acesso na porta 8080, como pode ser verificado:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/015.png">
</p>

<p align="justify">Outra forma de publicar é por meio do comando maven package, ou install sendo gerado na pasta target do projeto o .war do projeto que pode ser publicado em um servidor de aplicação:</p>

		mvn clean -X package
		ou
		mvn clean -X install

<p align="justify">Por meio dos procedimentos descritos acima é possível rodar a aplicação para visualizar suas funcionalidades pelo browser, a seguir será descrito brevemente as camadas da arquitetura proposta.</p>
<p align="justify">No arquivo pom.xml pode-se conferir as dependências do projeto, destacando o spring data neo4j, spring boot started web e o spring boot data rest, com isso foi formulado as camadas da arquitetura.</p>
<p align="justify">Inicialmente o pacote node mapeia os nodes Neo4J em entidades java, devendo ser destacado a anotação spring data neo4j @NodeEntity, conforme pode ser verificado na imagem abaixo:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/016.png">
</p>

<p align="justify">Tão importante quanto, o pacote relationship possue o mapeamento das relações criadas no banco Neo4J, devendo sempre ser anotadas com @RelationshipEntity e possuir um @StartNode que deve ser um node e @EndNode igualmente outro node, como demonstrado abaixo:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/017.png">
</p>

<p align="justify">A camada repository possue uma função importante por ser nela que as buscas com Chyper Query foram criadas, com o uso do framework de persistência spring data neo4j, diversas funcionalidades, como paginação e buscas declarativas são herdadas do framework, deixando a implementação bastante enxuta, como pode ser observado na imagem a seguir:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/018.png">
</p>

<p align="justify">Para levar as informações processadas nestas camadas para a camada de apresentação, foi produzido no pacote rest serviços web rest que podem ser consumidos por diversos framework`s e tecnologias na camada de apresentação. Como foi utilizado uma camada de serviços para ligar a camada rest com a camada dao as implementações desta camada se tornam bem simples e reduzidas, como demonstrado pela imagem abaixo:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/019.png">
</p>

<p align="justify">Dito isto, temos uma descrição resumida da camada de servidor que fica responsável por buscar informações no banco Neo4J e transporta-las para a camada de apresentação. A seguir será descrito a camada que processa essas informações e as exibe.</p>
A camada de apresentação utiliza tecnologia [AngularJS](https://angularjs.org/) e [D3JS](https://d3js.org/) para visualização dos gráficos gerados pelo Neo4J. 
<p align="justify">É importante falar que buscou-se uma padronização em pastas seguindo o padrão MVC, portanto temos visivelmente pastas para controller, model e view, porém não tão restrito, possuindo separado pastas para estilo, java script e html. Abaixo uma imagem exibe essa separação.</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/020.png">
</p>

<p align="justify">Outra informação relevante sobre essa camada, é mencionar o uso do ocLazyLoad, que em versões mais recentes do AngularJS já incorpora isso, porém na versão utilizada é incluída como plugin, isto possibilita trabalhar modulos separados e carregados sob demanda, permitindo assim expandir a camada de apresentação para inúmeras rotas e áreas de site, sem prejudicar a performance, pois estes modulos serão carregados somente quando acessados pela rota de navegação.</p>
<p align="justify">Abaixo é exibido trecho de código existente no arquivo config.js, um arquivo muito importante e onde estão escritas as rotas dos modulos com uso do ocLazyLoad:</p>

<p align="center">
  <img src="https://github.com/wescleysrn/mestradounb/blob/master/imagens/election-analysis/021.png">
</p>

<p align="justify">Estes são os detalhes que julguei importante descrever sobre o projeto, embora desenvolvedores experientes podem entender facilmente o código e melhorar. Com esta descrição do trabalho esperamos facilitar sua reprodução e confirmar os dados importantes obtidos atráves de fontes de dados abertos e que podem ser utilizados para apresentar informações importantes para a sociedade.</p>

## CONCLUSÃO

<p align="justify">Embora este trabalho tenha sido realizado como trabalho final da disciplina de Banco de Dados Massivos do mestrado profissional em computação aplicada da UnB - Universidade de Brasília, acreditamos no potencial de um projeto aberto e que leve este conceito de processar os dados abertos de doações de campanha como ponto de partida para realizar análises entre doadores e recebedores e quais as implicações desses financiamentos, auxiliando a população em geral na fiscalização do dinheiro público e direcionar auditorias mais detalhadas na investigação de possíveis beneficios politicos.</p>
<p align="justify">Vale lembrar que a simples doação de campanha, não indica má-fé servindo somente de indicador para analises mais profundas sobre a relação das empresas e pessoas com o cenário politico brasileiro. Sugerimos inclusive a carga de mais informações em trabalhos futuros, para possibilitar melhor interpretação e abrir oportunidade para conclusões fundamentadas.</p>
<p align="justify">Podemos acompanhar o trabalho de candidatos que foram fortemente financiados por determinados setores da economia, associando seu trabalho com legislações que trazem beneficios a esses setores, podemos acompanhar as licitações das empresas financiadoras e determinar aquelas que tiveram aditivos nos contratos públicos e demais indicativos que favoreçam uma investigação mais detalhada.</p>
<p align="justify">Por fim, acreditamos que as doações de campanha sejam um ponto de partida importante para ser considerado pela sociedade que busca cada vez mais transparência e bom uso do dinheiro público.</p>
