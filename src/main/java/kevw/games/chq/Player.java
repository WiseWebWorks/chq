package kevw.games.chq.model;

import java.awt.Color;
import java.util.Set;
import kevw.games.chq.model.places.City;
import kevw.games.chq.model.places.Development;
import kevw.games.chq.model.units.Unit;

public class Player {

  private static int playerNumber = 0;

  private double income;
  private int funds;
  private Set<Unit> units;
  private Set<Development> developments;
  private City capital;
  private Color color;

  private String name;

  public Player() {
    name = "Player #" + playerNumber++;
  }

  public Player(String name) {
    this.name = name;
  }

  public static int getPlayerNumber() {
    return playerNumber;
  }

  public static void setPlayerNumber(int playerNumber) {
    Player.playerNumber = playerNumber;
  }

  public double getIncome() {
    return income;
  }

  public void setIncome(double income) {
    this.income = income;
  }

  public int getFunds() {
    return funds;
  }

  public void setFunds(int funds) {
    this.funds = funds;
  }

  public Set<Unit> getUnits() {
    return units;
  }

  public void setUnits(Set<Unit> units) {
    this.units = units;
  }

  public Set<Development> getDevelopments() {
    return developments;
  }

  public void setDevelopments(Set<Development> developments) {
    this.developments = developments;
  }

  public City getCapital() {
    return capital;
  }

  public void setCapital(City capital) {
    this.capital = capital;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
