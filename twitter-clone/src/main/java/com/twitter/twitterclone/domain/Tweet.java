package com.twitter.twitterclone.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.NotNull;

@Entity
@Table(schema= "twitter", name = "tweet")
public class Tweet {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tweetId;

    @CreationTimestamp
    private Timestamp postTime;

    @ManyToOne
    private User tweetUser;

    @NotNull
    private String content;
    
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
	@Column(name = "liked_tweets")
	@JoinTable(
	  schema = "twitter",
	  name = "tweets_liked", 
	  joinColumns = @JoinColumn(name = "tweetId"), 
	  inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Tweet> likedBy = new HashSet<>();
    
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
	@Column(name = "disliked_tweets")
	@JoinTable(
	  schema = "twitter",
	  name = "tweets_disliked", 
	  joinColumns = @JoinColumn(name = "tweetId"), 
	  inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Tweet> dislikedBy = new HashSet<>();
	
    
    public User getTweetUser() {
        return tweetUser;
    }

    public void setTweetUser(User tweetUser) {
        this.tweetUser = tweetUser;
    }

    public Tweet() {
    }

    public Tweet(String content , User tweetUser) {
        this.content = content;
        this.tweetUser=tweetUser;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
