package model;

public class Company{
    protected int company_id;
    protected String company_name;
    protected String company_logo;

    public Company() {
    }

    public Company(int company_id, String company_name) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    public Company(String company_name, String company_logo) {
        this.company_name = company_name;
        this.company_logo = company_logo;
    }

    public Company(int company_id, String company_name, String company_logo) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_logo = company_logo;
    }

    public Company(String company_name) {
        this.company_name = company_name;
    }
    
    public int getFirma_id() {
        return company_id;
    }

    public void setFirma_id(int company_id) {
        this.company_id = company_id;
    }

    public String getFirma_ad() {
        return company_name;
    }

    public void setFirma_ad(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }    
}
