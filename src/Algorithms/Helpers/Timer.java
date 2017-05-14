package Algorithms.Helpers;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Timer {

  private long startTime;
  private long endTime;

  public enum TimeUnit {
    NS,
    MCS,
    MS,
    S;

    @Override
    public String toString() {
      switch (this) {
        case NS:
          return "ns";
        case MCS:
          return "\u00B5s";
        case MS:
          return "ms";
        default:
          return "s";
      }
    }

    public double getDivisor() {
      switch (this) {
        case NS:
          return 1.0;
        case MCS:
          return 1000.0;
        case MS:
          return 1000000.0;
        default:
          return 1000000000.0;
      }
    }
  }

  public void start() {
    this.startTime = System.nanoTime();
    this.endTime = 0;
  }

  public void stop() {
    this.endTime = System.nanoTime();
  }

  /**
   * Default case: ms, no cutting of decimal places
   */
  public void printDuration() {
    printDuration(TimeUnit.MS, Integer.MAX_VALUE);
  }

  /**
   * Default case: no cutting of decimal places
   * @param timeUnit output time unit
   */
  public void printDuration(TimeUnit timeUnit) {
    printDuration(timeUnit, Integer.MAX_VALUE);
  }

  /**
   * Print the time of the timer
   * @param timeUnit output time unit
   * @param decimalPlaces number of decimal places
   */
  public void printDuration(TimeUnit timeUnit, int decimalPlaces) {
    if (this.startTime == 0) throw new Error("Timer has not been started yet.");
    if (this.endTime == 0) throw new Error("Timer has not been stopped.");
    if (decimalPlaces < 0) throw new IllegalArgumentException("Decimal places can't be negative.");

    double duration = (this.endTime - this.startTime) / timeUnit.getDivisor();
    String roundedDuration = String.valueOf(duration);
    if (decimalPlaces != Integer.MAX_VALUE) {
      roundedDuration = this.roundToDecimalPlaces(duration, decimalPlaces);
    }

    System.out.println();
    System.out.println("Time: " + roundedDuration + " " + timeUnit.toString());
    System.out.println();
  }

  /**
   * Round passed duration to the number of passed decimal places
   * @param duration
   * @param decimalPlaces
   * @return String rounded duration
   */
  private String roundToDecimalPlaces(double duration, int decimalPlaces) {
    StringBuilder stringBuilder = new StringBuilder("#.");
    for (int i = 0; i < decimalPlaces; i++) {
      stringBuilder.append("#");
    }

    DecimalFormat df = new DecimalFormat(stringBuilder.toString());
    df.setRoundingMode(RoundingMode.HALF_UP);

    return df.format(duration);
  }
}
