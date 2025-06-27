package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    // 할 일 날씨로 검색
    Page<Todo> findTodosByWeatherContainingIgnoreCase(String weather, Pageable pageable);
    default Page<Todo> findTodosByWeather (String weather, Pageable pageable){
        return findTodosByWeatherContainingIgnoreCase(weather, pageable);
    }

    // 할 일 수정일로 기간 검색
    Page<Todo> findTodosByModifiedAtBetween(LocalDateTime modifiedAtStart,
                                            LocalDateTime modifiedAtEnd,
                                            Pageable pageable
    );
    default Page<Todo> findTodosByModifiedAt (LocalDateTime modifiedAtStart,
                                              LocalDateTime modifiedAtEnd,
                                              Pageable pageable) {
        return findTodosByModifiedAtBetween(modifiedAtStart, modifiedAtEnd, pageable);
    }
}
