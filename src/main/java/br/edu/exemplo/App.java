package br.edu.exemplo;

/**
 * Classe de entrada da aplicacao.
 *
 * <p>Ela simula o uso das classes Aluno e Disciplina como aconteceria em uma
 * pequena parte de um sistema academico.</p>
 */
public class App {

    public static void main(String[] args) {
        System.out.println("[LOG] Iniciando demonstracao das classes academicas.");

        Aluno aluno = new Aluno("Ana Maria", "CC2026001");

        System.out.println("[LOG] Registrando notas do aluno.");
        aluno.registrarNota(8.5);
        aluno.registrarNota(7.0);
        aluno.registrarNota(9.0);

        System.out.println("[LOG] Consultando dados do aluno.");
        System.out.println("[LOG] Nome: " + aluno.getNome());
        System.out.println("[LOG] Matricula: " + aluno.getMatricula());
        System.out.println("[LOG] Notas: " + aluno.getNotas());
        System.out.println("[LOG] Media final: " + aluno.calcularMedia());
        System.out.println("[LOG] Aprovado? " + aluno.estaAprovado());

        Disciplina disciplina = new Disciplina("Programacao Orientada a Objetos", 80, "A");

        System.out.println("[LOG] Consultando dados da disciplina.");
        System.out.println("[LOG] Nome: " + disciplina.getNome());
        System.out.println("[LOG] Carga horaria: " + disciplina.getCarga_horaria());
        System.out.println("[LOG] Pedra: " + disciplina.getPedra());

        System.out.println("[LOG] Demonstracao finalizada.");
    }
}
