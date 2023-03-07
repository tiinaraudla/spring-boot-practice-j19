package com.sda.study.springbootpractice.exceptions;

/**
 * @author Tiina Raudla
 * @Date
 */
public class StudentNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException(Long id) {

        super(String.format("Student not found for id: %d", id));
    }
    public StudentNotFoundException (String name) {

        super(String.format("Student not found for name: %s", name));
    }
}
