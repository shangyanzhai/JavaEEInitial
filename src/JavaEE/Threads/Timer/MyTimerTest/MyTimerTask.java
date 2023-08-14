package JavaEE.Threads.Timer.MyTimerTest;

// 任务要被放入优先级队列，所以任务需要具备比较能力
// 优先级队列天生是小堆
// 所以时间戳更小的，任务比较级更小
public abstract class MyTimerTask implements Runnable, Comparable<MyTimerTask> {
    // 在属性中保存任务是什么时候要被执行（时刻：时间戳）
    private long runAtTimestamp;
    private boolean isLoop;
    private long period;

    public void setRunAtTimestamp(long runAtTimestamp) {
        this.runAtTimestamp = runAtTimestamp;
    }

    public long getRunAtTimestamp() {
        return runAtTimestamp;
    }

    @Override
    public int compareTo(MyTimerTask o) {
        if (this.runAtTimestamp < o.runAtTimestamp) {
            return -1;
        } else if (this.runAtTimestamp == o.runAtTimestamp) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}