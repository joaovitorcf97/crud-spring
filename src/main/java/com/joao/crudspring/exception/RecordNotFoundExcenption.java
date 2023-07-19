package com.joao.crudspring.exception;

public class RecordNotFoundExcenption extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public RecordNotFoundExcenption(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}
