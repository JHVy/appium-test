package lesson16;

public class TestEnum {

    public static  void getAppiumDriver(MobileType mobileType){
        switch (mobileType){
            case IOS:
                System.out.println(mobileType.getName());
                break;
            case ANDROID:
                System.out.println(mobileType.getName());
                break;
            default:
                throw new IllegalArgumentException("......");
        }
    }

    public static void main(String[] args) {
        getAppiumDriver(MobileType.ANDROID);
        getAppiumDriver(MobileType.IOS);
    }
}
