<h1 style="font-weight: bold;">Car DealerShip API (Concessionária de carros) 💻</h1>

<p align="center">
  <div><b>Car DealerShip</b> é uma API projetada para gerenciar o cadastro de veículo. API permite o registro de novos veículos com informações detalhadas, como marca, modelo, ano e preço.</div>
  <div>A listagem de veículos cadastrados, oferece suporte a paginação para facilitar a navegação entre grandes volumes de dados. Filtros avançados garantem que os usuários possam buscar veículos de maneira precisa, com base em atributos como faixa de preço, marca e modelo.</div>
  <div>A atualização de informações permite a modificação dos dados de veículos existentes de forma ágil. Para garantir a gestão eficiente dos registros, a API também possui a funcionalidade de remoção de veículos, possibilitando a exclusão segura e eficaz de dados.</div>
  <div>A API Car DealerShip é acompanhada de uma suite de testes unitários desenvolvidos com JUnit e Mockito. Isso garante que cada funcionalidade seja testada de maneira robusta, promovendo a confiabilidade e a qualidade do código.</div>
</p>

<h2 id="technologies">💻 Tecnologias</h2>
<div style="display:inline_block">
    <br />
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java"/>
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring boot"/>
    <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" alt="hibernate"/>
    <img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white" alt="postgresql" />
    <img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" alt="swagger" />
    <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="maven" />
    <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" alt="docker" />
</div><br />

<h2 id="started">🚀 Iniciando</h2>

Tenha instalado em sua máquina as seguintes tecnologias:

- Java
- Apache Maven
- Docker

<h3>Clonando projeto</h3>

```bash
git clone https://github.com/vitoralveschaves/car-dealership
```

<h3>Iniciando o container com o banco de dados Postgres</h3>
<p>Com o projeto aberto em sua IDE (Intellij ou Eclipse), digite no terminal da IDE:</p>

```bash
docker compose up -d
```
<h3>Iniciando</h3>

- Inicia sua aplicação clicando no Run da sua IDE ou com as teclas Ctrl + Shift + F10 (Intellij)
- A aplicação estará executando em http://localhost:8081/api/v1

<h3>Documentação e Endpoints</h3>

- Com a aplicação rodando, acesse a documentação da API em http://localhost:8081/api/v1/api-docs.html
