package goodline.info.cardbag;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhotoRealm extends RealmObject {

    @PrimaryKey
    private int imageID;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
