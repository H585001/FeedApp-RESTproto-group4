package group4.feedapp.RESTproto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IoTDevice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private Poll linkedPoll;
	
	public IoTDevice() {}
	
	public IoTDevice(String name, Poll linkedPoll) {
		this.name = name;
		this.linkedPoll = linkedPoll;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Poll getLinkedPoll() {
		return linkedPoll;
	}

	public void setLinkedPoll(Poll linkedPoll) {
		this.linkedPoll = linkedPoll;
	}

}
