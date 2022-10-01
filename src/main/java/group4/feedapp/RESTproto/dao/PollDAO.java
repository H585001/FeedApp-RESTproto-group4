package group4.feedapp.RESTproto.dao;

import java.time.LocalDateTime;
import java.util.Collection;

import group4.feedapp.RESTproto.model.FAUser;
import group4.feedapp.RESTproto.model.Poll;

public interface PollDAO {
	Poll createPoll(String question, int noCount, int yesCount, LocalDateTime startTime, LocalDateTime endTime,
			boolean isPublic, int status, String accessCode, FAUser creator);
	Poll createPoll(Poll newPoll);
	Poll readPoll(Long id);
	Collection<Poll> readPolls();
	Poll updatePoll(Long id, Poll updatedPoll);
	Poll deletePoll(Long id);
}
