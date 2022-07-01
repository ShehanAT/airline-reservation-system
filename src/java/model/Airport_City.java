package model;

public class Airport_City {
    protected int airport_city_id;
    protected String airport_city_name;

    public Airport_City() {
    }

    public Airport_City(String airport_city_name) {
        this.airport_city_name = airport_city_name;
    }

    public Airport_City(int airport_city_id, String airport_city_name) {
        this.airport_city_id = airport_city_id;
        this.airport_city_name = airport_city_name;
    }

    public int getAirport_City_id() {
        return airport_city_id;
    }

    public void setAirport_City_id(int airport_city_id) {
        this.airport_city_id = airport_city_id;
    }

    public String getAirport_City_name() {
        return airport_city_name;
    }

    public void setAirport_City_name(String airport_city_name) {
        this.airport_city_name = airport_city_name;
    }
}
