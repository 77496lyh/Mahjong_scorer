import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class simpleJframe extends JFrame {
    private JFrame jf;
    private JPanel jp;
    private ImageIcon[][] ic;
    private ArrayList<majsoul> maj=new ArrayList<>();
    public simpleJframe() throws HeadlessException {
        ic=new ImageIcon[4][9];
        UIjichu();
    }

    public void showing(){
        jf.setVisible(true);
    }

    public void UIjichu(){
        majsoul zifeng =new majsoul("d",1,1);
        majsoul changfeng = new majsoul("d",1,1);

        Dimension d=new Dimension(46,70);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                if(i==3&&j>=7)
                    break;
                ic[i][j]=new ImageIcon("/Users/liyanhui/IdeaProjects/untitled/src/mahjong-icons/majiang"+i+j+".jpg");
                ic[i][j].setImage(ic[i][j].getImage().getScaledInstance(46,70,Image.SCALE_DEFAULT));
            }
        }
        jf=this;
        jp=new JPanel();
        jf.add(jp);
        jp.setLayout(null);
        jf.setBounds(200,50,1000,800);

        JButton counts =new JButton("计算");
        jp.add(counts);
        counts.setSize(100,50);
        counts.setLocation(50,200);
        counts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saunfan suanf = new saunfan(maj);
                suanf.judege(zifeng, changfeng, true, true, false, true, false, false,
                        false, false, false, false);
                DefaultListModel de = new DefaultListModel();
                int index=0;
                for(Map.Entry<String,Integer> entry:suanf.getMap().entrySet()){
                    String s=new String(entry.getKey()+"     "+entry.getValue()+"番");
                    de.add(index++,s);
                }
                de.add(index++,"");
                de.add(index++,"合计： "+suanf.getFan()+"番"+suanf.getFu()+"符");
                de.add( index++,"           "+suanf.getDefen()+"分");
                JList jl = new JList(de);
                jl.setSize(150, 300);
                jl.setLocation(700, 300);
                jp.add(jl);
                jp.updateUI();
                jf.repaint();
            }
        });

        JButton dele =new JButton("清除");
        jp.add(dele);
        dele.setSize(100,50);
        dele.setLocation(390,585);
        dele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maj.clear();
                jp.removeAll();
                jp.setVisible(false);
                UIjichu();
                jp.setVisible(true);
                jp.updateUI();
                jf.repaint();
            }
        });
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                if(i==3&&j>=7)
                    break;
                JButton jb=new JButton(ic[i][j]);
                jp.add(jb);
                jb.setSize(d);
                jb.setLocation(55*j,320+85*i);
                int type=i;
                int num=j;
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        char mid ='a';
                        mid+=type;
                        majsoul m_mid = new majsoul(String.valueOf(mid),num+1,1);
                        maj.add(m_mid);
                        Collections.sort(maj);
                        jp.removeAll();
                        jp.setVisible(false);
                        UIjichu();
                        jp.setVisible(true);
                        jp.updateUI();
                        jf.repaint();
                    }
                });
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int mid=0;
        for(majsoul i:maj){
            JLabel jl =new JLabel(ic[i.getType().charAt(0)-'a'][i.getNumber()-1]);
            jl.setSize(46,70);
            jl.setLocation(46*mid++,70);
            jp.add(jl);
        }
        jf.repaint();
    }
}
