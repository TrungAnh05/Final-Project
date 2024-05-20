package app;

public class PopData {
    private String name;
    private int year;

    public PopData(String name, int year, int population) {
        this.name = name;
        this.year = year;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

