package id.my.hendisantika.courseweb.mapper;

import id.my.hendisantika.courseweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:35
 * To change this template use File | Settings | File Templates.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CourseMapper {
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

}
