package lesson16;

public enum MobileType {
    ANDROID("Android"),
    IOS("iOS");

    private String name;

    MobileType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
