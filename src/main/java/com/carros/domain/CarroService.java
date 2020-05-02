package com.carros.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CarroService {

	@Autowired
	private CarroRepository rep;

	public Iterable<Carro> getCarros() {

		return rep.findAll();

	}

	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasilia"));
		carros.add(new Carro(3L, "Chevette"));

		return carros;
	}
}
