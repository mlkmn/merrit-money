package pl.mlkmn.enums;

public enum Page {
    LOGIN ("login"),
    REGISTER ("register"),
    DASHBOARD ("dashboard"),
    ERROR ("error"),
    ACCESS_DENIED ("accessDenied"),
    ADMIN_PANEL ("adminPanel");

    private String name;

    Page(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

}
