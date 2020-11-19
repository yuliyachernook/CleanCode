import Planes.ExperimentalPlane;
import models.MilitaryPlaneType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {

    private List<? extends Plane> allPlanesList;

    public Airport(List<? extends Plane> planes) {
        this.allPlanesList = planes;
    }

    public List<PassengerPlane> getPassengerPlanesList() {
        List<PassengerPlane> passengerPlanesList = new ArrayList<>();
        this.allPlanesList.stream().filter(plane->plane instanceof PassengerPlane).
                forEach(plane->passengerPlanesList.add((PassengerPlane)plane));
        return passengerPlanesList;
    }

    public List<MilitaryPlane> getMilitaryPlanesList() {
        List<MilitaryPlane> militaryPlanesList = new ArrayList<>();
        this.allPlanesList.stream().filter(plane->plane instanceof MilitaryPlane).
                forEach(plane->militaryPlanesList.add((MilitaryPlane)plane));
        return militaryPlanesList;
    }

    public List<ExperimentalPlane> getExperimentalPlanesList() {
        List<ExperimentalPlane> experimentalPlanesList = new ArrayList<>();
        this.allPlanesList.stream().filter(plane->plane instanceof ExperimentalPlane).
                forEach(plane->experimentalPlanesList.add((ExperimentalPlane)plane));
        return experimentalPlanesList;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanesList().stream().sorted(Comparator.comparingInt(PassengerPlane::getPassengersCapacity).reversed()).findFirst().get();
    }

    public List<MilitaryPlane> getTransportMilitaryPlanesList() {
        List<MilitaryPlane> transportMilitaryPlanesList = new ArrayList<>();
        getMilitaryPlanesList().stream().filter(plane->plane.getMilitaryType()==MilitaryPlaneType.TRANSPORT).
                forEach(plane->transportMilitaryPlanesList.add((MilitaryPlane) plane));
        return transportMilitaryPlanesList;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanesList() {
        List<MilitaryPlane> bomberMilitaryPlanesList = new ArrayList<>();
        getMilitaryPlanesList().stream().filter(plane->plane.getMilitaryType()==MilitaryPlaneType.BOMBER).
                forEach(x->bomberMilitaryPlanesList.add((MilitaryPlane) x));
        return bomberMilitaryPlanesList;
    }

    public List<MilitaryPlane> getFighterMilitaryPlanesList() {
        List<MilitaryPlane> fighterMilitaryPlanesList = new ArrayList<>();
        getMilitaryPlanesList().stream().filter(plane->plane.getMilitaryType()==MilitaryPlaneType.FIGHTER).
                forEach(x->fighterMilitaryPlanesList.add((MilitaryPlane) x));
        return fighterMilitaryPlanesList;
    }

    public List<Plane> sortPlanesByMaxDistance() {
        return allPlanesList.stream().sorted(Comparator.comparingInt(Plane::getMaxFlightDistance)).collect(Collectors.toList());
    }

    public List<Plane> sortPlanesByMaxSpeed() {
        return allPlanesList.stream().sorted(Comparator.comparingInt(Plane::getMaxSpeed)).collect(Collectors.toList());
    }

    public List<Plane> sortPlanesByMaxLoadCapacity() {
        return allPlanesList.stream().sorted(Comparator.comparingInt(Plane::getMaxLoadCapacity)).collect(Collectors.toList());

    }

    public List<? extends Plane> getAllPlanesList() {
        return allPlanesList;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + allPlanesList.toString() +
                '}';
    }
}
