package com.wallet.walletapi.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;
import lombok.Setter;

@Data
public abstract class BaseEntity implements Persistable<UUID> {
    
    @Id
    @Column("id")
    private UUID id;

    @Setter
    @Column("created_date")
    private LocalDateTime createdDate;

    @Setter
    @Column("modified_date")
    private LocalDateTime modifiedDate;

    @Transient
    private boolean isNew = true;
    
    @Override
    public boolean isNew(){
        return isNew;
    }

    public void setNew(boolean isNew){
        this.isNew = isNew;
    }
}
