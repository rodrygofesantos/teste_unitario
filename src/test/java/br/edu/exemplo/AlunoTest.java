package br.edu.exemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AlunoTest {
    @Test
    void deveCriarAlunoComNomeEMatricula() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        assertEquals("Ana Maria", aluno.getNome());
        assertEquals("CC2026001", aluno.getMatricula());
    }

    @Test
    void deveRemoverEspacosDoNomeEMatricula() {
        Aluno aluno = new Aluno(" Ana Maria ", " CC2026001 ");

        assertEquals("Ana Maria", aluno.getNome());
        assertEquals("CC2026001", aluno.getMatricula());
    }

    @Test
    void deveRegistrarNotasValidas() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        aluno.registrarNota(8.5);
        aluno.registrarNota(7.0);

        assertEquals(2, aluno.getNotas().size());
        assertEquals(8.5, aluno.getNotas().get(0));
        assertEquals(7.0, aluno.getNotas().get(1));
    }

    @Test
    void deveCalcularMediaDasNotas() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        aluno.registrarNota(6.0);
        aluno.registrarNota(8.0);
        aluno.registrarNota(10.0);

        assertEquals(8.0, aluno.calcularMedia());
    }

    @Test
    void deveRetornarZeroQuandoNaoHaNotas() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        assertEquals(0.0, aluno.calcularMedia());
    }

    @Test
    void deveAprovarAlunoComMediaMaiorOuIgualASete() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        aluno.registrarNota(7.0);
        aluno.registrarNota(8.0);

        assertTrue(aluno.estaAprovado());
    }

    @Test
    void deveReprovarAlunoComMediaMenorQueSete() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        aluno.registrarNota(6.0);
        aluno.registrarNota(6.5);

        assertFalse(aluno.estaAprovado());
    }

    @Test
    void naoDeveAceitarNotaMenorQueZero() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        assertThrows(IllegalArgumentException.class, () -> aluno.registrarNota(-1.0));
    }

    @Test
    void naoDeveAceitarNotaMaiorQueDez() {
        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        assertThrows(IllegalArgumentException.class, () -> aluno.registrarNota(11.0));
    }

    @Test
    void naoDeveCriarAlunoSemNome() {
        assertThrows(IllegalArgumentException.class, () -> new Aluno(" ", "CC2026001"));
    }

    @Test
    void naoDeveCriarAlunoSemMatricula() {
        assertThrows(IllegalArgumentException.class, () -> new Aluno("Ana Maria", " "));
    }
}
