package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean1 = " + prototypeBean1);
        System.out.println("PrototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        //ac.close(); //안뜬다. 말그대로 프로토타입이라 생성해주고 버린다 따로 우리가 클로즈해야한다.

        //셀프 close
        prototypeBean1.destroy();
        prototypeBean2.destroy();

    }
    @Scope("prototype")
    //prototypeBeanFind()의 첫줄에서 파라미터로 P  rototypeBean.class를 넣으면 해당 클래스를 자동으로 컴포넌트스캔 대상자로 인식
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypenBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
