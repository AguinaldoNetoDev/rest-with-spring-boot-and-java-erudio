package com.github.aguinaldoNetoDev.mapper.custom;

import com.github.aguinaldoNetoDev.data.dto.v2.PersonDTOV2;
import com.github.aguinaldoNetoDev.model.Person;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person entity) {
        PersonDTOV2 dtoV2 = new PersonDTOV2();

        dtoV2.setId(entity.getId());
        dtoV2.setFirstName(entity.getFirstName());
        dtoV2.setLastName(entity.getLastName());
        dtoV2.setAddress(entity.getAddress());
        dtoV2.setGender(entity.getGender());
        dtoV2.setBirthDay(new Date());

        return dtoV2;
    }

    public Person convertDTOToEntity(PersonDTOV2 dtoV2) {
        Person entity = new Person();

        entity.setFirstName(dtoV2.getFirstName());
        entity.setLastName(dtoV2.getLastName());
        entity.setAddress(dtoV2.getAddress());
        entity.setGender(dtoV2.getGender());
        //entity.setBirthDay(new Date());

        return entity;
    }

}
