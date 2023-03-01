package hello.core.order;

import hello.core.Appconfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {


//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    /**
     * 서비스니까 추상화에만 의존하는 모습을 보이자.
     */
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        Appconfig appconfig = new Appconfig();
        memberService = appconfig.memberService();
        orderService = appconfig.orderService();
    }

    @Test
    void createOrder(){

        // 맴버 객체 생성
        Member member = new Member(1L,"memberA", Grade.VIP);

        // 회원 가입 먼저
        memberService.join(member);

        // 주문하기
        Order order = orderService.createOrder(member.getId(), "아이스크림", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
