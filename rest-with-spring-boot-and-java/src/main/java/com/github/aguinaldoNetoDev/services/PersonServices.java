package com.github.aguinaldoNetoDev.services;

import com.github.aguinaldoNetoDev.controllers.PersonController;
import com.github.aguinaldoNetoDev.data.dto.v1.PersonDTO;
import com.github.aguinaldoNetoDev.exception.ResourceNotFoundException;
import com.github.aguinaldoNetoDev.mapper.custom.PersonMapper;
import com.github.aguinaldoNetoDev.model.Person;
import com.github.aguinaldoNetoDev.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static com.github.aguinaldoNetoDev.mapper.ObjectMapper.parseListObjects;
import static com.github.aguinaldoNetoDev.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper converter;

    public PersonDTO createV2(PersonDTO personDTO) {
        logger.info("Save person");

        var entity = parseObject(personDTO, Person.class);

        var dto = parseObject(repository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        var dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all people");

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);

        persons.forEach(this::addHateoasLinks);

        return persons;

    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Finding one person");

        Person entity = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        logger.info("Updating founded person");
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("Finding one person");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        logger.info("Deleting one person");

        repository.delete(entity);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class)
                .create(dto))
                .withRel("create")
                .withType("POST"));

        dto.add(linkTo(methodOn(PersonController.class)
                .findById(dto.getId()))
                .withSelfRel()
                .withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class)
                .findAll())
                .withRel("findAll")
                .withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class)
                .update(dto))
                .withRel("update")
                .withType("PUT"));

        dto.add(linkTo(methodOn(PersonController.class)
                .deleteById(dto.getId()))
                .withRel("delete")
                .withType("DELETE"));
    }

    /*SERVICE V2

    public PersonDTOV2 createV2(PersonDTOV2 dtoV2) {
        logger.info("Save person");

        Person entity = converter.convertDTOToEntity(dtoV2);

        return converter.convertEntityToDTO(repository.save(entity));
    }
     */
}
