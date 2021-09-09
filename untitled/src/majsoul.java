import java.util.Objects;

public class majsoul implements Comparable {
    private String type;  //花色 a-万 b-饼 c-条 d-字牌
    private int number;  //点数
    private int switchs; //-1表示暗杠 0表示鸣牌 1表示暗牌 2表示雀头

    public majsoul(String type, int number, int switchs) {
        this.type = type;
        this.number = number;
        this.switchs = switchs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int isSwitchs() {
        return switchs;
    }

    public void setSwitchs(int switchs) {
        this.switchs = switchs;
    }

    @Override
    public int compareTo(Object o) {    //按照花色从大到小排序
        if(o instanceof majsoul){
            majsoul maj=(majsoul)o;
            if(this.switchs==maj.switchs) {
                if (this.type.equals(maj.type)) {
                    return (this.number - maj.number);
                }
                return -this.type.compareTo(maj.type);
            }
            return -(this.switchs-maj.switchs);
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof majsoul)) return false;
        majsoul majsoul = (majsoul) o;
        return getNumber() == majsoul.getNumber() && switchs == majsoul.switchs && getType().equals(majsoul.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getNumber(), switchs);
    }
}
