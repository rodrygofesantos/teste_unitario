# Tutorial: Classe `Aluno` em Java com JUnit

Material didatico para a disciplina **Construção e Arquitetura de Sistemas** do curso de **Ciencia da Computação** do Unipac - Barbacena.

Este projeto apresenta uma classe simples chamada `Aluno`, demonstrando orientacao a objetos, encapsulamento, validacoes, logs no console e testes unitarios com JUnit.

## Objetivos da aula

Ao final deste tutorial, o aluno deve conseguir:

- Entender a diferenca entre classe e objeto.
- Identificar atributos e metodos em uma classe Java.
- Compreender por que usamos encapsulamento.
- Criar objetos usando construtores.
- Validar dados de entrada.
- Ler logs simples no console.
- Escrever e interpretar testes unitarios com JUnit.

## Estrutura do projeto

```text
classe-aluno-junit/
├── pom.xml
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

## 1. O que e uma classe?

Uma classe e um molde.

Quando dizemos `class Aluno`, estamos descrevendo que todo aluno do nosso sistema tera certas informacoes e certos comportamentos.

No projeto, a classe `Aluno` possui:

- `nome`: representa o nome do aluno.
- `matricula`: representa a identificacao academica.
- `notas`: representa as notas registradas.

Esses dados sao chamados de **atributos**.

## 2. O que e um objeto?

Um objeto e uma instancia concreta da classe.

Exemplo:

```java
Aluno aluno = new Aluno("Ana Maria", "CC2026001");
```

Aqui, `Aluno` e a classe. Ja `aluno` e o objeto criado na memoria.

Uma analogia simples:

- Classe: planta arquitetonica de uma casa.
- Objeto: casa construida a partir daquela planta.

Na disciplina de Arquitetura de Sistemas, essa ideia e importante porque sistemas bem organizados nascem de modelos claros.

## 3. A classe `Aluno`

Arquivo: `src/main/java/br/edu/exemplo/Aluno.java`

```java
private final String nome;
private final String matricula;
private final List<Double> notas;
```

Esses campos sao `private`, ou seja, nao podem ser acessados diretamente por outras classes.

Isso e **encapsulamento**.

### Por que encapsular?

Imagine que qualquer parte do sistema pudesse alterar as notas diretamente. Seria facil colocar uma nota invalida, como `-5` ou `20`.

Ao encapsular, a classe `Aluno` protege seus proprios dados e obriga o restante do sistema a usar metodos controlados, como:

```java
aluno.registrarNota(8.5);
```

Assim, antes de salvar a nota, o metodo verifica se ela esta entre `0.0` e `10.0`.

## 4. Construtor

O construtor e executado quando criamos um objeto.

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

Ponto importante para os alunos:

O construtor garante que um objeto `Aluno` ja nasca em um estado valido.

Um aluno sem nome ou sem matricula nao faz sentido para o sistema, entao a classe impede essa criacao.

## 5. Metodo `registrarNota`

```java
public void registrarNota(double nota) {
    if (nota < 0.0 || nota > 10.0) {
        throw new IllegalArgumentException("A nota deve estar entre 0.0 e 10.0.");
    }

    notas.add(nota);
}
```

Esse metodo representa um comportamento do aluno dentro do sistema.

Ele nao apenas adiciona uma nota. Ele tambem protege a regra de negocio:

> Uma nota valida deve estar entre 0 e 10.

Em arquitetura de sistemas, isso e uma decisao importante: a regra fica perto do dado que ela protege.

## 6. Metodo `calcularMedia`

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

Esse metodo percorre as notas, soma os valores e divide pela quantidade de notas.

Se nao houver nota registrada, a media sera `0.0`.

## 7. Metodo `estaAprovado`

```java
public boolean estaAprovado() {
    return calcularMedia() >= 7.0;
}
```

Aqui aparece uma regra de negocio simples:

Um aluno esta aprovado quando sua media e maior ou igual a `7.0`.

Esse metodo retorna um valor booleano:

- `true`: aprovado.
- `false`: reprovado.

## 8. Logs no console

O projeto usa `System.out.println` com prefixos como `[LOG]`, `[INFO]` e `[TESTE]`.

Em projetos profissionais, normalmente usamos bibliotecas de log, como Logback ou Log4j. Para uma primeira aula de orientacao a objetos, `System.out.println` ajuda porque deixa o fluxo do programa visivel.

Exemplo de saida esperada ao executar a aplicacao:

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
[INFO] Media calculada para Ana Maria: 8.166666666666666
[INFO] Situacao de Ana Maria: APROVADO
[LOG] Aprovado? true
[LOG] Demonstracao finalizada.
```

## 9. Testes unitarios com JUnit

Arquivo: `src/test/java/br/edu/exemplo/AlunoTest.java`

Um teste unitario verifica se uma pequena parte do sistema funciona corretamente.

Neste projeto, os testes verificam se:

- O aluno e criado com nome e matricula.
- Notas validas sao registradas.
- A media e calculada corretamente.
- O aluno e aprovado com media maior ou igual a 7.
- O aluno e reprovado com media menor que 7.
- Notas menores que 0 nao sao aceitas.
- Notas maiores que 10 nao sao aceitas.
- Um aluno sem nome nao pode ser criado.

### Padrao mental: Arrange, Act, Assert

Mesmo quando nao escrevemos esses nomes no codigo, muitos testes seguem tres momentos:

- **Arrange**: preparar os dados.
- **Act**: executar o comportamento testado.
- **Assert**: verificar o resultado.

Exemplo:

```java
aluno.registrarNota(6.0);
aluno.registrarNota(8.0);
aluno.registrarNota(10.0);

assertEquals(8.0, aluno.calcularMedia());
```

Neste teste:

- Preparamos tres notas.
- Chamamos `calcularMedia`.
- Verificamos se o resultado foi `8.0`.

## 10. Como executar

Para compilar e executar os testes, use:

```bash
mvn test
```

Para executar a classe principal:

```bash
mvn compile exec:java
```

Uma alternativa simples e executar pela IDE, clicando no metodo `main` da classe `App`.

## 11. Roteiro sugerido para explicar em sala

1. Mostre primeiro a classe `Aluno`.
2. Pergunte aos alunos quais informacoes um aluno precisa ter.
3. Relacione as respostas com os atributos `nome`, `matricula` e `notas`.
4. Explique que os atributos estao privados para proteger o estado interno.
5. Mostre o construtor e explique que ele cria objetos validos.
6. Mostre `registrarNota` e destaque a validacao.
7. Execute a classe `App` e leia os logs com a turma.
8. Abra os testes JUnit e explique que eles documentam o comportamento esperado.
9. Rode `mvn test` e mostre que os testes funcionam como uma rede de seguranca.
10. Altere uma regra de proposito, como a media para `8.0`, e mostre quais testes quebram.

## 12. Mensagem final para os alunos

Orientacao a objetos nao e apenas escrever `class`.

Orientacao a objetos e organizar responsabilidades.

Neste exemplo, a classe `Aluno` e responsavel por guardar os dados de um aluno e proteger as regras relacionadas a esses dados. O teste JUnit, por sua vez, confirma que essa responsabilidade esta sendo cumprida.

Esse e o inicio do pensamento arquitetural: cada parte do sistema deve ter um papel claro, pequeno e verificavel.
