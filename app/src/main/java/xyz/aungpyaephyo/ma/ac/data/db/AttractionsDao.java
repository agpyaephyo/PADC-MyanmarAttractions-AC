package xyz.aungpyaephyo.ma.ac.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;

/**
 * Created by aung on 7/28/17.
 */

@Dao
public interface AttractionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertAttraction(AttractionVO attraction);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAttractions(AttractionVO... attractions);

    @Query("SELECT * FROM attractions")
    LiveData<List<AttractionVO>> getAllAttractions();

    @Query("DELETE FROM attractions")
    void deleteAll();
}
