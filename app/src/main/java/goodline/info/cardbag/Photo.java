package goodline.info.cardbag;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {
    private long imageID;

    public Photo(){

        imageID = 0;
    }

    public Photo(long imageID) {

        this.imageID = imageID;
    }


    protected Photo(Parcel in) {
        imageID = in.readInt();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public long getImageID() {

        return imageID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(imageID);
    }
}
