package br.edu.ifpb.dac.falacampus.model.entity;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import com.mysql.cj.xdevapi.Schema.Validation;

import antlr.collections.Stack;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@Testable
@DisplayName("User")
@TestClassOrder(org.junit.jupiter.api.ClassOrderer.OrderAnnotation.class)
class UserTest {
//testes
	@Autowired
	private static Validator validator;
	private User user;
	 
	// teste de email sem caracter, no caso email invalido

	@ValueSource(strings = { "", "    ", " \t " })
	void testEmailWithoutCharacters(String email) {
		user.setEmail(email);

		Set<ConstraintViolation<User>> violations = validator.validateProperty(user, "email");
		assertNotEquals(0, violations.size(), () -> "Valid email");

		ConstraintViolation<User> constraintViolation = violations.iterator().next();
		assertTrue(constraintViolation.getPropertyPath().toString().contains("email"));
		assertThat(constraintViolation.getMessageTemplate(), containsString("NotEmpty"));
	}

	// test se a senha e valida
	@ValueSource(strings = { "eeeee", "efrrrgtrstuvwxyzABCD", "d43b*C3**" })
	void testPasswordValid(String password) {
		user.setPassword(password);

		Set<ConstraintViolation<User>> violations = validator.validateProperty(user, "password");
		assertEquals(0, violations.size(), () -> "Invalid password");
	}

	@Test
	@DisplayName("╯°□°）╯")
	void testNameCharacters() {
	}

	@Test
	@DisplayName("😱")
	void testNameContainingEmoji() {
	}
	

}
