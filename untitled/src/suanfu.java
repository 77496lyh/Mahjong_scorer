import java.util.ArrayList;

public class suanfu {
    private int fu;
    private majsoul zifeng;
    private majsoul changfeng;
    private ArrayList<majsoul> maj;
    private boolean menqianqingronghe; //门前清荣和
    private boolean zimo; //自摸
    private boolean danqi;  //单骑听牌
    private boolean bianzhang; //边张听牌
    private boolean kanzhang;   //坎张听牌

    public suanfu(majsoul zifeng, majsoul changfeng, ArrayList<majsoul> maj, boolean menqianqingronghe, boolean zimo, boolean danqi, boolean bianzhang, boolean kanzhang) {
        fu=20;
        this.zifeng = zifeng;
        this.changfeng = changfeng;
        this.maj = maj;
        this.menqianqingronghe = menqianqingronghe;
        this.zimo = zimo;
        this.danqi = danqi;
        this.bianzhang = bianzhang;
        this.kanzhang = kanzhang;
    }

    public int getFu() {
        return fu;
    }

    public void setFu(int fu) {
        this.fu = fu;
    }

    public int judege(){
        judege ju=new judege(maj);
        majsoul quetou= maj.get(0); //计算雀头
        if(quetou.equals(zifeng)){
            fu+=2;
        }
        if (quetou.equals(changfeng)){
            fu+=2;
        }
        if(quetou.getType().equals("d")){
            if(quetou.getNumber()==5||quetou.getNumber()==6||quetou.getNumber()==7){
                fu+=2;
            }
        }
        for (int i = 2; i < maj.size()-2; i++) {
            if(maj.get(i).isSwitchs()==1){ // 判断暗牌部分
                if(maj.get(i).equals(maj.get(i+1))&&maj.get(i+1).equals(maj.get(i+2))){
                    if(maj.get(i).getType().equals("d")){  //字牌暗刻
                        fu+=8;
                        i+=2;
                    }else if (maj.get(i).getNumber()==1||maj.get(i).getNumber()==9){  //19暗刻
                        fu+=8;
                        i+=2;
                    }else {  //其他暗刻
                        fu+=4;
                        i+=2;
                    }
                }
            }
            if(maj.get(i).isSwitchs()==0){ //判断明牌部分
                if(i+3<maj.size()) {
                    if (maj.get(i).equals(maj.get(i + 1)) && maj.get(i + 1).equals(maj.get(i + 2)) && maj.get(i + 2).equals(maj.get(i + 3))) {
                        if(maj.get(i).getType().equals("d")){  //字牌明杠
                            fu+=16;
                            i+=3;
                            continue;
                        }else if (maj.get(i).getNumber()==1||maj.get(i).getNumber()==9){  //19明杠
                            fu+=16;
                            i+=3;
                            continue;
                        }else {  //其他明杠
                            fu+=8;
                            i+=3;
                            continue;
                        }
                    }
                }
                if (maj.get(i).equals(maj.get(i + 1)) && maj.get(i + 1).equals(maj.get(i + 2))) {
                    if(maj.get(i).getType().equals("d")){  //字牌明刻
                        fu+=4;
                        i+=2;
                        continue;
                    }else if (maj.get(i).getNumber()==1||maj.get(i).getNumber()==9){  //19明刻
                        fu+=4;
                        i+=2;
                        continue;
                    }else {  //其他明刻
                        fu+=2;
                        i+=2;
                        continue;
                    }
                }
            }
            if(maj.get(i).isSwitchs()==-1){   //暗杠部分
                if(maj.get(i).getType().equals("d")){  //字牌暗杠
                    fu+=32;
                    i+=3;
                }else if (maj.get(i).getNumber()==1||maj.get(i).getNumber()==9){  //19暗杠
                    fu+=32;
                    i+=3;
                }else {  //其他暗杠
                    fu+=16;
                    i+=3;
                }
            }
        }
        if(menqianqingronghe){
            fu+=10;
        }
        if(zimo){
            fu+=2;
        }
        if(danqi||bianzhang||kanzhang){
            fu+=2;
        }
        if(fu%10!=0){
            fu+=10;
        }
        fu=fu/10*10;
        return this.fu;
    }

}
