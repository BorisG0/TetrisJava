public class Tile {
    int[][] look;
    int xPos, yPos;

    Tile(int[][] look){
        this.look = look;

    }

    public int[][] getLook(){
        return look;
    }

    public void rotate(){
        int[][] newLook = new int[look[0].length][look.length];

        for(int i = 0; i < newLook.length; i++){
            for(int j = 0; j < newLook[i].length; j++){
                newLook[i][j] = look[j][look[0].length - i - 1];
            }
        }
        look = newLook;

    }
}
