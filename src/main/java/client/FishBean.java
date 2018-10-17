package client;

public class FishBean {

    private long id;
    private String type;
    private String name;
    private FamilyBean family;
    private String pack;
    private String processing;
    private double weigh, value;


    public FishBean(long id, String type, String name, FamilyBean family, String pack, String processing, double weigh, double value) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.family = family;
        this.pack = pack;
        this.processing = processing;
        this.weigh = weigh;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FamilyBean getFamily() {
        return family;
    }

    public void setFamily(FamilyBean family) {
        this.family = family;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }

    public double getWeigh() {
        return weigh;
    }

    public void setWeigh(double weigh) {
        this.weigh = weigh;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
