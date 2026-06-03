package br.edu.exemplo;

/**
 * Representa uma disciplina dentro de um sistema academico simples.
 */
public class Disciplina {

    private final String nome;
    private final int carga_horaria;
    private final String pedra;

    /**
     * Cria uma disciplina com nome, carga horaria e pedra.
     *
     * @param nome nome da disciplina
     * @param carga_horaria quantidade de horas da disciplina
     * @param pedra identificacao da pedra da disciplina
     * @throws IllegalArgumentException quando algum dado obrigatorio for invalido
     */
    public Disciplina(String nome, int carga_horaria, String pedra) {
        if (textoEstaVazio(nome)) {
            throw new IllegalArgumentException("O nome da disciplina e obrigatorio.");
        }

        if (carga_horaria <= 0) {
            throw new IllegalArgumentException("A carga horaria deve ser maior que zero.");
        }

        if (textoEstaVazio(pedra)) {
            throw new IllegalArgumentException("A pedra da disciplina e obrigatoria.");
        }

        this.nome = nome.trim();
        this.carga_horaria = carga_horaria;
        this.pedra = pedra.trim();

        System.out.println("[INFO] Disciplina criada: " + this.nome + " | carga horaria: " + this.carga_horaria);
    }

    public String getNome() {
        return nome;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public String getPedra() {
        return pedra;
    }

    private boolean textoEstaVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
