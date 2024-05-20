package app;

public class PopulationAndTemp {


    private String countryId;
    private String name;
    private float avgTemp;
    private int year;

    private int population;


    public PopulationAndTemp(String countryId, String name, float avgTemp, int year, int population) {
        this.countryId = countryId;
        this.avgTemp = avgTemp;
        this.year = year;
        this.population = population;
        this.name = name;
    }

    public PopulationAndTemp(String name, float avgTemp, int year) {
        this.name = name;
        this.avgTemp = avgTemp;
        this.year = year;
        this.countryId = name;
    }

    
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryId() {
        return countryId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
    public void setAvgTemp(float avgTemp) {
        this.avgTemp = avgTemp;
    }

    public float getAvgTemp() {
        return avgTemp;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }


    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }
}
