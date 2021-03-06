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
public class QuoteNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public QuoteNotFoundException() {
        super();
    }

    public QuoteNotFoundException(String msg) {
        super(msg);
    }

    public QuoteNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
