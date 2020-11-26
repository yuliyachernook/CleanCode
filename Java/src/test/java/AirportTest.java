import Planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalPlaneType;
import models.MilitaryPlaneType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {

    private Airport airport;

    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryPlaneType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryPlaneType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryPlaneType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryPlaneType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryPlaneType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryPlaneType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalPlaneType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalPlaneType.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @BeforeTest
    public void setUp() {
        airport = new Airport(planes);
    }

    @Test
    public void HasAtLeastOneBomberInMilitaryPlanesTest() {
        Assert.assertTrue(airport.getBomberMilitaryPlanesList().size() > 0);
    }

    @Test
    public void HasAtLeastOneFighterInMilitaryPlanesTest() {
        Assert.assertTrue(airport.getFighterMilitaryPlanesList().size() > 0);
    }

    @Test
    public void HasAtLeastOneTransportInMilitaryPlanesTest() {
        Assert.assertTrue(airport.getTransportMilitaryPlanesList().size() > 0);
    }

    @Test
    public void GetPassengerPlaneWithMaxCapacityTest() {
        Assert.assertEquals(planeWithMaxPassengerCapacity, airport.getPassengerPlaneWithMaxPassengersCapacity());
    }

    @Test
    public void SortPlanesByMaxLoadCapacityTest() {
        airport.sortPlanesByMaxLoadCapacity();
        Assert.assertTrue(airport.getAllPlanesList().get(0).getMaxLoadCapacity() <= airport.getAllPlanesList().get(1).getMaxLoadCapacity());
    }

    @Test
    public void SortPlanesByMaxDistanceTest() {
        airport.sortPlanesByMaxDistance();
        Assert.assertTrue(airport.getAllPlanesList().get(0).getMaxFlightDistance() <= airport.getAllPlanesList().get(1).getMaxFlightDistance());
    }

    @Test
    public void SortPlanesByMaxSpeedTest() {
        airport.sortPlanesByMaxSpeed();
        Assert.assertTrue(airport.getAllPlanesList().get(0).getMaxSpeed() <= airport.getAllPlanesList().get(1).getMaxSpeed());
    }

    @Test
    public void ExperimentalPlanesHasClassificationLevelHigherThanUnclassifiedTest(){
        Assert.assertTrue(airport.getExperimentalPlanesList().get(0).getClassificationLevel() != ClassificationLevel.UNCLASSIFIED);
    }
    @Test
    public void AirportHasAtLeastOneTransportMilitaryPlanesTest() {
        Assert.assertTrue(airport.getTransportMilitaryPlanesList().stream().anyMatch(militaryPlane ->
                militaryPlane.getMilitaryType()==MilitaryPlaneType.TRANSPORT));
    }
}
