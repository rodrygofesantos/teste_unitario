package br.edu.exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes unitarios da classe Aluno.
 *
 * <p>Um teste unitario verifica uma pequena unidade do sistema, normalmente uma
 * classe ou um metodo, de forma automatizada e repetivel.</p>
 */
class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    void prepararTeste() {
        System.out.println("[TESTE] Preparando um novo objeto Aluno para o teste.");
        aluno = new Aluno("Carlos Souza", "CC2026002");
    }

    @Test
    @DisplayName("Deve criar aluno com nome e matricula")
    void deveCriarAlunoComNomeEMatricula() {
        System.out.println("[TESTE] Verificando se nome e matricula foram armazenados corretamente.");

        // Arrange: o metodo @BeforeEach ja preparou o objeto Aluno usado neste teste.

        // Act: consultamos o estado do objeto para observar o resultado da criacao.
        String nome = aluno.getNome();
        String matricula = aluno.getMatricula();

        // Assert: confirmamos se o objeto guardou exatamente os dados esperados.
        assertEquals("Carlos Souza", nome);
        assertEquals("CC2026002", matricula);
    }

    @Test
    @DisplayName("Deve remover espacos do nome e matricula")
    void deveRemoverEspacosDoNomeEMatricula() {
        System.out.println("[TESTE] Verificando se espacos extras sao removidos.");

        // Arrange: preparamos valores validos com espacos extras.
        String nomeComEspacos = " Carlos Souza ";
        String matriculaComEspacos = " CC2026002 ";

        // Act: criamos um aluno com os valores preparados.
        Aluno alunoComEspacos = new Aluno(nomeComEspacos, matriculaComEspacos);

        // Assert: confirmamos se os dados foram normalizados.
        assertEquals("Carlos Souza", alunoComEspacos.getNome());
        assertEquals("CC2026002", alunoComEspacos.getMatricula());
    }

    @Test
    @DisplayName("Deve registrar notas validas")
    void deveRegistrarNotasValidas() {
        System.out.println("[TESTE] Registrando duas notas validas.");

        // Arrange: definimos os dados de entrada que serao usados no comportamento testado.
        double primeiraNota = 8.0;
        double segundaNota = 9.0;

        // Act: executamos o comportamento principal do teste, registrando as notas.
        aluno.registrarNota(primeiraNota);
        aluno.registrarNota(segundaNota);

        // Assert: verificamos se as notas foram armazenadas na quantidade e ordem esperadas.
        assertEquals(2, aluno.getNotas().size());
        assertEquals(primeiraNota, aluno.getNotas().get(0));
        assertEquals(segundaNota, aluno.getNotas().get(1));
    }

    @Test
    @DisplayName("Deve calcular media das notas")
    void deveCalcularMediaDasNotas() {
        System.out.println("[TESTE] Calculando a media de tres notas.");

        // Arrange: registramos notas conhecidas para que a media esperada seja previsivel.
        aluno.registrarNota(6.0);
        aluno.registrarNota(8.0);
        aluno.registrarNota(10.0);

        // Act: chamamos o metodo que calcula a media do aluno.
        double media = aluno.calcularMedia();

        // Assert: comparamos a media calculada com o resultado esperado.
        assertEquals(8.0, media);
    }

    @Test
    @DisplayName("Deve retornar zero quando nao ha notas")
    void deveRetornarZeroQuandoNaoHaNotas() {
        System.out.println("[TESTE] Calculando media sem notas registradas.");

        // Arrange: o metodo @BeforeEach ja criou um aluno sem notas.

        // Act: calculamos a media sem registrar nenhuma nota.
        double media = aluno.calcularMedia();

        // Assert: confirmamos o valor padrao esperado.
        assertEquals(0.0, media);
    }

    @Test
    @DisplayName("Deve aprovar aluno com media maior ou igual a 7")
    void deveAprovarAlunoComMediaMaiorOuIgualASete() {
        System.out.println("[TESTE] Verificando regra de aprovacao.");

        // Arrange: registramos notas que produzem media maior ou igual a 7.0.
        aluno.registrarNota(7.0);
        aluno.registrarNota(8.0);

        // Act: executamos a regra de negocio que verifica a aprovacao.
        boolean aprovado = aluno.estaAprovado();

        // Assert: confirmamos que o resultado da regra foi verdadeiro.
        assertTrue(aprovado);
    }

    @Test
    @DisplayName("Deve reprovar aluno com media menor que 7")
    void deveReprovarAlunoComMediaMenorQueSete() {
        System.out.println("[TESTE] Verificando regra de reprovacao.");

        // Arrange: registramos notas que produzem media menor que 7.0.
        aluno.registrarNota(5.0);
        aluno.registrarNota(6.0);

        // Act: executamos a regra de negocio que verifica a aprovacao.
        boolean aprovado = aluno.estaAprovado();

        // Assert: confirmamos que o aluno nao foi aprovado.
        assertFalse(aprovado);
    }

    @Test
    @DisplayName("Nao deve aceitar nota menor que zero")
    void naoDeveAceitarNotaMenorQueZero() {
        System.out.println("[TESTE] Tentando registrar uma nota invalida menor que zero.");

        // Arrange: preparamos uma nota invalida para testar a protecao da classe.
        double notaInvalida = -1.0;

        // Act: descrevemos a acao que deve falhar quando for executada pelo JUnit.
        Executable registrarNotaInvalida = () -> aluno.registrarNota(notaInvalida);

        // Assert: verificamos se a classe lancou a excecao esperada.
        assertThrows(IllegalArgumentException.class, registrarNotaInvalida);
    }

    @Test
    @DisplayName("Nao deve aceitar nota maior que dez")
    void naoDeveAceitarNotaMaiorQueDez() {
        System.out.println("[TESTE] Tentando registrar uma nota invalida maior que dez.");

        // Arrange: preparamos uma nota acima do limite permitido.
        double notaInvalida = 11.0;

        // Act: descrevemos a tentativa de registrar a nota invalida.
        Executable registrarNotaInvalida = () -> aluno.registrarNota(notaInvalida);

        // Assert: verificamos se a validacao impediu o registro da nota.
        assertThrows(IllegalArgumentException.class, registrarNotaInvalida);
    }

    @Test
    @DisplayName("Nao deve criar aluno sem nome")
    void naoDeveCriarAlunoSemNome() {
        System.out.println("[TESTE] Tentando criar aluno sem nome.");

        // Arrange: preparamos dados invalidos para criar um aluno sem nome.
        String nomeInvalido = " ";
        String matriculaValida = "CC2026003";

        // Act: descrevemos a criacao do aluno invalido, sem executar diretamente aqui.
        Executable criarAlunoSemNome = () -> new Aluno(nomeInvalido, matriculaValida);

        // Assert: verificamos se o construtor bloqueou a criacao do objeto invalido.
        assertThrows(IllegalArgumentException.class, criarAlunoSemNome);
    }

    @Test
    @DisplayName("Nao deve criar aluno sem matricula")
    void naoDeveCriarAlunoSemMatricula() {
        System.out.println("[TESTE] Tentando criar aluno sem matricula.");

        // Arrange: preparamos dados invalidos para criar um aluno sem matricula.
        String nomeValido = "Carlos Souza";
        String matriculaInvalida = " ";

        // Act: descrevemos a criacao do aluno invalido, sem executar diretamente aqui.
        Executable criarAlunoSemMatricula = () -> new Aluno(nomeValido, matriculaInvalida);

        // Assert: verificamos se o construtor bloqueou a criacao do objeto invalido.
        assertThrows(IllegalArgumentException.class, criarAlunoSemMatricula);
    }
}
