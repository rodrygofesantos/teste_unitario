package br.edu.exemplo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um aluno dentro de um sistema academico simples.
 *
 * <p>Esta classe foi criada para demonstrar os conceitos fundamentais de
 * orientacao a objetos:</p>
 *
 * <ul>
 *     <li>Classe: o molde que descreve o que um aluno possui e faz.</li>
 *     <li>Objeto: uma instancia concreta de Aluno em memoria.</li>
 *     <li>Atributos: dados internos do aluno, como nome, matricula e notas.</li>
 *     <li>Metodos: comportamentos do aluno, como registrar nota e calcular media.</li>
 *     <li>Encapsulamento: protecao dos dados internos por meio de campos privados.</li>
 * </ul>
 */
public class Aluno {

    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 10.0;
    private static final double MEDIA_PARA_APROVACAO = 7.0;

    private final String nome;
    private final String matricula;
    private final List<Double> notas;

    /**
     * Cria um aluno com nome e matricula.
     *
     * @param nome nome completo do aluno
     * @param matricula identificador academico do aluno
     * @throws IllegalArgumentException quando nome ou matricula estiverem vazios
     */
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

        System.out.println("[INFO] Aluno criado: " + this.nome + " | matricula: " + this.matricula);
    }

    /**
     * Registra uma nova nota para o aluno.
     *
     * @param nota valor numerico entre 0.0 e 10.0
     * @throws IllegalArgumentException quando a nota estiver fora do intervalo permitido
     */
    public void registrarNota(double nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota deve estar entre 0.0 e 10.0.");
        }

        notas.add(nota);
        System.out.println("[INFO] Nota registrada para " + nome + ": " + nota);
    }

    /**
     * Calcula a media aritmetica das notas registradas.
     *
     * @return media das notas ou 0.0 quando ainda nao houver notas
     */
    public double calcularMedia() {
        if (notas.isEmpty()) {
            System.out.println("[INFO] Nenhuma nota registrada para " + nome + ". Media considerada: 0.0");
            return 0.0;
        }

        double soma = 0.0;

        for (double nota : notas) {
            soma += nota;
        }

        double media = soma / notas.size();
        System.out.println("[INFO] Media calculada para " + nome + ": " + media);
        return media;
    }

    /**
     * Verifica se o aluno atingiu a media minima para aprovacao.
     *
     * @return true quando a media for maior ou igual a 7.0; false caso contrario
     */
    public boolean estaAprovado() {
        boolean aprovado = calcularMedia() >= MEDIA_PARA_APROVACAO;
        System.out.println("[INFO] Situacao de " + nome + ": " + (aprovado ? "APROVADO" : "REPROVADO"));
        return aprovado;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    /**
     * Retorna uma copia somente leitura das notas.
     *
     * <p>Esse cuidado evita que outra parte do programa altere a lista interna
     * diretamente, preservando o encapsulamento da classe.</p>
     */
    public List<Double> getNotas() {
        return Collections.unmodifiableList(notas);
    }

    private boolean textoEstaVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
