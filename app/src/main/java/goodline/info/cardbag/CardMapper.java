package goodline.info.cardbag;

import java.util.ArrayList;
import java.util.List;

public final class CardMapper {
    public static List<Card> map2DataList(List<CardRealm> realmList) {
        List<Card> cards = new ArrayList<>();
        for (CardRealm cardRealm : realmList) {
            Card card = new Card (
                    cardRealm.getId(),
                    cardRealm.getNameCard(),
                    CategoryMapper.map2data(cardRealm.getCategory()),
                    cardRealm.getSale(),
                    PhotoMapper.photoMap2Data(cardRealm.getPhotoList())
            );
            cards.add(card);
        }
        return cards;
    }
    public static CardRealm cardMap2Realm(Card card) {
        CardRealm cardRealm = new CardRealm();
        cardRealm.setId(card.getId());
        cardRealm.setNameCard(card.getNameCard());
        cardRealm.setSale(card.getSale());
        cardRealm.setCategory(CategoryMapper.categoreMap2Realm(card.getCategory()));
        cardRealm.setPhotoList(PhotoMapper.photoMap2Realm(card.getPhotoList()));
        return cardRealm;
    }
}
