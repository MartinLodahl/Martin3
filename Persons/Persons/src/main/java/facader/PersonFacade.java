package facader;

import Entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MartinLodahl
 */
public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p;
        try {
            em.getTransaction().begin();
            p = em.find(Person.class, (long)id);
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p;
        try {
            em.getTransaction().begin();
            p = em.find(Person.class, (long)id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public List<Person> getPersons() {
        List<Person> returnList = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Person p;
        
        try {
            em.getTransaction().begin();
            returnList = em.createNamedQuery("Person.findAll").getResultList();
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return returnList;
        //Bad option below 
        /*   int i = 0;
        List<Person> returnList = new ArrayList();
        while (true) {
            Person p = getPerson(i);
            if (p != null) {
                returnList.add(p);
            } else {
                break;
            }
            i++;
        }
        return returnList;*/
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            p = em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

}
