# Classe `Aluno` em Java com Testes Unitários JUnit

Material de apoio para a disciplina **Construção e Arquitetura de Sistemas** do curso de **Ciência da Computação**.

Este repositório apresenta um exemplo didático e completo de uma classe Java simples, usando a entidade `Aluno` para introduzir conceitos essenciais de **orientação a objetos**, **encapsulamento**, **regras de negócio**, **logs de execução** e **testes unitários com JUnit**.

O objetivo não é apenas mostrar código funcionando. O objetivo é formar raciocínio arquitetural: cada classe deve ter uma responsabilidade clara, seus dados internos devem ser protegidos, suas regras devem ser verificáveis e seu comportamento deve poder ser testado.

## 1. Visão Geral da Aula

### Tema central

Construção de uma classe simples em Java, com validações e testes automatizados.

### Problema trabalhado

Como representar um aluno em um sistema acadêmico simples?

A classe `Aluno` precisa:

- Guardar nome e matrícula.
- Registrar notas válidas.
- Calcular a média.
- Informar se o aluno está aprovado ou reprovado.
- Proteger seu estado interno contra alterações indevidas.
- Ter seu comportamento validado por testes unitários.

### Competências desenvolvidas

Ao final da aula, o estudante deverá ser capaz de:

- Diferenciar classe, objeto, atributo e método.
- Explicar o papel do construtor na criação de objetos válidos.
- Aplicar encapsulamento com atributos privados.
- Implementar validações simples em uma classe de domínio.
- Interpretar logs de execução.
- Escrever testes unitários usando JUnit.
- Identificar as etapas **Arrange**, **Act** e **Assert** em um teste.
- Relacionar decisões simples de código com princípios de arquitetura de sistemas.

## 2. Pré-Requisitos

Para acompanhar a aula, recomenda-se que o aluno já tenha noções básicas de:

- Sintaxe básica da linguagem Java.
- Criação de classes e métodos.
- Tipos primitivos, como `double` e `boolean`.
- Uso básico de listas em Java.
- Execução de programas pela IDE ou terminal.

Ferramentas recomendadas:

- Java 17 ou superior.
- Maven.
- IDE Java, como IntelliJ IDEA, Eclipse, NetBeans ou VS Code.

## 3. Estrutura do Projeto

```text
classe-aluno-junit/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/
│   │           └── edu/
│   │               └── exemplo/
│   │                   ├── Aluno.java
│   │                   └── App.java
│   └── test/
│       └── java/
│           └── br/
│               └── edu/
│                   └── exemplo/
│                       └── AlunoTest.java
```

### Responsabilidade de cada arquivo

| Arquivo | Responsabilidade |
| --- | --- |
| `Aluno.java` | Representa o aluno e concentra regras relacionadas a notas e aprovação. |
| `App.java` | Demonstra o uso da classe `Aluno` em uma execução simples. |
| `AlunoTest.java` | Valida automaticamente o comportamento da classe `Aluno`. |
| `pom.xml` | Configura o projeto Maven e a dependência do JUnit. |
| `README.md` | Serve como roteiro de estudo e plano de aula. |

## 4. Modelo Conceitual

Antes de abrir o código, apresente aos alunos o modelo mental da solução.

```text
Aluno
├── Dados
│   ├── nome
│   ├── matrícula
│   └── notas
└── Comportamentos
    ├── registrarNota()
    ├── calcularMedia()
    └── estaAprovado()
```

Em orientação a objetos, uma classe não deve ser apenas um agrupamento de variáveis. Ela deve representar uma ideia do domínio e proteger as regras relacionadas a essa ideia.

Neste exemplo, `Aluno` não é só uma estrutura de dados. `Aluno` é uma pequena unidade de domínio do sistema acadêmico.

## 5. Conceitos Fundamentais

### Classe

Classe é o molde que descreve quais dados e comportamentos um objeto terá.

No projeto:

```java
public class Aluno {
    ...
}
```

A classe `Aluno` define o que todo aluno do sistema possui e o que ele consegue fazer.

### Objeto

Objeto é uma instância concreta de uma classe.

