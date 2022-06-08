package org.serratec.enums;

import org.serratec.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EStatus {

    NÃO_FINALIZADO((0),("Não Finalizado")),
    FINALIZADO((1),("Finalizado"));

    private Integer codigo;
    private String status;

    private EStatus(Integer codigo, String status) {
        this.codigo = codigo;
        this.status = status;
    }

    private EStatus(){
        
    }

    @JsonCreator
    public static EStatus verifica(Integer value)throws EnumValidationException{
        for (EStatus s : values()) {
            if(value.equals(s.getCodigo())){
                return s;
            }     
        }
        throw new EnumValidationException("Status preenchido incorretamente");
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
