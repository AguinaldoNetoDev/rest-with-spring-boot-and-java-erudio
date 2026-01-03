package com.github.aguinaldoNetoDev.services;

import com.github.aguinaldoNetoDev.data.dto.PersonDTO;
import com.github.aguinaldoNetoDev.exception.ResourceNotFoundException;
import com.github.aguinaldoNetoDev.mapper.ObjectMapper;
import com.github.aguinaldoNetoDev.model.Person;
import com.github.aguinaldoNetoDev.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static com.github.aguinaldoNetoDev.mapper.ObjectMapper.parseListObjects;
import static com.github.aguinaldoNetoDev.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonDTO create(PersonDTO dto) {
        logger.info("Save person");

        // parametro 1: passa o DTO para converter em uma entidade, parametro 2: passa o tipo da entidade
        var entity = parseObject(dto, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        return parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all people");

        // parametro 1: busca os dados na entidade, parametro 2: converte de entidade para DTO
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO dto) {
        logger.info("Finding one person");

        Person entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        logger.info("Updating founded person");
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Finding one person");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        logger.info("Deleting one person");

        repository.delete(entity);
    }
}
