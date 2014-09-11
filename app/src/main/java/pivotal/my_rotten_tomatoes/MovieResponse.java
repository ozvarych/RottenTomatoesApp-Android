package pivotal.my_rotten_tomatoes;

import com.google.gson.annotations.Expose;


import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    @Expose
    private String total;
    @Expose
    private List<Movie> movies = new ArrayList<Movie>();

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Movie> getMovies(){

        return movies;
    }

    public void setMovies(List<Movie> movies){

        this.movies= movies;
    }

}
