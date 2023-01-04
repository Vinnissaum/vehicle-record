package br.com.tinnova.vehiclesrecords.exceptions;

public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String msg) {
        super(msg);
    }
}

