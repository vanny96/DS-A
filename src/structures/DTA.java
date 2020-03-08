package structures;

public class DTA {
    private int length = 0;
    private int preHash = 0;

    public void append(char c){
        preHash = preHash * 31 + c;
        length++;
    }

    public void skip(char c){
        preHash = preHash - c * (int) Math.pow(31, length-1);
        length--;
    }

    public int getPreHash(){
        return preHash;
    }
}