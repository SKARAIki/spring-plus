package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryQueryImpl implements TodoRepositoryQuery {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {

        return Optional
                .ofNullable(jpaQueryFactory
                        .selectFrom(todo) // Q.todo에서 가져옴
                        .leftJoin(todo.user, user) // todo.user 를 기준 삼고 fetchjoin 한다?
                        .fetchJoin().where(todo.id.eq(todoId)) // todo.id가 매개변수 todoId와 동일한지
                        .fetchOne() // 하나만 즉시실행
                );
    }
}