```java
Aluno aluno = new Aluno("Ana Maria", "CC2026001");
```

Nesse exemplo:

- `Aluno` é a classe.
- `aluno` é o objeto.
- `new Aluno(...)` cria uma nova instância em memória.

### Atributos

Atributos representam o estado interno do objeto.

```java
private final String nome;
private final String matricula;
private final List<Double> notas;
```

Esses atributos indicam que um aluno possui nome, matrícula e notas.

### Métodos

Métodos representam comportamentos.

```java
public void registrarNota(double nota)
public double calcularMedia()
public boolean estaAprovado()
```

Cada método responde a uma ação relevante do domínio acadêmico.

### Encapsulamento

Encapsulamento é a prática de proteger os dados internos da classe e controlar o acesso por meio de métodos.

No projeto, os atributos são `private`, o que impede que outras classes alterem diretamente o estado interno do aluno.

Isso evita situações como:

```java
aluno.notas.add(50.0);
```

Uma nota `50.0` não é válida. Portanto, a alteração precisa passar por um método que valide a regra:

```java
aluno.registrarNota(8.5);
```

Esse é um ponto arquitetural importante: regras de negócio devem ficar próximas dos dados que elas protegem.

## 6. Análise da Classe `Aluno`

Arquivo: `src/main/java/br/edu/exemplo/Aluno.java`

### Construtor

O construtor cria um objeto em estado válido.

```java
public Aluno(String nome, String matricula) {
    if (textoEstaVazio(nome)) {
        throw new IllegalArgumentException("O nome do aluno e obrigatorio.");
    }

    if (textoEstaVazio(matricula)) {
        throw new IllegalArgumentException("A matricula do aluno e obrigatoria.");
    }

    this.nome = nome.trim();
    this.matricula = matricula.trim();
    this.notas = new ArrayList<>();
}
```

Discussão em sala:

- Um aluno sem nome é válido no sistema?
- Um aluno sem matrícula deveria existir?
- O que acontece quando deixamos qualquer parte do sistema criar objetos inválidos?

Conclusão esperada:

O construtor protege a integridade inicial do objeto.

### Registro de nota

```java
public void registrarNota(double nota) {
    if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
        throw new IllegalArgumentException("A nota deve estar entre 0.0 e 10.0.");
    }

    notas.add(nota);
}
```

Esse método possui duas responsabilidades importantes:

- Validar a entrada.
- Registrar a nota quando ela for válida.

Esse método demonstra uma regra de negócio simples:

```text
Uma nota válida deve estar entre 0.0 e 10.0.
```

### Cálculo de média

```java
public double calcularMedia() {
    if (notas.isEmpty()) {
        return 0.0;
    }

    double soma = 0.0;

    for (double nota : notas) {
        soma += nota;
    }

    return soma / notas.size();
}
```

Esse método mostra uma operação derivada do estado interno do objeto.

A média não fica armazenada como atributo. Ela é calculada a partir das notas registradas.

### Verificação de aprovação

```java
public boolean estaAprovado() {
    return calcularMedia() >= MEDIA_PARA_APROVACAO;
}
```

Aqui aparece uma regra de decisão:

```text
Aluno aprovado = média maior ou igual a 7.0.
```

Essa regra está dentro da classe `Aluno` porque pertence ao comportamento do aluno no contexto acadêmico.

## 7. Logs de Execução

O projeto utiliza `System.out.println` com marcadores como `[LOG]`, `[INFO]` e `[TESTE]`.

Em sistemas profissionais, é comum usar bibliotecas como Logback, Log4j ou SLF4J. Neste projeto, o uso de `System.out.println` é propositalmente didático: ele ajuda o aluno iniciante a visualizar a sequência de execução.

Exemplo esperado ao executar a aplicação:

