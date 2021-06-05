package entity;

public class Address {
    private String homeAddress;
    private String tempAddress;

    public Address() {
    }

    public Address(String homeAddress, String tempAddress) {
        this.homeAddress = homeAddress;
        this.tempAddress = tempAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(String tempAddress) {
        this.tempAddress = tempAddress;
    }
}
