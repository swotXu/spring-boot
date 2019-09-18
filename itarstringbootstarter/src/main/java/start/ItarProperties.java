package start;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "bbt"
)
public class ItarProperties {
    private String name;
    private String age;
    private Integer length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
