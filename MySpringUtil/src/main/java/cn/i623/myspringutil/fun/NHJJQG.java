package cn.i623.myspringutil.fun;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cn.i623.myspringutil.fun.NHJJQG.Coin.oneCionThrow;

public class NHJJQG {

    static Coin coin1 = new Coin();
    static Coin coin2 = new Coin();
    static Coin coin3 = new Coin();

    static Random random = new Random();
    static List<Integer> dongList = new ArrayList();
    static List<Boolean> yaoList = new ArrayList();

    public static void main(String[] args) {
        SixThrow();
        String xia = readYao(yaoList);
        String shang = readYao(yaoList);
        System.out.println("："+"下"+xia+"上"+shang);
    }

    static String readYao(List<Boolean> yaoList) {
       String gua=  NHJJQG.yaoList.get(0)
            ? (NHJJQG.yaoList.get(1)
                ? (NHJJQG.yaoList.get(2) ? ("乾") : ("兑"))
                : (NHJJQG.yaoList.get(2) ? ("离") : ("震"))
            )
            : (NHJJQG.yaoList.get(1)
                ? (NHJJQG.yaoList.get(2) ? ("巽") : ("坎"))
                : (NHJJQG.yaoList.get(2) ? ("艮") : ("坤"))
            );
       yaoList.remove(0);
       yaoList.remove(0);
       yaoList.remove(0);
       return gua;
    }

    static void SixThrow() {
        for (int i = 0; i < 6; i++) {
            CionThrow(i);
        }
        System.out.println();
        System.out.println(yaoList);
        System.out.println(dongList);
    }


    static void CionThrow(int i) {
        Integer obeverse = 0;
        obeverse += oneCionThrow(coin1).getObverse() ? 1 : 0;
        obeverse += oneCionThrow(coin2).getObverse() ? 1 : 0;
        obeverse += oneCionThrow(coin3).getObverse() ? 1 : 0;
        System.out.print(obeverse+"\t");
        switch (obeverse) {
            case 0:
                yaoList.add(true);
                dongList.add(++i);
                break;
            case 1:
                yaoList.add(true);
//                dongList.add(0);
                break;
            case 2:
                yaoList.add(false);
//                dongList.add(0);
                break;
            case 3:
                yaoList.add(false);
                dongList.add(++i);
                break;
        }
    }


    @Data
    static class Coin {
        Boolean obverse;

        static Coin oneCionThrow(Coin coin) {
            boolean b = random.nextBoolean();
//            System.out.println(b);
            coin.setObverse(b);
            return coin;
        }
    }
}
