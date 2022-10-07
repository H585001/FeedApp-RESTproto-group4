package group4.feedapp.RESTproto.dao;

import java.util.Collection;

import group4.feedapp.RESTproto.model.FAUser;
import group4.feedapp.RESTproto.model.Poll;
import group4.feedapp.RESTproto.model.Vote;

public interface VoteDAO {
	Vote createVote(FAUser voter, Poll votePoll, boolean answer);
	Vote createVote(Vote vote);
	Vote readVote(Long id);
	Vote findUserVote(Poll poll, FAUser user);
	Collection<Vote> readVotes();
	Vote updateVote(Long id, Vote updatedVote);
	Vote deleteVote(Long id);
}
