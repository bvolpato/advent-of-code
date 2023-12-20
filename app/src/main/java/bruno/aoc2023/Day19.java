package bruno.aoc2023;

import bruno.util.AOCReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 19: Aplenty ---
 *
 * <p>The Elves of Gear Island are thankful for your help and send you on your way. They even have a
 * hang glider that someone stole from Desert Island; since you're already going that direction, it
 * would help them a lot if you would use it to get down there and return it to them.
 *
 * <p>As you reach the bottom of the relentless avalanche of machine parts, you discover that
 * they're already forming a formidable heap. Don't worry, though - a group of Elves is already here
 * organizing the parts, and they have a system.
 *
 * <p>To start, each part is rated in each of four categories:
 *
 * <p>x: Extremely cool looking m: Musical (it makes a noise when you hit it) a: Aerodynamic s:
 * Shiny
 *
 * <p>Then, each part is sent through a series of workflows that will ultimately accept or reject
 * the part. Each workflow has a name and contains a list of rules; each rule specifies a condition
 * and where to send the part if the condition is true. The first rule that matches the part being
 * considered is applied immediately, and the part moves on to the destination described by the
 * rule. (The last rule in each workflow has no condition and always applies if reached.)
 *
 * <p>Consider the workflow ex{x>10:one,m<20:two,a>30:R,A}. This workflow is named ex and contains
 * four rules. If workflow ex were considering a specific part, it would perform the following steps
 * in order:
 *
 * <p>Rule "x>10:one": If the part's x is more than 10, send the part to the workflow named one.
 * Rule "m<20:two": Otherwise, if the part's m is less than 20, send the part to the workflow named
 * two. Rule "a>30:R": Otherwise, if the part's a is more than 30, the part is immediately rejected
 * (R). Rule "A": Otherwise, because no other rules matched the part, the part is immediately
 * accepted (A).
 *
 * <p>If a part is sent to another workflow, it immediately switches to the start of that workflow
 * instead and never returns. If a part is accepted (sent to A) or rejected (sent to R), the part
 * immediately stops any further processing.
 *
 * <p>The system works, but it's not keeping up with the torrent of weird metal shapes. The Elves
 * ask if you can help sort a few parts and give you the list of workflows and some part ratings
 * (your puzzle input). For example:
 *
 * <p>px{a<2006:qkq,m>2090:A,rfg} pv{a>1716:R,A} lnx{m>1548:A,A} rfg{s<537:gd,x>2440:R,A}
 * qs{s>3448:A,lnx} qkq{x<1416:A,crn} crn{x>2662:A,R} in{s<1351:px,qqz} qqz{s>2770:qs,m<1801:hdj,R}
 * gd{a>3333:R,R} hdj{m>838:A,pv}
 *
 * <p>{x=787,m=2655,a=1222,s=2876} {x=1679,m=44,a=2067,s=496} {x=2036,m=264,a=79,s=2244}
 * {x=2461,m=1339,a=466,s=291} {x=2127,m=1623,a=2188,s=1013}
 *
 * <p>The workflows are listed first, followed by a blank line, then the ratings of the parts the
 * Elves would like you to sort. All parts begin in the workflow named in. In this example, the five
 * listed parts go through the following workflows:
 *
 * <p>{x=787,m=2655,a=1222,s=2876}: in -> qqz -> qs -> lnx -> A {x=1679,m=44,a=2067,s=496}: in -> px
 * -> rfg -> gd -> R {x=2036,m=264,a=79,s=2244}: in -> qqz -> hdj -> pv -> A
 * {x=2461,m=1339,a=466,s=291}: in -> px -> qkq -> crn -> R {x=2127,m=1623,a=2188,s=1013}: in -> px
 * -> rfg -> A
 *
 * <p>Ultimately, three parts are accepted. Adding up the x, m, a, and s rating for each of the
 * accepted parts gives 7540 for the part with x=787, 4623 for the part with x=2036, and 6951 for
 * the part with x=2127. Adding all of the ratings for all of the accepted parts gives the sum total
 * of 19114.
 *
 * <p>Sort through all of the parts you've been given; what do you get if you add together all of
 * the rating numbers for all of the parts that ultimately get accepted?
 *
 * <p>Your puzzle answer was 383682. --- Part Two ---
 *
 * <p>Even with your help, the sorting process still isn't fast enough.
 *
 * <p>One of the Elves comes up with a new plan: rather than sort parts individually through all of
 * these workflows, maybe you can figure out in advance which combinations of ratings will be
 * accepted or rejected.
 *
 * <p>Each of the four ratings (x, m, a, s) can have an integer value ranging from a minimum of 1 to
 * a maximum of 4000. Of all possible distinct combinations of ratings, your job is to figure out
 * which ones will be accepted.
 *
 * <p>In the above example, there are 167409079868000 distinct combinations of ratings that will be
 * accepted.
 *
 * <p>Consider only your list of workflows; the list of part ratings that the Elves wanted you to
 * sort is no longer relevant. How many distinct combinations of ratings will be accepted by the
 * Elves' workflows?
 *
 * <p>Your puzzle answer was 117954800808317.
 */
