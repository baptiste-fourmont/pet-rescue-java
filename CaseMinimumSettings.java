

public interface CaseMinimumSettings {
    //Pour les commentaires de ces fonctions voir CaseGame.java où elles sont expliquées
    public void setXandY();
    public void deleteCase(int cx, int cy);
    public boolean checkCase(int cx, int cy,int k);
    public boolean isLost();
    public void checkAnimals();
    public void updatetabV();
    public void updatetabH();
    public void Swap2Cases(int x1, int y1, int x2, int y2);
    public void SwapCases(int actual);
    public void setPresent(boolean b);
    public int getType();
    public int getValue();
    public int getX();
    public int getY();
}
