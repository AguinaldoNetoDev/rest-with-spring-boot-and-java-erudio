package com.github.aguinaldoNetoDev.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.aguinaldoNetoDev.serializer.GenderSerializer;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = GenderSerializer.class)
    private String gender;


    public PersonDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO personDTO)) return false;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getGender(), personDTO.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getGender());
    }
}
