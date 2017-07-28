package xyz.aungpyaephyo.ma.ac.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import xyz.aungpyaephyo.ma.ac.data.db.AttractionImagesTypeConverter;

/**
 * Created by aung on 7/6/16.
 */
@Entity(tableName = "attractions")
@TypeConverters(AttractionImagesTypeConverter.class)
public class AttractionVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("images")
    private String[] images;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getImages() {
        return images;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
