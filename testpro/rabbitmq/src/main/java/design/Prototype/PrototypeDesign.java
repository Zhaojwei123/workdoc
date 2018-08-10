package design.Prototype;

/**
 * Created by ZhaoJiwei on 2018/8/10.
 * 浅克隆(ShallowClone)和深克隆(DeepClone)。在Java语言中，数据类型分为值类型（基本数据类型）和引用类型，值类型包括int、double、byte、boolean、char等简单数据类型，引用类型包括类、接口、数组等复杂类型。浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制
 */
public class PrototypeDesign implements Cloneable{

    @Override
    protected PrototypeDesign clone() throws CloneNotSupportedException {
        return (PrototypeDesign) super.clone();
    }
}
