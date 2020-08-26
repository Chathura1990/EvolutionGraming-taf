package com.app.evolutionGaming.Ui.model;

import com.app.evolutionGaming.Ui.pageHelper.SearchPageService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class SearchCarModel {

    private String oughtWordOrPhrase;
    private String mark;
    private String model;
    private int yearMin;
    private int yearMax;
    private double engineVolumeMin;
    private double engineVolumeMax;
    private String engineType;
    private String bodyType;
    private String vinCode;
    private String townRegion;

    public SearchCarModel setOughtWordOrPhrase(String oughtWordOrPhrase){
        this.oughtWordOrPhrase = oughtWordOrPhrase;
        return this;
    }

    public SearchCarModel setMark(String mark){
        this.mark = mark;
        return this;
    }

    public SearchCarModel setModel(String model){
        this.model = model;
        return this;
    }

    public SearchCarModel setYearMin(int yearMin){
        this.yearMin = yearMin;
        return this;
    }

    public SearchCarModel setYearMax(int yearMax){
        this.yearMax = yearMax;
        return this;
    }

    public SearchCarModel setEngineVolumeMin(double engineVolumeMin){
        this.engineVolumeMin = engineVolumeMin;
        return this;
    }

    public SearchCarModel setEngineVolumeMax(double engineVolumeMax){
        this.engineVolumeMax = engineVolumeMax;
        return this;
    }

    public SearchCarModel setEngineType(String engineType){
        this.engineType = engineType;
        return this;
    }

    public SearchCarModel setBodyType(String bodyType){
        this.bodyType = bodyType;
        return this;
    }

    public SearchCarModel setVinCode(String vinCode){
        this.vinCode = vinCode;
        return this;
    }

    public SearchCarModel setTownRegion(String townRegion){
        this.townRegion = townRegion;
        return this;
    }

}
