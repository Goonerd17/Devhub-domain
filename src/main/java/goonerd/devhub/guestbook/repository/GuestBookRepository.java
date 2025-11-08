package goonerd.devhub.guestbook.repository;

import goonerd.devhub.guestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

}
