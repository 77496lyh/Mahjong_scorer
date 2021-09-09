import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 算符类
*  目前只做到对对胡
*
*
*
* */


public class saunfan {
    private int fan;
    private int defen;
    private int fu;
    private ArrayList<majsoul> maj;
    private HashMap<String, Integer> map;

    private boolean zhuangjia;
    private boolean danqi;
    private boolean liangmian;
    private boolean kanzhang;
    private boolean lizhi;
    private boolean zimo;
    private boolean menqianqing;
    private boolean qianggang;
    private boolean duanyaojiu;
    private boolean menqianqingzimo;
    private boolean zifengpai;
    private boolean changfengpai;
    private boolean sanyuanpai;

    public int getDefen() {
        return defen;
    }

    public void setDefen(int defen) {
        this.defen = defen;
    }

    public int getFu() {
        return fu;
    }

    public void setFu(int fu) {
        this.fu = fu;
    }

    private boolean pinghu;
    private boolean yibeikou;
    private boolean lingshangkaihua;
    private boolean haidimoyue;
    private boolean heidilaoyu;
    private boolean yifa;
    private boolean lianglizhi;
    private boolean sansetongke;
    private boolean sangangzi;
    private boolean duiduihu;

    public saunfan(ArrayList<majsoul> maj) {
        this.fan = 0;
        this.defen=0;
        this.maj = maj;
        this.fu=0;
        lizhi = false;
        zimo = false;
        menqianqing = true;
        duanyaojiu = true;
        qianggang = false;
        menqianqingzimo = false;
        zifengpai = false;
        changfengpai = false;
        sanyuanpai = false;
        qianggang = false;
        pinghu = true;
        yibeikou=false;
        sansetongke=false;
        sangangzi=false;
        zhuangjia=false;
    }

