package xyz.aungpyaephyo.ma.ac.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by aung on 7/28/17.
 */
@Entity(tableName = "images_in_attractions", foreignKeys = {
        @ForeignKey(entity = AttractionVO.class, parentColumns = "id", childColumns = "attraction_id")
})
public class ImageInAttractionVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "attraction_id")
    private long attractionId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(long attractionId) {
        this.attractionId = attractionId;
    }
}
