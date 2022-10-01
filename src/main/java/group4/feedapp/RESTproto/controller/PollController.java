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
public class PollController {
	private final PollService pollService;
	private final FAUserService userService;

	public PollController(PollService pollService, FAUserService userService) {
		this.pollService = pollService;
		this.userService = userService;
	}
	
	@GetMapping("/polls")
    public Collection<Poll> getPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/polls/{id}")
    public Poll getPoll(@PathVariable Long id) {

        Poll poll = pollService.getPoll(id);

        if (poll == null) {
            System.out.println(String.format("Poll with the id  \"%s\" not found!", id));
            // TODO Exception
        }

        return poll;
    }

    @PutMapping("/polls/{id}")
    public Poll updatePoll(@RequestBody Poll updatedPoll, @PathVariable Long id) {

        Poll poll = pollService.updatePoll(id, updatedPoll);

        if (poll == null) {
            System.out.println(String.format("Poll with the id  \"%s\" not found!", id));
            // TODO Exception
        }

        return poll;
    }

    @PostMapping("/polls")
    public Poll addPoll(@RequestBody Poll newPoll, Long creatorId) {
    	FAUser creator = userService.getUser(creatorId);
        return pollService.addPoll(newPoll.getQuestion(), newPoll.getNoCount(), newPoll.getYesCount(), 
        		newPoll.getStartTime(), newPoll.getEndTime(),newPoll.isPublic(), newPoll.getStatus(), 
        		newPoll.getAccessCode(), creator);
    }

    @DeleteMapping("/poll/{id}")
    public Poll deletePoll(@PathVariable Long id) {

        Poll poll = pollService.deletePoll(id);

        if (poll == null) {
            System.out.println(String.format("Poll with the id  \"%s\" not found!", id));
            // TODO Exception
        }

        return poll;
    }	
	
}