public class Day19 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2023, 19);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part 1: " + part1(clipboard));
    System.out.println("Part 2: " + part2(clipboard));
  }

  public static int part1(String clipboard) {

    String[] split = clipboard.split("\n\n");
    Map<String, Workflow> workflowMap = parseWorkflows(split[0].split("\n"));
    List<Part> partList = parseParts(split[1].split("\n"));

    int ans = 0;

    for (Part part : partList) {
      Workflow workflow = workflowMap.get("in");

      boolean accepted = false;

      while (workflow != null) {
        System.out.println("Part: " + part + " - Workflow: " + workflow.name);
        String destination = workflow.destination;

        for (Rule rule : workflow.rules) {
          if (rule.matches(part)) {
            destination = rule.destination;
            break;
          }
        }

        if (destination.equals("A")) {
          accepted = true;
          break;
        } else if (destination.equals("R")) {
          accepted = false;
          break;
        } else {
          workflow = workflowMap.get(destination);
        }
      }

      if (accepted) {
        ans += part.x.max + part.m.max + part.a.max + part.s.max;
      }
    }

    return ans;
  }

  private static List<Part> parseParts(String[] parts) {
    List<Part> partList = new ArrayList<>();
    for (String partStr : parts) {
      partList.add(Part.parse(partStr));
    }
    return partList;
  }

  private static Map<String, Workflow> parseWorkflows(String[] workflows) {
    Map<String, Workflow> workflowMap = new HashMap<>();
    for (String workflow : workflows) {
      Workflow w = Workflow.parse(workflow);
      workflowMap.put(w.name, w);

      System.out.println(w);
    }
    return workflowMap;
  }

  public static BigInteger part2(String clipboard) {
    String[] split = clipboard.split("\n\n");
    Map<String, Workflow> workflowMap = parseWorkflows(split[0].split("\n"));

    Part part =
        new Part(new Range(1, 4001), new Range(1, 4001), new Range(1, 4001), new Range(1, 4001));

    return dfs(0, part, "in", workflowMap);
  }

  private static BigInteger dfs(
      int level, Part part, String in, Map<String, Workflow> workflowMap) {

    if (in.equals("R")) {
      return BigInteger.ZERO;
    }
    if (in.equals("A")) {
      return BigInteger.valueOf(part.x.max - part.x.min)
          .multiply(BigInteger.valueOf(part.m.max - part.m.min))
          .multiply(BigInteger.valueOf(part.a.max - part.a.min))
          .multiply(BigInteger.valueOf(part.s.max - part.s.min));
    }

    BigInteger ans = BigInteger.ZERO;
    part = new Part(part);

    Workflow workflow = workflowMap.get(in);
    for (Rule rule : workflow.rules) {

      Pattern pattern = Pattern.compile("([xmas])([<>])(\\d+)");

      Matcher matcher = pattern.matcher(rule.condition);
      if (!matcher.matches()) {
        throw new RuntimeException("Invalid condition: " + rule.condition);
      }

      String field = matcher.group(1);
      String operator = matcher.group(2);
      int value = Integer.parseInt(matcher.group(3));

      Range letterRange =
          switch (field) {
            case "x" -> part.x;
            case "m" -> part.m;
            case "a" -> part.a;
            case "s" -> part.s;
            default -> throw new RuntimeException("Invalid field: " + field);
          };

      Range destRange;
      Range afterRange;
      if (operator.equals(">")) {
        destRange = new Range(value + 1, letterRange.max);
        afterRange = new Range(letterRange.min, value + 1);
      } else if (operator.equals("<")) {
        destRange = new Range(letterRange.min, value);
        afterRange = new Range(value, letterRange.max);
      } else {
        throw new RuntimeException("Invalid operator: " + operator);
      }

      if (field.equals("x")) {
        part.x = destRange;
      } else if (field.equals("m")) {
        part.m = destRange;
      } else if (field.equals("a")) {
        part.a = destRange;
      } else if (field.equals("s")) {
        part.s = destRange;
      }

      ans = ans.add(dfs(level + 1, part, rule.destination, workflowMap));

      if (field.equals("x")) {
        part.x = afterRange;
      } else if (field.equals("m")) {
        part.m = afterRange;
      } else if (field.equals("a")) {
        part.a = afterRange;
      } else if (field.equals("s")) {
        part.s = afterRange;
      }
    }

    ans = ans.add(dfs(level + 1, part, workflow.destination, workflowMap));

    return ans;
  }
}

