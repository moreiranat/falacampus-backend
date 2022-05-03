package br.edu.ifpb.dac.falacampus.model.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.mysql.cj.xdevapi.Schema.Validation;

class UserTest {
	/*
	 * @Test void test() { l("Not yet implemented"); }
	 */
	private Set<ConstraintViolation<User>> violations;

	@Autowired
	private static Validator validator;
	private User user;

	// teste de email sem caracter, no caso email invalido

	@ValueSource(strings = { "", "    ", " \t " })
	void testEmailWithoutCharacters(String email) {
		user.setEmail(email);
		violations = validator.validateProperty(user, "email");
		assertNotEquals(0, violations.size(), () -> "Valid email");

		ConstraintViolation<User> constraintViolation = violations.iterator().next();
		assertTrue(constraintViolation.getPropertyPath().toString().contains("email"));
		assertThat(constraintViolation.getMessageTemplate(), containsString("NotEmpty"));
	}
	
	//test se a senha e valida 
	@ValueSource(strings = { "eeeee", "efrrrgtrstuvwxyzABCD", "d43b*C3**" })
	void testPasswordValid(String password) {
		user.setPassword(password);

		violations = validator.validateProperty(user, "password");
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
