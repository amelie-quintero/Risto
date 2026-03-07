package com.example.Risto.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.example.Risto.entities.Admin;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class AdminRepositoryTests {

	@Autowired
	private AdminRepository adminStore;
	
	private Admin admin1;
	
	@Test
	@DisplayName(value = "Test that data is persisted to the repository correctly and the id field is correctly generated")
	void testSave() {
		admin1 = adminStore.save(Admin.builder().active(true).email("admin@example.com").password("pw").username("admin").build());
		assertNotEquals(0, admin1.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the findByEmail method is working correctly")
	void testUsername() {
		adminStore.saveAll(List.of(
				Admin.builder().active(true).email("admin1@example.com").password("pw").username("admin").build(),
				Admin.builder().active(true).email("admin2@example.com").password("pw").username("admin").build()
		));
		admin1 = adminStore.findByEmail("admin1@example.com").orElse(null);
		assertNotNull(admin1);
		assertEquals("admin1@example.com", admin1.getEmail());
	}
	
	@Test
	@DisplayName(value = "Test that the findbyUsername method is working correctly")
	void testFindByUsername() {
		adminStore.saveAll(List.of(
				Admin.builder().active(true).email("admin1@example.com").password("pw").username("admin1").build(),
				Admin.builder().active(true).email("admin2@example.com").password("pw").username("admin2").build()
		));
		admin1 = adminStore.findByUsername("admin1").orElse(null);
		assertNotNull(admin1);
		assertEquals("admin1", admin1.getUsername());
	}
	
	@Test
	@DisplayName(value = "Test that the findByActiveTrue method is working correctly")
	void testFindByActiveTrue() {
		adminStore.saveAll(List.of(
				Admin.builder().active(true).email("admin@example.com").password("pw").username("admin4").build(),
				Admin.builder().active(true).email("admin@example.com").password("pw").username("admin5").build(),
				Admin.builder().active(false).email("admin@example.com").password("pw").username("admin6").build()
		));
		List<Admin> activeAdmins = adminStore.findByActiveTrue();
		assertEquals(2, activeAdmins.size());
		
		Admin active1 = activeAdmins.stream().filter(a -> a.getUsername() == "admin4").findAny().orElse(null);
		Admin active2 = activeAdmins.stream().filter(a -> a.getUsername() == "admin5").findAny().orElse(null);
		
		assertNotNull(active1);
		assertNotNull(active2);
	}
}
