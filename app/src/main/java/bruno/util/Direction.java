package bruno.util;

public enum Direction {
  UP(-1, 0) {
    @Override
    public Direction right() {
      return Direction.RIGHT;
    }

    @Override
    public Direction left() {
      return LEFT;
    }
  },
  DOWN(1, 0) {
    @Override
    public Direction right() {
      return Direction.LEFT;
    }

    @Override
    public Direction left() {
      return RIGHT;
    }
  },
  RIGHT(0, 1) {
    @Override
    public Direction right() {
      return Direction.DOWN;
    }

    @Override
    public Direction left() {
      return UP;
    }
  },
  LEFT(0, -1) {
    @Override
    public Direction right() {
      return Direction.UP;
    }

    @Override
    public Direction left() {
      return DOWN;
    }
  };

  public int row;
  public int col;

  Direction(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public abstract Direction right();

  public abstract Direction left();
}
