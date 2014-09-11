package pivotal.my_rotten_tomatoes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.google.gson.Gson;

public class MovieRequestAsyncTask extends AsyncTask<String, Integer, String> {

    private String _requestURL;
    private ProgressDialog _progressDialog;

    private MovieAdapter _movieAdapter;
    private LayoutInflater _layoutInflater;

    @Override
    protected void onPreExecute(){
        // add a loading buffer to the screen while getting data
        showProgressDialog();
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;

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

        dismissProgressDialog();

        super.onPostExecute(result);
        //Do anything with response..

        Gson gson = new Gson();
        Movie movie = gson.fromJson(result.toString(), Movie.class);
        Log.d("tag", result);

        //Toast toast = Toast.makeText(null, result.toString(), Toast.LENGTH_LONG );
        //toast.show();


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

}
