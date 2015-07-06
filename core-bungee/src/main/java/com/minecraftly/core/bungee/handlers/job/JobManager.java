package com.minecraftly.core.bungee.handlers.job;

import com.minecraftly.core.bungee.handlers.job.queue.JobQueue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Keir on 28/06/2015.
 */
public class JobManager {

    private final Map<Class<? extends JobQueue>, JobQueue<?>> jobQueues = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends JobQueue<?>> T getJobQueue(Class<T> clazz) {
        return (T) jobQueues.get(clazz);
    }

    public void addJobQueue(JobQueue<?> jobQueue) {
        Class<? extends JobQueue> clazz = jobQueue.getClass();

        if (jobQueues.containsKey(jobQueue.getClass())) {
            throw new UnsupportedOperationException("Attempted to double register class: " + clazz.getName());
        }

        jobQueues.put(clazz, jobQueue);
    }

    public void removeJobQueue(Class<? extends JobQueue> clazz) {
        jobQueues.remove(clazz);
    }

}
