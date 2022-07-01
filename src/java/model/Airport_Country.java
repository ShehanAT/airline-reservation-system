package model;

public class Airport_Country {
    protected int airport_country_id;
    protected String airport_country_name;

    public Airport_Country() {
    }
    
    public Airport_Country(String airport_country_name) {
        this.airport_country_name = airport_country_name;
    }
    
    public Airport_Country(int airport_country_id, String airport_country_name) {
        this.airport_country_id = airport_country_id;
        this.airport_country_name = airport_country_name;
    }

    public int getAirport_country_id() {
        return airport_country_id;
    }

    public String getAirport_country_name() {
        return airport_country_name;
    }

    public void setAirport_country_id(int airport_country_id) {
        this.airport_country_id = airport_country_id;
    }

    public void setAirport_country_ad(String airport_country_name) {
        this.airport_country_name = airport_country_name;
    }
    
}
