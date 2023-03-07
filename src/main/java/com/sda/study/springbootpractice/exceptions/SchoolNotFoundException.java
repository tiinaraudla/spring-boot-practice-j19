package com.sda.study.springbootpractice.exceptions;

/**
 * @author Tiina Raudla
 * @Date
 */
public class SchoolNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SchoolNotFoundException(Long id) {
        super(String.format("School not found for id: %d", id));
    }
    public SchoolNotFoundException (String name) {
        super(String.format("School not found for name: %s", name));
    }
}
