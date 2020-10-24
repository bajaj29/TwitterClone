package com.twitter.twitterclone.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(schema= "twitter", name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "handle",  unique = true)
	@NotNull
	private String handle;
	
	@Column(name = "email", unique = true)
	@NotNull
	private String email;
	
	@Column(name = "password")
	@NotNull
	private String password;
	
	@OneToMany(mappedBy = "tweetUser")
    private List<Tweet> tweets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( schema = "twitter",
    			name = "user_following",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;
	
	@ManyToMany(mappedBy = "likedBy")
	private Set<Tweet> tweetLiked = new HashSet<>();
	
	@ManyToMany(mappedBy = "dislikedBy")
	private Set<Tweet> tweetDisLiked = new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String handle, String email, String password) {
		super();
		this.handle = handle;
		this.email = email;
		this.password = password;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
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

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public Set<Tweet> getTweetLiked() {
		return tweetLiked;
	}

	public void setTweetLiked(Set<Tweet> tweetLiked) {
		this.tweetLiked = tweetLiked;
	}

	public Set<Tweet> getTweetDisLiked() {
		return tweetDisLiked;
	}

	public void setTweetDisLiked(Set<Tweet> tweetDisLiked) {
		this.tweetDisLiked = tweetDisLiked;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", handle=" + handle + ", email=" + email + ", password=" + password + ", tweets="
				+ tweets + ", following=" + following + ", tweetLiked=" + tweetLiked + ", tweetDisLiked="
				+ tweetDisLiked + "]";
	}

}
