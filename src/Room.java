class Room {
    private final int x;
    private final int y;

    Room(String[] dimensions) throws ArrayIndexOutOfBoundsException {
        this(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
    }

    Room(int x, int y) throws IllegalArgumentException {
        this.x = x;
        this.y = y;
        if (x < 1 || y < 1) {
            throw new IllegalArgumentException("ERROR: Invalid room dimensions.");
        }
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
