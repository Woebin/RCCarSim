import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

public class TestSuite {
    private Room room;
    private Car car;

    @Before
    public void setup() {
        this.room = new Room(new String[]{"3", "5"});
        this.car = new Car(new String[]{"1", "1", "n"}, room);
    }

    @Test
    public void testCreateValidRoom() {
        Assert.assertEquals(3, room.getX());
        Assert.assertEquals(5, room.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRoomInput() {
        new Room(-5, -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNumericRoomInput() {
        new Room(new String[]{"hey", "oh no"});
    }

    @Test
    public void testDriveInBounds() {
        car.steer("ffrf");
        Assert.assertEquals(new Point(2, 3), car.getPosition());
        Assert.assertEquals("E", car.getFacingDirection());
        Assert.assertFalse(car.outOfBounds());
    }

    @Test
    public void testDriveIntoWall() {
        car.steer("ffffffffff");
        Assert.assertEquals(new Point(1, 5), car.getPosition());
        Assert.assertTrue(car.outOfBounds());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSteeringInput() {
        car.steer("ffru");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacingCarOutOfBounds() {
        new Car(12, 13, "n", room);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFacingDirection() {
        new Car(0, 0, "f", room);
    }
}
