package model;

public class Company{
    protected int firma_id;
    protected String company_name;
    protected String company_logo;

    public Company() {
    }

    public Company(int firma_id, String company_name) {
        this.firma_id = firma_id;
        this.company_name = company_name;
    }

    public Company(String company_name, String company_logo) {
        this.company_name = company_name;
        this.company_logo = company_logo;
    }

    public Company(int firma_id, String company_name, String company_logo) {
        this.firma_id = firma_id;
        this.company_name = company_name;
        this.company_logo = company_logo;
    }

    public Company(String company_name) {
        this.company_name = firma_ad;
    }
    
    public int getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(int firma_id) {
        this.firma_id = firma_id;
    }

    public String getFirma_ad() {
        return firma_ad;
    }

    public void setFirma_ad(String firma_ad) {
        this.firma_ad = firma_ad;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }    
}
