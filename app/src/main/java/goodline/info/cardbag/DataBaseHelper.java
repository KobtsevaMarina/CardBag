package goodline.info.cardbag;

import java.util.Arrays;
import java.util.List;

public class DataBaseHelper {
    public static List<Category> categories = Arrays.asList(
            new Category(0, "Одежда и обувь"),
            new Category(2, "Супермаркеты"),
            new Category(3, "Красота"),
            new Category(4, "Автомобиль"),
            new Category(5, "Бытовая техника"));

    public static List<Card> cards = Arrays.asList();
}
