package pivotal.my_rotten_tomatoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Oksana on 2014-08-31.
 */
public class MovieAdapter extends BaseAdapter{

    private LayoutInflater _layoutInflater;
    private List<Movie> _movies;
    private Context _context;

    public MovieAdapter(LayoutInflater layoutInflater, List<Movie> movies, Context context){
        _layoutInflater = layoutInflater;
        _movies = movies;
        _context = context;
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
    public Movie getItem(int i){
        return _movies.get(i);
    }

    private void preloadImages(List<Movie> movies){
        int size = movies.size();
        for(int i = 0; i < size; i++)
        {
            Movie movie = getItem(i);
            Picasso.with(_context).load(movie.getPosters().getProfile()).resize(150, 200).centerCrop().fetch();
        }
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
            movieHolder.movieImage = (ImageView)view.findViewById(R.id.movie_image);
            view.setTag(movieHolder);
        } else {
            movieHolder = (CustomMovieHolder) view.getTag();
        }

        Movie movie = _movies.get(i);
        movieHolder.movieTitle.setText(movie.getTitle());
        movieHolder.movieYear.setText(movie.getYear());
        movieHolder.movieRating.setText("Rated: " + movie.getMpaa_rating());
        movieHolder.movieRuntime.setText(movie.getRuntime() + " min");
        Picasso.with(_context).load(movie.getPosters().getProfile()).resize(150, 200).centerCrop().into(movieHolder.movieImage );
        //movieHolder.movieImage(movie.getPosters());

        return view;
    }

    private class CustomMovieHolder {
        TextView movieTitle;
        TextView movieYear;
        TextView movieRating;
        TextView movieRuntime;
        ImageView movieImage;
    }

}
