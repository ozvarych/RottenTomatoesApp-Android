package pivotal.my_rotten_tomatoes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class MovieRequestAsyncTask extends AsyncTask<String, Integer, String> {

    private String _requestURL;
    private ProgressDialog _progressDialog;

    private MovieAdapter _movieAdapter;
    private LayoutInflater _layoutInflater;
    private List<Movie> _movies;
    private MovieResponse _movieResponse;
    private Context _context;

    private ListView _movieListView;


    public MovieRequestAsyncTask(LayoutInflater layoutInflater, ListView listView, Context context){
        _layoutInflater = layoutInflater;
        _movieListView = listView;
        _context = context;
    }

    @Override
    protected void onPreExecute(){
        // add a loading buffer to the screen while getting data
        //showProgressDialog()
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        Log.d("This is uri", uri[0]);
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();

            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    /*@Override
    protected void onProcessUpdate(final String result) {
        super.onPostExecute(result);
        //Do anything with response..
    }*/

    @Override
    protected void onPostExecute(final String result) {
        // Remove loading icon

        //ProgressDialog _progressDialog = new ProgressDialog(getActivity());
        //dismissProgressDialog();

        //super.onPostExecute(result);
        //Do anything with response..

        Gson gson = new Gson();
        _movieResponse = gson.fromJson(result.toString(), MovieResponse.class);
        Log.d("This is my response: ", result);
        setUpMovieAdapter();
        //_movies = _movieResponse.getMovies();
        //Log.d("tag", result);
    }

    private void showProgressDialog()
    {
        _progressDialog.setTitle("Loading...");
        _progressDialog.setMessage("Retrieving movies");
        _progressDialog.show();
}

    private void dismissProgressDialog()
    {
        _progressDialog.dismiss();
    }

    /*private boolean isShowingProgressDialog(){
        return _progressDialog.isShowing();
    }*/

    private void setUpMovieAdapter(){
        _movieAdapter = new MovieAdapter(_layoutInflater, _movieResponse.getMovies(), _context);
        _movieListView.setAdapter(_movieAdapter);
        _movieAdapter.notifyDataSetChanged();
    }
}
