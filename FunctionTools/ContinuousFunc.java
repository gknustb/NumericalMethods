package FunctionTools;

import java.util.Objects;

public class ContinuousFunc {
  private final MathFunction function;

  public ContinuousFunc(MathFunction func) {
    Objects.requireNonNull(func, "Must pass a function.");
    this.function = func;
  }

  private double absolute(double value) {
    return (value >= 0) ? value : -value;
  }

  // searches for A zero, not all zeroes
  public double searchZero(double lowerBound, double upperBound, double precision) {
    double searchedUpper = lowerBound + precision;

    for (double searchedLower = lowerBound; searchedLower < upperBound; searchedLower += precision) {
      if (function.calculateFunction(searchedLower) == 0)
        return searchedLower;

      searchedUpper = searchedLower + precision;

      double existanceTest = function.calculateFunction(searchedLower) * function.calculateFunction(searchedUpper);

      if (existanceTest < 0)
        return calculateZero(searchedLower, searchedUpper, 0, 0);
    }
    return Double.NaN;
  }

  private double calculateZero(double lowerBound, double upperBound, int iteration, double lastIteration) {
    double intervalMiddle = (lowerBound + upperBound) / 2.0;

    double residualError = absolute(function.calculateFunction(intervalMiddle));
    double Error = 0;

    double middleResult = function.calculateFunction(intervalMiddle);
    double lowerBoundResult = function.calculateFunction(lowerBound);

    if (iteration >= 1)
      Error = absolute(intervalMiddle - lastIteration);

    if (residualError < 0.00000001 && Error < 0.00000001)
      return intervalMiddle;

    if (lowerBoundResult * middleResult < 0)
      return calculateZero(lowerBound, intervalMiddle, iteration + 1, intervalMiddle);

    return calculateZero(intervalMiddle, upperBound, iteration + 1, intervalMiddle);
  }
}
