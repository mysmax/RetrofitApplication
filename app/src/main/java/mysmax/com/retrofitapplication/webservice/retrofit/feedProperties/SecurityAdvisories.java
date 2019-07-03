
package mysmax.com.retrofitapplication.webservice.retrofit.feedProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityAdvisories {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("type")
    @Expose
    private String type;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public SecurityAdvisories withHref(String href) {
        this.href = href;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SecurityAdvisories withType(String type) {
        this.type = type;
        return this;
    }

}
