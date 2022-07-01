package model;

public class Airport{
    protected int airport_id;
    protected int airport_country_id;
    protected int airport_city_id;
    protected String airport_name;
    protected String airport_code;
    protected String airport_country_name;
    protected String airport_city_name;

    public Airport() {
    }

    public Airport(int airport_id, String airport_name, String airport_code) {
        this.airport_id = airport_id;
        this.airport_name = airport_name;
        this.airport_code = airport_code;
    }

    public Airport(int airport_id, int airport_country_id, int airport_city_id, String airport_name, String airport_code) {
        this.airport_id = airport_id;
        this.airport_country_id = airport_country_id;
        this.airport_city_id = airport_city_id;
        this.airport_name = airport_name;
        this.airport_code = airport_code;
    }
   
    public Airport(int airport_country_id, int airport_city_id, String airport_name, String airport_code) {
        this.airport_country_id = airport_country_id;
        this.airport_city_id = airport_city_id;
        this.airport_name = airport_name;
        this.airport_code = airport_code;
    }
    
    public Airport(int airport_country_id, int airport_city_id, String airport_name, String airport_code, String airport_country_name, String airport_city_name) {
        this.airport_country_id = airport_country_id;
        this.airport_city_id = havaalani_city_id;
        this.airport_name = airport_name;
        this.airport_code = airport_code;
        this.airport_country_name = airport_country_name;
        this.airport_city_name = airport_city_name;
    }
    
    public Airport(int airport_id, int airport_country_id, int havaalani_city_id, String airport_name, String airport_code, String airport_country_name, String airport_city_name) {
        this.airport_id = airport_id;
        this.airport_country_id = airport_country_id;
        this.havaalani_city_id = havaalani_city_id;
        this.airport_name = airport_name;
        this.airport_code = airport_code;
        this.airport_country_name = airport_country_name;
        this.airport_city_name = airport_city_name;
    }

    public int getHavaalani_id() {
        return airport_id;
    }

    public void setHavaalani_id(int airport_id) {
        this.airport_id = airport_id;
    }

    public int getAirport_country_id() {
        return airport_country_id;
    }

    public void setAirport_country_id(int airport_country_id) {
        this.airport_country_id = airport_country_id;
    }

    public int getAirport_city_id() {
        return havaalani_city_id;
    }

    public void setAirport_city_id(int havaalani_city_id) {
        this.havaalani_city_id = havaalani_city_id;
    }

    public String getHavaalani_ad() {
        return airport_name;
    }

    public void setHavaalani_ad(String airport_name) {
        this.airport_name = airport_name;
    }

    public String getHavaalani_code() {
        return airport_code;
    }

    public void setHavaalani_code(String airport_code) {
        this.airport_code = airport_code;
    }

    public String getAirport_country_ad() {
        return airport_country_name;
    }

    public void setAirport_country_ad(String airport_country_name) {
        this.airport_country_name = airport_country_name;
    }

    public String getHavaalani_city_ad() {
        return airport_city_name;
    }

    public void setHavaalani_city_ad(String airport_city_name) {
        this.airport_city_name = airport_city_name;
    }
    
}
