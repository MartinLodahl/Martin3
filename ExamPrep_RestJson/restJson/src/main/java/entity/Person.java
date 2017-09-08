/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author MartinLodahl
 */
public class Person {

    String fname;
    String lname;
    int id;
    int age;

    public Person(String fName, String lName, int id, int age) {
        this.fname = fName;
        this.lname = lName;
        this.id = id;
        this.age = age;
    }

}
