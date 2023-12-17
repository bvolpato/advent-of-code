package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.*;

public class Day07 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 7);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  abstract static class Node {

    abstract boolean isReady(Map<String, Node> wires);

    abstract int getValue(Map<String, Node> wires);
  }

  static class Literal extends Node {

    int value;

    public Literal(int value) {
      this.value = value;
    }

    @Override
    int getValue(Map<String, Node> wires) {
      return value;
    }

    @Override
    boolean isReady(Map<String, Node> wires) {
      return true;
    }

    @Override
    public String toString() {
      return "Literal:" + value;
    }
  }

  static class RefNode extends Node {

    String wire;

    public RefNode(String wire) {
      this.wire = wire;
    }

    @Override
    boolean isReady(Map<String, Node> wires) {
      return wires.get(wire).isReady(wires);
    }

    @Override
    int getValue(Map<String, Node> wires) {

      int value = wires.get(wire).getValue(wires);
      wires.put(wire, new Literal(value));

      return value;
    }

    @Override
    public String toString() {
      return "Ref:" + wire;
    }
  }

  static class OpNode extends Node {

    Node left;
    Node right;
    String op;

    public OpNode(Node left, Node right, String op) {
      this.left = left;
      this.right = right;
      this.op = op;
    }

    @Override
    boolean isReady(Map<String, Node> wires) {
      return left.isReady(wires) && (right == null || right.isReady(wires));
    }

    @Override
    int getValue(Map<String, Node> wires) {

      if (op.equals("AND")) {
        return (left).getValue(wires) & (right).getValue(wires);
      } else if (op.equals("OR")) {
        return (left).getValue(wires) | (right).getValue(wires);
      } else if (op.equals("NOT")) {
        return ~(left).getValue(wires);
      } else {
        throw new RuntimeException("Unknown op: " + op);
      }
    }

    @Override
    public String toString() {
      return "Op:" + op + " " + left + " " + right;
    }
  }

  static class ShiftNode extends Node {

    Node wire;
    int shift;
    boolean left;

    public ShiftNode(Node wire, int shift, boolean left) {
      this.wire = wire;
      this.shift = shift;
      this.left = left;
    }

    @Override
    boolean isReady(Map<String, Node> wires) {
      return wire.isReady(wires);
    }

    @Override
    int getValue(Map<String, Node> wires) {
      return left ? (wire).getValue(wires) << shift : (wire).getValue(wires) >> shift;
    }

    @Override
    public String toString() {
      return "Shift:" + wire + " " + shift + " " + left;
    }
  }

  private static int part1(String clipboard) {
    List<String> lines = AOCReader.lines(clipboard);
    Map<String, Node> wires = processWires(lines);
    return wires.get("a").getValue(wires);
  }

  private static Map<String, Node> processWires(List<String> lines) {
    Map<String, Node> wires = new HashMap<>();

    for (String line : lines) {

      String command = line.split(" -> ")[0];
      String wire = line.split(" -> ")[1];

      Node node;
      if (command.matches("\\d+")) {
        node = new Literal(Integer.parseInt(command));
      } else if (command.matches("[a-z]+")) {
        node = new RefNode(command);
      } else if (command.contains(" AND ")) {
        String[] split = command.split(" AND ");

        node = new OpNode(parseNode(split[0]), parseNode(split[1]), "AND");
      } else if (command.contains(" OR ")) {
        String[] split = command.split(" OR ");

        node = new OpNode(parseNode(split[0]), parseNode(split[1]), "OR");
      } else if (command.contains("NOT ")) {
        node = new OpNode(parseNode(command.split("NOT ")[1]), null, "NOT");
      } else if (command.contains("LSHIFT ")) {
        String[] split = command.split(" LSHIFT ");
        node = new ShiftNode(parseNode(split[0]), Integer.parseInt(split[1]), true);
      } else if (command.contains("RSHIFT ")) {
        String[] split = command.split(" RSHIFT ");
        node = new ShiftNode(parseNode(split[0]), Integer.parseInt(split[1]), false);
      } else {
        throw new RuntimeException("Unknown command: " + command);
      }
      wires.put(wire, node);
    }
    return wires;
  }

  private static Node parseNode(String node) {
    if (node.matches("\\d+")) {
      return new Literal(Integer.parseInt(node));
    }
    return new RefNode(node);
  }

  private static int part2(String clipboard) {
    int part1 = part1(clipboard);
    List<String> lines = AOCReader.lines(clipboard);
    Map<String, Node> wires = processWires(lines);

    wires.put("b", new Literal(part1));

    return wires.get("a").getValue(wires);
  }
}
