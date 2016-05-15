package studyjam.auron.com.spesa;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.annotations.RealmModule;
import studyjam.auron.com.spesa.database.Card;
import studyjam.auron.com.spesa.database.MovimentiDB;

/**
 * Created by luca on 4/21/16.
 */
public class CardApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .name("cardauron-dp")
                .schemaVersion(1)
                .setModules(new CardModule())
                .deleteRealmIfMigrationNeeded()
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm dynamicRealm, long l, long l1) {
                    }
                })
                .build();

        Realm.setDefaultConfiguration(config);
    }

    @RealmModule(classes = {Card.class, MovimentiDB.class
    })
    class CardModule {
    }

}
