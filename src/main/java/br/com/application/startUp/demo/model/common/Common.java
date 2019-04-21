package br.com.application.startUp.demo.model.common;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * A class who defines a common entity. Entity extended for another classes.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
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


    /**
     * A mehod utilized for set the date which the data was created. It's called before persist the data.
     *
     * @author Samuel Biazotto de Oliveira.
     **/
   @PrePersist
   private void prePersiste(){
        inserted = new Date();
   }


    /**
     * A mehod utilized for set the date which the data was updated. It's called before update the data.
     *
     * @author Samuel Biazotto de Oliveira.
     **/
   @PreUpdate
    private void preUpdate() {
        updated = new Date();
   }
}
