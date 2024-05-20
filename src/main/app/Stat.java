package app;

public class Stat {
    private String id;
    private String name;
    private float proportion;
    private float startData;
    private float endData;

    public Stat(String id, float proportion) {
        this.id = id;
        this.proportion = proportion;
    }

    public Stat(String id, String name, float proportion) {
        this.id = id;
        this.proportion = proportion;
        this.name = name;
    }

    public Stat(String id, String name, float proportion, float startData, float endData) {
        this.id = id;
        this.name = name;
        this.proportion = proportion;
        this.startData = startData;
        this.endData = endData;
    }


    public void setStartData(float startData) {
        this.startData = startData;
    }

    public float getStartData() {
        return startData;
    }


    public void setEndData(float endData) {
        this.endData = endData;
    }

    public float getEndData() {
        return endData;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setProportion(float proportion) {
        this.proportion = proportion;
    }

    public float getProportion() {
        return proportion;
    }

}
