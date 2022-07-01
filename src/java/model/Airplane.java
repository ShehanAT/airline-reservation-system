package model;

public class Airplane {
    int plane_id;
    String airplane_name;
    int airplane_seat;
    int company_id;
    String company_name;

    public Airplane() {
    }

    public Airplane(int plane_id, String airplane_name) {
        this.plane_id = plane_id;
        this.airplane_name = airplane_name;
    }

    public Airplane(String airplane_name, int airplane_seat, int company_id) {
        this.airplane_name = airplane_name;
        this.airplane_seat = airplane_seat;
        this.company_id = company_id;
    }
    
    public Airplane(int plane_id, String airplane_name, int airplane_seat, int company_id) {
        this.plane_id = plane_id;
        this.airplane_name = airplane_name;
        this.airplane_seat = airplane_seat;
        this.company_id = company_id;
    }

    public Airplane(int plane_id, String airplane_name, int airplane_seat, String company_name) {
        this.plane_id = plane_id;
        this.airplane_name = airplane_name;
        this.airplane_seat = airplane_seat;
        this.company_name = company_name;
    }

    public int getUcak_id() {
        return plane_id;
    }

    public void setUcak_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public String getUcak_name() {
        return airplane_name;
    }

    public void setAirplane_name(String airplane_name) {
        this.airplane_name = airplane_name;
    }

    public int getAirplane_seat() {
        return airplane_seat;
    }

    public void setAirplane_seat(int airplane_seat) {
        this.airplane_seat = airplane_seat;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    
    
    
}
