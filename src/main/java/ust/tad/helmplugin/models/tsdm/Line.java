package ust.tad.helmplugin.models.tsdm;

import java.util.Objects;

public class Line {

  private int number;

  private double comprehensibility = 0;

  private boolean analyzed = false;

  private static final String INVALIDANNOTATIONEXCEPTIONMESSAGE =
      "Invalid values for annotations with comprehensibility=%s and analyzed=%s";

  public Line() {}

  public Line(int number) {
    this.number = number;
  }

  public Line(int number, double comprehensibility, boolean analyzed)
      throws InvalidAnnotationException {
    if (!isComprehensibilityInPercent(comprehensibility)) {
      throw new InvalidAnnotationException(
          "Comprehensibility must be a value between 0 and 1, value given: " + comprehensibility);
    } else if (areAnnotationsValid(comprehensibility, analyzed)) {
      this.number = number;
      this.comprehensibility = comprehensibility;
      this.analyzed = analyzed;
    } else {
      throw new InvalidAnnotationException(
          String.format(INVALIDANNOTATIONEXCEPTIONMESSAGE, comprehensibility, analyzed));
    }
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public double getComprehensibility() {
    return this.comprehensibility;
  }

  public void setComprehensibility(double comprehensibility) throws InvalidAnnotationException {
    if (!isComprehensibilityInPercent(comprehensibility)) {
      throw new InvalidAnnotationException(
          "Comprehensibility must be a value between 0 and 1, value given: " + comprehensibility);
    } else if (areAnnotationsValid(comprehensibility, this.analyzed)) {
      this.comprehensibility = comprehensibility;
    } else {
      throw new InvalidAnnotationException(
          String.format(INVALIDANNOTATIONEXCEPTIONMESSAGE, comprehensibility, analyzed));
    }
  }

  public boolean isAnalyzed() {
    return this.analyzed;
  }

  public boolean getAnalyzed() {
    return this.analyzed;
  }

  public void setAnalyzed(boolean analyzed) throws InvalidAnnotationException {
    if (areAnnotationsValid(this.comprehensibility, analyzed)) {
      this.analyzed = analyzed;
    } else {
      throw new InvalidAnnotationException(
          String.format(INVALIDANNOTATIONEXCEPTIONMESSAGE, comprehensibility, analyzed));
    }
  }

  public Line number(int number) {
    setNumber(number);
    return this;
  }

  public Line comprehensibility(double comprehensibility) throws InvalidAnnotationException {
    setComprehensibility(comprehensibility);
    return this;
  }

  public Line analyzed(boolean analyzed) throws InvalidAnnotationException {
    setAnalyzed(analyzed);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Line)) {
      return false;
    }
    Line line = (Line) o;
    return number == line.number
        && comprehensibility == line.comprehensibility
        && analyzed == line.analyzed;
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, comprehensibility, analyzed);
  }

  @Override
  public String toString() {
    return "{"
        + " number='"
        + getNumber()
        + "'"
        + ", comprehensibility='"
        + getComprehensibility()
        + "'"
        + ", analyzed='"
        + isAnalyzed()
        + "'"
        + "}";
  }

  public boolean isComprehensibilityInPercent(double comprehensibility) {
    return comprehensibility >= 0 && comprehensibility <= 1;
  }

  public boolean areAnnotationsValid(double comprehensibility, boolean analyzed) {
    return true; // !(!analyzed && comprehensibility != 0);
  }
}
