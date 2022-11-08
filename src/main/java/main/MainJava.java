package main;

import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

public class MainJava {
    public static void main(String[] args) {
        System.out.println("asd");
    
        var buffer = new Buffer();
        CompletableFuture.runAsync(() ->{
            currentThreadSleepPretty(2 * 1000);
            buffer.one = "one";
    
            currentThreadSleepPretty(2 * 1000);
            buffer.two = "two";
    
            currentThreadSleepPretty(2 * 1000);
            buffer.three = "three";
    
            currentThreadSleepPretty(2 * 1000);
            buffer.four = "four";
    
            currentThreadSleepPretty(2 * 1000);
            buffer.five = "five";
        });
        while (true) {
            System.out.print("\033[F\r" + buffer);
//            if (buffer.isAll()) break;
        }
    }
    
    private static void currentThreadSleepPretty(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    static class Buffer {
        String one;
        String two;
        String three;
        String four;
        String five;
        
        public boolean isAll() {
            return one != null
                    && two != null
                    && three != null
                    && four != null
                    && five != null;
        }
    
        @Override public String toString() {
            var y = System.out;
//            var ls = System.lineSeparator();
            var ls = "\n";
            return ls +
//                    + ((one == null) ? "null" : one) + ls
//                    + ((two == null) ? "null" : two) + ls
//                    + ((three == null) ? "null" : three) + ls
//                    + ((four == null) ? "null" : four) + ls
//                    + ((five == null) ? "null" : five) + ls
                     ((one == null) ? "null" : one) + " - "
                    + ((two == null) ? "null" : two) + " - "
                    + ((three == null) ? "null" : three) + " - "
                    + ((four == null) ? "null" : four) + " - "
                    + ((five == null) ? "null" : five)
                    ;
        }
    }
}

