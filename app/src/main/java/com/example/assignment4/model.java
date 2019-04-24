package com.example.assignment4;
import android.provider.BaseColumns;

public final class model {

    String ID;
    String title, description, url, rating;
    public static class DatabaseEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "MovieTrailers";
        public static final String COL_ID = "ID";
        public static final String COL_TITLE = "Title";
        public static final String COL_DESCRIPTION = "Description";
        public static final String COL_URL = "URL";
        public static final String COL_RATING = "Rating";
    }

    public model(){};
    public model(String title, String descr)
    {
        super();
        this.title = title;
        this.description = description;
    }

    public String getId()
    {
        return ID;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescr()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }

    public String getRating()
    {
        return rating;
    }

    public void setId(String id)
    {
        this.ID = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescr(String desc)
    {
        this.description = desc;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }
}
