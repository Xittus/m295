package ch.m295.todorganizer;

import ch.m295.todorganizer.category.Category;
import ch.m295.todorganizer.category.CategoryRepository;
import ch.m295.todorganizer.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {


        private CategoryService categoryService;
        private final CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);

        private final Category categoryMock = mock(Category.class);

        @BeforeEach
        void setUp() {
            categoryService = new CategoryService(categoryRepositoryMock);
        }

        @Test
        void createDepartment() {
            when(categoryRepositoryMock.save(categoryMock)).thenReturn(categoryMock);
            categoryService.insertCategory(categoryMock);
            verify(categoryRepositoryMock, times(1)).save(any());
        }
    }

