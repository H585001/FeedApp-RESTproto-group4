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
import group4.feedapp.RESTproto.model.Poll;
import group4.feedapp.RESTproto.service.FAUserService;
import group4.feedapp.RESTproto.service.PollService;

@RestController
public class FAUserController {
	private final FAUserService userService;
	private final PollService pollService;

	public FAUserController(FAUserService userService, PollService pollService) {
		this.userService = userService;
		this.pollService = pollService;
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
    
    @GetMapping("/users/{id}/polls")
    public Collection<Poll> getUserPolls(@PathVariable Long id) {
    	return pollService.getUserPolls(id);
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
        }else {
        	user.getVotes().stream().forEach(v -> pollService.deleteVote(v));
        }
        
        return user;
    }	
    
    @PostMapping("/users/{id}/polls")
    public Poll addPoll(@RequestBody Poll newPoll, @PathVariable Long id) {
    	FAUser creator = userService.getUser(id);
    	
    	 if (creator == null) {
             System.out.println(String.format("User with the id  \"%s\" not found!", id));
             return null;
         }
    	
        return pollService.addPoll(newPoll.getQuestion(), newPoll.getNoCount(), newPoll.getYesCount(), 
        		newPoll.getStartTime(), newPoll.getEndTime(),newPoll.isPublic(), newPoll.getStatus(), 
        		newPoll.getAccessCode(), creator);
    }
	
}
