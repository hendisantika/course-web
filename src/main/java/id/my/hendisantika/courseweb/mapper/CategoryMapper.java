package id.my.hendisantika.courseweb.mapper;

import id.my.hendisantika.courseweb.course.Category;
import id.my.hendisantika.courseweb.dto.CategoryDto;
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
 * Time: 06:34
 * To change this template use File | Settings | File Templates.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryDto entityToDto(Category category) {
        log.debug("Convert 'Category' entity to DTO. ['id': {}, 'title', {}]",
                category.getId(), category.getTitle());

        CategoryDto dto = modelMapper.map(category, CategoryDto.class);

        log.debug("DTO '{}' initialized with id {}", dto.getTitle(), dto.getId());
        return dto;
    }
}
