package group4.feedapp.RESTproto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FAUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String email;
	private String password;
    private String name;
    private boolean isAdmin;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="creator", orphanRemoval=true)
    @JsonIgnore
    private List<Poll> createdPolls;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "voter", orphanRemoval=true)
    @JsonIgnore
    private List<Vote> votes;
    
    public FAUser() {}
    
	public FAUser(String email, String password, String name, boolean isAdmin) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.isAdmin = isAdmin;
		this.createdPolls = new ArrayList<Poll>();
		this.votes = new ArrayList<Vote>();
	}
	
	public FAUser(String email, String password, String name, boolean isAdmin, List<Poll> createdPolls,
			List<Vote> votes) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.isAdmin = isAdmin;
		this.createdPolls = createdPolls;
		this.votes = votes;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Poll> getCreatedPolls() {
		return createdPolls;
	}

	public void setCreatedPolls(List<Poll> createdPolls) {
		this.createdPolls = createdPolls;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", isAdmin="
				+ isAdmin + "]";
	}
	
}
