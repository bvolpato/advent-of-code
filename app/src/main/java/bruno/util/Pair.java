package bruno.util;

public class Pair<T1, T2> {
  T1 first;
  T2 second;

  public Pair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }

  public T1 getFirst() {
    return first;
  }

  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }

  public void setSecond(T2 second) {
    this.second = second;
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return first.equals(other.first) && second.equals(other.second);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return 31 * first.hashCode() + second.hashCode();
  }
}
