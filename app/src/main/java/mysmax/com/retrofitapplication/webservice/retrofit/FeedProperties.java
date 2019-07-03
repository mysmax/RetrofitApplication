package mysmax.com.retrofitapplication.webservice.retrofit;

import com.google.gson.annotations.SerializedName;

public class FeedProperties {
    @SerializedName("timeline_url")
    String timelineUrl;
    @SerializedName("user_url")
    String userUrl;

    public String getTimelineUrl() {
        return timelineUrl;
    }

    public void setTimelineUrl(String timelineUrl) {
        this.timelineUrl = timelineUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
