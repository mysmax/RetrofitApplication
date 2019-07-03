
package mysmax.com.retrofitapplication.webservice.retrofit.EventProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Actor {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("display_login")
    @Expose
    private String displayLogin;
    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Actor withId(long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Actor withLogin(String login) {
        this.login = login;
        return this;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public Actor withDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
        return this;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public Actor withGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Actor withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Actor withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

}