```text
[LOG] Iniciando demonstracao da classe Aluno.
[INFO] Aluno criado: Ana Maria | matricula: CC2026001
[LOG] Registrando notas do aluno.
[INFO] Nota registrada para Ana Maria: 8.5
[INFO] Nota registrada para Ana Maria: 7.0
[INFO] Nota registrada para Ana Maria: 9.0
[LOG] Consultando dados do aluno.
[LOG] Nome: Ana Maria
[LOG] Matricula: CC2026001
[LOG] Notas: [8.5, 7.0, 9.0]
[INFO] Media calculada para Ana Maria: 8.166666666666666
[LOG] Media final: 8.166666666666666
[INFO] Situacao de Ana Maria: APROVADO
[LOG] Aprovado? true
[LOG] Demonstracao finalizada.
```

Ponto para enfatizar:

Logs ajudam a entender o fluxo de execução, mas não substituem testes automatizados.

## 8. Testes Unitários com JUnit

Arquivo: `src/test/java/br/edu/exemplo/AlunoTest.java`

Um teste unitário verifica uma pequena unidade do sistema. Neste projeto, a unidade testada é a classe `Aluno`.

Os testes verificam:

- Criação de aluno com nome e matrícula.
- Registro de notas válidas.
- Cálculo correto da média.
- Aprovação com média maior ou igual a 7.
- Reprovação com média menor que 7.
- Rejeição de nota menor que zero.
- Rejeição de nota maior que dez.
- Rejeição de aluno sem nome.

### Padrão Arrange, Act e Assert

Os testes estão comentados usando o padrão **Arrange, Act e Assert**.

| Etapa | Significado | Pergunta que o aluno deve fazer |
| --- | --- | --- |
| Arrange | Preparar o cenário do teste. | Quais dados e objetos preciso preparar? |
| Act | Executar o comportamento testado. | Qual ação estou testando? |
| Assert | Verificar o resultado. | O resultado obtido é o esperado? |

Exemplo didático:

```java
// Arrange: registramos notas conhecidas para que a media esperada seja previsivel.
aluno.registrarNota(6.0);
aluno.registrarNota(8.0);
aluno.registrarNota(10.0);

// Act: chamamos o metodo que calcula a media do aluno.
double media = aluno.calcularMedia();

// Assert: comparamos a media calculada com o resultado esperado.
assertEquals(8.0, media);
```

Esse padrão torna o teste mais legível e facilita a manutenção.

## 9. Como Executar o Projeto

### Executar os testes

```bash
mvn test
```

### Executar a aplicação principal

```bash
mvn compile exec:java
```

### Executar pela IDE

Também é possível executar diretamente pela IDE:

- Abra o arquivo `App.java`.
- Localize o método `main`.
- Clique em executar.

Para os testes:

- Abra o arquivo `AlunoTest.java`.
- Execute a classe de teste ou cada método individualmente.

## 10. Plano de Aula Sugerido

### Duração recomendada

Entre 80 e 100 minutos.

### Parte 1: Contextualização do problema

Tempo sugerido: 10 minutos.

Condução:

- Pergunte aos alunos quais dados um sistema acadêmico precisa guardar sobre um aluno.
- Anote respostas como nome, matrícula e notas.
- Mostre que esses elementos se transformam em atributos.
- Pergunte quais ações esse aluno deve realizar no sistema.
- Relacione as respostas com métodos.

Objetivo pedagógico:

Fazer o aluno perceber que código orientado a objetos nasce da modelagem de um problema real.

### Parte 2: Construção da classe

Tempo sugerido: 20 minutos.

Condução:

- Abra `Aluno.java`.
- Explique atributos privados.
- Explique o construtor.
- Mostre as validações de nome e matrícula.
- Mostre a lista de notas.

Perguntas para a turma:

- Por que os atributos não estão públicos?
- O que poderia dar errado se a lista de notas fosse livremente alterada?
- Por que o construtor valida os dados?

Objetivo pedagógico:

Relacionar encapsulamento com integridade do objeto.

### Parte 3: Regras de negócio

Tempo sugerido: 20 minutos.

Condução:

- Explique `registrarNota`.
- Explique `calcularMedia`.
- Explique `estaAprovado`.
- Mostre que cada método representa uma responsabilidade pequena.

Pontos de arquitetura:

- Regra de nota válida pertence ao aluno neste exemplo.
- Média é calculada a partir do estado interno.
- Aprovação é uma decisão de negócio.
- Métodos pequenos são mais fáceis de testar.

