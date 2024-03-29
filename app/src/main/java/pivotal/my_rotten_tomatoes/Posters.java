package pivotal.my_rotten_tomatoes;

import com.google.gson.annotations.Expose;

/**
 * Created by Oksana on 2014-08-31.
 */
public class Posters {

    @Expose
    public String thumbnail;
    @Expose
    public String profile;
    @Expose
    public String detailed;
    @Expose
    public String original;

    public String getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getProfile(){
        return profile;
    }

    public void setProfile(String profile){
        this.profile= profile;
    }

    public String getDetailed(){
        return detailed;
    }

    public void setDetailed(String detailed){
        this.detailed= detailed;
    }

    public String getOriginal(){
        return original;
    }

    public void setOriginal(String original){
        this.original= original;
    }


}
