package PackageSpaceInvaders;

public abstract class Ship {
    private int x;
    private int y;
    private int speed;

    public Ship(int x, int y, int speed) {
        this.setX(x);
        this.setY(y);
        this.setSpeed(speed);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
