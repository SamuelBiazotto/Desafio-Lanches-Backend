package br.com.application.startUp.demo.model.common;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Common implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp with time zone DEFAULT now()")
    private Date inserted;

    @Column(insertable = false, columnDefinition = "timestamp with time zone DEFAULT now()")
    private Date updated;

    @Column(columnDefinition="boolean default false")
    private Boolean deleted;

    public Common() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

   @PrePersist
   private void prePersiste(){
        inserted = new Date();
   }


   @PreUpdate
    private void preUpdate() {
        updated = new Date();
   }
}
