package goodline.info.cardbag;

import java.util.Arrays;
import java.util.List;

public class DataBaseHelper {
    public static List<Category> categories = Arrays.asList(
            new Category(0, "Не отображается"),//TODO: разобраться, почему не отображается первый элемент списка
            new Category(0, "Одежда и обувь"),
            new Category(2, "Супермаркеты"),
            new Category(3, "Красота"),
            new Category(4, "Автомобиль"));

    public static List<Category> getCategories() {
        return categories;
    }
}
