package br.edu.exemplo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes unitarios da classe Disciplina.
 */
class DisciplinaTest {

    @Test
    @DisplayName("Deve criar disciplina com nome, carga horaria e pedra")
    void deveCriarDisciplinaComNomeCargaHorariaEPedra() {
        System.out.println("[TESTE] Verificando se os dados da disciplina foram armazenados corretamente.");

        // Arrange: preparamos dados validos para criar a disciplina.
        String nome = "Programacao Orientada a Objetos";
        int carga_horaria = 80;
        String pedra = "A";

        // Act: criamos a disciplina usando os dados preparados.
        Disciplina disciplina = new Disciplina(nome, carga_horaria, pedra);

        // Assert: confirmamos se o objeto guardou exatamente os dados esperados.
        assertEquals(nome, disciplina.getNome());
        assertEquals(carga_horaria, disciplina.getCarga_horaria());
        assertEquals(pedra, disciplina.getPedra());
    }

    @Test
    @DisplayName("Deve remover espacos do nome e pedra")
    void deveRemoverEspacosDoNomeEPedra() {
        System.out.println("[TESTE] Verificando se espacos extras sao removidos da disciplina.");

        // Arrange: preparamos valores validos com espacos extras.
        String nomeComEspacos = " Programacao Orientada a Objetos ";
        int carga_horaria = 80;
        String pedraComEspacos = " A ";

        // Act: criamos a disciplina com os valores preparados.
        Disciplina disciplina = new Disciplina(nomeComEspacos, carga_horaria, pedraComEspacos);

        // Assert: confirmamos se os dados foram normalizados.
        assertEquals("Programacao Orientada a Objetos", disciplina.getNome());
        assertEquals(carga_horaria, disciplina.getCarga_horaria());
        assertEquals("A", disciplina.getPedra());
    }

    @Test
    @DisplayName("Nao deve criar disciplina sem nome")
    void naoDeveCriarDisciplinaSemNome() {
        System.out.println("[TESTE] Tentando criar disciplina sem nome.");

        // Arrange: preparamos dados invalidos para criar uma disciplina sem nome.
        String nomeInvalido = " ";
        int carga_horaria = 80;
        String pedra = "A";

        // Act: descrevemos a criacao da disciplina invalida.
        Executable criarDisciplinaSemNome = () -> new Disciplina(nomeInvalido, carga_horaria, pedra);

        // Assert: verificamos se o construtor bloqueou a criacao do objeto invalido.
        assertThrows(IllegalArgumentException.class, criarDisciplinaSemNome);
    }

    @Test
    @DisplayName("Nao deve criar disciplina com carga horaria zerada")
    void naoDeveCriarDisciplinaComCargaHorariaZerada() {
        System.out.println("[TESTE] Tentando criar disciplina com carga horaria zerada.");

        // Arrange: preparamos dados invalidos para criar uma disciplina sem carga horaria valida.
        String nome = "Programacao Orientada a Objetos";
        int carga_horaria = 0;
        String pedra = "A";

        // Act: descrevemos a criacao da disciplina invalida.
        Executable criarDisciplinaSemCargaHoraria = () -> new Disciplina(nome, carga_horaria, pedra);

        // Assert: verificamos se o construtor bloqueou a criacao do objeto invalido.
        assertThrows(IllegalArgumentException.class, criarDisciplinaSemCargaHoraria);
    }

    @Test
    @DisplayName("Nao deve criar disciplina sem pedra")
    void naoDeveCriarDisciplinaSemPedra() {
        System.out.println("[TESTE] Tentando criar disciplina sem pedra.");

        // Arrange: preparamos dados invalidos para criar uma disciplina sem pedra.
        String nome = "Programacao Orientada a Objetos";
        int carga_horaria = 80;
        String pedraInvalida = " ";

        // Act: descrevemos a criacao da disciplina invalida.
        Executable criarDisciplinaSemPedra = () -> new Disciplina(nome, carga_horaria, pedraInvalida);

        // Assert: verificamos se o construtor bloqueou a criacao do objeto invalido.
        assertThrows(IllegalArgumentException.class, criarDisciplinaSemPedra);
    }
}
