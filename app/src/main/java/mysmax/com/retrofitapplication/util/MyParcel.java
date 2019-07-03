package mysmax.com.retrofitapplication.util;


import android.os.Parcel;
import android.os.Parcelable;

public class MyParcel implements Parcelable{

    private int myInt;
    private String myString;

    public MyParcel(int intVal, String strVal)
    {
        this.myInt = intVal;
        this.myString = strVal;
    }


    protected MyParcel(Parcel in) {
        myInt = in.readInt();
        myString = in.readString();
    }

    public static final Creator<MyParcel> CREATOR = new Creator<MyParcel>() {
        @Override
        public MyParcel createFromParcel(Parcel in) {
            return new MyParcel(in);
        }

        @Override
        public MyParcel[] newArray(int size) {
            return new MyParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(myInt);
        dest.writeString(myString);
    }
}
