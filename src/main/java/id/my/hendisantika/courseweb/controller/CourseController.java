package id.my.hendisantika.courseweb.controller;

import id.my.hendisantika.courseweb.event.CourseCreated;
import id.my.hendisantika.courseweb.mapper.CourseMapper;
import id.my.hendisantika.courseweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:43
 * To change this template use File | Settings | File Templates.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;
    private final CourseMapper mapper;
    private final Flux<CourseCreated> events;
}
