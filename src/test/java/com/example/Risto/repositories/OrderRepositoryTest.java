package com.example.Risto.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.example.Risto.constants.OrderStatus;
import com.example.Risto.entities.Order;
import com.example.Risto.entities.User;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class OrderRepositoryTest {
	
	@Autowired
	private OrderRepository orderStore;
	
	@Autowired
	private UserRepository userStore;
	
	private User user1, user2;
	
	@BeforeAll
	void setup() {
		List<User> users = (List<User>) userStore.saveAll(List.of(
				User.builder().email("email1@example.com").username("user1").password("pw1").active(true).build(),
				User.builder().email("email2@example.com").username("user2").password("pw2").active(true).build()
		));
		user1 = users.stream().filter(u -> u.getUsername() == "user1").findFirst().get();
		user2 = users.stream().filter(u -> u.getUsername() == "user2").findFirst().get();
	}
	
	@Test
	@DisplayName(value = "Test that data is correctly persisted to the OrderRepository")
	void testPersist() {
		Order order = orderStore.save(Order.builder().user(user1).date(Date.valueOf("2000-12-31")).status(OrderStatus.PENDING).build());
		int oId = order.getId();
		
		Optional<Order> fetchOrder = orderStore.findById(oId);
		
		assertTrue(fetchOrder.isPresent());
		assertEquals(oId, fetchOrder.get().getId());
	}
	
	@Test
	@DisplayName(value = "Test that the findByUserId method is working")
	void testFindByUserId() {
		int uId = user1.getId();
		List<Order> orders = List.of(
				Order.builder().user(user1).date(Date.valueOf("2000-12-31")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-12-30")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user2).date(Date.valueOf("2000-11-30")).status(OrderStatus.PENDING).build()
		);
		orderStore.saveAll(orders);
		
		List<Order> foundOrders = orderStore.findByUserId(uId);
		
		assertEquals(2, foundOrders.size());
		
		Optional<Order> order1 = foundOrders.stream()
				.filter(order -> order.getDate().compareTo(Date.valueOf("2000-12-31")) == 0)
				.findAny();
		
		Optional <Order> order2 = foundOrders.stream()
				.filter(order -> order.getDate().compareTo(Date.valueOf("2000-12-30")) == 0)
				.findAny();
		assertTrue(order1.isPresent());
		assertTrue(order2.isPresent());
	}
	
	@Test
	@DisplayName(value = "Test that the findByDateBetween method is working")
	void testFindByDateBetween() {
		List<Order> orders = List.of(
				Order.builder().user(user1).date(Date.valueOf("2000-12-31")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-12-29")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-11-30")).status(OrderStatus.PENDING).build()
		);
		orderStore.saveAll(orders);
		
		List<Order> foundNoneBecauseBefore = orderStore.findByDateBetween(Date.valueOf("2000-11-01"), Date.valueOf("2000-11-15"));
		List<Order> foundFirstDate = orderStore.findByDateBetween(Date.valueOf("2000-11-01"), Date.valueOf("2000-12-01"));
		List<Order> foundFirstTwoDates = orderStore.findByDateBetween(Date.valueOf("2000-11-15"), Date.valueOf("2000-12-30"));
		List<Order> foundLastDate = orderStore.findByDateBetween(Date.valueOf("2000-12-30"), Date.valueOf("2001-01-01"));
		
		assertTrue(foundNoneBecauseBefore.isEmpty());
		assertEquals(1, foundFirstDate.size());
		assertEquals(2, foundFirstTwoDates.size());
		assertEquals(1, foundLastDate.size());
		
		assertEquals("2000-11-30", foundFirstDate.get(0).getDate().toString());
		
		assertTrue(foundFirstTwoDates.stream().anyMatch(order -> order.getDate().compareTo(Date.valueOf("2000-11-30")) == 0));
		assertTrue(foundFirstTwoDates.stream().anyMatch(order -> order.getDate().compareTo(Date.valueOf("2000-12-29")) == 0));
		
		assertEquals("2000-12-31", foundLastDate.get(0).getDate().toString());
	}
	
	@Test
	@DisplayName(value = "Test that the findByStatus method is working")
	void testFindByStatus() {
		List<Order> orders = List.of(
				Order.builder().user(user1).date(Date.valueOf("2000-12-31")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-12-31")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-12-30")).status(OrderStatus.INPROGRESS).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-12-30")).status(OrderStatus.INPROGRESS).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-11-30")).status(OrderStatus.PENDING).build(),
				Order.builder().user(user1).date(Date.valueOf("2000-11-30")).status(OrderStatus.COMPLETED).build()
		);
		orderStore.saveAll(orders);
		
		List<Order> pending = orderStore.findByStatus(OrderStatus.PENDING);
		List<Order> inProgress = orderStore.findByStatus(OrderStatus.INPROGRESS);
		List<Order> completed = orderStore.findByStatus(OrderStatus.COMPLETED);
		
		assertEquals(3, pending.size());
		assertEquals(2, inProgress.size());
		assertEquals(1, completed.size());
	}
}
