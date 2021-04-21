package com.example.movieapp;

public class popularmoviedata {
    String title,date,lang,poster,overview;
    Integer count,rate,id;
    Boolean adult;

    public popularmoviedata(String title, String date, String lang, String poster, String overview, Integer count, Integer rate, Boolean adult, Integer id) {
        this.title = title;
        this.date = date;
        this.lang = lang;
        this.poster = poster;
        this.overview = overview;
        this.count = count;
        this.rate = rate;
        this.adult = adult;
        this.id=id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
}
