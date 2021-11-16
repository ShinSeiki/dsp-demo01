package com.apso.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apso.dsp.model.Camion;

public interface CamionRepository extends JpaRepository<Camion, Integer> {

	List<Camion> findByPlacaveh(String placaveh);
	
}
