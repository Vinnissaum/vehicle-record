package br.com.tinnova.vehiclesrecords.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
enum ErrorType {
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    ENTITY_IN_USE("/entity-in-use", "Integrity violation: Entity in use!");

    private final String title;
    private final String uri;

    ErrorType(String path, String title) {
        this.title = title;
        this.uri = "https://tinnova-vehicles" + path;
    }

}
