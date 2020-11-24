package PackageSpaceInvaders;

public class Alien extends Ship {

    private boolean moveRight;
    private boolean moveLeft;
    private boolean isVisible;

    public Alien(int x, int y, int speed) {
        super(x, y, speed);

        this.setMoveLeft(false);
        this.setMoveRight(true);
        this.setVisible(true);
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
