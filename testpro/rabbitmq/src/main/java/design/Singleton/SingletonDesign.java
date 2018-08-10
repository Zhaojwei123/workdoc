package design.Singleton;

/**
 * Created by ZhaoJiwei on 2018/8/10.
 * Initialization Demand Holder (IoDH)
 */
class SingletonDesign {

    private SingletonDesign() {
    }

    private static class HolderClass {
        private final static SingletonDesign instance = new SingletonDesign();
    }

    public static SingletonDesign getInstance() {
        return HolderClass.instance;
    }

}
