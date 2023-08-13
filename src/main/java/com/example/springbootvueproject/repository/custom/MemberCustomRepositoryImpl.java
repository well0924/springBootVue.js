package com.example.springbootvueproject.repository.custom;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.QMember;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public MemberCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<MemberResponse> memberList(Pageable pageable) {
        List<MemberResponse>responses = new ArrayList<>();

        List<Member>memberList = jpaQueryFactory
                .select(QMember.member)
                .from(QMember.member)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        int count = jpaQueryFactory
                .select(QMember.member)
                .from(QMember.member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .size();

        for(Member member: memberList){
            MemberResponse response = new MemberResponse(member.getId(),
                    member.getUserName(),
                    member.getPassword(),
                    member.getUserAge(),
                    member.getUserEmail(),
                    member.getCreatedTime(),
                    member.getUpdatedTime());
            responses.add(response);
        }
        return new PageImpl<>(responses,pageable,count);
    }

    @Override
    public Page<MemberResponse>memberSearch(SearchType searchType, String keyword, Pageable pageable) {
        List<MemberResponse>responses = new ArrayList<>();
        List<Member>list = memberList(searchType,keyword,pageable);
        int count= searchCount(searchType,keyword,pageable);
        for(Member member:list){
            MemberResponse response = new MemberResponse(member.getId(),
                    member.getUserName(),
                    member.getPassword(),
                    member.getUserAge(),
                    member.getUserEmail(),
                    member.getCreatedTime(),
                    member.getUpdatedTime());
            responses.add(response);
        }
        return new PageImpl<>(responses,pageable,count);
    }
    private List<Member>memberList(SearchType searchType,String keyword,Pageable pageable){
        return jpaQueryFactory
                .select(QMember.member)
                .from(QMember.member)
                .where(switch (searchType){
                    case t,c,w,i -> null;
                    case n -> memberNameEq(keyword);
                    case e -> memberEmailEq(keyword);
                    case all -> memberEmailEq(keyword).or(memberNameEq(keyword));
                }).orderBy(getAllOrderSpecifiers(pageable.getSort())
                        .toArray(OrderSpecifier[]::new))
                .distinct()
                .fetch();
    }
    private int searchCount(SearchType searchType,String keyword,Pageable pageable){
        return jpaQueryFactory
                .select(QMember.member)
                .from(QMember.member)
                .where(switch (searchType){
                    case t,c,w,i -> null;
                    case n -> memberNameEq(keyword);
                    case e -> memberEmailEq(keyword);
                    case all -> memberEmailEq(keyword).or(memberNameEq(keyword));
                }).orderBy(getAllOrderSpecifiers(pageable.getSort())
                        .toArray(OrderSpecifier[]::new))
                .distinct()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch()
                .size();
    }
    BooleanBuilder memberNameEq(String searchVal){
        return nullSafeBuilder(()-> QMember.member.userName.contains(searchVal));
    }

    BooleanBuilder memberEmailEq(String searchVal){
        return nullSafeBuilder(()-> QMember.member.userEmail.contains(searchVal));
    }

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Sort sort) {
        List<OrderSpecifier>orders =  new ArrayList<>();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            String prop = order.getProperty();

            PathBuilder<Member> orderByExpression =  new PathBuilder<>(Member.class,"member1");

            orders.add(new OrderSpecifier(direction,orderByExpression.get(prop)));
        });

        return orders;
    }
}
