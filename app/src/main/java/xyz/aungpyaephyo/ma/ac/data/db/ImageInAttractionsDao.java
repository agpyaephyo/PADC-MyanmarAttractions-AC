package xyz.aungpyaephyo.ma.ac.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.aungpyaephyo.ma.ac.data.vo.ImageInAttractionVO;

/**
 * Created by aung on 7/28/17.
 */
@Dao
public interface ImageInAttractionsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertImageInAttraction(ImageInAttractionVO imageInAttraction);

    @Query("SELECT * FROM images_in_attractions WHERE attraction_id = :attractionId")
    List<ImageInAttractionVO> getImagesByAttraction(long attractionId);

    @Query("DELETE FROM images_in_attractions")
    void deleteAll();
}
