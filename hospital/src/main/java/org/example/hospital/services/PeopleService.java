package org.example.hospital.services;

import org.example.hospital.models.Person;
import org.example.hospital.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService{

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findByNameAndAge(String nameToFind,int ageToFind){
        return peopleRepository.findByNameAndAge(nameToFind,ageToFind);
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public List<Person> findByNameAndAgeAndGender(String name,int age,String gender){
        return peopleRepository.findByNameAndAgeAndGender(name,age,gender);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    public Person findLastAdded(){
        return peopleRepository.findLastAdded();
    }

    @Transactional
    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }

    public Person findById(int id){
        return peopleRepository.findById(id);
    }
}
