package org.example.hospital.repositories;

import org.example.hospital.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardsRepository extends JpaRepository<Ward,Integer> {
    List<Ward> findAll();
    Ward findById(int id);

    void deleteById(int id);
}
