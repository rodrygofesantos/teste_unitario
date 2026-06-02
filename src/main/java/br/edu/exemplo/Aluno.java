package br.edu.exemplo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aluno {
    private final String nome;
    private final String matricula;
    private final List<Double> notas;

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

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public List<Double> getNotas() {
        return Collections.unmodifiableList(notas);
    }

    public void registrarNota(double nota) {
        if (nota < 0.0 || nota > 10.0) {
            throw new IllegalArgumentException("A nota deve estar entre 0.0 e 10.0.");
        }

        notas.add(nota);
        System.out.println("[INFO] Nota registrada para " + nome + ": " + nota);
    }

    public double calcularMedia() {
        if (notas.isEmpty()) {
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

    public boolean estaAprovado() {
        boolean aprovado = calcularMedia() >= 7.0;
        String situacao = aprovado ? "APROVADO" : "REPROVADO";

        System.out.println("[INFO] Situacao de " + nome + ": " + situacao);
        return aprovado;
    }

    private static boolean textoEstaVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
