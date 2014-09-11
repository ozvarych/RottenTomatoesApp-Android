package pivotal.my_rotten_tomatoes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oksana on 2014-08-31.
 */
public class MovieAdapter extends BaseAdapter{

    private LayoutInflater _layoutInflater;
    private List<Movie> _movies;


    public MovieAdapter(LayoutInflater layoutInflater, List<Movie> movies){
        _layoutInflater = layoutInflater;
        _movies = movies;
    }

    @Override
    public int getCount() {
        return _movies.size();
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public Object getItem(int i){
        return _movies.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //Add information
        CustomMovieHolder  movieHolder;
        if(view == null)
        {
            movieHolder = new CustomMovieHolder ();
            view = _layoutInflater.inflate(R.layout.movie_row, null);
            movieHolder.movieTitle = (TextView)view.findViewById(R.id.movie_title);
            movieHolder.movieYear = (TextView)view.findViewById(R.id.movie_year);
            movieHolder.movieRating = (TextView)view.findViewById(R.id.movie_rating);
            movieHolder.movieRuntime = (TextView)view.findViewById(R.id.movie_runtime);
            //movieHolder.movieImage = (Gallery)view.findViewById(R.id.movie_image);
            view.setTag(movieHolder);
        } else {
            movieHolder = (CustomMovieHolder) view.getTag();
        }

        Movie movie = _movies.get(i);
        movieHolder.movieTitle.setText(movie.getTitle());
        movieHolder.movieYear.setText(movie.getYear());
        movieHolder.movieRating.setText(movie.getMpaa_rating());
        movieHolder.movieTitle.setText(movie.getTitle());
        movieHolder.movieTitle.setText(movie.getTitle());

        return view;
    }

    private class CustomMovieHolder {
        TextView movieTitle;
        TextView movieYear;
        TextView movieRating;
        TextView movieRuntime;
        //Gallery movieImage;
    }

}
