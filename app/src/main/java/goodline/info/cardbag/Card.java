package goodline.info.cardbag;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Card implements Parcelable {
    private  int id;
    private String nameCard;
    private Category category;
    private String sale;
    public List<Photo> photoList;

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public static Creator<Card> getCREATOR() {
        return CREATOR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    protected Card(Parcel in) {
        nameCard = in.readString();
        category = (Category) in.readParcelable(Category.class.getClassLoader());
        sale = in.readString();
        photoList = new ArrayList<>();
        in.readList(photoList, getClass().getClassLoader());
    }


    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
    public Card() {
        id = 0;
        nameCard = null;
        category = null;
        sale = null;
        photoList = null;
    }
    public Card(int id, String nameCard, Category category, String sale, List<Photo> photoList) {
        this.id = id;
        this.nameCard = nameCard;
        this.category = category;
        this.sale = sale;
        this.photoList = photoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameCard);
        dest.writeParcelable(category, flags);
        dest.writeString(sale);
        dest.writeList(photoList);
    }


    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }


    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

}
