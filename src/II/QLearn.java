package II;

import java.util.Random;

public class QLearn extends NNLearner{
    private final double dCoof = -1, lnvel = 10;
    private float weightl[][][] = new float[16][9][4];
    private int lenp = 3, distp = 0;

    public QLearn(NN ii){
        super(ii);
    }

    public void learn(int length, int dist){
        calculate();

        Random r = new Random();

        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                for (int o = 0; o < 4; o++) {
                    weight[i][j][o] += (r.nextFloat() % lnvel);
                }
            }
        }

        for(int o = 0; o < 4; o++){
            for(int i = 0; i < 16; i++){
                for(int j = 0; j < 9; j++) {
                    weight[i][j][o] += (weightl[i][j][o] - weight[i][j][o]) * (length - lenp) * (distp-dist) * (float)dCoof;
                    //System.out.println(weight[i][j][o] + " ");
                }
            }
        }

        weightl = weight;
        lenp = length;
        distp = dist;
    }
}
