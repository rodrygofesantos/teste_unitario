package br.edu.exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertEquals("Carlos Souza", aluno.getNome());
        assertEquals("CC2026002", aluno.getMatricula());
    }

    @Test
    @DisplayName("Deve registrar notas validas")
    void deveRegistrarNotasValidas() {
        System.out.println("[TESTE] Registrando duas notas validas.");

        aluno.registrarNota(8.0);
        aluno.registrarNota(9.0);

        assertEquals(2, aluno.getNotas().size());
        assertEquals(8.0, aluno.getNotas().get(0));
        assertEquals(9.0, aluno.getNotas().get(1));
    }

    @Test
    @DisplayName("Deve calcular media das notas")
    void deveCalcularMediaDasNotas() {
        System.out.println("[TESTE] Calculando a media de tres notas.");

        aluno.registrarNota(6.0);
        aluno.registrarNota(8.0);
        aluno.registrarNota(10.0);

        assertEquals(8.0, aluno.calcularMedia());
    }

    @Test
    @DisplayName("Deve aprovar aluno com media maior ou igual a 7")
    void deveAprovarAlunoComMediaMaiorOuIgualASete() {
        System.out.println("[TESTE] Verificando regra de aprovacao.");

        aluno.registrarNota(7.0);
        aluno.registrarNota(8.0);

        assertTrue(aluno.estaAprovado());
    }

    @Test
    @DisplayName("Deve reprovar aluno com media menor que 7")
    void deveReprovarAlunoComMediaMenorQueSete() {
        System.out.println("[TESTE] Verificando regra de reprovacao.");

        aluno.registrarNota(5.0);
        aluno.registrarNota(6.0);

        assertFalse(aluno.estaAprovado());
    }

    @Test
    @DisplayName("Nao deve aceitar nota menor que zero")
    void naoDeveAceitarNotaMenorQueZero() {
        System.out.println("[TESTE] Tentando registrar uma nota invalida menor que zero.");

        assertThrows(IllegalArgumentException.class, () -> aluno.registrarNota(-1.0));
    }

    @Test
    @DisplayName("Nao deve aceitar nota maior que dez")
    void naoDeveAceitarNotaMaiorQueDez() {
        System.out.println("[TESTE] Tentando registrar uma nota invalida maior que dez.");

        assertThrows(IllegalArgumentException.class, () -> aluno.registrarNota(11.0));
    }

    @Test
    @DisplayName("Nao deve criar aluno sem nome")
    void naoDeveCriarAlunoSemNome() {
        System.out.println("[TESTE] Tentando criar aluno sem nome.");

        assertThrows(IllegalArgumentException.class, () -> new Aluno(" ", "CC2026003"));
    }
}
