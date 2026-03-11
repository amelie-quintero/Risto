package com.example.Risto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Risto.entities.Admin;
import com.example.Risto.repositories.AdminRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTests {
	
	@Mock
	private AdminRepository adminStore;
	
	@InjectMocks
	private AdminService adminService;

	@Test
	@DisplayName(value = "Test that records are returned from getAllAdmins method")
	void testGetAllAdmins() {
		Admin admin = Admin.builder().active(true).email("admin1@example.com").password("pw").username("admin1").build();
		doReturn(List.of(admin)).when(adminStore).findAll();
		
		List<Admin> admins = adminService.getAllAdmins();
		assertEquals(1, admins.size());
		assertEquals(admin, admins.get(0));
	}
	
	@Test
	@DisplayName(value = "Test that records are returned from getAllActiveAdmins method")
	void testGetAllActiveAdmins() {
		Admin admin = Admin.builder().active(true).email("admin1@example.com").password("pw").username("admin1").build();
		doReturn(List.of(admin)).when(adminStore).findByActiveTrue();
		
		List<Admin> admins = adminService.getAllActiveAdmins();
		assertEquals(1, admins.size());
		assertEquals(admin, admins.get(0));
	}
	
	@Test
	@DisplayName(value = "Test expected behavior of the getAdminFromUsername method")
	void testGetAdminFromUsername() {
		Admin admin = Admin.builder().active(true).email("admin1@example.com").password("pw").username("admin1").build();
		
//		First return a non-null value, then return a null value from the admin store
		doReturn(Optional.of(admin)).doReturn(Optional.empty()).when(adminStore).findByUsername(anyString());
		
		Admin admin1 = adminService.getAdminFromUsername("test1");
		Admin admin2 = adminService.getAdminFromUsername("test2");
		
		assertEquals(admin, admin1);
		assertNull(admin2);
	}
	
	@Test
	@DisplayName(value = "Test expected behavior of the adminLogin method")
	void testAdminLogin() {
		List<Admin> adminList = List.of(Admin.builder().build());
		List<Admin> adminEmpty = List.of();
		
		doReturn(adminList).when(adminStore).findAdminLogin(eq("test1"), anyString());
		doReturn(adminEmpty).when(adminStore).findAdminLogin(eq("test2"), anyString());
		
		assertTrue(adminService.adminLogin("test1", "any"));
		assertFalse(adminService.adminLogin("test2", "any"));
	}
}
