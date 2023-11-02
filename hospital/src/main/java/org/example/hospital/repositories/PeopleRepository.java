package org.example.hospital.repositories;

import org.example.hospital.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    List<Person> findAll();
    List<Person> findByNameAndAge(String nameToFind,int age);
    List<Person> findByNameAndAgeAndGender(String name,int age,String gender);
    void deleteById(int id);
    Person findById(int id);

    @Query("SELECT MAX(id) FROM Person")
    Person findLastAdded();
    //поч не работает
}
