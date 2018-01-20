package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class SessaoTest {
	
	private Sala sala;
	private Filme filme;
	private BigDecimal somaDosPrecosDaSalaEFilme;
	private Sessao sessao;

	@Before
	public void preparaTestes() {
		this.sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
		this.filme = new Filme("Rougue One", Duration.ofMinutes(120), "Sci-Fi", new BigDecimal("12.0"));
		this.somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
	}
	
	@Test
	public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis() {
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		sessao.setIngressos(ingressos);
		
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
	}
}
