package mysmax.com.retrofitapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDetails implements Parcelable {
    private String userName;
    private String userDesignation;
    private String userAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.userDesignation);
        dest.writeString(this.userAddress);
    }

    public UserDetails() {
    }

    protected UserDetails(Parcel in) {
        this.userName = in.readString();
        this.userDesignation = in.readString();
        this.userAddress = in.readString();
    }

    public static final Parcelable.Creator<UserDetails> CREATOR = new Parcelable.Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel source) {
            return new UserDetails(source);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };
}
