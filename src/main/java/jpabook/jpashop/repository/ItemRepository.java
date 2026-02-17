package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else { // 준영속 상태의 엔티티를 영속 상태로 변경할 때 사용
            // 파라미터로 넘어온 준영속 엔티티의 식별자로 1차 캐시에서 엔티티를 조회한다.
            // 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고 1차 캐시에 저장한다.
            // 조회한 영속성 엔티티에 파라미터로 넘어온 값을 채워 넣는다.
            // 영속 상태인 엔티티를 반환한다.
            // 중요: 기존에 파라미터는 영속성 컨택스트에서 관리되지 않고, 반환된 값이 영속성 컨텍스트에서 관리된다.
            // 주의: 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만, 병합을 사용하면 모든 속성이 변경된다.(모든 필드를 교체한다.), 실무에서는 위험
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
