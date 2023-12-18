package bruno.aoc2015;

import bruno.util.AOCReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 12: JSAbacusFramework.io ---
 *
 * <p>Santa's Accounting-Elves need help balancing the books after a recent order. Unfortunately,
 * their accounting software uses a peculiar storage format. That's where you come in.
 *
 * <p>They have a JSON document which contains a variety of things: arrays ([1,2,3]), objects
 * ({"a":1, "b":2}), numbers, and strings. Your first job is to simply find all of the numbers
 * throughout the document and add them together.
 *
 * <p>For example:
 *
 * <p>[1,2,3] and {"a":2,"b":4} both have a sum of 6. [[[3]]] and {"a":{"b":4},"c":-1} both have a
 * sum of 3. {"a":[-1,1]} and [-1,{"a":1}] both have a sum of 0. [] and {} both have a sum of 0.
 *
 * <p>You will not encounter any strings containing numbers.
 *
 * <p>What is the sum of all numbers in the document?
 *
 * <p>Your puzzle answer was 191164. --- Part Two ---
 *
 * <p>Uh oh - the Accounting-Elves have realized that they double-counted everything red.
 *
 * <p>Ignore any object (and all of its children) which has any property with the value "red". Do
 * this only for objects ({...}), not arrays ([...]).
 *
 * <p>[1,2,3] still has a sum of 6. [1,{"c":"red","b":2},3] now has a sum of 4, because the middle
 * object is ignored. {"d":"red","e":[1,2,3,4],"f":5} now has a sum of 0, because the entire
 * structure is ignored. [1,"red",5] has a sum of 6, because "red" in an array has no effect.
 *
 * <p>Your puzzle answer was 87842.
 */
public class Day12 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 12);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static int part1(String clipboard) {

    int finalNum = 0;

    Pattern pattern = Pattern.compile("-?\\d+");
    Matcher matcher = pattern.matcher(clipboard);
    while (matcher.find()) {
      finalNum += Integer.parseInt(matcher.group());
    }

    return finalNum;
  }

  private static int part2(String clipboard) {

    Gson gson = new GsonBuilder().create();
    JsonElement element = gson.fromJson(clipboard, JsonElement.class);
    System.out.println("Clipboard content: " + element);

    AtomicInteger sum = new AtomicInteger(0);

    sumUp(element, sum);

    return sum.get();
  }

  public static void sumUp(JsonElement element, AtomicInteger sum) {
    if (element.isJsonArray()) {
      for (JsonElement jsonElement : element.getAsJsonArray()) {
        sumUp(jsonElement, sum);
      }
    } else if (element.isJsonObject()) {
      JsonObject jsonObject = element.getAsJsonObject();
      if (jsonObject.entrySet().stream().anyMatch(e -> e.getValue().toString().equals("\"red\""))) {
        return;
      }
      for (Map.Entry<String, JsonElement> jsonElement : jsonObject.entrySet()) {
        sumUp(jsonElement.getValue(), sum);
      }
    } else if (element.isJsonPrimitive()) {
      if (element.getAsJsonPrimitive().isNumber()) {
        sum.addAndGet(element.getAsInt());
      }
    }
  }
}
