package com.devuoon.backboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.devuoon.backboard.entity.Board;
import com.devuoon.backboard.entity.Member;
import com.devuoon.backboard.entity.Reply;
import com.devuoon.backboard.repository.BoardRepository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class BoardService {        
    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    // 페이징 되는 리스트 메서드
    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));   // pageSize 동적으로 변경 가능
        return this.boardRepository.findAll(pageable);
    }

    // 검색 추가 메서드
    public Page<Board> getList(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); 
        
        // Specification<Board> spec = searchBoard(keyword);
        // return this.boardRepository.findAll(spec, pageable); // 쿼리 생성로직 만들어서
        return this.boardRepository.findAllByKeyword(keyword, pageable);
    }

    public Board getBoard(Long bno) throws NotFoundException {
        Optional<Board> board = this.boardRepository.findById(bno);
        if (board.isPresent()) { // 데이터가 존재하면
            return board.get();
        } else {
            throw new NotFoundException("board not found");
        }
    }

    // 24.06. 
    public void setBoard(String title, String content, Member writer) {
        // 빌더로 생성한 객체
        Board board = Board.builder().title(title).content(content).createDate(LocalDateTime.now()).build();
        board.setWriter(writer);
        this.boardRepository.save(board);   // PK가 없으면 INSERT
    }

    public void modBoard(Board board, String title, String content) {
        board.setTitle(title);
        board.setContent(content);
        board.setModifyDate(LocalDateTime.now()); //수정된 일시 추가하려면

        this.boardRepository.save(board);   // PK가 있으면 UPDATE
    }

    
    public void remBoard(Board board) {
        this.boardRepository.delete(board);
    }

    // 검색쿼리 대신 검색기능 
    public Specification<Board> searchBoard(String keyword) {
        return new Specification<Board>() {
            private static final long serialVersionUID = 1L;   // 필요한 값이라서 추가

            @Override
            @Nullable
            public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // query를 JPA로 생성
                query.distinct(true);
                Join<Board, Reply> r = b.join("replyList", JoinType.LEFT);

                return cb.or(cb.like(b.get("title"),"%" + keyword + "%"),     // 게시글 제목에서 검색
                             cb.like(b.get("content"),"%" + keyword + "%"),   // 게시글 내용에서 검색
                             cb.like(r.get("content"),"%" + keyword + "%"));  // 댓글 내용에서 검색
            }

        };
    }

}
