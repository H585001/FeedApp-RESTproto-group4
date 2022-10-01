package group4.feedapp.RESTproto.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IoTVotes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private int noCount;
	private int yesCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iotDevice_id")
	private IoTDevice device;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
	private Poll iotVotePoll;

	public IoTVotes() {}
	
	public IoTVotes(int noCount, int yesCount, IoTDevice device, Poll iotVotePoll) {
		this.noCount = noCount;
		this.yesCount = yesCount;
		this.device = device;
		this.iotVotePoll = iotVotePoll;
	}

	public Long getId() {
		return id;
	}

	public int getNoCount() {
		return noCount;
	}

	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}

	public int getYesCount() {
		return yesCount;
	}

	public void setYesCount(int yesCount) {
		this.yesCount = yesCount;
	}

	public IoTDevice getDevice() {
		return device;
	}

	public void setDevice(IoTDevice device) {
		this.device = device;
	}

	public Poll getIotVotePoll() {
		return iotVotePoll;
	}

	public void setIotVotePoll(Poll iotVotePoll) {
		this.iotVotePoll = iotVotePoll;
	}

}
