package goodline.info.cardbag;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public final class PhotoMapper {
    public static List<Photo> photoMap2Data(List<PhotoRealm> realmList) {
        List<Photo> photos = new ArrayList<>();
        for (PhotoRealm photoRealm : realmList) {
            Photo photo = new Photo(
                    photoRealm.getImageID()
            );
            photos.add(photo);
        }
        return photos;
    }
    public static RealmList<PhotoRealm> photoMap2Realm(List <Photo> photo)
    {
        RealmList <PhotoRealm> photoRealm = new RealmList<>();
        for(Photo photos: photo) {
            PhotoRealm photoRealm1 = new PhotoRealm();
            photoRealm1.setImageID(photos.getImageID());
            photoRealm.add(photoRealm1);
        }
        return photoRealm;
    }
}
