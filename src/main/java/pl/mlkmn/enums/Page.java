package pl.mlkmn.enums;

public enum Page {
    LOGIN ("login"),
    REGISTER ("register"),
    DASHBOARD ("dashboard"),
    ERROR ("error");

    private String name;

    Page(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

}
