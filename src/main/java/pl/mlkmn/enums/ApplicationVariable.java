package pl.mlkmn.enums;

public enum ApplicationVariable {
    MODEL_PACKAGE ("pl.mlkmn.model");

    private String name;

    ApplicationVariable(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
