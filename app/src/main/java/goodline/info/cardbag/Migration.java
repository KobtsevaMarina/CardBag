package goodline.info.cardbag;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion==0){
            oldVersion++;
        }

        if (oldVersion == 1) {
            schema.get("PhotoRealm")
                    .addField("test", long.class);
            oldVersion++;
        }
        if (oldVersion == 2) {
            schema.get("PhotoRealm")
                    .removeField("test");
            oldVersion++;
        }
    }
}
