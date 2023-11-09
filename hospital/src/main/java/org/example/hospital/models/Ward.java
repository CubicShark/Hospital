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

    @NotEmpty
    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @OneToMany(mappedBy = "ward")
    private List<Person> people;

    public Ward() {
    }

    public Ward(int capacity, String gender, String age) {
        this.capacity = capacity;
        this.gender = gender;
        this.age = age;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
