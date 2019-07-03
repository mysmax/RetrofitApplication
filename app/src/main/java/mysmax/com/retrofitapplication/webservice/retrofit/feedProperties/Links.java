
package mysmax.com.retrofitapplication.webservice.retrofit.feedProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("timeline")
    @Expose
    private Timeline timeline;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("security_advisories")
    @Expose
    private SecurityAdvisories securityAdvisories;

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Links withTimeline(Timeline timeline) {
        this.timeline = timeline;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Links withUser(User user) {
        this.user = user;
        return this;
    }

    public SecurityAdvisories getSecurityAdvisories() {
        return securityAdvisories;
    }

    public void setSecurityAdvisories(SecurityAdvisories securityAdvisories) {
        this.securityAdvisories = securityAdvisories;
    }

    public Links withSecurityAdvisories(SecurityAdvisories securityAdvisories) {
        this.securityAdvisories = securityAdvisories;
        return this;
    }

}
