package II;

public abstract class NNLearner extends NN{
    protected NN n;

    public NNLearner(NN ii){
        n = ii;
    }

    public abstract void learn(int length, int dis);
}
