import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class judege {
    private int symbol;
    private int fan;
    private int totalNumber;
    private ArrayList<majsoul> maj;
    private int gangNumber;
    private int mingpai=0;
    private int anpai=0;

    public judege(ArrayList<majsoul> maj) {
        this.maj = maj;
        this.gangNumber=maj.size()-14;
    }

    public boolean hupai(){
        ArrayList<majsoul> jumaj= new ArrayList<>();
        for(majsoul o : maj){
            if(o.isSwitchs()==1) {
                majsoul k=new majsoul(o.getType(),o.getNumber(),o.isSwitchs());
                jumaj.add(k);
                anpai++;
            }else {
                mingpai++;
            }
        }
        int count=anpai;
        if(count==2){ //如果只剩俩张牌，判断是否为将
            if(jumaj.get(0).equals(jumaj.get(1))) {
                maj.get(maj.indexOf(jumaj.get(0))).setSwitchs(2);
                maj.get(maj.indexOf(jumaj.get(1))).setSwitchs(2);
                return true;
            }
        }
        for (int i = 0; i < anpai-1; i++) {   //遍历所有的牌做将
            majsoul mid=new majsoul("",0,0);
            majsoul mid2=new majsoul("",0,0);
            if(jumaj.get(i).equals(jumaj.get(i+1))){ //找到可能的将牌

                mid2.setNumber(jumaj.get(i).getNumber());
                mid2.setType(jumaj.get(i).getType());
                mid2.setSwitchs(jumaj.get(i).isSwitchs());

                maj.get(maj.indexOf(mid2)).setSwitchs(2);
                maj.get(maj.indexOf(mid2)).setSwitchs(2);

                mid.setNumber(jumaj.get(i).getNumber());  //移除将牌
                mid.setType(jumaj.get(i).getType());
                mid.setSwitchs(jumaj.get(i).isSwitchs());
                jumaj.remove(i);
                jumaj.remove(i);

                if(hupaidigui(jumaj)){
                    return true;
                }

                jumaj.add(mid);
                jumaj.add(mid);
                mid2.setSwitchs(2);
                maj.get(maj.indexOf(mid2)).setSwitchs(1);
                maj.get(maj.indexOf(mid2)).setSwitchs(1);
                Collections.sort(jumaj);

            }
        }
        return false;
    }

    public boolean hupaidigui(ArrayList<majsoul> arm) {
        if (arm.size() == 0) {
            return true;
        }
        for (int i = 0; i < arm.size()-2; i++) {
            majsoul mid= arm.get(i);
            if(arm.get(i).equals(arm.get(i+1))&&arm.get(i+1).equals(arm.get(i+2))){  //判断刻子
                arm.remove(i);
                arm.remove(i);
                arm.remove(i);
                if(hupaidigui(arm)){
                    return true;
                }
                arm.add(mid);
                arm.add(mid);
                arm.add(mid);
                Collections.sort(arm);
            }
            if(arm.contains(new majsoul(mid.getType(), mid.getNumber() + 1, mid.isSwitchs())) &&
                    arm.contains(new majsoul(mid.getType(), mid.getNumber() + 2, mid.isSwitchs()))){  //判断顺子
                arm.remove(i);
                arm.remove(new majsoul(mid.getType(),mid.getNumber()+1,mid.isSwitchs()));
                arm.remove(new majsoul(mid.getType(),mid.getNumber()+2,mid.isSwitchs()));
                if(hupaidigui(arm)){
                    return true;
                }
                arm.add(mid);
                arm.add(new majsoul(mid.getType(),mid.getNumber()+1,mid.isSwitchs()));
                arm.add(new majsoul(mid.getType(),mid.getNumber()+2,mid.isSwitchs()));
                Collections.sort(arm);
            }
        }
        return false;
    }
}
