package hello.core.discount;

import hello.core.annotationpackage.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // 함수 이름은 자동으로 rateDiscountPolicy 지정된다
@MainDiscountPolicy //@Quailfier("MainDiscountPolicy") 와 같다. 하지만 직접 생성했기때문에 오타방지가능하다
public class RateDiscountPolicy implements  DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
