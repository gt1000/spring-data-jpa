package gt1000.item;

import gt1000.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(name = "item_seq_generator", sequenceName = "item_seq", allocationSize = 1)
@Table(name = "item")
@Entity
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "item_seq_generator")
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock_number", nullable = false)
    private Integer stockNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "sell_status")
    private ItemSellStatus sellStatus;

    @Lob
    @Column(name = "item_detail")
    private String itemDetail;
}