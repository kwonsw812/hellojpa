package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne(mappedBy = "locker")
    private Member member;
}