class Range {
  int min;
  int max;

  public Range(Range range) {
    this(range.min, range.max);
  }

  public Range(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public boolean contains(int value) {
    return value >= min && value <= max;
  }

  @Override
  public String toString() {
    return "(" + min + ", " + max + ')';
  }
}

class Workflow {
  String name;
  List<Rule> rules;
  String destination;

  public Workflow(String name, List<Rule> rules, String destination) {
    this.name = name;
    this.rules = rules;
    this.destination = destination;
  }

  // Parse line in the format
  // workflow_name{ruleA>valueA:destination_ruleA,ruleB<valueB:destinationRuleB,destinationWorkflow}
  public static Workflow parse(String line) {
    String[] split = line.split("\\{");
    String name = split[0];
    String[] rules = split[1].replace("}", "").split(",");
    List<Rule> ruleList = new ArrayList<>();
    for (int i = 0; i < rules.length - 1; i++) {
      String[] splitRule = rules[i].split(":");
      String condition = splitRule[0];
      String destination = splitRule[1];
      ruleList.add(new Rule(condition, destination));
    }

    String destination = rules[rules.length - 1];
    return new Workflow(name, ruleList, destination);
  }

  @Override
  public String toString() {
    return "Workflow{"
        + "name='"
        + name
        + '\''
        + ", rules="
        + rules
        + ", destination='"
        + destination
        + '\''
        + '}';
  }
}

class Rule {
  String condition;
  String destination;

  public Rule(String condition, String destination) {
    this.condition = condition;
    this.destination = destination;
  }

  public boolean matches(Part part) {

    Pattern pattern = Pattern.compile("([xmas])([<>])(\\d+)");

    Matcher matcher = pattern.matcher(condition);
    if (!matcher.matches()) {
      throw new RuntimeException("Invalid condition: " + condition);
    }

    String field = matcher.group(1);
    String operator = matcher.group(2);
    int value = Integer.parseInt(matcher.group(3));

    int valueField =
        switch (field) {
          case "x" -> part.x.max;
          case "m" -> part.m.max;
          case "a" -> part.a.max;
          case "s" -> part.s.max;
          default -> throw new RuntimeException("Invalid field: " + field);
        };

    switch (operator) {
      case ">":
        return valueField > value;
      case "<":
        return valueField < value;
      default:
        throw new RuntimeException("Invalid operator: " + operator);
    }
  }

  @Override
  public String toString() {
    return "Rule{"
        + "condition='"
        + condition
        + '\''
        + ", destination='"
        + destination
        + '\''
        + '}';
  }
}

class Part {
  Range x;
  Range m;
  Range a;
  Range s;

  public Part(Part part) {
    this(new Range(part.x), new Range(part.m), new Range(part.a), new Range(part.s));
  }

  public Part(int x, int m, int a, int s) {
    this.x = new Range(x, x);
    this.m = new Range(m, m);
    this.a = new Range(a, a);
    this.s = new Range(s, s);
  }

  public Part(Range x, Range m, Range a, Range s) {
    this.x = x;
    this.m = m;
    this.a = a;
    this.s = s;
  }

  public static Part parse(String line) {
    String[] split = line.replace("{", "").replace("}", "").split(",");
    int x = Integer.parseInt(split[0].split("=")[1]);
    int m = Integer.parseInt(split[1].split("=")[1]);
    int a = Integer.parseInt(split[2].split("=")[1]);
    int s = Integer.parseInt(split[3].split("=")[1]);
    return new Part(x, m, a, s);
  }

  @Override
  public String toString() {
    return "Part{" + "x=" + x + ", m=" + m + ", a=" + a + ", s=" + s + '}';
  }
}
