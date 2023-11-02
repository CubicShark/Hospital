package org.example.hospital.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "Ward")
public class Ward {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 0, message = "Capacity should be greater than 0")
    @Max(value = 6,message = "Capacity shouldn't be greater than 6")
    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_Full")
    private boolean isFull;

    @OneToMany(mappedBy = "ward")
    private List<Person> people;

    public Ward() {
    }

    public Ward(int capacity, boolean isFull) {
        this.capacity = capacity;
        this.isFull = isFull;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", isFull=" + isFull +
                ", people=" + people +
                '}';
    }
}
