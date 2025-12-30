package com.github.aguinaldoNetoDev.repository;

import com.github.aguinaldoNetoDev.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
