package group4.feedapp.RESTproto.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Poll {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String question;
	private int noCount;
	private int yesCount;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean isPublic;
	private int status;
	private String accessCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
	@JsonIgnore
	private FAUser creator;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "votePoll", orphanRemoval=true)
	@JsonIgnore
	private List<Vote> userVotes;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "iotVotePoll", orphanRemoval=true)
	@JsonIgnore
	private List<IoTVotes> iotVotes;
	
	public Poll() {}
	
	public Poll(String question, int noCount, int yesCount, LocalDateTime startTime, LocalDateTime endTime,
			boolean isPublic, int status, String accessCode, FAUser creator) {
		this.question = question;
		this.noCount = noCount;
		this.yesCount = yesCount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isPublic = isPublic;
		this.status = status;
		this.accessCode = accessCode;
		this.creator = creator;
		this.userVotes = new ArrayList<Vote>();;
		this.iotVotes = new ArrayList<IoTVotes>();;
	}

	public Poll(String question, int noCount, int yesCount, LocalDateTime startTime, LocalDateTime endTime,
			boolean isPublic, int status, String accessCode, FAUser creator, List<Vote> userVotes,
			List<IoTVotes> iotVotes) {
		this.question = question;
		this.noCount = noCount;
		this.yesCount = yesCount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isPublic = isPublic;
		this.status = status;
		this.accessCode = accessCode;
		this.creator = creator;
		this.userVotes = userVotes;
		this.iotVotes = iotVotes;
	}

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public FAUser getCreator() {
		return creator;
	}

	public void setCreator(FAUser creator) {
		this.creator = creator;
	}

	public List<Vote> getUserVotes() {
		return userVotes;
	}

	public void setUserVotes(List<Vote> userVotes) {
		this.userVotes = userVotes;
	}

	public List<IoTVotes> getIotVotes() {
		return iotVotes;
	}

	public void setIotVotes(List<IoTVotes> iotVotes) {
		this.iotVotes = iotVotes;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", question=" + question + ", noCount=" + noCount + ", yesCount=" + yesCount
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", isPublic=" + isPublic + ", status=" + status
				+ ", accessCode=" + accessCode + ", creator=" + creator + ", userVotes=" + userVotes + ", iotVotes="
				+ iotVotes + "]";
	}

}
