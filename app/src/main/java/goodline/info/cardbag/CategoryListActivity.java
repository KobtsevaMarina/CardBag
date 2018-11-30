package goodline.info.cardbag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import io.realm.Realm;
import io.realm.RealmResults;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static goodline.info.cardbag.DataBaseHelper.categories;

public class CategoryListActivity extends AppCompatActivity implements CategoryListAdapter.onItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Дабaвить карту");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rvCategoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Category> categoriesRest = categories;
        List <CategoryRealm> categoriesLocal = null;
        List<Category> categories = new ArrayList<>();

        //categoriesLocal = getCategoriesFromLocal();
        //categoriesRest = getCategoriesFromRemote();


        //if(categoriesLocal == null || categoriesLocal.isEmpty())
        //{
        categoriesRest = getCategoriesFromRemote();
        addCategories(categoriesRest);
        //}

        categoriesRest = getCategoriesFromRemote();
        if(categoriesRest == null) {
            Toast.makeText(this, "Ошибка сервера", Toast.LENGTH_LONG).show();
            return;
        }
        if(categoriesRest!=null) {
            addCategories(categoriesRest);
        }

        categoriesLocal = getCategoriesFromLocal();
        categories = map2Data(categoriesLocal);

        CategoryListAdapter adapter = new CategoryListAdapter(this, categoriesRest, this);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }
    private RealmResults<CategoryRealm> getCategoriesFromLocal() {
        return Realm.getDefaultInstance().where(CategoryRealm.class).findAll();
    }


    private List<Category> getCategoriesFromRemote() {
        return categories;
    }
    private List<CategoryRealm> map2RealmList(List<Category> categories) {
        List<CategoryRealm> realmList = new ArrayList<>();
        for(Category category : categories){
            CategoryRealm categoryRealm = new CategoryRealm();
            categoryRealm.setId(category.getId());
            categoryRealm.setName(category.getName());
            realmList.add(categoryRealm);
        }

        return realmList;
    }

    private List <Category> map2Data(List<CategoryRealm> realmList) {
        List<Category> categories = new ArrayList<>();
        for(CategoryRealm categoryRealm : realmList) {
            Category category = new Category(
                    categoryRealm.getId(),
                    categoryRealm.getName()
            );
            categories.add(category);
        }
        return categories;

    }

    private void addCategories(List<Category>  list) {
       final List<CategoryRealm> realmList = map2RealmList(list);
        Realm realm = Realm.getDefaultInstance();
        //realm.beginTransaction();

        //realm.commitTransaction();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmList);
            }
        });
        realm.close();
    }
    @Override
    public void onItemClick(Category item) {

        Intent intent = new Intent(this, AddCardActivity.class);
        intent.putExtra(Category.class.getSimpleName(), item);
        setResult(RESULT_OK, intent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        onBackPressed();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_category_list, menu);
        return true;
    }
}
