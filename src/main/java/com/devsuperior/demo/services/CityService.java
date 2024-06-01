package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll(Sort.by("name"));
        return cities.stream().map(CityDTO::new).toList();
    }

    @Transactional
    public CityDTO findById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found"));
        return new CityDTO(cityRepository.save(city));
    }

    @Transactional
    public CityDTO insert(CityDTO cityDTO) {
        City city = new City(null, cityDTO.getName());
        return new CityDTO(cityRepository.save(city));
    }

    @Transactional
    public void delete(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("City not found");
        }
        try {
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("This city is being used!");
        }
    }

}
