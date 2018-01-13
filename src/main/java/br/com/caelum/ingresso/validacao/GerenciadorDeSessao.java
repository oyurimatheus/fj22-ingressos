package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	private List<Sessao> sessoesSala;

	public GerenciadorDeSessao(List<Sessao> sessoes) {
		this.sessoesSala = sessoes;
	}

	public boolean cabe(Sessao sessaoAtual) {
		Optional<Boolean> optionalCabe = sessoesSala.stream()
				.map(sessaoExistente -> horarioIsValido(sessaoExistente, sessaoAtual)).reduce(Boolean::logicalAnd);

		return optionalCabe.orElse(true);
	}

	private boolean horarioIsValido(Sessao sessaoExistente, Sessao sessaoAtual) {
		LocalDate hoje = LocalDate.now();

		LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje);
		LocalDateTime horarioSessaoAtual = sessaoAtual.getHorario().atDate(hoje);

		boolean ehAntes = horarioSessaoAtual.isBefore(horarioSessaoExistente);

		if (ehAntes)
			return horarioSessaoAtual.plus(sessaoExistente.getFilme().getDuracao()).isBefore(horarioSessaoExistente);
		else
			return horarioSessaoExistente.plus(sessaoExistente.getFilme().getDuracao()).isBefore(horarioSessaoAtual);
	}
}
