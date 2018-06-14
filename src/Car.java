import java.awt.Point;

class Car {
    private static final String cardinalDirectionsRegex = "[NESW]+";
    private static final String driveCommandsRegex = "[FBLR]+";
    private int x, y;
    private String facingDirection;
    private final Room room;

    Car(String[] input, Room room) throws IllegalArgumentException {
        this(Integer.parseInt(input[0]), Integer.parseInt(input[1]), input[2].toUpperCase(), room);
    }

    Car(int x, int y, String facingDirection, Room room) throws IllegalArgumentException {
        this.room = room;
        this.x = x;
        this.y = y;
        this.facingDirection = facingDirection.toUpperCase();
        validatePosition();
        verifyInputAgainstRegex(facingDirection, cardinalDirectionsRegex, "ERROR: Invalid facing direction.");
    }

    String getFacingDirection() {
        switch (facingDirection) {
            case "N":
                return "north";
            case "E":
                return "east";
            case "S":
                return "south";
            case "W":
                return "west";
            default:
                return "unknown";
        }
    }

    Point getPosition() {
        return new Point(x, y);
    }

    void steer(String steeringDirections) throws IllegalArgumentException {
        steeringDirections = steeringDirections.toUpperCase();
        verifyInputAgainstRegex(steeringDirections, driveCommandsRegex, "ERROR: Invalid drive command.");
        for (int i = 0; i < steeringDirections.length(); i++) {
            switch (steeringDirections.charAt(i)) {
                case 'F':
                    drive(1);
                    break;
                case 'B':
                    drive(-1);
                    break;
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
            }
            if (outOfBounds()) {
                System.out.println("Your car has hit the wall!");
                break;
            }
        }
    }

    private void drive(int distance) {
        switch (facingDirection) {
            case "N":
                y += distance;
                break;
            case "E":
                x += distance;
                break;
            case "S":
                x -= distance;
                break;
            case "W":
                y -= distance;
                break;
        }
        System.out.println("Driving to point " + x + ", " + y);
    }

    private void turnLeft() {
        switch (facingDirection) {
            case "N":
                facingDirection = "W";
                break;
            case "E":
                facingDirection = "N";
                break;
            case "S":
                facingDirection = "E";
                break;
            case "W":
                facingDirection = "S";
                break;
        }
        System.out.println("Turning left to face " + getFacingDirection());
    }

    private void turnRight() {
        switch (facingDirection) {
            case "N":
                facingDirection = "E";
                break;
            case "E":
                facingDirection = "S";
                break;
            case "S":
                facingDirection = "W";
                break;
            case "W":
                facingDirection = "N";
                break;
        }
        System.out.println("Turning right to face " + getFacingDirection());
    }

    private void validatePosition() {
        if (outOfBounds()) {
            throw new IllegalArgumentException("ERROR: Location is out of bounds.");
        }
    }

    private void verifyInputAgainstRegex(String input, String regex, String exceptionMessage) {
        if (!input.matches(regex)) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    boolean outOfBounds() {
        return (x >= room.getX() || y >= room.getY() || y < 0 || x < 0);
    }

}
