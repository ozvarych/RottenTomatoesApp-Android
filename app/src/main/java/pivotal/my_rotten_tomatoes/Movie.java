package pivotal.my_rotten_tomatoes;

import com.google.gson.annotations.Expose;

public class Movie {

    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String year;
    @Expose
    private String mpaa_rating;
    @Expose
    private String runtime;
    @Expose
    private Posters posters;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getMpaa_rating(){
        return mpaa_rating;
    }

    public void setMpaa_rating(String mpaa_rating){
        this.mpaa_rating = mpaa_rating;
    }

    public String getRuntime(){
        return runtime;
    }

    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    public Posters getPosters(){
        return posters;
    }

    public void setPosters(Posters posters){
        this.posters= posters;
    }

}

