package goodline.info.cardbag;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
    private String nameCard;
    private String category;
    private String sale;

    protected Card(Parcel in) {
        nameCard = in.readString();
        category = in.readString();
        sale = in.readString();
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
        dest.writeString(category);
        dest.writeString(sale);
    }


    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
