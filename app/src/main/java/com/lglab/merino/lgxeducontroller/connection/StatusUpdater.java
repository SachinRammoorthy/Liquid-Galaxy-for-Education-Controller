package com.lglab.merino.lgxeducontroller.connection;

public class StatusUpdater implements Runnable {
    private volatile boolean cancelled;

    private LGConnectionManager lgConnectionManager;

    public StatusUpdater(LGConnectionManager lgConnectionManager) {
        this.lgConnectionManager = lgConnectionManager;
    }

    public void run() {
        try {
            while (!cancelled) {
                lgConnectionManager.tick();
                Thread.sleep(200l); //TICKS every 200ms
            }
        } catch (InterruptedException e) {

        }
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
