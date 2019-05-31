package app.iterator.concurrent;

/*
 * Copyright (c) 2001, 2005. Steven J. Metsker.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 *
 * Please use this software as you wish with the sole
 * restriction that you may not claim that you wrote it.
 */

import java.util.*;

/**
 * Show a "synchronized" list with iteration over the list.
 */
public class ShowConcurrentMutex implements Runnable {
    private List list;

    protected static List upMachineNames() {
        return new ArrayList(Arrays.asList(new String[] { "Mixer1201",
                "ShellAssembler1301", "StarPress1401", "UnloadBuffer1501" }));
    }

    public static void main(String[] args) {
        new ShowConcurrentMutex().go();
    }

    protected void go() {
        System.out.println("This version synchronizes properly:");
        list = Collections.synchronizedList(upMachineNames());
        synchronized (list) {
            display();
        }
    }

    private void display() {
        for (int i = 0; i < list.size(); i++) {
            if (i == 1) { // simulate wake-up
                new Thread(this).start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
            System.out.println(list.get(i));
        }
    }

    /**
     * * Insert an element in the list, in a separate thread.
     */
    public void run() {
        synchronized (list) {
            list.add(0, "Fuser1101");
        }
    }
}