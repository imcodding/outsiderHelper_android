
package com.mia.outsiderhelper.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("base")
    private String mBase;
    @SerializedName("clouds")
    private Clouds mClouds;
    @SerializedName("cod")
    private Long mCod;
    @SerializedName("coord")
    private Coord mCoord;
    @SerializedName("dt")
    private Long mDt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("name")
    private String mName;
    @SerializedName("sys")
    private Sys mSys;
    @SerializedName("timezone")
    private Long mTimezone;
    @SerializedName("visibility")
    private Long mVisibility;
    @SerializedName("weather")
    private List<Weather> mWeather;
    @SerializedName("wind")
    private Wind mWind;

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        mBase = base;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public void setClouds(Clouds clouds) {
        mClouds = clouds;
    }

    public Long getCod() {
        return mCod;
    }

    public void setCod(Long cod) {
        mCod = cod;
    }

    public Coord getCoord() {
        return mCoord;
    }

    public void setCoord(Coord coord) {
        mCoord = coord;
    }

    public Long getDt() {
        return mDt;
    }

    public void setDt(Long dt) {
        mDt = dt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Main getMain() {
        return mMain;
    }

    public void setMain(Main main) {
        mMain = main;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Sys getSys() {
        return mSys;
    }

    public void setSys(Sys sys) {
        mSys = sys;
    }

    public Long getTimezone() {
        return mTimezone;
    }

    public void setTimezone(Long timezone) {
        mTimezone = timezone;
    }

    public Long getVisibility() {
        return mVisibility;
    }

    public void setVisibility(Long visibility) {
        mVisibility = visibility;
    }

    public List<Weather> getWeather() {
        return mWeather;
    }

    public void setWeather(List<Weather> weather) {
        mWeather = weather;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public static class Main {

        @SerializedName("feels_like")
        private Double mFeelsLike;
        @SerializedName("humidity")
        private Long mHumidity;
        @SerializedName("pressure")
        private Long mPressure;
        @SerializedName("temp")
        private Double mTemp;
        @SerializedName("temp_max")
        private Double mTempMax;
        @SerializedName("temp_min")
        private Double mTempMin;

        public Double getFeelsLike() {
            return mFeelsLike;
        }

        public void setFeelsLike(Double feelsLike) {
            mFeelsLike = feelsLike;
        }

        public Long getHumidity() {
            return mHumidity;
        }

        public void setHumidity(Long humidity) {
            mHumidity = humidity;
        }

        public Long getPressure() {
            return mPressure;
        }

        public void setPressure(Long pressure) {
            mPressure = pressure;
        }

        public Double getTemp() {
            return mTemp;
        }

        public void setTemp(Double temp) {
            mTemp = temp;
        }

        public Double getTempMax() {
            return mTempMax;
        }

        public void setTempMax(Double tempMax) {
            mTempMax = tempMax;
        }

        public Double getTempMin() {
            return mTempMin;
        }

        public void setTempMin(Double tempMin) {
            mTempMin = tempMin;
        }

    }

    public static class Sys {

        @SerializedName("country")
        private String mCountry;
        @SerializedName("id")
        private Long mId;
        @SerializedName("message")
        private Double mMessage;
        @SerializedName("sunrise")
        private Long mSunrise;
        @SerializedName("sunset")
        private Long mSunset;
        @SerializedName("type")
        private Long mType;

        public String getCountry() {
            return mCountry;
        }

        public void setCountry(String country) {
            mCountry = country;
        }

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
            mId = id;
        }

        public Double getMessage() {
            return mMessage;
        }

        public void setMessage(Double message) {
            mMessage = message;
        }

        public Long getSunrise() {
            return mSunrise;
        }

        public void setSunrise(Long sunrise) {
            mSunrise = sunrise;
        }

        public Long getSunset() {
            return mSunset;
        }

        public void setSunset(Long sunset) {
            mSunset = sunset;
        }

        public Long getType() {
            return mType;
        }

        public void setType(Long type) {
            mType = type;
        }

    }

    public static class Coord {

        @SerializedName("lat")
        private Double mLat;
        @SerializedName("lon")
        private Double mLon;

        public Double getLat() {
            return mLat;
        }

        public void setLat(Double lat) {
            mLat = lat;
        }

        public Double getLon() {
            return mLon;
        }

        public void setLon(Double lon) {
            mLon = lon;
        }

    }

    public static class Clouds {

        @SerializedName("all")
        private Long mAll;

        public Long getAll() {
            return mAll;
        }

        public void setAll(Long all) {
            mAll = all;
        }

    }

    public static class Weather {

        @SerializedName("description")
        private String mDescription;
        @SerializedName("icon")
        private String mIcon;
        @SerializedName("id")
        private Long mId;
        @SerializedName("main")
        private String mMain;

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getIcon() {
            return mIcon;
        }

        public void setIcon(String icon) {
            mIcon = icon;
        }

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
            mId = id;
        }

        public String getMain() {
            return mMain;
        }

        public void setMain(String main) {
            mMain = main;
        }

    }

    public static class Wind {

        @SerializedName("deg")
        private Long mDeg;
        @SerializedName("speed")
        private Double mSpeed;

        public Long getDeg() {
            return mDeg;
        }

        public void setDeg(Long deg) {
            mDeg = deg;
        }

        public Double getSpeed() {
            return mSpeed;
        }

        public void setSpeed(Double speed) {
            mSpeed = speed;
        }

    }
}
