package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        // TX
        book.setName("asdfasdf");

        // 변경감지 == dirty checking
        // TX commit, commit 시점에 JPA가 변경한 것을 찾아서 update 쿼리를 자동으로 생성해서 데이터베이스에 반영을 한다. = dirty checking (변경 감지), flush할 때, dirty checking이 일어난다.


    }
}
