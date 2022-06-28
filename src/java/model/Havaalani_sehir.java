package model;

public class Airport_city {
    protected int airport_city_id;
    protected String airport_city_name;

    public Airport_city() {
    }

    public Airport_city(String airport_city_name) {
        this.airport_city_name = airport_city_name;
    }

    public Airport_city(int airport_city_id, String airport_city_name) {
        this.airport_city_id = airport_city_id;
        this.airport_city_name = airport_city_name;
    }

    public int getAirport_city_id() {
        return airport_city_id;
    }

    public void setAirport_city_id(int airport_city_id) {
        this.airport_city_id = airport_city_id;
    }

    public String getHavaalani_sehir_ad() {
        return airport_city_name;
    }

    public void setHavaalani_sehir_ad(String airport_city_name) {
        this.airport_city_name = airport_city_name;
    }
}
