package group4.feedapp.RESTproto.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group4.feedapp.RESTproto.model.FAUser;
import group4.feedapp.RESTproto.service.FAUserService;

@RestController
public class FAUserController {
	private final FAUserService userService;

	public FAUserController(FAUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
    public Collection<FAUser> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public FAUser getUser(@PathVariable Long id) {

        FAUser user = userService.getUser(id);

        if (user == null) {
            System.out.println(String.format("User with the id  \"%s\" not found!", id));
            // TODO Exception
        }

        return user;
    }

    @PutMapping("/users/{id}")
    public FAUser updateUser(@RequestBody FAUser updatedUser, @PathVariable Long id) {

        FAUser user = userService.updateUser(id, updatedUser);

        if (user == null) {
            System.out.println(String.format("User with the id  \"%s\" not found!", id));
            // TODO Exception
        }

        return user;
    }

    @PostMapping("/users")
    public FAUser addUser(@RequestBody FAUser newUser) {
        return userService.addUser(newUser.getEmail(),newUser.getPassword(),newUser.getName(), newUser.isAdmin());
    }

    @DeleteMapping("/users/{id}")
    public FAUser deleteUser(@PathVariable Long id) {

        FAUser user = userService.deleteUser(id);

        if (user == null) {
            System.out.println(String.format("User with the id  \"%s\" not found!", id));
            // TODO Exception
        }

        return user;
    }	
	
}
