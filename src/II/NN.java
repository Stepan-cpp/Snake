package II;

public class NN{
    protected int input[][] = new int[16][9];
    protected float weight[][][] = new float[16][9][4];
    protected float output[] = new float[4];

    public void setInput(int[][] map16x9){
        input = map16x9;
    }

    public int getOut(){
        calculate();
        int im = 0;
        for(int i=0;i<4;i++){
            if(output[i] > output[im]){
                im = i;
            }
        }
        //System.out.println(output[0] + " " + output[1] + " " + output[2] + " " + output[3]);
        return im;
    }

    public void calculate(){
        for(int o = 0; o < 4; o++){
            output[o] = 0;
            for(int i = 0; i < 16; i++){
                for(int j = 0; j < 9; j++) {
                    output[o] += input[i][j] * weight[i][j][o];
                    if(input[i][j] != 0){
                        System.out.println("+i");
                    }
                    if(weight[i][j][o] != 0){
                        System.out.println("+w");
                    }
                }
            }
        }
    }
}
