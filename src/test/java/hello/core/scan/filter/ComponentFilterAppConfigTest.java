package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }


    // @ComponentScan은 @Component만 스캔한다. 여기서 MyIncludeComponent와 MyExcludeComponent는 @Component를 하지않았기때문에
    // 애초에 스캔 대상에 포함되지않는다. 하지만 MyIncludeComponent와 MyExcludeComponent나 BeanA나 BeanB에 @COmponent를 등록하면
    // 스캔대상이 된다.
    // includeFilters는 새로운 변수명이 아니라 함수이다. (Annotation은 이런 동작 방식인건가..)
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
