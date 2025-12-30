package com.github.aguinaldoNetoDev.services;

import com.github.aguinaldoNetoDev.exception.ResourceNotFoundException;
import com.github.aguinaldoNetoDev.model.Person;
import com.github.aguinaldoNetoDev.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person create(Person person) {
        logger.info("Save person");
        return repository.save(person);
    }

    public Person findById(Long id) {
        logger.info("Finding one person");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        return repository.findAll();
    }

    public Person update(Person person) {
        logger.info("Finding one person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        logger.info("Updating founded person");
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Finding one person");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        logger.info("Deleting one person");
        repository.delete(entity);
    }
}
