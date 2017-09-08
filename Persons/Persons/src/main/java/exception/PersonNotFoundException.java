package exception;

import java.io.Serializable;
import javax.xml.ws.soap.AddressingFeature.Responses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author MartinLodahl
 */
public class PersonNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public PersonNotFoundException() {
        super();
    }

    public PersonNotFoundException(String msg) {
        super(msg);
    }

    public PersonNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
