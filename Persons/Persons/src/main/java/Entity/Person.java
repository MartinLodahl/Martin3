/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MartinLodahl
 */
@Entity
@Table(name = "Person")
@NamedQueries(
        {
            @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
            ,
  /*  @NamedQuery(name = "Person.findById", query = "SELECT s FROM Person s WHERE s.id = :id")
            ,
    @NamedQuery(name = "Person.findByFirstname", query = "SELECT s FROM Person s WHERE s.firstname = :firstname")
            ,
    @NamedQuery(name = "Person.findByLastname", query = "SELECT s FROM Person s WHERE s.lastname = :lastname")
            ,
    @NamedQuery(name = "Person.findAl", query = "SELECT s FROM Person s")
            ,
    @NamedQuery(name = "Person.AmountOfStudentsInSemester", query = "SELECT count(stu) FROM Semester se JOIN se.studentList stu WHERE se.name= :name")
            ,
    @NamedQuery(name = "Student.AmountOfStudentsAllSemester", query = "SELECT count(stu) FROM Semester se JOIN se.studentList stu")
        */})
public class Person implements Serializable {

    private String fName;
    private String lName;
    private String phone;

    public Person(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public Person(){
        
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Person[ id=" + id + " ]";
    }

}
