package PackageSpaceInvaders;

public class Player extends Ship {
    private boolean moveRight;
    private boolean moveLeft;

    public Player(int x, int y, int speed) {
        super(x, y, speed);
        this.setMoveLeft(false);
        this.setMoveRight(false);
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }
}
