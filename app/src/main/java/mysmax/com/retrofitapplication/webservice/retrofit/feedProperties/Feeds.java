
package mysmax.com.retrofitapplication.webservice.retrofit.feedProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feeds {

    @SerializedName("timeline_url")
    @Expose
    private String timelineUrl;
    @SerializedName("user_url")
    @Expose
    private String userUrl;
    @SerializedName("security_advisories_url")
    @Expose
    private String securityAdvisoriesUrl;
    @SerializedName("_links")
    @Expose
    private Links links;

    public String getTimelineUrl() {
        return timelineUrl;
    }

    public void setTimelineUrl(String timelineUrl) {
        this.timelineUrl = timelineUrl;
    }

    public Feeds withTimelineUrl(String timelineUrl) {
        this.timelineUrl = timelineUrl;
        return this;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public Feeds withUserUrl(String userUrl) {
        this.userUrl = userUrl;
        return this;
    }

    public String getSecurityAdvisoriesUrl() {
        return securityAdvisoriesUrl;
    }

    public void setSecurityAdvisoriesUrl(String securityAdvisoriesUrl) {
        this.securityAdvisoriesUrl = securityAdvisoriesUrl;
    }

    public Feeds withSecurityAdvisoriesUrl(String securityAdvisoriesUrl) {
        this.securityAdvisoriesUrl = securityAdvisoriesUrl;
        return this;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Feeds withLinks(Links links) {
        this.links = links;
        return this;
    }

}
