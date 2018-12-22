package goodline.info.cardbag;

public final class CategoryMapper {

    public static Category map2data(CategoryRealm  categoryRealm) {
        Category category = new Category();
        category.setId(categoryRealm.getId());
        category.setName(categoryRealm.getName());
        return category;
    }
    public static CategoryRealm categoreMap2Realm(Category category) {
        CategoryRealm categoryRealm = new CategoryRealm();
        categoryRealm.setId(category.getId());
        categoryRealm.setName(category.getName());
        return categoryRealm;
    }
}
