import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum YesNoEnum {
    YES(1, "yes"),
    NO(0, "no");

    private Integer value;
    private String name;
    private static Map<Integer, YesNoEnum> enumMap = new HashMap<>();

    static {
        //  可以在这里加载枚举的配置文件 比如从 properties  数据库中加载
        //  加载完后 使用DynamicEnumUtil.addEnum 动态增加枚举值
        //  然后正常使用枚举即可
        EnumSet<YesNoEnum> set = EnumSet.allOf(YesNoEnum.class);
        for (YesNoEnum each : set) {
            // 增加一个缓存 减少对枚举的修改
            enumMap.put(each.value, each);
        }
    }

    YesNoEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    // 根据关键字段获取枚举值  可以在这里做一些修改 来达到动态添加的效果
    public YesNoEnum getEnum(Integer value) {
        // 这里可以做一些修改  比如若从 enumMap 中没有取得 则加载配置动态添加
        return enumMap.get(value);
    }

    //  动态添加方法  添加完后加入缓存 减少对枚举的修改
    public YesNoEnum addEnum(String enumName, Integer value, String name) {
        YesNoEnum yesNoEnum = DynamicEnumUtil.addEnum(YesNoEnum.class, enumName, new Class[]{Integer.class, String.class}, new Object[]{value, name});
        enumMap.put(value, yesNoEnum);
        return yesNoEnum;
    }
}