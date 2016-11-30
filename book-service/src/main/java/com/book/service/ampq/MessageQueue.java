package com.book.service.ampq;

/**
 * Class containing the different queues used in the application.
 *
 * @author afernandez
 */
public class MessageQueue {

    public static final String DELAYED_EXCHANGE = "tasks.delayed.exchange";

    public static final String TASKS_QUEUE = "tasks.queue";
    public static final String TASKS_RESULT_QUEUE = "tasks.result.queue";
    public static final String TASKS_DELAYED_QUEUE = "tasks.delayed.queue";
}
