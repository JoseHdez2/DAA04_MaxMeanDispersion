package main;

public class MyTimer {
    private double timeStart, timeStop, timeCount;
    boolean running;
    
    public MyTimer(){
        timeCount = 0;
        running = false;
    }
    
    /**
     * Starts measuring time.
     * Ignores call if it is already running.
     * PENDING should throw Exception, otherwise unwanted behavior will occur.
     */
    public void start(){
        if (running) return;
        else {
           timeStart = System.nanoTime();
           running = true;
        }
    }
    
    /**
     * Will stop, start and return time measured when stopping.
     * Returns time for convenience. (HACK?)
     */
    public Double restart(){
        stop();
        start();
        return getTimeCountAsSeconds();
    }
    
    /**
     * Stops running, and updates the measured time.
     */
    public void stop(){
        timeStop = System.nanoTime();
        running = false;
        timeCount = timeStop - timeStart;
    }
    
    /**
     * @return Time measured, as nanoseconds.
     */
    public Double getTimeCount(){
        return timeCount;
    }
    
    /**
     * @return Time measured, as seconds.
     */
    public Double getTimeCountAsSeconds(){
        return timeCount / 1e9d;
    }
}
