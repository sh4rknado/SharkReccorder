package com.jb.sharkreccorder.Model;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AModel {

    @Ignore
    private boolean isSelected;

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;

    public AModel() { this.id = UUID.randomUUID().toString(); }

    public boolean isSelected() {  return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    public String getId() { return id; }
    public void setId(String _id) { this.id = _id; }

    //region CONVERT_TO_JSON
    public static String ConvertToJson(AModel model) {
        return new GsonBuilder().create().toJson(model);
    }

    public static String ConvertToJson(Map<String, String> dictionary) {
        return new GsonBuilder().create().toJson(dictionary);
    }

    public static String ConvertToJson(List<String> strings) {
        return new GsonBuilder().create().toJson(strings);
    }
    //endregion

    //region CONVERT_TO_OBJECT
    public static AModel ConvertJSonToAModel(String json, Type type) {
        return new GsonBuilder().create().fromJson(json, type);
    }

    public static Map<String, String> ConvertToDictionary(String json, Type type) {
        return new GsonBuilder().create().fromJson(json, type);
    }

    public static List<String>  ConvertToListString(String json, Type type) {
        return new GsonBuilder().create().fromJson(json, type);
    }
    //endregion

}