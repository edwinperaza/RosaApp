package cl.moriahdp.RosaApp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;

@Dao
public interface HomeModelObjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HomeModelObject homeModelObject);

    @Query("DELETE FROM homemodelobject")
    void deleteAll();

    @Query("SELECT * from homemodelobject")
    List<HomeModelObject> getAll();
}
