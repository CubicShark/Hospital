package org.example.hospital.services;


import org.example.hospital.models.Ward;
import org.example.hospital.repositories.WardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WardsService {

    private final WardsRepository wardsRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WardsService(WardsRepository wardsRepository, JdbcTemplate jdbcTemplate) {
        this.wardsRepository = wardsRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    //@Lock(LockModeType.PESSIMISTIC_READ)
    public List<Ward> findAll(){
       return wardsRepository.findAll();
    }

    public Ward findById(int id){
        return wardsRepository.findById(id);
    }

    @Transactional
    public void save(Ward ward){
        wardsRepository.save(ward);
    }

    @Transactional
    public void deleteById(int id){
        wardsRepository.deleteById(id);
    }

    //@Lock(LockModeType.PESSIMISTIC_READ)
    @Transactional
    public void updateIsFull(boolean isFull,int id){
        String sql = "UPDATE Ward SET is_Full = ? where id = ? ";
        jdbcTemplate.update(sql,isFull,id);
    }
}
