import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class RCCarSim {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Room room;

    public static void main(String[] args) {
        room = makeRoom();
        Car car = makeCar();
        driveCar(car);
    }

    private static Room makeRoom() {
        try {
            System.out.print("Input room dimensions: ");
            String[] roomDimensions = splitInput();
            validateInputIntegers(roomDimensions);
            return new Room(roomDimensions);
        } catch (IllegalArgumentException | IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return makeRoom();
        }
    }

    private static Car makeCar() {
        try {
            System.out.print("Input car coordinates and facing: ");
            String[] carPosition = splitInput();
            validateInputIntegers(carPosition);
            return new Car(carPosition, room);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return makeCar();
        }
    }

    private static void driveCar(Car car) {
        try {
            System.out.print("Input drive command: ");
            String input = bufferedReader.readLine();
            car.steer(input);
            if (!car.outOfBounds()) {
                System.out.println(String.format("Successfully drove to location %d, %d. Final facing is %s.",
                        car.getPosition().x,
                        car.getPosition().y,
                        car.getFacingDirection()));
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            driveCar(car);
        }
    }

    private static String[] splitInput() throws IOException {
        String input = bufferedReader.readLine();
        return input.split(" ");
    }

    private static void validateInputIntegers(String[] input) {
        if (input.length < 2 || input.length > 3) {
            throw new IllegalArgumentException("ERROR: Invalid input length.");
        }
        if (!isIntegers(input)) {
            throw new NumberFormatException("ERROR: Dimensions and positions must consist of integers only.");
        }
    }

    private static boolean isIntegers(String[] input) {
        for (int i = 0; i < 2; i++) {
            try {
                Integer.valueOf(input[i]);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}

