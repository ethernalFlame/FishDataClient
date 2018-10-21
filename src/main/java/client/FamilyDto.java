package client;

public class FamilyDto {
    private long id;
    private String name;
    private long areolId;
    private String areolName;

    public FamilyDto(long id, String name, long areolId, String areolName) {
        this.id = id;
        this.name = name;
        this.areolId = areolId;
        this.areolName = areolName;
    }

    public String getAreolName() {
        return areolName;
    }

    public void setAreolName(String areolName) {
        this.areolName = areolName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAreolId() {
        return areolId;
    }

    public void setAreolId(long areolId) {
        this.areolId = areolId;
    }
}
