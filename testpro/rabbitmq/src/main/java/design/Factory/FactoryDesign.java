package design.Factory;

/**
 * Created by ZhaoJiwei on 2018/8/10.
 * 两个类A和B之间的关系应该仅仅是A创建B或是A使用B，而不能两种关系都有。将对象的创建和使用分离，也使得系统更加符合“单一职责原则”，有利于对功能的复用和系统的维护。
 https://blog.csdn.net/nugongahou110/article/details/50425823/
 */




public class FactoryDesign {

    interface Button {
        public void display();
    }

    //Spring按钮类：具体产品
    class SpringButton implements Button {
        public void display() {
            System.out.println("显示浅绿色按钮。");
        }
    }

    //Summer按钮类：具体产品
    class SummerButton implements Button {
        public void display() {
            System.out.println("显示浅蓝色按钮。");
        }
    }

    //文本框接口：抽象产品
    interface TextField {
        public void display();
    }

    //Spring文本框类：具体产品
    class SpringTextField implements TextField {
        public void display() {
            System.out.println("显示绿色边框文本框。");
        }
    }

    //Summer文本框类：具体产品
    class SummerTextField implements TextField {
        public void display() {
            System.out.println("显示蓝色边框文本框。");
        }
    }

    //组合框接口：抽象产品
    interface ComboBox {
        public void display();
    }

    //Spring组合框类：具体产品
    class SpringComboBox implements ComboBox {
        public void display() {
            System.out.println("显示绿色边框组合框。");
        }
    }

    //Summer组合框类：具体产品
    class SummerComboBox implements ComboBox {
        public void display() {
            System.out.println("显示蓝色边框组合框。");
        }
    }

    //界面皮肤工厂接口：抽象工厂
    interface SkinFactory {
         Button createButton();
         TextField createTextField();
         ComboBox createComboBox();
    }

    //Spring皮肤工厂：具体工厂
    class SpringSkinFactory implements SkinFactory {
        public Button createButton() {
            return new SpringButton();
        }

        public TextField createTextField() {
            return new SpringTextField();
        }

        public ComboBox createComboBox() {
            return new SpringComboBox();
        }
    }

    //Summer皮肤工厂：具体工厂
    class SummerSkinFactory implements SkinFactory {
        public Button createButton() {
            return new SummerButton();
        }

        public TextField createTextField() {
            return new SummerTextField();
        }

        public ComboBox createComboBox() {
            return new SummerComboBox();
        }
    }

}
