package bin.gameComponents;

public class HitBox {
    public int aX, aY;
    public int bX, bY;

    public HitBox(int x, int y, int width, int height){
        this.aX = x + (int) (width  * 0.15);
        this.aY = y + (int) (height * 0.1);
        this.bX = x + (int) (width  * 0.85);
        this.bY = y + (int) (height * 0.9);
    }

    public void setCords(int x, int y, int width, int height) {
        this.aX = x + (int) (width  * 0.15);
        this.aY = y + (int) (height * 0.1);
        this.bX = x + (int) (width  * 0.85);
        this.bY = y + (int) (height * 0.9);
    }


}
//           - X +
//  A  ****************
//     *              *
//     *              * -
//     *              * Y
//     *              * +
//     *              *
//     *              *
//     ****************  B
