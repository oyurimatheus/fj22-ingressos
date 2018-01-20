package br.com.caelum.ingresso.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;

@Component
@SessionScope
public class Carrinho {
	
	List<Ingresso> ingressos = new ArrayList<>();
	
	public void add(Ingresso ingresso) {
		this.ingressos.add(ingresso);
	}
}
