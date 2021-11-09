package gjut.exist.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name="unit_no")
    private String unitNo;
    @Column(name="street")
    private String street;
    @Column(name="baranggay")
    private String baranggay;
    @Column(name = "subdivision")
    private String subdivision;
    @Column(name = "municipality")
    private String municipality;
    @Column(name = "province")
    private String province;
    @Column(name = "zipcode")
    private int zipcode;

    public Address(){}

    public Address(String unitNo, String street, String baranggay,
                   String subdivision, String municipality,
                   String province, int zipcode){
        this.unitNo = unitNo;
        this.street = street;
        this.baranggay = baranggay;
        this.subdivision = subdivision;
        this.municipality = municipality;
        this.province = province;
        this.zipcode = zipcode;

    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBaranggay() {
        return baranggay;
    }

    public void setBaranggay(String baranggay) {
        this.baranggay = baranggay;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String toString(){
        return getUnitNo() + " " + getStreet() + " " + getBaranggay()
                + " " + getSubdivision() + " " + getMunicipality()
                + " " + getProvince() + ", " + getZipcode();
    }

}
