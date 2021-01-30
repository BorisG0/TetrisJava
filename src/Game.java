import java.awt.*;

public class Game {
    int[][] array;
    Tile currentTile;
    int width, height, xPos, yPos, points;
    Game(int width, int height){
        points = 0;
        this.width = width;
        this.height = height;
        array = new int[width][height];
        currentTile = randomTile();
        xPos = width/2 - currentTile.getLook().length/2;
        yPos = 0;
    }

    public Color getCurrentTileColor(){
        return currentTile.color;
    }

    public int getPoints(){
        return points;
    }

    public Tile randomTile(){
        int[][] newLook = null;
        int randNum = (int)Math.round(Math.random() * 6);
        Color color = null;

        switch (randNum){
            case 0:
                newLook = new int[][]{{1,1,1,1}};
                color = Color.red;
                break;
            case 1:
                newLook = new int[][]{{1,1,0},{0,1,1}};
                color = Color.green;
                break;
            case 2:
                newLook = new int[][]{{0,1,1},{1,1,0}};
                color = Color.cyan;
                break;
            case 3:
                newLook = new int[][]{{1,1},{1,1}};
                color = Color.magenta;
                break;
            case 4:
                newLook = new int[][]{{0,1},{1,1},{0,1}};
                color = Color.yellow;
                break;
            case 5:
                newLook = new int[][]{{1,1,1},{1,0,0}};
                color = Color.orange;
                break;
            case 6:
                newLook = new int[][]{{1,0,0},{1,1,1}};
                color = Color.white;
                break;
        }


        return new Tile(newLook, color);
    }

    public void nextTile(){
        currentTile = randomTile();
        yPos = 0;
        xPos = width/2 - currentTile.getLook().length/2;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(array[i][j] == 1) array[i][j] = 2;

            }
        }
        checkLines();
    }

    public void clearLine(int height){
        for(int i = 0; i < width; i++){
            array[i][height] = 0;
        }
        for(int i = height; i > 0; i--){
            for(int j = 0; j < width; j++){
                array[j][i] = array[j][i - 1];
            }
        }
        points++;

    }

    public void checkLines(){
        boolean isFull;
        for(int i = 0; i < height; i++){
            isFull = true;
            for(int j = 0; j < width; j++){
                if (array[j][i] != 2) {
                    isFull = false;
                    break;
                }
            }
            if(isFull)
            clearLine(i);
        }
    }

    public void progress(){
        //checkLines();
        if(yPos + currentTile.getLook()[0].length >= height){
            nextTile();
            return;
        }
        for(int i = 0; i < currentTile.getLook().length; i++){
            for(int j = 0; j < currentTile.getLook()[i].length; j++){
                if(array[i + xPos][j + yPos + 1] == 2 && currentTile.getLook()[i][j] == 1){
                    nextTile();
                    return;
                }
                //array[i + xPos][j + yPos] = currentTile.getLook()[i][j];
            }
        }

        yPos++;

        reposition();

    }

    public void rotate(){
        int oldLookLength = currentTile.getLook().length;
        currentTile.rotate();
        xPos += oldLookLength/2 - currentTile.getLook().length/2;

        if(xPos < 0) xPos = 0;

        if(xPos + currentTile.getLook().length > width) xPos = width - currentTile.getLook().length;

        if(currentTile.getLook()[0].length + yPos >height) rotate();

        for(int i = 0; i < currentTile.getLook().length; i++){
            for(int j = 0; j < currentTile.getLook()[i].length; j++){
                if(array[i + xPos][j + yPos] == 2 && currentTile.getLook()[i][j] == 1){
                    rotate();
                    return;
                }
                array[i + xPos][j + yPos] = currentTile.getLook()[i][j];
            }
        }
        reposition();
    }

    public void leftMove(){

        if(xPos <= 0){
            return;
        }

        for(int i = 0; i < currentTile.getLook().length; i++){
            for(int j = 0; j < currentTile.getLook()[i].length; j++){
                if(array[i + xPos - 1][j + yPos] == 2 && currentTile.getLook()[i][j] == 1){
                    return;
                }
                array[i + xPos][j + yPos] = currentTile.getLook()[i][j];
            }
        }
        xPos--;

        reposition();
    }

    public void rightMove(){

        if(xPos + currentTile.getLook().length >= width){
            return;
        }

        for(int i = 0; i < currentTile.getLook().length; i++){
            for(int j = 0; j < currentTile.getLook()[i].length; j++){
                if(array[i + xPos + 1][j + yPos] == 2 && currentTile.getLook()[i][j] == 1){
                    return;
                }
                array[i + xPos][j + yPos] = currentTile.getLook()[i][j];
            }
        }
        xPos++;
        reposition();
    }

    public void reposition(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(array[i][j] == 1) array[i][j] = 0;

            }
        }

        for(int i = 0; i < currentTile.getLook().length; i++){
            for(int j = 0; j < currentTile.getLook()[i].length; j++){
                if(currentTile.getLook()[i][j] == 1)
                array[i + xPos][j + yPos] = currentTile.getLook()[i][j];
            }
        }
    }

    public int getWidth(){
        return array.length;
    }

    public int getHeight(){
        return array[0].length;
    }

    public int getSquare(int x, int y){
        return array[x][y];
    }
}
