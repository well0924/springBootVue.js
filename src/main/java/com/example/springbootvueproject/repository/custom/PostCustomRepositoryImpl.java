package com.example.springbootvueproject.repository.custom;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.QPost;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
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
public class PostCustomRepositoryImpl implements PostCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
    
    //게시글 목록
    @Override
    public Page<PostResponse> postList(Pageable pageable) {
        List<PostResponse>postResponseList = new ArrayList<>();
        List<Post>list = jpaQueryFactory.select(QPost.post).from(QPost.post).fetch();

        int pageCount =jpaQueryFactory.select(QPost.post).from(QPost.post).fetch().size();

        for(Post post:list){
            PostResponse response = new PostResponse(post.getId(),
                    post.getTitle(),
                    post.getContents(),
                    post.getAuthor(),
                    post.getCreatedTime(),
                    post.getUpdatedTime());

            postResponseList.add(response);
        }
        return new PageImpl<>(postResponseList,pageable,pageCount);
    }
    
    //게시글 검색
    @Override
    public Page<PostResponse> findSearchAll(SearchType searchType, String keyword, Pageable pageable) {
        List<PostResponse>postResponseList = new ArrayList<>();
        List<Post>postList = postSearchList(searchType,keyword,pageable);
        int searchCount = searchResultCount(searchType,keyword,pageable);

        for(Post post:postList){
            PostResponse response = new PostResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getContents(),
                    post.getAuthor(),
                    post.getCreatedTime(),
                    post.getUpdatedTime());
            postResponseList.add(response);
        }

        return new PageImpl<>(postResponseList,pageable,searchCount);
    }

    private List<Post> postSearchList(SearchType searchType,String searchVal,Pageable pageable){
        return jpaQueryFactory
                .select(QPost.post)
                .from(QPost.post)
                .where(switch (searchType){
                    //switch 문 (java 14)
                    case t -> postTitleEq(searchVal);
                    case c -> postContentsEq(searchVal);
                    case w -> postAuthorEq(searchVal);
                    case i, n, e -> null;
                    case all -> postContentsEq(searchVal).and(postContentsEq(searchVal)).and(postAuthorEq(searchVal));
                })
                .orderBy(getAllOrderSpecifiers(pageable.getSort())
                        .toArray(OrderSpecifier[]::new))
                .distinct()
                .fetch();
    }

    private int searchResultCount(SearchType searchType,String searchVal,Pageable pageable){
        return jpaQueryFactory
                .select(QPost.post.count())
                .from(QPost.post)
                .where(switch (searchType){
                    //switch 문 (java 14)
                    case t -> postTitleEq(searchVal);
                    case c -> postContentsEq(searchVal);
                    case w -> postAuthorEq(searchVal);
                    case i, n, e -> null;
                    case all -> postContentsEq(searchVal).and(postContentsEq(searchVal)).and(postAuthorEq(searchVal));
                })
                .orderBy(getAllOrderSpecifiers(pageable.getSort())
                        .toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .size();
    }

    BooleanBuilder postContentsEq(String searchVal){
        return nullSafeBuilder(()-> QPost.post.contents.contains(searchVal));
    }


    BooleanBuilder postTitleEq(String searchVal){
        return nullSafeBuilder(()-> QPost.post.title.contains(searchVal));
    }


    BooleanBuilder postAuthorEq(String searchVal){
        return nullSafeBuilder(()-> QPost.post.author.contains(searchVal));
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

            PathBuilder<Post> orderByExpression =  new PathBuilder<>(Post.class,"post");

            orders.add(new OrderSpecifier(direction,orderByExpression.get(prop)));
        });

        return orders;
    }
}
