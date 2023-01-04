package br.com.tinnova.vehiclesrecords.exceptions.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class StandardApiError {

    private Integer status;
    private String type;
    private String title;
    private String detail;

}
