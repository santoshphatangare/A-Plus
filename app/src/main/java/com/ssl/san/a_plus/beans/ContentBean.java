package com.ssl.san.a_plus.beans;

/**
 * Created by Santosh on 10-Jan-18.
 */

public class ContentBean {
    int contentId;
    int chapterId;
    int contentIndex;
    String contentTitle;
    String content;
    String contentImage;
    String webResult = "";
    String videoResult = "";
    String authorResult= "";

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getContentIndex() {
        return contentIndex;
    }

    public void setContentIndex(int contentIndex) {
        this.contentIndex = contentIndex;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public String getWebResult() {
        return webResult;
    }

    public void setWebResult(String webResult) {
        this.webResult = webResult;
    }

    public String getVideoResult() {
        return videoResult;
    }

    public void setVideoResult(String videoResult) {
        this.videoResult = videoResult;
    }

    public String getAuthorResult() {
        return authorResult;
    }

    public void setAuthorResult(String authorResult) {
        this.authorResult = authorResult;
    }
}
