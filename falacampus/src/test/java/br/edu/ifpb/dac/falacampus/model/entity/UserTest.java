package br.edu.ifpb.dac.falacampus.model.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
class UserTest {
	
	
	@Test
	@DisplayName("╯°□°）╯")
	void testNameCharacters() {
	}

	@Test
	@DisplayName("😱")
	void testNameContainingEmoji() {
	}

}
