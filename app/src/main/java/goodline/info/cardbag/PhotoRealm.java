package goodline.info.cardbag;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhotoRealm extends RealmObject {

    @PrimaryKey
    private long imageID;

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }
}
