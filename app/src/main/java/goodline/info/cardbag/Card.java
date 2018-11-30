package goodline.info.cardbag;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
    private  int id;
    private String nameCard;
    private Category category;
    private String sale;
    private int[] imageId = new int[2];

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

        imageId[0]=in.readInt();
        imageId[1]=in.readInt();
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
        dest.writeInt(imageId[0]);
        dest.writeInt(imageId[1]);
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

    public int[] getImageId() {
        return imageId;
    }

    public void setImageId(int[] imageId) {
        this.imageId = imageId;
    }
}
