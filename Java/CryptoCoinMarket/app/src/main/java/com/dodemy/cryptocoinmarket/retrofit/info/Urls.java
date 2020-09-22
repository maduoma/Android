package com.dodemy.cryptocoinmarket.retrofit.info;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls implements Serializable
{

    @SerializedName("website")
    @Expose
    private List<String> website = null;
    @SerializedName("explorer")
    @Expose
    private List<String> explorer = null;
    @SerializedName("source_code")
    @Expose
    private List<String> sourceCode = null;
    @SerializedName("message_board")
    @Expose
    private List<String> messageBoard = null;
    @SerializedName("chat")
    @Expose
    private List<Object> chat = null;
    @SerializedName("announcement")
    @Expose
    private List<Object> announcement = null;
    @SerializedName("reddit")
    @Expose
    private List<String> reddit = null;
    @SerializedName("twitter")
    @Expose
    private List<String> twitter = null;
    private final static long serialVersionUID = 6443875637389629883L;

    public List<String> getWebsite() {
        return website;
    }

    public void setWebsite(List<String> website) {
        this.website = website;
    }

    public List<String> getExplorer() {
        return explorer;
    }

    public void setExplorer(List<String> explorer) {
        this.explorer = explorer;
    }

    public List<String> getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(List<String> sourceCode) {
        this.sourceCode = sourceCode;
    }

    public List<String> getMessageBoard() {
        return messageBoard;
    }

    public void setMessageBoard(List<String> messageBoard) {
        this.messageBoard = messageBoard;
    }

    public List<Object> getChat() {
        return chat;
    }

    public void setChat(List<Object> chat) {
        this.chat = chat;
    }

    public List<Object> getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(List<Object> announcement) {
        this.announcement = announcement;
    }

    public List<String> getReddit() {
        return reddit;
    }

    public void setReddit(List<String> reddit) {
        this.reddit = reddit;
    }

    public List<String> getTwitter() {
        return twitter;
    }

    public void setTwitter(List<String> twitter) {
        this.twitter = twitter;
    }

}
