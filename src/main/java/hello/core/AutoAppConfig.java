package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class
AutoAppConfig {
    //기존 @Component 등록된 MemoryMemberRepository class 와 일부러 충돌을 내기 위해 완전 똑같은 Bean 생성
    // 위 COmponent의 Bean 이름은 앞문자를 소문자로 바꾼 memoryMemberRepository이다
    //오버라이딩되어 밑에 있는것이 우선처리된다
    /*
    @Bean(name="memoryMemberRepository") //name= "memoryMemberRepository"설정을 안하면 bean이름은 memberRepository
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
     */
}