    /* 传入的自风和场风 switchs参数要为1*/

    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    public void judege(majsoul zifeng, majsoul changfeng, boolean lizhi, boolean zimo, boolean qianggang, boolean liangmian,
                       boolean lingshang, boolean haidi, boolean hedi, boolean yifa, boolean lianglizhi,boolean zhuangjia) {
        if(!new judege(maj).hupai()){
            return;
        }
        Collections.sort(maj);
        map=new HashMap<String,Integer>();
        majsoul zifeng2 = new majsoul(zifeng.getType(), zifeng.getNumber(), -1);
        majsoul changfeng2 = new majsoul(zifeng.getType(), zifeng.getNumber(), -1);
        this.zhuangjia=zhuangjia;
        this.lizhi = lizhi; //立直
        this.qianggang = qianggang;//抢杠
        this.lingshangkaihua=lingshang; //岭上开花
        this.haidimoyue=haidi;  //海底摸月
        this.heidilaoyu=hedi;   //河底摸鱼
        this.yifa=yifa; //一发
        this.lianglizhi=lianglizhi; // 两立直
        int zifengNum = 0; //自风牌数量
        int changfengNum = 0; //场风牌数量
        int sanyuanNum = 0; //三元牌数量
        for (majsoul i : maj) {
            if (i.isSwitchs() == 0) {//门前清
                menqianqing = false;
            }
            if (i.getType().equals("d") || i.getNumber() == 9 || i.getNumber() == 1) { //断幺九
                duanyaojiu = false;
            }
            if (i.equals(zifeng) || i.equals(zifeng2)) {
                zifengNum++;
            }
            if (i.equals(changfeng) || i.equals(changfeng2)) {
                changfengNum++;
            }
            if (i.getType().equals("d") && i.getNumber() >= 5 && i.getNumber() <= 7) {
                sanyuanNum++;
            }
        }
        if (zifengNum >= 3) zifengpai = true;
        if (changfengNum >= 3) changfengpai = true;
        if (sanyuanNum >= 3) sanyuanpai = true;


        // 平胡
        if (!menqianqing) {
            pinghu = false;
        }
        if(!liangmian){
            pinghu = false;
        }
        if(maj.get(0).equals(zifeng)||maj.get(0).equals(changfeng)||
                (maj.get(0).getType().equals("d")&&maj.get(0).getNumber() >= 5 && maj.get(0).getNumber() <= 7)){
            pinghu=false;
        }
        for (int i = 2; i < maj.size() - 1; i++) {
            if(maj.get(i).equals(i+1)){
                pinghu=false;
            }
        }

        //一杯口
        if(menqianqing){
            for (int i = 2; i < maj.size()-5; i++) {
                if(maj.get(i).equals(maj.get(i+1))&&maj.get(i+2).equals(maj.get(i+3))&&maj.get(i+4).equals(maj.get(i+5))){ //判断是否是连对
                    if(maj.get(i).getNumber()==maj.get(i+2).getNumber()-1&&maj.get(i+2).getNumber()==maj.get(i+4).getNumber()-1){
                        if(!maj.get(i).getType().equals("d")){ //判断是否是字牌
                            yibeikou=true;
                        }
                    }
                }
            }
        }

        // 三色同刻
        for (int i = 2; i < maj.size()-2; i++) {
            if(maj.get(i).equals(maj.get(i+1))&&maj.get(i+1).equals(maj.get(i+2))){
                for (int j = i+3; j < maj.size()-2; j++) {
                    if(maj.get(j).getNumber()==maj.get(i).getNumber()){
                        if(maj.get(j).equals(maj.get(j+1))&&maj.get(j+1).equals(maj.get(j+2))){
                            for (int k = j+2; k < maj.size()-2; k++) {
                                if(maj.get(j).getNumber()==maj.get(i).getNumber()) {
                                    if (maj.get(j).equals(maj.get(j + 1)) && maj.get(j + 1).equals(maj.get(j + 2))) {
                                        sansetongke=true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //三杠子
        if(maj.size()>=17)
            sangangzi=true;

        //对对胡
        int numMid=0;
        for (int i = 2; i < maj.size()-2; i++){
            if(maj.get(i).equals(maj.get(i+1))&&maj.get(i+1).equals(maj.get(i+2))){
                numMid++;
                i+=2;
            }
        }
        if(numMid>=3)
            duiduihu=true;

        if (menqianqing && zimo) {//门前清自摸
            menqianqingzimo = true;
        }

        // 下面是番形统计
        if(this.lizhi){
            map.put("立直",1);
        }
        if(duanyaojiu){
            map.put("断幺九",1);
        }
        if(menqianqingzimo){
            map.put("门前清自摸",1);
        }
        if(zifengpai){
            map.put("役牌：自风牌",1);
        }
        if(changfengpai) {
            map.put("役牌：场风牌", 1);
        }
        if(sanyuanpai){
            map.put("役牌：三元牌",1);
        }
        if(pinghu){
            map.put("平胡",1);
        }
        if(yibeikou){
            map.put("一杯口",1);
        }
        if(this.qianggang){
            map.put("抢杠",1);
        }
        if(lingshangkaihua){
            map.put("岭上开花",1);
        }
        if(haidimoyue){
            map.put("海底摸月",1);
        }
        if(heidilaoyu){
            map.put("河底捞鱼",1);
        }
        if(this.yifa){
            map.put("一发",1);
        }
        if(this.lianglizhi){
            map.put("两立直",2);
        }
        if(sansetongke){
            map.put("三色同刻",2);
        }
        if(sangangzi){
            map.put("三杠子",2);
        }
        if(duiduihu){
            map.put("对对和",2);
        }
        for(int val:map.values()){
            fan+=val;
        }
        suanfu suanf =new suanfu(zifeng,changfeng,maj,menqianqing&&(!zimo),zimo,false,false,false);
        int fu=suanf.judege();
        int a;
        if(fan<=4){
            a=fu* (int) Math.pow(2, (double) fan+2);
        }else if(fan==5){
            a=2000;
        }else if(fan==6||fan==7){
            a=3000;
        }else if(fan==8||fan==9||fan==10){
            a=4000;
        }else if(fan==11||fan==12){
            a=5000;
        }else {
            a=8000;
        }
        if(zhuangjia)
            defen=6*a;
        else
            defen=4*a;
        this.fu=fu;
    }
}
