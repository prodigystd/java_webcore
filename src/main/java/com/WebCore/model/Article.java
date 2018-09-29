package com.WebCore.model;

public class Article {
    private String articleHeader;
    private String articleContent;
    private long articleId;
    private int user_id;
    private String authorName;

    public String getAuthorName() { return authorName;}

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getArticleId()
    {
        return articleId;
    }

    public void setArticleId(long articleId)
    {
        this.articleId = articleId;
    }

    public String getArticleHeader() {
        return articleHeader;
    }

    public void setArticleHeader(String articleHeader)
    {
        this.articleHeader = articleHeader;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public String getArticleContentShort(int length) {
        if(articleContent.length()>length)
            return articleContent.substring(0,length)+"...";
        return articleContent;
    }

    public void setArticleContent(String articleContent)
    {
        this.articleContent = articleContent;
    }

}
