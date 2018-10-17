package client;

public class FamilyBean {

    private String name, areol;

    public FamilyBean(String name, String areol) {
        this.name = name;
        this.areol = areol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreol() {
        return areol;
    }

    public void setAreol(String areol) {
        this.areol = areol;
    }

}
