package pl.mlkmn.enums;

public enum DataSourceProperty {
    DRIVER_CLASS_NAME ("driver.class.name"),
    URL ("url"),
    USERNAME ("username"),
    PASSWORD ("password");

    private String name;

    DataSourceProperty(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
}
