package webdev.services;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/api/user")
	public Iterable<User> findAllUsers(@RequestParam(name="username", required=false) String username, 
									   @RequestParam(name="password", required=false) String password) {
		if (username != null && password != null) {
			return userRepository.findUserByCredentials(username, password);
		} else if (username != null) {
			return userRepository.findUserByUsername(username);
		}
		return userRepository.findAll();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			userRepository.save(user);
			return user;
		}
		return null;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser, HttpSession session) {
		User currentUser = (User)
		session.getAttribute("currentUser");
		if (currentUser != null) {
			Optional<User> data = userRepository.findById(currentUser.getId());
			if(data.isPresent()) {
				User user = data.get();
				user.setUsername(newUser.getUsername());
				user.setPhone(newUser.getPhone());
				user.setEmail(newUser.getEmail());
				user.setRole(newUser.getRole());
				user.setDateOfBirth(newUser.getDateOfBirth());
				userRepository.save(user);
				return user;
			}
		}
		return null;
	}
	
	@DeleteMapping("/api/user/{userId}") 
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		session.setAttribute("currentUser", user);
		userRepository.save(user);
		return user;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)
		session.getAttribute("currentUser");
		if (currentUser != null) {
			Optional<User> data = userRepository.findById(currentUser.getId());
			if (data.isPresent()) {
				return data.get();
			}
		}
		return null;
	}

	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User credentials, HttpSession session) {
		for (User user : userRepository.findUserByCredentials(credentials.getUsername(), credentials.getPassword())) {
			session.setAttribute("currentUser", user);
			return user;
		}
		return null;
	}
}