Objetivo pedagógico:

Mostrar que arquitetura começa nas pequenas decisões de código.

### Parte 4: Execução e logs

Tempo sugerido: 10 minutos.

Condução:

- Execute `App.java`.
- Leia os logs com a turma.
- Mostre a ordem dos eventos.
- Relacione cada log com uma chamada de método.

Objetivo pedagógico:

Fazer o aluno visualizar o fluxo de execução de um objeto.

### Parte 5: Testes unitários

Tempo sugerido: 25 minutos.

Condução:

- Abra `AlunoTest.java`.
- Explique o papel do `@BeforeEach`.
- Mostre cada teste.
- Destaque os comentários de Arrange, Act e Assert.
- Execute os testes.

Perguntas para a turma:

- Qual comportamento este teste está verificando?
- Qual é o cenário preparado?
- Qual método está sendo executado?
- O que está sendo confirmado no `assert`?

Objetivo pedagógico:

Mostrar que testes são documentação executável do comportamento do sistema.

### Parte 6: Experimento controlado

Tempo sugerido: 10 minutos.

Atividade:

Altere temporariamente a regra de aprovação de `7.0` para `8.0`.

Depois, execute os testes novamente.

Discussão:

- Qual teste falhou?
- Por que falhou?
- O teste encontrou um erro ou uma mudança de regra?
- Se a regra mudou de verdade, quais testes precisam ser atualizados?

Objetivo pedagógico:

Mostrar que testes protegem o comportamento esperado, mas também precisam acompanhar mudanças legítimas de regra.

## 11. Erros Comuns dos Alunos

### Confundir classe com objeto

Correção conceitual:

- Classe é o modelo.
- Objeto é a instância criada a partir do modelo.

### Colocar todos os atributos como `public`

Correção conceitual:

Atributos públicos enfraquecem o encapsulamento e permitem alterações inválidas no estado interno.

### Testar apenas o caso feliz

Correção conceitual:

Bons testes também cobrem erros esperados, como nota negativa, nota maior que dez e nome vazio.

### Achar que log é teste

Correção conceitual:

Log mostra o que aconteceu. Teste verifica automaticamente se o que aconteceu está correto.

### Não separar Arrange, Act e Assert

Correção conceitual:

Testes desorganizados ficam difíceis de ler, explicar e manter.

## 12. Exercícios Propostos

### Exercício 1: Validar matrícula vazia

Crie um teste para garantir que não seja possível criar um aluno com matrícula vazia.

### Exercício 2: Média sem notas

Crie um teste para verificar que um aluno sem notas possui média `0.0`.

### Exercício 3: Média mínima configurável

Altere a média de aprovação para `6.0` e atualize os testes.

### Exercício 4: Situação textual

Crie um método `obterSituacao()` que retorne:

- `"APROVADO"` quando o aluno estiver aprovado.
- `"REPROVADO"` quando o aluno estiver reprovado.

Depois, crie testes para esse novo método.

### Exercício 5: Refatoração de logs

Pesquise uma biblioteca de log profissional para Java e discuta como ela poderia substituir `System.out.println`.

## 13. Critérios de Avaliação

Uma solução satisfatória deve apresentar:

- Classe com responsabilidade clara.
- Atributos privados.
- Construtor com validação.
- Métodos pequenos e compreensíveis.
- Regras de negócio testáveis.
- Testes para cenários válidos e inválidos.
- Comentários úteis nos testes.
- Uso correto de Arrange, Act e Assert.

## 14. Fechamento da Aula

Orientação a objetos não é apenas escrever `class`.

Orientação a objetos é distribuir responsabilidades de forma clara.

Neste projeto, a classe `Aluno` concentra dados e comportamentos relacionados ao aluno. Os testes JUnit documentam e verificam esse comportamento. Os logs ajudam a observar a execução. A arquitetura aparece justamente nessa organização: cada parte do sistema tem um papel compreensível, verificável e evolutivo.

Essa é uma base simples, mas poderosa, para construir sistemas maiores com mais qualidade.
