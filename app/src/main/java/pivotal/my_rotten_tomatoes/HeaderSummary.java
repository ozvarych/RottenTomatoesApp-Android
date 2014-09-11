package pivotal.my_rotten_tomatoes;

/**
 * Created by Oksana on 2014-08-28.
 */
public class HeaderSummary {

    private String language;
    private String contentLength;

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language )
    {
        this.language= language;
    }

    public String getContentLength()
    {
        return contentLength;
    }

    public void setContentLength(String contentLength)
    {
        this.contentLength = contentLength;
    }

}
