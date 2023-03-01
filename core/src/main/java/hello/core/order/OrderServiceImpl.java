package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository ; // 회원 DB에 접속용
    private DiscountPolicy discountPolicy;  // 인터페이스에만 의존하도록 코드를 변경함.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);// 멤버를 찾고
        int discountPrice = discountPolicy.discount(findMember, itemPrice);// 멤버의 등급에 따라 할인율 확인
        /**
         * 서비스impl은 멤버,할인에 대해 아무것도 몰라~ 너네가 알아서해줘~ 하는 느낌임.
         * 그래서 할인 정책이 바뀌더라도 OrderService와 관련되선 고칠 필요가 없음. 이게 설계가 아주 잘 된 것이라고 볼 수 있다.
         */
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
