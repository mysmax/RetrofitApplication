
package mysmax.com.retrofitapplication.webservice.retrofit.EventProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("push_id")
    @Expose
    private long pushId;
    @SerializedName("size")
    @Expose
    private long size;
    @SerializedName("distinct_size")
    @Expose
    private long distinctSize;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("head")
    @Expose
    private String head;
    @SerializedName("before")
    @Expose
    private String before;
    @SerializedName("commits")
    @Expose
    private List<Commit> commits = null;

    public long getPushId() {
        return pushId;
    }

    public void setPushId(long pushId) {
        this.pushId = pushId;
    }

    public Payload withPushId(long pushId) {
        this.pushId = pushId;
        return this;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Payload withSize(long size) {
        this.size = size;
        return this;
    }

    public long getDistinctSize() {
        return distinctSize;
    }

    public void setDistinctSize(long distinctSize) {
        this.distinctSize = distinctSize;
    }

    public Payload withDistinctSize(long distinctSize) {
        this.distinctSize = distinctSize;
        return this;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Payload withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Payload withHead(String head) {
        this.head = head;
        return this;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public Payload withBefore(String before) {
        this.before = before;
        return this;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public Payload withCommits(List<Commit> commits) {
        this.commits = commits;
        return this;
    }

}
