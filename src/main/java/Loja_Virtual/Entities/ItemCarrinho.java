package Loja_Virtual.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "item_carrinho")
@Entity
@Getter
@Setter
public class ItemCarrinho {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "carrinho_id", nullable = false)
        private Carrinho carrinho;

        @ManyToOne
        @JoinColumn(name = "produto_id", nullable = false)
        private Produto produto;

        @Column(nullable = false)
        private Integer quantidade;


}
