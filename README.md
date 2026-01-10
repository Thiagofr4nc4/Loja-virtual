# 🛒 Loja Virtual API (Back-end)

## Em Desenvolvimento 🚧

Este projeto é o desenvolvimento de uma infraestrutura robusta para uma Loja Online. O foco principal é a aplicação de padrões de arquitetura modernos, segurança de dados e gerenciamento de relacionamentos complexos entre usuários, carrinhos e produtos.

---

## 🎯 Sobre o Projeto 

Diferente de um sistema simples, esta API foi construída pensando em cenários reais de mercado. Ela resolve problemas críticos de comunicação de dados e organização:

*   **Inteligência no Cadastro**: Ao se registrar, o sistema garante automaticamente que cada cliente receba um carrinho de compras exclusivo, eliminando falhas de processo.
*   **Privacidade e Segurança**: Através do uso de **Mappers**, a API filtra informações sensíveis. O sistema nunca expõe senhas ou dados estruturais internos na resposta para o usuário.
*   **Confiabilidade**: O sistema valida se um e-mail já existe e se os dados enviados são válidos (como formato de e-mail e tamanho de senha) antes mesmo de tentar salvar no banco.

---

## 🛠️ Tecnologias e Conceitos Aplicados
Para este projeto, utilizei o que há de mais atual no ecossistema Java em **2026**:

*   **Java 21**: Uso de `Records` para dados imutáveis e código mais limpo.
*   **Spring Boot 4**: Gerenciamento de toda a infraestrutura da aplicação.
*   **Hibernate 6 & JPA**: Mapeamento de banco de dados, incluindo relações Many-to-Many e One-to-One.
*   **DTO Pattern (Data Transfer Object)**: Implementado para resolver o erro de **Recursão Infinita** (ciclos infinitos em JSON) e desacoplar o banco de dados da interface do usuário.
*   **Bean Validation**: Garantia de integridade de dados na entrada.
*   **Swagger (OpenAPI)**: Documentação interativa para testes rápidos dos endpoints.

---

## 🏗️ Arquitetura do Sistema

O código está organizado seguindo o padrão de **Camadas**, garantindo que cada parte do sistema tenha uma responsabilidade única:

1.  **Controllers**: Responsáveis pelas rotas de acesso (Ex: `/usuarios/cadastrar`).
2.  **Services**: Onde reside o "coração" do projeto, com as regras de negócio e transações seguras.
3.  **Repositories**: Camada de comunicação direta com o banco de dados (H2 em memória).
4.  **Entities**: Representação fiel das tabelas do banco de dados.

---

## 🚀 Como testar a aplicação

1. Clone o repositório.
2. Certifique-se de possuir o **JDK 21** instalado.
3. Execute o projeto através da sua IDE ou terminal (`./mvnw spring-boot:run`).
4. Com a aplicação rodando, acesse a documentação interativa no navegador:
   > `http://localhost:8080/swagger-ui/index.html`

### Fluxo de Teste Recomendado:
1. Cadastre um **Produto** no menu de produtos.
2. Cadastre um **Usuário** (observe que ele já retorna um `carrinhoId`).
3. Adicione o produto ao carrinho usando o ID do usuário e o ID do produto.

---

## 🚧 Roadmap de Evolução
- [ ] Implementação de autenticação com **Spring Security e JWT**.
- [ ] Adição de campo de "quantidade" nos itens do carrinho.
- [ ] Criação do histórico de pedidos finalizados.

---
**Desenvolvido por [Seu Nome]** - Janeiro de 2026.