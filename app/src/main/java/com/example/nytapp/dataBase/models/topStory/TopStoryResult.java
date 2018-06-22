package com.example.nytapp.dataBase.models.topStory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStoryResult {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
