package group4.feedapp.RESTproto.dao;

import java.util.Collection;

import group4.feedapp.RESTproto.model.IoTDevice;
import group4.feedapp.RESTproto.model.IoTVotes;
import group4.feedapp.RESTproto.model.Poll;

public interface IoTVotesDAO {
	IoTVotes createIoTVotes(IoTDevice device, Poll votePoll, int noCount, int yesCount);
	IoTVotes createIoTVotes(IoTVotes votes);
	IoTVotes readIoTVotes(Long id);
	Collection<IoTVotes> findIoTVotes(Long pollId, Long deviceId);
	Collection<IoTVotes> readIoTVotes();
	IoTVotes updateIoTVotes(Long id, IoTVotes updatedIoTVotes);
	IoTVotes deleteIoTVotes(Long id);
}
