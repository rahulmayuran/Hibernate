package com.hibernate.Exception;

import java.io.Serializable;

public class StudentException extends Exception implements Serializable {

    public StudentException(){super();}

    public StudentException(String message) {
        super(message);
    }

}
