package com.sda.study.springbootpractice;

import com.sda.study.springbootpractice.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Parameterized tests for User Validator
 *
 * @author Tiina Raudla
 * @Date
 */
public class UserValidatorParameterizedTest {
    @ParameterizedTest
    @ValueSource(strings = {"Tiina12345", "akdjbaksbd", "2389745ajlsdbhvuhoadvc23087465"})
    public void givenUserPassword_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @MethodSource("getPasswords")
    public void givenUserPasswords_whenEncodedPasswordCalled_shouldReturnEncodedPasswords(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ArgumentsSource(Parameters.class)
    public void givenUserPasswordsFromProvider_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvSource({"Tiina123456, Tiina#sda_java#123456", "Raudla123456, Raudla1#sda_java#23456"})
    public void givenPasswordsFromCsv_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataSource.csv")
    public void givenPasswordsFromCsvFile_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tiina12345", "akdjbaksbd", "2389745ajlsdbhvuhoadvc23087465"})
    public void givenUserPasswordConverted_whenEncodedPasswordCalled_shouldReturnEncodedPassword(@ConvertWith(PasswordConverter.class) String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }
    //For method source
    static Stream<Arguments> getPasswords() {
        return Stream.of(Arguments.of("Vioiasubvd334"), Arguments.of("liudbawod324875"), Arguments.of("akhdfb3867574234"));
    }

    //For argument source
    static class Parameters implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(Arguments.of("Vioiasubvd334"), Arguments.of("liudbawod324875"), Arguments.of("akhdfb3867574234"));
        }
    }

    //For argument converter
    static class PasswordConverter implements ArgumentConverter {
        @Override
        public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
            if(o instanceof String) {
                return ((String) o).replaceAll("12345" , "67890");
            }

            fail("Cannot replace string!");
            return null;
        }
    }
}