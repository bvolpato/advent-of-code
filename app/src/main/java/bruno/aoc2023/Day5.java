package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Day5 {

    static class XtoYSized {
        long destinationStart;
        long sourceStart;
        long length;

        public XtoYSized(long destinationStart, long sourceStart, long length) {
            this.destinationStart = destinationStart;
            this.sourceStart = sourceStart;
            this.length = length;
        }

        @Override
        public String toString() {
            return "{" +
                    "destination=" + destinationStart +
                    ", source=" + sourceStart +
                    ", length=" + length +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, UnsupportedFlavorException, InterruptedException {
        String clipboard = MyStringUtils.getFromClipboard();
        System.out.println("Clipboard content: " + clipboard);

        List<String> listLines = MyStringUtils.asListLines(clipboard);


        List<List<XtoYSized>> maps = new ArrayList<>();
        List<XtoYSized> currMap = null;

        long[] seeds = null;
        for (String line : listLines) {
            if (line.isEmpty()) continue;

            if (line.contains("seeds:")) {
                seeds = Arrays.stream(line.split(":")[1].trim().split("\\s+")).mapToLong(Long::valueOf).toArray();
            } else if (line.contains("map:")) {

                if (currMap != null) {
                    maps.add(currMap);
                }

                currMap = new ArrayList<>();
            } else {

                long[] mapArray = Arrays.stream(line.trim().split("\\s+")).mapToLong(Long::valueOf).toArray();
                currMap.add(new XtoYSized(mapArray[0], mapArray[1], mapArray[2]));

            }
        }

        maps.add(currMap);

        System.out.println("Seeds: " + Arrays.toString(seeds));

        AtomicLong min = new AtomicLong(Long.MAX_VALUE);
        for (int i = 0; i < seeds.length; i += 2) {
            long seedStart = seeds[i];
            long quantTot = seeds[i + 1];

            System.out.println("Seed start: " + seedStart + ", quantity: " + quantTot);

            for (long seedI = seedStart; seedI <= seedStart + quantTot; seedI++) {


                long finalSeedI = seedI;
                long seed = finalSeedI;

                for (List<XtoYSized> map : maps) {

                    for (XtoYSized mapItem : map) {
                        if (seed >= mapItem.sourceStart && seed <= mapItem.sourceStart + mapItem.length) {
                            seed -= mapItem.sourceStart - mapItem.destinationStart;
                            break;
                        }
                    }
                }

                if (seed < min.get()) {
                    min.set(seed);
                }
            }
        }
        System.out.println(min);
    }
}
