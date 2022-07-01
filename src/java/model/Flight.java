package model;

public class Flight {
    protected int flight_id;
    protected int flight_departure_id;
    protected int end_heir_id;
    protected String flight_date;
    protected String flight_hour;
    protected String flight_time;
    protected int company_id;
    protected int plane_id;
    protected double flight_fare;
    protected String company_name;
    protected String airplane_name;
    protected String ucus_departure;
    protected String ucus_arrival;

    public Flight() {
    }

    public Flight(int flight_id, int flight_departure_id, int end_heir_id, String flight_date, String flight_hour, String flight_time, int company_id, int plane_id, double flight_fare) {
        this.flight_id = flight_id;
        this.flight_departure_id = flight_departure_id;
        this.end_heir_id = end_heir_id;
        this.flight_date = flight_date;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_id = company_id;
        this.plane_id = plane_id;
        this.flight_fare = flight_fare;
    }

    public Flight(int flight_id, String flight_date, String flight_hour, String flight_time, double flight_fare, String company_name, String airplane_name, String ucus_departure, String ucus_arrival) {
        this.flight_id = flight_id;
        this.flight_date = flight_date;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.flight_fare = flight_fare;
        this.company_name = company_name;
        this.airplane_name = airplane_name;
        this.ucus_departure = ucus_departure;
        this.ucus_arrival = ucus_arrival;
    }

    public Flight(int flight_departure_id, int end_heir_id, String flight_date, String flight_hour, String flight_time, int company_id, int plane_id, double flight_fare) {
        this.flight_departure_id = flight_departure_id;
        this.end_heir_id = end_heir_id;
        this.flight_date = flight_date;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_id = company_id;
        this.plane_id = plane_id;
        this.flight_fare = flight_fare;
    }
    
    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getFlight_departure_id() {
        return flight_departure_id;
    }

    public void setFlight_departure_id(int flight_departure_id) {
        this.flight_departure_id = flight_departure_id;
    }

    public int getFlight_arrival_id() {
        return end_heir_id;
    }

    public void setFlight_arrival_id(int end_heir_id) {
        this.end_heir_id = end_heir_id;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }

    public String getFlight_saat() {
        return flight_hour;
    }

    public void setFlight_saat(String flight_hour) {
        this.flight_hour = flight_hour;
    }

    public String getFlight_sure() {
        return flight_time;
    }

    public void setFlight_sure(String flight_time) {
        this.flight_time = flight_time;
    }

    public int getFirma_id() {
        return company_id;
    }

    public void setFirma_id(int company_id) {
        this.company_id = company_id;
    }

    public int getUcak_id() {
        return plane_id;
    }

    public void setUcak_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public double getFlight_fee() {
        return flight_fare;
    }

    public void setFlight_fee(double flight_fare) {
        this.flight_fare = flight_fare;
    }

    public String getFirma_name() {
        return company_name;
    }

    public void setFirma_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUcak_name() {
        return airplane_name;
    }

    public void setUcak_name(String airplane_name) {
        this.airplane_name = airplane_name;
    }

    public String getFlight_departure() {
        return ucus_departure;
    }

    public void setFlight_departure(String ucus_departure) {
        this.ucus_departure = ucus_departure;
    }

    public String getFlight_arrival() {
        return ucus_arrival;
    }

    public void setFlight_arrival(String ucus_arrival) {
        this.ucus_arrival = ucus_arrival;
    }
    
    
    
}
