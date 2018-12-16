package goodline.info.cardbag;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardRealm extends RealmObject {
    @PrimaryKey
    private int id;
    private String nameCard;
    private CategoryRealm category;
    private String sale;
    private RealmList<PhotoRealm> photoList;

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public CategoryRealm getCategory() {
        return category;
    }

    public void setCategory(CategoryRealm category) {
        this.category = category;
    }


    public RealmList<PhotoRealm> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(RealmList<PhotoRealm> photoList) {
        this.photoList = photoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
