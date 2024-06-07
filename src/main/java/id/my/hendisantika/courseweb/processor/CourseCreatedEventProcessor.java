package id.my.hendisantika.courseweb.processor;

import id.my.hendisantika.courseweb.event.CourseCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:38
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
public class CourseCreatedEventProcessor
        implements ApplicationListener<CourseCreated>,
        Consumer<FluxSink<CourseCreated>> {

    private final Executor executor;
    private final BlockingQueue<CourseCreated> queue = new LinkedBlockingQueue<>();

    CourseCreatedEventProcessor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void onApplicationEvent(CourseCreated event) {
        this.queue.offer(event);
    }

    @Override
    public void accept(FluxSink<CourseCreated> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    CourseCreated event = queue.take();
                    sink.next(event);
                } catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}
