package group4.feedapp.RESTproto.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fauser_id")
	@JsonIgnore
	private FAUser voter;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
	@JsonIgnore
	private Poll votePoll;
	
	private boolean answer;
	
	public Vote() {}

	public Vote(FAUser voter, Poll votePoll, boolean answer) {
		this.voter = voter;
		this.votePoll = votePoll;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public FAUser getVoter() {
		return voter;
	}

	public void setVoter(FAUser voter) {
		this.voter = voter;
	}

	public Poll getVotePoll() {
		return votePoll;
	}

	public void setVotePoll(Poll votePoll) {
		this.votePoll = votePoll;
	}

	public boolean getAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", voter=" + voter + ", votePoll=" + votePoll + ", answer=" + answer + "]";
	}
	
}
