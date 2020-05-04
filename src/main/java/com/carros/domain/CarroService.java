package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {

		return rep.findAll().stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());

	}

	public Optional<CarroDTO> getCarroById(Long id) {

		return rep.findById(id).map(c -> CarroDTO.create(c));

		/*
		 * FORMA COMPLETA DE FAZER SEM LAMBDA Optional<Carro> carro = rep.findById(id);
		 * 
		 * if (carro.isPresent()) { return Optional.of(new CarroDTO(carro.get())); }
		 * else { return null; }
		 */

	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}

	public CarroDTO salvar(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");

		return CarroDTO.create(rep.save(carro));

	}

	public boolean delete(Long id) {

		if (getCarroById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro!");

		Optional<Carro> optional = rep.findById(id);

		if (optional.isPresent()) {

			Carro db = optional.get();

			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());

			rep.save(db);

			return CarroDTO.create(db);

		} else {
			return null;
		}
	}

	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasilia"));
		carros.add(new Carro(3L, "Chevette"));

		return carros;
	}

}
