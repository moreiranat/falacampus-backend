package br.edu.ifpb.dac.falacampus.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.falacampus.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//@Query("SELECT e FROM user e WHERE e.name LIKE :name ORDER BY e.id")
    public List<User> findByName(@Param("name") String name, Pageable pageable);
    
    public User findByName(String name);
	public User findByRegistration(Long registration);
	Optional<User> findByEmail(String email);
}
