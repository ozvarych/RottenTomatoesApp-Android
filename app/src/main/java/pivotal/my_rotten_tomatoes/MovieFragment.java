package pivotal.my_rotten_tomatoes;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Oksana on 2014-09-03.
 */
public class MovieFragment extends Fragment {

    public static final String API_KEY = "edegahgsfy672jd84wwjwkbc";
    private static final String DEFAULT_PAGE_LIMIT = "16";
    private static final String DEFAULT_PAGE_REQUEST = "1";
    private static final String DEFAULT_COUNTRY = "ca";

    private String _requestURL;
    private MovieRequestAsyncTask _movieRequestAsyncTask;
    private LayoutInflater _layoutInflater;
    private MovieAdapter _movieAdapter;
    private ListView _listView;
    private ProgressDialog _progressDialog;

    private String _pageLimit;
    private String _pageRequest;
    private String _country;

    public MovieFragment (){
        _pageLimit = DEFAULT_PAGE_LIMIT;
        _pageRequest = DEFAULT_PAGE_REQUEST;
        _country = DEFAULT_COUNTRY;
    }

    public MovieFragment (final String pageLimit, final String pageRequest, final String country){
        _pageLimit = pageLimit;
        _pageRequest = pageRequest;
        _country = country;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        _requestURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?page_limit=" +
                _pageLimit + "&page=" + _pageRequest + "&country=" + _country + " &apikey=" + API_KEY;

        _layoutInflater = inflater;
        View rootView =  _layoutInflater.inflate(R.layout.movie_fragment, container, false);
        _listView = (ListView)rootView.findViewById(R.id.lv_MoviesList);

        //ProgressDialog _progressDialog = new ProgressDialog(getActivity());

        // Get Movie List from Rotten Tomatoes
        new MovieRequestAsyncTask().execute(_requestURL);

        return rootView;
    }

}
