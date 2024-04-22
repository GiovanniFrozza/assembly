# Sistema de Votação em Assembleias Sicredi

## Introdução
Este projeto foi desenvolvido para facilitar o processo de tomada de decisões em cooperativas através de votações. 
O sistema permite cadastrar pautas, abrir sessões de votação, receber votos e divulgar resultados.

## Tecnologias Utilizadas
- **Java**: Versão 17
- **Spring Boot**: 3.2.5
- **Maven**: Gerenciador de dependências
- **JUnit**: Framework para testes automatizados.
- **MongoDB**: Banco de dados para persistência de dados
- **Swagger**: Documentação da API
 
## Funcionalidades
- **Cadastro de Pautas**: Permite o registro de novas pautas para votação.
- **Abertura de Sessões de Votação**: Inicia uma sessão de votação que pode ser configurada para durar um tempo específico.
- **Recebimento de Votos**: Associados podem votar em pautas abertas, apenas um voto por associado.
- **Resultados da Votação**: Os votos são contabilizados e o resultado final da pauta é disponibilizado.

## Integrações com APIs Externas
### Validação de Elegibilidade para Votar
Inicialmente, a aplicação deveria integrar-se a uma API fornecida pelo organizador do desafio para validar se os usuários estão autorizados a votar. 
No entanto, devido a problemas de disponibilidade dessa API durante o desenvolvimento, foi necessário implementar uma solução alternativa.
Para contornar essa indisponibilidade, foi criado uma simulação (*mock*) da API esperada. Essa simulação é realizada diretamente no código, onde uma função retorna um resultado randomico simulando se o associado pode ou não votar.

### Validação de CPF
A aplicação utiliza a API da Invertexto para validar CPF, garantindo que os associados cadastrados informe corretamente o cpf.


## Arquitetura Backend 
O backend foi construído utilizando o framework Spring Boot, organizado em uma arquitetura de pacotes clara e modular que promove a separação de responsabilidades 
e facilita a manutenção. Aqui está uma visão geral da estrutura de diretórios do backend:

- `sicredi`:
  - `vote`: 
    - `adapter`: Camada de adaptação que comunica com outras partes do sistema ou serviços externos.
      - `dto`: Objetos de Transferência de Dados usados para enviar e receber dados da API.
        - `request`: DTOs para capturar dados de entrada.
        - `response`: DTOs para formatar dados de saída.
      - `feign`: Responsável pela comunicação com APIs externas.
      - `mapper`: Responsável pela conversão entre DTOs e entidades de domínio.
      - `resource`: Define os endpoints da API que serão expostos ao frontend.
    - `domain`: Core do domínio de negócio da aplicação.
      - `enumerator`: Enumerações específicas do domínio.
      - `model`: Entidades de domínio que refletem a estrutura do banco de dados.
      - `repository`: Interfaces para o acesso aos dados persistidos.
      - `service`: Classes de serviço que contêm a lógica de negócio.
    - `infrastructure`: Suporte técnico e configurações da aplicação.
      - `config`: Configurações do aplicativo Spring Boot.
      - `exceptions`: Classes de exceção personalizadas para tratamento de erros.


## Como Utilizar
### Acesso Remoto à Aplicação
Para utilizar a aplicação sem a necessidade de baixar o código localmente, você pode acessar diretamente através da URL https://assembly-u4dv.onrender.com/swagger-ui/index.html, a aplicação está hospedada no Render em uma versão gratuita, 
por isso ao acessar a URL pela primeira vez depois de um periodo de ausência levará em torno de 1 minuto para que fique disponível novamente.

### Local
`git clone https://github.com/GiovanniFrozza/assembly.git` <br>
Acesse `http://localhost:8080/swagger-ui.html` para visualizar e interagir com a documentação da API.


## Decisões de Projeto
### Escolha do MongoDB
- **Flexibilidade de Esquema**: Permite um modelo de dados mais flexível que se adapta facilmente às mudanças..
- **Escalabilidade**: Alta escalabilidade horizontal, facilitando o aumento da capacidade de armazenamento. Isso é ideal para lidar com o aumento de carga durante períodos de votação intensos.
- **Desempenho com Grandes Volumes de Dados**: Para aplicativos que lidam com grandes volumes de inserções e consultas, como um sistema de votação, o MongoDB proporciona um desempenho robusto.
