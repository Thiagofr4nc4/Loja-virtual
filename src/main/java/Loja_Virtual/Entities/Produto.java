package Loja_Virtual.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private int estoque;

    @Column
    private Double percentualDesconto;

    @Column
    private LocalDateTime descontoInicio;

    @Column
    private LocalDateTime descontoFim;

    public boolean descontoAtivo(){
        if (percentualDesconto == null || descontoInicio == null || descontoFim == null ){
            return false;
        }

        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(descontoInicio) && agora.isBefore(descontoFim);
    }

    public double getPrecoFinal() {
        if (!descontoAtivo()) {
            return preco;
        }
        return preco * (1 - (percentualDesconto / 100));
    }




    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<ItemCarrinho> itens = new ArrayList<>();
}
