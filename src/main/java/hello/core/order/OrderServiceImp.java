package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImp implements OrderService {
    //감독은 역할에만 신경쓸수있고 실행하는 책임만 진다
    private final MemberRepository memberRepository; //로미오 역할
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy; // 줄리엣 역할
    //final은 생성자에서만 값을 설정할수있고 그이후에는 못바꾼다.
    // 또는 private final MemberRepository memberRepository = new MemoryMemberRepository로 바꿀수있
    // 그래서 수정자 주입이나 다른 주입방식은 final를 사용하면 안된다

    /*
    @RequiredArgsConstructo로 인해 필요가 없어졌다
    자동으로 생성하기때문이다
    @Autowired // <- 생성자는 필요없다
    */
    public OrderServiceImp(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);    }
    //테스트 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
